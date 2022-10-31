<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>
  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  
  <%--직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/join.css" />
  
   <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/join.js"></script>
  
    <div id="Main" class="">
      <div class="login_form">
        <div class="title d-flex justify-content-center align-items-center pt-5">
          <h4 class="title">회원가입</h4>
        </div>
        <div class="Main d-flex flex-column w-100">
          <form name="joinFrm">
            <fieldset>
              <%-- userid --%>
              <div id="userid" class="login_input d-flex flex-column">
                <label for="userid">이메일 주소<span id="login_label_ettential">필수입력</span></label>
                <input type="text" id="userid" name="userid" placeholder="예) example@email.co.kr">
                <p id="userid_error" class="login_error"></p>
              </div>

              <%-- passwd --%>
              <div id="passwd" class="login_input d-flex flex-column">
                <label for="passwd">비밀번호<span id="login_label_ettential">필수입력</span></label>
                <input type="password" name="passwd" placeholder="영문, 숫자, 특수문자를 조합 (8-16자)" id="passwd">
                <p id="passwd_error" class="login_error">영문, 숫자, 특수문자를 조합하여 입력해주세요. (8-16자)</p>
                
                <label for="passwd_check">비밀번호 확인<span id="login_label_ettential">필수입력</span></label>
                <input type="password" name="passwd_check" placeholder="비밀번호 확인">
                <p id="passwd_check_error" class="login_error">비밀번호가 일치하지 않습니다.</p>
              </div>
              
              
              
              <%-- mobile --%>
              <div id="mobile" class="login_input d-flex flex-column">
                <label for="mobile">핸드폰번호<span id="login_label_ettential">필수입력</span></label>
                <input type="text" name="mobile" placeholder="-를 제외한 핸드폰번호 11자리" id="mobile" maxlength="11">
                <p id="mobile_error" class="login_error">-를 제외한 숫자 11자리를 입력해주세요</p>
                <p id="mobile_ok" class="login_error">핸드폰인증은 필수사항입니다.</p>
                <button type="button" id="btn_mobile_check" class="btn btn-white border rounded mt-1">인증하기</button>
              </div>
              
              <%-- mobileConfirm --%>
              <div id="mobileConfirm" class="login_input">
                <label for="mobileConfirm">인증번호</label>
                <input type="text" name="mobileConfirm" placeholder="인증번호 8자리를 입력해주세요" id="mobileConfirm" maxlength="8">
                <div id="mobile_complete" class="d-flex">
                  <button type="button" id="btn_mobile_check_complete" class="btn btn-white border rounded mt-2">인증완료</button>
                  <div id="div_timer" class="mt-3 ml-4" style="color:red; font-size:14px; font-weight:bold;"></div>
                </div>
              </div>

              
              <%-- agreement --%>
              <div class="login_agreement d-flex flex-column mt-5">
                <div class="login_checkbox d-flex justify-content-between">
                  <div>
                    <input type="checkbox" id="agree_age" name="agree_age"/></input>
                    <label for="agree_age">[필수] 만 14세 이상이며 모두 동의합니다.</label>
                  </div>
                  <a type="button" onclick="">자세히</a>
                </div>

                <div class="login_checkbox d-flex justify-content-between">
                  <div>
                    <input type="checkbox" id="agree_message" name="agree_message"/>
                    <label for="agree_message">[선택] 광고성 정보 수신에 모두 동의합니다.</label>
                  </div>
                  <a type="button" onclick="">자세히</a>
                </div>
              </div>
            </fieldset>
            <button id="btn_join" type="button" class="mt-4" data-toggle="modal" data-target="#emailConfirm" data-dismiss="modal">가입하기</button>
          </form>
        </div>
      </div>
    </div>
    
    
    
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />





<%-- 이메일인증 modal --%>
<div class="modal fade" id="emailConfirm">
   <div class="modal-dialog">
     <div class="modal-content">
     
       <!-- Modal header -->
       <div class="modal-header">
         <h2 class="modal-title">이메일인증코드 발송</h2>
         <button type="button" class="close emailConfirmClose" data-dismiss="modal">&times;</button>
       </div>
       
       <!-- Modal body -->
       <div class="modal-body">
       		<span id="send_email_guide">가입을 하시려면 본인이메일인증을 해주세요</span>
       		
			<div class="d-flex flex-column mt-2">
				<button id="btn_send_email" class="btn btn-white border mr-1">인증번호 전송</button>
				<span id="send_guide" class="mt-2"></span>
				<div class="d-flex align-items-center">
				  <input class="mt-2" type="text" id="emailConfirmCode" name="emailConfirmCode" placeholder="인증번호를 입력해주세요">
				  <div id="div_email_timer" class="mt-3 ml-4" style="color:red; font-size:14px; font-weight:bold;"></div>
				</div>
			</div>
       </div>
       
       <!-- Modal footer -->
       <div class="modal-footer">
         <button type="button" class="btn btn-white border" id="emailConfirmComplete">인증 완료</button>
         <button type="button" class="btn btn-white border emailConfirmClose" data-dismiss="modal">Close</button>
       </div>
     </div>
     
   </div>
 </div>