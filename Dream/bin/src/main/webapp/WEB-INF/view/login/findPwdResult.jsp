<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String ctxPath = request.getContextPath();
%>

<%--header 호출 --%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/css/findPwd.css" />
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath%>/js/findPwdResult.js"></script>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css"
	href="<%=ctxPath%>/css/findPwdResult.css" />

<%-- fullpage 라이브러리 --%>
<link href="<%=ctxPath%>/fullpagelib/jquery.fullPage.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=ctxPath%>/fullpagelib/jquery.fullPage.js"></script>


<body>
	<div id="container">
		<div id="div_findpwd">
			임시 비밀번호를 전송하였습니다.<br /> 전송 받은 임시 비밀번호로 로그인해주세요.
		</div>

		<!-- 이메일 아이디찾기 버튼 부분  -->
		<div class="div_login">
			<button id="btn_login" type="button" onclick="location.href='<%=ctxPath%>/login/login.dream'" style="margin : 20px auto">로그인</button>
		</div>
		<!-- 이메일 아이디찾기 버튼 부분  -->
	</div>
</body>

<%--footer 호출 --%>
  <jsp:include page="/WEB-INF/view/footer.jsp" />