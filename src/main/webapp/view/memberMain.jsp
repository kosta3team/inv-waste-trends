<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>

<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
   rel="stylesheet">

<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">


<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/index.css">

</head>

<body>

   <%@ include file="memberHeader.jsp"%>

   <div class="container-fluid p-0 main-container">

      <div class="row g-0">

         <div
            class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

            <h3 class="mb-3 fw-bold">입고요청</h3>

            <div class="table-container rounded">

               <table class="table table-hover table-light inventory-table">

                  <thead>
                     <tr>
                        <th>상품명</th>
                        <th>수량(box)</th>
                        <th>상태</th>
                     </tr>
                  </thead>

                  <tbody>
                  
                     <tr>
                        <td>샤인머스켓</td>
                        <td>15</td>
                        <td>승인</td>
                     </tr>
                     <tr>
                        <td>신고배</td>
                        <td>15</td>
                        <td>거절</td>
                     </tr>
                     <tr>
                        <td>아오리사과</td>
                        <td>15</td>
                        <td>대기</td>
                     </tr>
                     <tr>
                        <td>샤인머스켓</td>
                        <td>15</td>
                        <td>승인</td>
                     </tr>
                     <tr>
                        <td>신고배</td>
                        <td>15</td>
                        <td>거절</td>
                     </tr>
                     <tr>
                        <td>아오리사과</td>
                        <td>15</td>
                        <td>대기</td>
                     </tr>
                     <tr>
                        <td>샤인머스켓</td>
                        <td>15</td>
                        <td>승인</td>
                     </tr>
                     <tr>
                        <td>신고배</td>
                        <td>15</td>
                        <td>거절</td>
                     </tr>
                     <tr>
                        <td>아오리사과</td>
                        <td>15</td>
                        <td>대기</td>
                     </tr>

                  </tbody>

               </table>

            </div>

         </div>


         <div
            class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

            <h3 class="mb-3 fw-bold">재고현황</h3>

            <div class="table-container rounded">

               <table class="table table-hover table-light inventory-table">

                  <thead>
                     <tr>
                        <th>상품명</th>
                        <th>수량(box)</th>
                     </tr>
                  </thead>

                  <tbody>
                  
                     <tr>
                        <td>신고배</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>아오리사과</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>청송포도</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>신고배</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>아오리사과</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>청송포도</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>신고배</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>아오리사과</td>
                        <td>5</td>
                     </tr>
                     <tr>
                        <td>청송포도</td>
                        <td>5</td>
                     </tr>
                  </tbody>

               </table>

            </div>

         </div>

      </div>


      <div class="row g-0" style="height: 50%;">

         <div
            class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

            <h3 class="mb-3 fw-bold">판매현황</h3>

            <div class="table-container rounded">

               <table class="table table-hover table-light inventory-table">

                  <thead>
                     <tr>
                        <th>상품명</th>
                        <th>수량(box)</th>
                        <th>단가</th>
                        <th>금액</th>
                     </tr>
                  </thead>

                  <tbody>
                  <c:forEach var="Sale" items="${SaleLists}" varStatus="status">
                     <tr>
                        <td>${Sale.productName}</td>
                        <td>${Sale.quantity}</td>
                        <td><fmt:formatNumber value="${Sale.price}" pattern="#,###원"/></td>
                     <td><fmt:formatNumber value="${Sale.totalPrice}" pattern="#,###원"/></td>
                     </tr>
                  </c:forEach>
                     
                  </tbody>

               </table>

            </div>

         </div>

         <div
            class="col-6 dashboard-section
                    d-flex flex-column align-items-center">

            <h3 class="mb-3 fw-bold">폐기 요청 현황</h3>

            <div class="table-container rounded">

               <table class="table table-hover table-light inventory-table">

                  <thead>
                     <tr>
                        <th>상품명</th>
                        <th>수량(box)</th>
                        <th>폐기사유</th>
                        <th>상태</th>
                     </tr>
                  </thead>

                  <tbody>
                  
                     <tr>
                        <td>금실딸기</td>
                        <td>20</td>
                        <td>곰팡이</td>
                        <td><span class="badge bg-danger"> 폐기요청 </span></td>
                     </tr>
                     <tr>
                        <td>금실딸기</td>
                        <td>20</td>
                        <td>곰팡이</td>
                        <td><span class="badge bg-danger"> 폐기요청 </span></td>
                     </tr>
                     <tr>
                        <td>금실딸기</td>
                        <td>20</td>
                        <td>곰팡이</td>
                        <td><span class="badge bg-danger"> 폐기요청 </span></td>
                     </tr>
                     <tr>
                        <td>금실딸기</td>
                        <td>20</td>
                        <td>곰팡이</td>
                        <td><span class="badge bg-danger"> 폐기요청 </span></td>
                     </tr>
                     <tr>
                        <td>금실딸기</td>
                        <td>20</td>
                        <td>곰팡이</td>
                        <td><span class="badge bg-danger"> 폐기요청 </span></td>
                     </tr>
                     <tr>
                        <td>금실딸기</td>
                        <td>20</td>
                        <td>곰팡이</td>
                        <td><span class="badge bg-danger"> 폐기요청 </span></td>
                     </tr>
                  
                     
                  </tbody>

               </table>

            </div>

         </div>

      </div>

   </div>

   <%@ include file="footer.jsp"%>
   <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js">
      
   </script>

   <script src="https://cdn.jsdelivr.net/npm/chart.js">
      
   </script>

</body>

</html>