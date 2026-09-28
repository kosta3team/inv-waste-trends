<%@ page language="java"
    contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String type = request.getParameter("type");

    String month = request.getParameter("month");
    String year = request.getParameter("year");

    // ========================================
    // 특정 양력 월 비교
    // 선택한 월의 작년 vs 올해
    // ========================================

    if ("solar".equals(type)) {

        if (month == null) {
            month = "9";
        }
%>

{
    "type": "solar",
    "month": "<%=month%>",
    "data": [
        {
            "year": "2025",
            "amount": 280
        },
        {
            "year": "2026",
            "amount": 340
        }
    ]
}

<%
    }

    // ========================================
    // 특정 음력 월 비교
    // 선택한 음력 월의 작년 vs 올해
    // ========================================

    else if ("lunar".equals(type)) {

        if (month == null) {
            month = "9";
        }
%>

{
    "type": "lunar",
    "month": "<%=month%>",
    "data": [
        {
            "year": "2025",
            "amount": 260
        },
        {
            "year": "2026",
            "amount": 310
        }
    ]
}

<%
    }

    // ========================================
    // 연중 폐기량
    // 선택한 연도의 월별 폐기량
    // ========================================

    else if ("year".equals(type)) {

        if (year == null) {
            year = "2026";
        }

        if ("2026".equals(year)) {
%>

{
    "type": "year",
    "year": "2026",
    "data": [
        {"month":"1월","amount":160},
        {"month":"2월","amount":180},
        {"month":"3월","amount":200},
        {"month":"4월","amount":220},
        {"month":"5월","amount":250},
        {"month":"6월","amount":240},
        {"month":"7월","amount":270},
        {"month":"8월","amount":290},
        {"month":"9월","amount":310}
    ]
}

<%
        } else {
%>

{
    "type": "year",
    "year": "<%=year%>",
    "data": [
        {"month":"1월","amount":150},
        {"month":"2월","amount":170},
        {"month":"3월","amount":190},
        {"month":"4월","amount":210},
        {"month":"5월","amount":230},
        {"month":"6월","amount":220},
        {"month":"7월","amount":250},
        {"month":"8월","amount":270},
        {"month":"9월","amount":280},
        {"month":"10월","amount":300},
        {"month":"11월","amount":320},
        {"month":"12월","amount":340}
    ]
}

<%
        }
    }

    // ========================================
    // 연도별 총량 비교
    // 작년 vs 재작년
    // 현재 월까지
    // ========================================

    else if ("compare".equals(type)) {
%>

{
    "type": "compare",
    "data": [
        {
            "month":"1월",
            "lastYear":180,
            "twoYearsAgo":160
        },
        {
            "month":"2월",
            "lastYear":200,
            "twoYearsAgo":170
        },
        {
            "month":"3월",
            "lastYear":220,
            "twoYearsAgo":190
        },
        {
            "month":"4월",
            "lastYear":240,
            "twoYearsAgo":210
        },
        {
            "month":"5월",
            "lastYear":260,
            "twoYearsAgo":230
        },
        {
            "month":"6월",
            "lastYear":280,
            "twoYearsAgo":250
        },
        {
            "month":"7월",
            "lastYear":300,
            "twoYearsAgo":270
        },
        {
            "month":"8월",
            "lastYear":320,
            "twoYearsAgo":290
        },
        {
            "month":"9월",
            "lastYear":340,
            "twoYearsAgo":310
        }
    ]
}

<%
    }
%>