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
<script type="text/javascript" src="<%=ctxPath%>/js/findPwd.js"></script>

<script>
$(document).ready(function(){
	
	$("a#btn_findPwd").click(function(){
		// alert("버튼 클릭됨.");
		$.ajax({
			 url:"<%= ctxPath%>/login/findPwd.dream",
			 data:{"mobile":$("input#mobile").val(),"email":$("input#email").val()}, // data 는 /MyMVC/member/idDuplicateCheck.up 로 전송해야할 데이터를 말한다.
			 type:"post",     // type 을 생략하면 type:"get" 이다.
			 dataType:"json",
			 async:true,      // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
			                  // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다.  
			 success:function(json){ 
				 // dataType:"json" 을 생략하면 
				 // text 는 "{"isExists":true}" 또는 "{"isExists":false}" 되어지는 string 타입이다. 
				 
				 // dataType:"json" 을 생략하지 않고 넣어주면 
				 // text 는 {"isExists":true} 또는 {"isExists":false} 되어지는 object 타입이다. 
				 
				 // const json = JSON.parse(text);
				 // JSON.parse(text); 은 JSON.parse("{"isExists":true}"); 또는 JSON.parse("{"isExists":false}"); 와 같은 것인데
				 // 그 결과물은 {"isExists":true} 또는 {"isExists":false} 와 같은 문자열을 자바스크립트 객체로 변환해주는 것이다. 
				 // 조심할 것은 text 는 반드시 JSON 형식으로 되어진 문자열이어야 한다. 
				 
				 if(!json.isExistUser) {
					 alert("일치하는 회원정보가 없습니다.");
					 return;
					 // 입력한 userid 가 이미 사용중이라면 
				 }
				 else{
					 if(json.success_count == 1){
						 alert("문자메시지 발송 완료");
						 location.href="<%=ctxPath%>/login/findPwdResult.dream";
						 return;
					 }
					 else if(json.error_count != 0){
						alert("문자메시지 발송 실패");
						/* location.href=history.back(); */
						return;
					 }
				 }
				 
				
			 },
			 
			 error: function(request, status, error){
				 alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
			 
		});// end of ajax--------------------------------------------------
		
		
		
	});// end of $("a#btn_findPwd").click(function(e){}--------------
			
}); // end of $(document).ready(function(){}----------------------


</script>


<div id="container">
	<form name="pwdfindFrm">
		<%-- 비밀번호 찾기 제목 --%>
		<div class="find_title">
			<h2 id="find_pwd_title">비밀번호 찾기</h2>
		</div>
		<%-- 비밀번호 찾기 제목 --%>


		<%-- 안내 문구 --%>
		<div class="find_notice">
			<p id="p_notice">
				가입 시 등록하신 휴대폰 번호와 이메일을 입력하시면,<br> 휴대폰으로 임시 비밀번호를 전송해 드립니다.
			</p>
		</div>
		<%-- 안내 문구 --%>


		<%-- 아이디 찾기 입력창 --%>
		<div class="find_inputbox">
			<div class="input_title" id="div_mobile">휴대폰 번호</div>
			<input class="input_info" id="mobile" name="mobile" type="text"
				placeholder="가입하신 휴대폰 번호"
				oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
			<div class="warning" id="mobile_warning">휴대폰 번호를 정확히 입력해주세요.</div>


			<div class="input_title" id="div_email">이메일 주소</div>
			<input class="input_info" id="email" name="email" type="text"
				placeholder="예) dream@dream.co.kr" />
			<div class="warning" id="email_warning">이메일 주소를 정확히 입력해주세요.</div>

		</div>
		<%-- 아이디 찾기 입력창 --%>


		<%-- 이메일 아이디찾기 버튼 부분  --%>
		<div class="find_buttonbox">
			<a id="btn_findPwd" class="btn_find">문자 발송하기</a>
		</div>
		<%-- 이메일 아이디찾기 버튼 부분  --%>
	</form>
</div>


<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />
