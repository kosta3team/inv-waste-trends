<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String loginName = (String) session.getAttribute("loginName");
    String userType = (String) session.getAttribute("userType");

    if (loginName == null) {
        response.sendRedirect(
            request.getContextPath() + "/controller?cmd=loginUI"
        );
    } else if ("member".equals(userType)) {
        response.sendRedirect(
            request.getContextPath() + "/controller?cmd=memberMain"
        );
    } else if ("admin".equals(userType)) {
        response.sendRedirect(
            request.getContextPath() + "/controller?cmd=wasteTrends"
        );
    }
%>