<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    	    	

<c:forEach items="${WasteRequestList}" var="wasteRequestList" varStatus="status">
    <tr>
        <td>${status.count}</td>
        <td>${wasteRequestList.wasteNo}</td>
        <td>${wasteRequestList.wasteReqDate}</td>
        <td>${wasteRequestList.wasteDate}</td>
        <td>${wasteRequestList.memberName}</td>
        <td>${wasteRequestList.name}</td>
        <td>${wasteRequestList.status}</td>
        <td>
            <button class="btn btn-outline-secondary btn-detail" 
                    data-bs-toggle="modal" 
                    data-bs-target="#warehouseDetailModal" 
                    data-waste-no="${wasteRequestList.wasteNo}">상세조회</button>
        </td>
    </tr>
</c:forEach>


	