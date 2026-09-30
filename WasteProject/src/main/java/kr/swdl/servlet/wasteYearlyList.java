package kr.swdl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class WasteYearlyList implements Action{
	
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException{
		// 파라미터 수집
		String year = request.getParameter("year");
		if (year== null) year = "2026";
		
		// 더미데이터 생성
		List<Map<String, String>> yearlyList = new ArrayList<>();
		List<String> labels = new ArrayList();
		List<String> values = new ArrayList();
		
		for (int i = 1; i <= 12; i++) {
			Map<String, String> row = new HashMap<>();
			row.put("month", i+ "월");
			
			// 그럴싸한 더미 수치 생성
			int randomAmount = (int)(Math.random()*40)+10;
			row.put("status", String.valueOf(randomAmount));
			row.put("status", randomAmount > 40 ? "주의": "정상");
			yearlyList.add(row);
			
			labels.add("'" + i + "월'");
			values.add(String.valueOf(randomAmount));
		}
		
		// jsp로 데이터 포워딩
		request.setAttribute("yearlyList", yearlyList);
		request.setAttribute("selectedYear", year);
		
		// 차트 데이터 jsp로 전송
		request.setAttribute("chartLabels", "["+ String.join(", ", labels) + "]");
		request.setAttribute("chartValues", "["+ String.join(", ", values) + "]");
		
		return "view/wasteYearlyList.jsp";
		
	}
}
