<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    	    	

<c:forEach items="${list}" var="wasteRequest" varStatus="status">
    <tr>
        <td>${status.count}</td>
        <td>${wasteRequest.wasteNo}</td>
        <td>${wasteRequest.wasteReqDate}</td>
        <td>${wasteRequest.wasteDate}</td>
        <td>${wasteRequest.memberName}</td>
        <td>${wasteRequest.name}</td>
        <td>${wasteRequest.status}</td>
        <td>
            <button class="btn btn-outline-secondary btn-detail" 
                    data-bs-toggle="modal" 
                    data-bs-target="#warehouseDetailModal" 
                    data-waste-no="${wasteRequest.wasteNo}">상세조회</button>
        </td>
    </tr>
</c:forEach>


	