package test.kr.swdl.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class KamisPriceTest{

    // -----------------------------------
    // KAMIS 인증 정보 (본인 값으로 교체)
    // -----------------------------------
	private static final String CERT_KEY = "ee6ae40d-0973-4fe6-942e-ade6ae51f1f0";
	private static final String CERT_ID = "9887";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        // -----------------------------------
        // 사용자 입력 받기
        // -----------------------------------

        System.out.print("구분 입력 (1: 소매, 2: 도매) : ");
        String clsInput = sc.nextLine().trim();

        // 01: 소매, 02: 도매
        String productClsCode = "2".equals(clsInput) ? "02" : "01";


        System.out.print("부류코드 입력 (예: 채소류=200, 과일류=400, 곡물=100) : ");
        String itemCategoryCode = sc.nextLine().trim();

        if (itemCategoryCode.isEmpty()) {
            itemCategoryCode = "200"; // 기본값: 채소류
        }


        System.out.print("찾고 싶은 품목명 (전체 조회하려면 엔터) : ");
        String searchItemName = sc.nextLine().trim();


        System.out.print("기준 날짜 입력 (YYYY-MM-DD, 엔터시 오늘) : ");
        String dateInput = sc.nextLine().trim();

        LocalDate baseDate = dateInput.isEmpty()
                ? LocalDate.now()
                : LocalDate.parse(dateInput);


        sc.close();


        // -----------------------------------
        // 조회 실행 (오늘 데이터가 아직 없으면 하루씩 뒤로 가며 재시도)
        // -----------------------------------

        boolean success = false;

        for (int i = 0; i < 7; i++) {

            String regDay = baseDate.minusDays(i).toString();

            success = search(productClsCode, itemCategoryCode, searchItemName, regDay);

            if (success) {
                break;
            }

            System.out.println(regDay + " 유효 데이터 없음 → 이전 날짜 재시도");
        }

        if (!success) {
            System.out.println("최근 7일 내 데이터를 찾지 못했습니다.");
        }
    }


    /*
     * dailyPriceByCategoryList는 하루치를 조회해도
     * 내부적으로 당일/1일전/1주일전/2주일전/1개월전/1년전/일평년 값을
     * 한 번에 비교해서 돌려준다.
     * 단, 당일 데이터가 아직 집계 전이면 item 자체가 빈 placeholder로만
     * 올 때가 있어서, 그 경우 이 함수는 false를 반환하고
     * main()에서 하루 전 날짜로 재시도한다.
     *
     * 반환값: 유효한 품목을 하나라도 찾았으면 true
     */
    private static boolean search(
            String productClsCode,
            String itemCategoryCode,
            String searchItemName,
            String regDay) {

        try {

            System.out.println("조회 날짜 : " + regDay);


            // -----------------------------------
            // API URL 생성
            // -----------------------------------

            String apiUrl = "https://www.kamis.or.kr/service/price/xml.do";

            StringBuilder urlBuilder = new StringBuilder(apiUrl);

            urlBuilder.append("?action=dailyPriceByCategoryList");

            urlBuilder.append("&p_cert_key=")
                      .append(URLEncoder.encode(CERT_KEY, StandardCharsets.UTF_8));

            urlBuilder.append("&p_cert_id=")
                      .append(URLEncoder.encode(CERT_ID, StandardCharsets.UTF_8));

            urlBuilder.append("&p_returntype=xml");

            // 소매/도매 구분 (필수)
            urlBuilder.append("&p_product_cls_code=").append(productClsCode);

            // 부류코드 (필수)
            urlBuilder.append("&p_item_category_code=").append(itemCategoryCode);

            // 지역코드 (전국 비교 기준, 공식 샘플 값)
            urlBuilder.append("&p_country_code=1101");

            // kg 환산 여부
            urlBuilder.append("&p_convert_kg_yn=Y");

            // 조회 날짜
            urlBuilder.append("&p_regday=").append(regDay);


            // -----------------------------------
            // API 연결
            // -----------------------------------

            URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(10000);
            conn.setInstanceFollowRedirects(true);


            // -----------------------------------
            // 응답 확인
            // -----------------------------------

            int responseCode = conn.getResponseCode();

            System.out.println("응답 코드 : " + responseCode);

            if (responseCode != 200) {
                conn.disconnect();
                System.out.println("HTTP 오류로 조회 실패");
                return false;
            }


            // -----------------------------------
            // 원본 XML 읽기
            // -----------------------------------

            String rawXml = readAll(conn);

            conn.disconnect();


            // -----------------------------------
            // XML 파싱
            // -----------------------------------

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(
                    new ByteArrayInputStream(rawXml.getBytes(StandardCharsets.UTF_8))
            );


            // -----------------------------------
            // condition (요청 처리 결과 코드) 확인
            // condition 값이 "000"이면 정상, 그 외는 에러/데이터 없음
            // -----------------------------------

            String condition = getFirstTagValue(document, "condition");

            System.out.println("condition : " + condition);


            // -----------------------------------
            // item 목록 순회
            // -----------------------------------

            NodeList items = document.getElementsByTagName("item");

            System.out.println("item 개수 : " + items.getLength());

            if (items.getLength() == 0) {
                System.out.println("응답에 item이 없습니다.");
                return false;
            }

            boolean found = false;

            for (int j = 0; j < items.getLength(); j++) {

                Element item = (Element) items.item(j);

                // item의 모든 자식 태그를 이름->값 으로 통째로 읽는다.
                // (실제 태그명이 item_name / itemname 등으로 API마다 달라서
                //  하드코딩된 태그명이 틀려도 여기서 전부 확인 가능하다)
                Map<String, String> fields = readAllFields(item);

                String itemName = firstNonEmpty(fields, "item_name", "itemname");
                String kindName = firstNonEmpty(fields, "kind_name", "kindname");
                String unit     = firstNonEmpty(fields, "unit");
                String rank     = firstNonEmpty(fields, "rank");

                if (itemName.isEmpty()) {
                    continue;
                }

                // 특정 품목명으로 필터링 (입력했을 경우만, 품종명까지 같이 검색)
                if (!searchItemName.isEmpty()
                        && !(itemName + kindName).contains(searchItemName)) {
                    continue;
                }

                // day1/dpr1, day2/dpr2 ... day7/dpr7 순서로
                // 값이 채워진 가장 최근 가격을 찾는다.
                // (실제 태그명 확인 결과: 라벨은 dayN, 가격은 dprN 이다)
                String latestLabel = "";
                String latestPrice = "";

                for (int p = 1; p <= 7; p++) {

                    String dayLabel = fields.get("day" + p);
                    String dayPrice = fields.get("dpr" + p);

                    if (dayLabel == null || dayPrice == null) {
                        continue;
                    }

                    if (!dayPrice.isEmpty() && !"-".equals(dayPrice)) {
                        latestLabel = dayLabel;
                        latestPrice = dayPrice;
                        break;
                    }
                }

                found = true;

                System.out.println("======================");
                System.out.println("품목명 : " + itemName);
                System.out.println("품종명 : " + kindName);

                if (!rank.isEmpty()) {
                    System.out.println("등급   : " + rank);
                }

                if (!unit.isEmpty()) {
                    System.out.println("단위   : " + unit);
                }

                if (!latestPrice.isEmpty()) {
                    System.out.println("최근시세 (" + latestLabel + ") : " + latestPrice);
                } else {
                    System.out.println("최근시세 : 데이터 없음 (전 기간 값이 비어있음)");
                }
            }

            System.out.println("======================");

            if (!found) {
                System.out.println("조건에 맞는 품목을 찾지 못했습니다.");
            }

            return found;


        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }
    }


    // -----------------------------------
    // item 엘리먼트의 자식 태그를 전부 "태그명 -> 값" 형태로 읽기
    // -----------------------------------

    private static Map<String, String> readAllFields(Element item) {

        Map<String, String> fields = new LinkedHashMap<>();

        NodeList children = item.getChildNodes();

        for (int k = 0; k < children.getLength(); k++) {

            Node child = children.item(k);

            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            String tagName = child.getNodeName();

            String value = child.getTextContent() == null
                    ? ""
                    : child.getTextContent().trim();

            fields.put(tagName, value);
        }

        return fields;
    }


    // -----------------------------------
    // 여러 후보 태그명 중 값이 있는 첫 번째를 반환
    // -----------------------------------

    private static String firstNonEmpty(Map<String, String> fields, String... keys) {

        for (String key : keys) {

            String value = fields.get(key);

            if (value != null && !value.isEmpty()) {
                return value;
            }
        }

        return "";
    }


    // -----------------------------------
    // 응답 스트림을 문자열로 통째로 읽기
    // -----------------------------------

    private static String readAll(HttpURLConnection conn) throws Exception {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        return sb.toString();
    }


    // -----------------------------------
    // 문서 최상위에서 태그 값 꺼내기 (condition 용)
    // -----------------------------------

    private static String getFirstTagValue(Document document, String tagName) {

        NodeList nodes = document.getElementsByTagName(tagName);

        if (nodes.getLength() == 0) {
            return "";
        }

        Node node = nodes.item(0);

        return node.getTextContent() == null ? "" : node.getTextContent().trim();
    }
}