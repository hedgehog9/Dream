<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String ctxPath = request.getContextPath();
%>
<%-- Bootstrap CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  <%-- Font Awesome 5 Icons --%>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
  <%-- title Icon --%>
  <link href="<%= ctxPath%>/images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/header.css" />
  
  <%-- Optional JavaScript --%>
  <script type="text/javascript" src="<%= ctxPath%>/jquery3.6.0/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>
  <!-- sweet alert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
   <!-- toastr css라이브러리 -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
  <!-- toastr cdn 라이브러리 둘다 제이쿼리 밑에 있어야함 -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>  

  <%-- JqueryUI JS,CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
  <script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js" ></script> 
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/header.js" ></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4> 주문자 상세정보</h4>
	<%-- 
	<div id="orderid"> 주문자 아이디 : ${requestScope.userid} </div>
	<div id="ordername"> 받는사람 : ${requestScope.order_name} </div>
	<div id="ordermoblie"> 연락처 : ${requestScope.mobile} </div>
	<div id="orderpostcode"> 우편번호: ${requestScope.post_code} </div>
	<div id="orderaddress"> 주소 : ${requestScope.address}</div>
	<div id="orderdetailaddress"> 상세주소 : ${requestScope.detail_address}</div>
	--%>
	
	<table border="1">
		<tr>
			<td class="bg-dark text-white" style="width:100px;">아이디 :</td>
			<td> ${requestScope.userid}</td>
		</tr>
		
		<tr>
			<td class="bg-dark text-white">받는사람 :</td>
			<td> ${requestScope.order_name}</td>
		</tr>
		
		<tr>
			<td class="bg-dark text-white">연락처 :</td>
			<td> ${requestScope.mobile}</td>
		</tr>
		
		<tr>
			<td class="bg-dark text-white">우편번호:</td>
			<td> ${requestScope.post_code}</td>
		</tr>
		
		<tr>
			<td class="bg-dark text-white">주소 :</td>
			<td> ${requestScope.address}</td>
		</tr>
		
		<tr>
			<td class="bg-dark text-white">상세주소 :</td>
			<td> ${requestScope.detail_address}</td>
		</tr>
	</table>
</body>
</html>