package test.kr.swdl.servlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KamisApiTest {

	private static final String CERT_KEY = "ee6ae40d-0973-4fe6-942e-ade6ae51f1f0";
	private static final String CERT_ID = "9887";

	public static void main(String[] args) {
		try {

			String apiUrl = "https://www.kamis.or.kr/service/price/xml.do";

			StringBuilder urlBuilder = new StringBuilder(apiUrl);

			urlBuilder.append("?action=productInfo");

			urlBuilder.append("&p_cert_key=").append(URLEncoder.encode(CERT_KEY, StandardCharsets.UTF_8));

			urlBuilder.append("&p_cert_id=").append(URLEncoder.encode(CERT_ID, StandardCharsets.UTF_8));

			urlBuilder.append("&p_returntype=xml");

			URL url = new URL(urlBuilder.toString());

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setConnectTimeout(5000);

            conn.setReadTimeout(10000);

            conn.setInstanceFollowRedirects(true);

            int responseCode = conn.getResponseCode();

            System.out.println("응답 코드 : " + responseCode);


            if (responseCode != HttpURLConnection.HTTP_OK) {

                System.out.println("API 호출 실패");

                conn.disconnect();

                return;
            }

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document document =
                    builder.parse(conn.getInputStream());

            NodeList items =
                    document.getElementsByTagName("item");

            System.out.println("전체 상품 개수 : "
                    + items.getLength());

            System.out.println("========================");

            for (int i = 0; i < items.getLength(); i++) {

                Element item =
                        (Element) items.item(i);

                Node itemCodeNode =
                        item.getElementsByTagName("itemcode").item(0);

                Node itemNameNode =
                        item.getElementsByTagName("itemname").item(0);

                Node kindNameNode =
                        item.getElementsByTagName("kindname").item(0);

                if (itemCodeNode == null
                        || itemNameNode == null
                        || kindNameNode == null) {

                    continue;
                }

                String itemCode =
                        itemCodeNode.getTextContent().trim();

                String itemName =
                        itemNameNode.getTextContent().trim();

                String kindName =
                        kindNameNode.getTextContent().trim();

                int code;

                try {

                    code = Integer.parseInt(itemCode);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "품목코드가 숫자가 아님 : "
                            + itemCode);

                    continue;
                }
                if (code >= 400 && code<500) {

                    System.out.println("품목코드 : "
                            + itemCode);

                    System.out.println("품목명 : "
                            + itemName);

                    System.out.println("품종명 : "
                            + kindName);

                    System.out.println("--------------------");
                }
            }
            conn.disconnect();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}