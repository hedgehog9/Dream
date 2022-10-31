<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String ctxPath = request.getContextPath();
%>

<%--header 호출 --%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/findEmail.css" />
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/findEmail.js" ></script>

<div class="notfound_notice" id="notfound_notice">
      <p><i class="fa-solid fa-lg fa-triangle-exclamation" style="color: red;"></i>일치하는 사용자 정보를 찾을 수 없습니다.</p>
   </div>
   <div id="container">
      

      <%-- 아이디찾기 제목 --%>
      <div class="find_title">
         <h2 id="find_email_title">이메일 아이디 찾기</h2>
      </div>
      <%-- 아이디찾기 제목 --%>
      

      <%-- 안내 문구 --%>
      <div class="find_notice">
         <p id="p_notice">
            가입 시 등록한 휴대폰 번호를 입력하면<br>
            이메일 주소의 일부를 알려드립니다. 
         </p>
      </div>
      <%-- 안내 문구 --%>
      

      <%-- 아이디 찾기 입력창 --%>
      <div class="find_inputbox">
         <div id="div_mobile">휴대폰 번호</div>
         <input id="mobile" name="mobile" type="text" placeholder="가입하신 휴대폰 번호" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
      </div>
      <%-- 아이디 찾기 입력창 --%>


      <%-- 버튼 클릭시 select 해서 이메일 값 알아오기  --%>


      <%-- 이메일 아이디찾기 버튼 부분  --%>
      <div class="find_buttonbox">
         <%-- href = ""findEmailEnd로 이동 href="<%=ctxPath %>/login/findEmailEnd.dream"--%>
         <a  id="btn_find" class="btn_find">이메일 아이디 찾기</a>
      </div>
      <%-- 이메일 아이디찾기 버튼 부분  --%>

   </div>
   
   
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />   
   



