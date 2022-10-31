<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

<%--header 호출 --%>
<jsp:include page="/WEB-INF/view/header.jsp" />
<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/membership.css" />
<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/membership.js" ></script>

<div id="Main" class="container d-flex">
	<%-------------------- 사이드바 시작 ----------------------%>
  		
     <%-- sidebar 호출 나중에 margin left : 50px --%>
		<jsp:include page="/WEB-INF/view/sidebar.jsp" />
     
    <%-------------------- 사이드바 끝 ----------------------%>


   <div id="content">
      <div id="membership_header" >
      	<input type="hidden" id="userid" name="userid" value="${sessionScope.userid}"  />
         <h2 id="membership_title">DREAM CATCHER</h2>
         <p id="membership_date"><span id="membership_year"></span>년 <span id="membership_month">mm</span>월 <span id="membership_date">d</span>일까지 멤버십 혜택이 유지되고, 이후 자동 해지됩니다.</p>
         <button id="regist" type="button">드림캐쳐 신청하기</button>
         <p style="font-size: 7px; margin-top: 8px;"><a href="" data-toggle="modal" data-target="#modal_membership_policy">드림캐처 맴버십 이용 약관</a>에 동의합니다.(월 3,900원 결제)</p>
      </div>
         <div id = "membership_info" class="row">
            
               <a href="#" id ="benefit_1" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_1">
                        <i class="fas fa-light fa-truck icon_membership" style="margin-right: 5px;"></i>
                        <div class="benefit_info" style="text-align: left;">무료배송</div>
                  <i class="fa-solid fa-angle-right icon_membership" ></i>
               </a>

               <a href="#" id ="benefit_2" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_2">
                  <%-- <i class="fas fa-light fa-truck icon_membership" "></i>   --%> 
                  <i class="fa-solid fa-p position_bottom" style="margin-right: 5px;"></i> 
                  <div class="benefit_info" style="text-align: left;">포인트 추가적립</div>
                  <i class="fa-solid fa-angle-right icon_membership" ></i>
               </a>

            <div class="w-100"></div>

            <a href="#" id ="benefit_3" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_3">
                     <i class="fas fa-light fa-truck icon_membership" style="margin-right: 5px;"></i>
                     
                     <div class="benefit_info" style="text-align: left;">무료 디퓨저</div>
               <i class="fa-solid fa-angle-right icon_membership" ></i>
            </a>

            <a href="#" id ="benefit_4" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_4">
                     <i class="fas fa-light fa-truck icon_membership" style="margin-right: 5px;"></i>
                     <div class="benefit_info" style="text-align: left;">무료 반품</div>
               <i class="fa-solid fa-angle-right icon_membership" ></i>
            </a>

            <div class="w-100"></div>

            <a href="#" id ="benefit_5" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_5">
                     <i class="fa-solid fa-box position_bottom"></i>
                     <div class="benefit_info" style="text-align: left;">쿠폰 증정</div>
               <i class="fa-solid fa-angle-right icon_membership" ></i>
            </a>

            <a href="#" id ="benefit_6" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_6">
                     <i class="fas fa-light fa-truck icon_membership" style="margin-right: 5px;"></i>
                     <div class="benefit_info" style="text-align: left;">멤버십 전용특가</div>
               <i class="fa-solid fa-angle-right icon_membership" ></i>
               
            </a>
            
            <div class="w-100"></div>

            <a href="#" id ="benefit_7" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_7">
                     <i class="fas fa-light fa-truck icon_membership" style="margin-right: 5px;"></i>
                     <div class="benefit_info" style="text-align: left;">낮시간주문 새벽도착</div>
               <i class="fa-solid fa-angle-right icon_membership" ></i>
            </a>

            <a href="#" id ="benefit_8" class="info col d-flex justify-content-between" data-toggle="modal" data-target="#modal_benefit_8">
                     <i class="fas fa-light fa-truck icon_membership" style="margin-right: 5px;"></i>
                     <div class="benefit_info" style="text-align: left;">무료 샘플</div>
               <i class="fa-solid fa-angle-right icon_membership" ></i>
            </a>

         </div>
   </div>
   
   
