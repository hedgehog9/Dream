<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

<%--header 호출 --%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%=ctxPath %>/css/findEmailEnd.css" />
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%=ctxPath %>/js/findEmailEnd.js" ></script>

<script>
$(document).ready(function(){
	const email =  sessionStorage.getItem('userid'); 
	$("div#email").html(email);
	sessionStorage.removeItem('userid');
});

</script>


<div id="container">

      <%-- 아이디찾기 제목 --%>
      <div class="find_title">
         <h2 id="find_email_title">이메일 주소 찾기에 성공하였습니다.</h2>
      </div>
      <%-- 아이디찾기 제목 --%>
      

      <%-- 안내 문구 --%>
      <div class="find_notice">
         <p id="p_notice">
            이메일 주소
         </p>
         <div id="email"></div>
      </div>
      <%-- 안내 문구 --%>
      

      <%-- 이메일 아이디찾기 버튼 부분  --%>
      <div class="find_buttonbox">
         <a id="findPwd" href="<%= ctxPath%>/login/findPwd.dream" class="btn_find">비밀번호 찾기</a>
         <a id="login"  href="<%= ctxPath%>/login/login.dream" class="btn_find">로그인</a>
      </div>
      <%-- 이메일 아이디찾기 버튼 부분  --%>

   </div>
   
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />   
