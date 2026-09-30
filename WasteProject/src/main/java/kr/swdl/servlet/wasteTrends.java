package kr.swdl.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class WasteTrends implements Action {
	

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String startYear = request.getParameter("startYear");
        String endYear = request.getParameter("endYear");
        String month = request.getParameter("month");
        String region = request.getParameter("region");
        
        // 1. 초기값 셋팅
        if (startYear == null) startYear = "2024";
        if (endYear == null) endYear = "2025";
        if (month == null) month = "ALL";

        if (!"ALL".equals(month)) {
        	month = month.replaceAll("[^0-9]", "");
        }
        // 2. 더미 데이터 수치 (추후 DB 연동 시 아래에서 생성된 기간을 쿼리 조건으로 사용)
        String lastYearTotal = "50", thisYearTotal = "46";
        String lastMonthTotal = "5", thisMonthTotal = "4.8";

        // 3. 날짜 동적 계산 로직 (질문자님 기획 완벽 반영)
        String startShort = startYear.substring(2); 
        String endShort = endYear.substring(2);     
        
        String lastYearPeriod = "", thisYearPeriod = "", lastMonthPeriod = "", thisMonthPeriod = "";

        // 하단에 만든 헬퍼 메서드로 '해당 월의 말일' 또는 '현재 날짜'를 똑똑하게 가져옵니다.
        String startEndDate = getEndDateString(Integer.parseInt(startYear), month);
        String endEndDate = getEndDateString(Integer.parseInt(endYear), month);

        if ("ALL".equals(month)) {
            lastMonthPeriod = "해당 없음";
            thisMonthPeriod = "해당 없음";
        } else {
            int m = Integer.parseInt(month);
            String formattedMonth = String.format("%02d", m);
            
            // 특정월 기간 (선택한 월의 1일 ~ 말일 또는 오늘)
            lastMonthPeriod = String.format("%s.%s.01 ~ %s", startShort, formattedMonth, startEndDate);
            thisMonthPeriod = String.format("%s.%s.01 ~ %s", endShort, formattedMonth, endEndDate);
        }

        // 총 폐기량 기간 (질문자님 기획대로 무조건 1월 1일부터 누적 합산 기간)
        lastYearPeriod = String.format("%s.01.01 ~ %s", startShort, startEndDate);
        thisYearPeriod = String.format("%s.01.01 ~ %s", endShort, endEndDate);

        // 차트데이터
        String chartDataTotal = "[" + lastYearTotal + ", " + thisYearTotal + "]";
        String chartDataMonth = "[" + lastMonthTotal + ", "+ thisMonthTotal + "]";
        request.setAttribute("chartDataTotal", chartDataTotal);
        request.setAttribute("chartDataMonth", chartDataMonth);
        
        // 4. JSP로 데이터 전송 (짐 싸기)
        request.setAttribute("lastYearTotal", lastYearTotal);
        request.setAttribute("thisYearTotal", thisYearTotal);
        request.setAttribute("lastMonthTotal", lastMonthTotal);
        request.setAttribute("thisMonthTotal", thisMonthTotal);

        request.setAttribute("lastYearPeriod", lastYearPeriod);
        request.setAttribute("thisYearPeriod", thisYearPeriod);
        request.setAttribute("lastMonthPeriod", lastMonthPeriod);
        request.setAttribute("thisMonthPeriod", thisMonthPeriod);

        request.setAttribute("startYear", startYear);
        request.setAttribute("endYear", endYear);
        request.setAttribute("selectedMonth", month);
        request.setAttribute("selectedRegion", region);

        return "view/wasteTrends.jsp";
    }
    
    // --- 날짜 계산 헬퍼 메서드 (핵심 로직) ---
    private String getEndDateString(int targetYear, String monthStr) {
        // 서버의 현재 시스템 날짜를 가져옵니다. (JSP의 우측 상단 날짜와 동일하게 맞춰집니다)
        LocalDate today = LocalDate.now(); 
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        int targetMonth;
        int targetDay;

        if ("ALL".equals(monthStr)) {
            if (targetYear >= currentYear) {
                // 올해나 미래를 전체(ALL)로 조회하면, 미래 데이터가 없으므로 오늘 날짜로 끊어줍니다.
                targetMonth = currentMonth;
                targetDay = currentDay;
            } else {
                targetMonth = 12;
                targetDay = 31;
            }
        } else {
            targetMonth = Integer.parseInt(monthStr);
            if (targetYear >= currentYear && targetMonth >= currentMonth) {
                // 선택한 년/월이 이번 달이거나 미래일 경우, 현재 날짜(오늘)까지만 끊어줍니다.
                targetMonth = currentMonth;
                targetDay = currentDay;
            } else {
                // 과거의 달일 경우, 자바 내장 함수로 그 달의 진짜 말일(28, 29, 30, 31)을 알아서 계산합니다.
                targetDay = YearMonth.of(targetYear, targetMonth).lengthOfMonth();
            }
        }

        String shortYear = String.valueOf(targetYear).substring(2);
        return String.format("%s.%02d.%02d", shortYear, targetMonth, targetDay);
    }
}