</div>


    <%-- 이용약관 모달 시작  --%>
   <div class="modal fade" id="modal_membership_policy">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
               <h5 class="modal-title">드림캐처 멤버십 이용약관</h5>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">

               <br>
                  <h5 style="text-align: center;">드림캐처 멤버십 이용약관</h5>

               <br>
               <h5>제１조 (목적)</h5>
               <p>
                  본 약관은 드림 주식회사(이하 “회사”)가 운영하는 웹사이트, 모바일 웹 및 앱 등에서 제공하는 드림캐쳐 서비스를 이용하고자 하는 자와 회사 간의 권리·의무, 서비스 이용절차 및 기타 제반사항을 정함에 그 목적이 있다.
               </p>
               <h5>제２조 (용어의 정의)</h5>
               <p>
                  ① 드림캐쳐 서비스(이하 “본 서비스”라고 한다)란, 대상회원이 드림캐쳐 멤버십으로 가입함으로써 회사가 대상회원에게 추가적으로 제공하는 각종 서비스 및 관련 부가서비스 일체를 의미한다.
                  ② 회원이란, 『드림 이용약관』에 동의하고 그 서비스를 이용하는 자로서, 본 서비스를 이용하기 위해 본 약관에 동의한 자를 의미한다.
                  ③ 대상회원이란, 『드림 이용약관』에 동의하고 그 서비스를 이용하는 자로서, 본 서비스 중 개별 서비스를 이용할 수 있는 요건을 갖춘 자를 의미한다.
                  ④ 대상 서비스이란, 회사가 제공하는 본 서비스 중 회사가 개별 서비스에 대한 상세 설명을 통해 지정한 서비스를 의미한다.
                  ⑤ 대상지역이란, 회사가 제공하는 본 서비스 중 회사가 개별 서비스에 대한 상세 설명을 통해 지정한 지역을 의미한다.
                  ⑥ 무료체험 기간이란, 본 회원이 본 서비스 이용계약을 체결한 이후부터 본 서비스의 이용료가 결제되기 직전까지 본 서비스를 무상으로 이용할 수 있는 기간을 의미한다.
               </p>
               <h5>제４조 (드림캐쳐 서비스 가입 신청)</h5>
               <p>
                  ① 드림캐쳐 서비스는 대상회원이 본 서비스를 위한 약관에 동의하고 결제정보를 입력함으로써 가입 신청할 수 있다.
                  ② 회사는 대상회원에게 『드림 이용약관』상 결격사유가 없으면 가입 신청을 승인하며, 대상회원은 회사가 가입 신청을 승인하는 즉시 본 회원이 된다.
               </p>
               <h5>제５조 (추가 정보의 수집)</h5>
               <p>
                  회사는 본 서비스를 제공하기 위하여 필수적으로 요구되는 정보를 별도의 동의 없이 추가로 수집할 수 있다. 다만, 수집하는 정보가 개인정보에 해당하는 경우 관련 법령 및 『드림 이용약관』상의 개인정보 보호 규정을 준수해야 한다.
               </p>
               <h5>제６조 (본 회원의 의무)</h5>
               <p>회원은 회사로부터 본 서비스의 수령 및 이용료의 결제를 위해 회원 본인의 정보를 최신으로 유지하여야 하며, 회사는 회원정보가 부정확하여 발생하는 불이익에 대하여는 책임지지 않는다.</p>
               <h5>제７조 (회사의 통지 의무)</h5>
               <p>① 회사는 회원에게 본 서비스의 개별 서비스에 대해 설명하여야 하고, 서비스 내용의 변경이 있는 경우, 이를 회원에게 통지하여야 한다.
                  ② 회원의 이용료가 결제된 경우 회사는 회원에게 결제 사실을 통지하여야 한다.</p>
               <h5>제８조 (금지행위)</h5>
               <p>① 회원은 이 약관에 따른 회원의 권리, 의무의 전부 또는 일부를 타인에게 대여, 양도, 위임할 수 없다.

                  ② 회원은 본 서비스의 정상적인 운영 또는 다른 회원의 본 서비스 이용을 방해하는 행위를 하여서는 아니된다.</p>
               <h5>제９조 (서비스의 중단 및 탈퇴)</h5>
               <p>① 회원의 카드 한도 초과 등으로 이용료의 결제가 이루어 지지 않을 경우 회사는 해당 회원에게 그 기간 동안 본 서비스를 제공하지 않을 수 있으며, 위와 같은 상황이 지속되는 경우 회사는 해당 회원에게 영구적으로 서비스의 제공을 중단할 수 있다. 단, 회원이 다수의 결제수단을 등록한 경우, 회사는 회사가 정한 세부정책에 따라 결제수단을 변경하여 결제할 수 있다.

                  ② 회원은 언제든지 탈퇴함으로써 본 서비스의 이용을 해지할 수 있다.</p>
               <h5>제1０조 (환불)</h5>
               <p>
                  ① 회사는 본 서비스 및 개별 서비스에 대하여 결제 및 환불 조건과 그 변경 내용에 대해 회원에게 설명하여야 한다.
                  ② 회원이 이용료를 결제한 이후 본 서비스가 중도에 해지되는 경우, 다음 각 호를 기준으로 환불 진행 여부를 결정할 수 있다.
                  A. 회원이 이용료 결제 후 이용료를 결제한 해당 월에(연회원의 경우 해당 해) 본 서비스를 단 한 번도 이용하지 않은 경우: 해당 월 이용료 (연회원의 경우 연회비) 전액 환불
                  B. 회원이 이용료 결제 후 이용료를 결제한 해당 월에(연회원의 경우 해당 해) 본 서비스를 1회 이상 이용한 경우: 특별한 사정이 없는 한 환불 불가
               </p>
               <h5>제１1조 (기타)</h5>
               <p>
                  본 약관과 『드림 이용 약관』이 상충하는 경우 본 약관이 우선하여 적용되고, 본 약관에서 규정하지 않은 사항은 『드림 이용 약관』을 적용한다.
               </p>
               <br/>
               <p id="policy_date">
                  본 약관은 2020년 4월 24일자로 개정됨
               </p>
               <br/>

            </div>
            
             <%-- Modal footer --%>
         </div>
      </div>
   </div>
   
   
    <%-- 이용약관 모달 끝  --%>

    <%-- 혜택 모달 시작========================================================================== --%>


    <%-- 혜택 1 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_1">
      <div class="modal-dialog modal-dialog-scrollable">
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 1 모달 끝 --%>
   

    <%-- 혜택 2 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_2">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 2 모달 끝 --%>
   

    <%-- 혜택 3 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_3">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 3 모달 끝 --%>
   

    <%-- 혜택 4 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_4">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 4 모달 끝 --%>
   

    <%-- 혜택 5 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_5">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 5 모달 끝 --%>
   

    <%-- 혜택 6 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_6">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 6 모달 끝 --%>
   

    <%-- 혜택 7 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_7">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 7 모달 끝 --%>
   

    <%-- 혜택 8 모달 시작 --%>
   <div class="modal fade" id="modal_benefit_8">
      <div class="modal-dialog modal-dialog-scrollable">
       <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
             <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title membership_title">혜택 상세설명</h5>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
             <%-- Modal body --%>
            <div class="modal-body">
               <p>
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
                  혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 혜택 내용 
               </p>
            </div>
         </div>
      </div>
   </div>
    <%-- 혜택 8 모달 끝 --%>
   
    <%-- 혜택 모달 끝========================================================================== --%>


<%-- footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />

<%-- My Info footer 호출 --%>
<jsp:include page="/WEB-INF/view/myPageFooter.jsp" />