<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>
<%-- Bootstrap CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.0-dist/css/bootstrap.min.css" >
<%-- Font Awesome 5 Icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<%-- Optional JavaScript --%>
<%--<script type="text/javascript" src="<%= ctxPath%>/jquery3.6.0/jquery-3.6.0.min.js"></script>--%>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>
<%--- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/footer.css" />
<%--- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/footer.js" ></script>




   <%-- <div id="footer"> 시작  --%>
   <div id="footer">

      <%-- div id="top" start --%>
      <div id="top">
         <%-- 고객센터 --%>
         <div id="Service">
            <strong>고객센터</strong>
            <strong><a class="color_black" href="#" style="font-size: 16px;">1588-9999</a></strong>
            <ul class="footer_ul">
               <li class="footer_li">운영시간 평일 11:00 - 18:00 (토∙일, 공휴일 휴무) 점심시간 평일 13:00 - 14:00</li>
               <li class="footer_li" style="color: black;">1:1 문의하기는 앱에서만 가능합니다.</li>
            </ul>
            <button id="btn_question" type="button" onClick="javascript:location.href='/Dream/notice/qna.dream'">자주 묻는 질문 </button>
            <%--<button id="btn_question" type="button" onClick="javascript:location.href='/Dream/admin/shippingMangement.dream'">배송관리 메뉴 추가시 버튼 삭제</button> --%>
         </div>

         <div id = "left_left">
            <div id="information_use">
               <strong>이용안내</strong>
               <ul class="menu_list footer_ul">
                  <li class="menu_item footer_li"><a class="color_gray" href="#modal_check_standard" data-toggle="modal">검수기준</a></li>
                  <li class="menu_item footer_li"><a class="color_gray" href="#modal_policy" data-toggle="modal">이용정책</a></li>
                  <li class="menu_item footer_li"><a class="color_gray" href="#modal_penalty"  data-toggle="modal">패널티 정책</a></li>
                  <li class="menu_item footer_li"><a class="color_gray" href="#modal_guideline"  data-toggle="modal">커뮤니티 가이드라인</a></li>
               </ul>
            </div>
            
            <div id="center">
               <strong>고객지원</strong>
               <ul class="menu_list footer_ul">
                  <li class="menu_item footer_li"><a class="color_gray" href="공지사항.jsp">공지사항</a></li>
                  <li class="menu_item footer_li"><a class="color_gray" href="서비스소개.jsp">서비스 소개</a></li>
                  <li class="menu_item footer_li"><a class="color_gray" href="쇼룸안내.jsp">쇼룸 안내</a></li>
                  <li class="menu_item footer_li"><a class="color_gray" href="판매자방문접수.jsp">판매자 방문접수</a></li>
               </ul>
            </div>
         </div>
      </div>
      <%-- div id="top" end --%>
      

      <%-- <div id="company_info_list"> start --%>
      <div id="company_info_list">
         <ul class="bottom_ul footer_ul">
            <li class="bottom_li footer_li"><a class="color_black" href="회사소개.jsp">회사소개</a></li>
            <li class="bottom_li footer_li"><a class="color_black" href="인재채용.jsp">인재채용</a></li>
            <li class="bottom_li footer_li"><a class="color_black" href="제휴제안.jsp">제휴제안</a></li>
            <li class="bottom_li footer_li"><a class="color_black" href="#modal_term" href="#modal_penalty"  data-toggle="modal">이용약관</a></li>
            <li class="bottom_li footer_li"><a class="color_black" href="개인정보처리방침.jsp" style="font-weight: 700;" >개인정보처리방침</a></li>
         </ul>

         <div id="social_icons">
            <a class="color_black" href="https://www.instagram.com/"><i class="fa-brands fa-instagram s24"></i></a>
            <a class="color_black" href="https://www.facebook.com/"><i class="fa-brands fa-square-facebook s24"></i></a>
            <a class="color_black" href="https://twitter.com/i/flow/login"><i class="fa-brands fa-square-twitter s24"></i></a>
            <button type="button" class="btn_company_info" onclick="func_show_business_info()" >사업자 정보 <i id="btn_b_info" class="fa-solid fa-chevron-down"></i></button>
         </div>
      </div>
      <%-- <div id="company_info_list"> end --%>

      <%-- 회사 정보, --%>
      <div>
         <div class="company_info" >
            <span id="company_info">
               드림 주식회사 · 대표 최현우 &nbsp; 사업자등록번호 : 111-22-01234 <a id="business_info" href="https://www.sist.co.kr/">사업자정보확인</a>  &nbsp; 통신판매업 : 제 2021-쌍용강북C-0093호 &nbsp; 사업장소재지 : 서울특별시 마포구 서교동 447-5 풍성빌딩, 2,3,4층 &nbsp; 호스팅 서비스 : 네이버 클라우드 ㈜
            </span>
         </div>

         <div class="notice">
            <span id="notice">
               드림(주)는 통신판매 중개자로서 통신판매의 당사자가 아니므로 개별 판매자가 등록한 상품정보에 대해서 책임을 지지 않습니다. 단, 거래과정에서 검수하고 보증하는 내용에 대한 책임은 당사에 있습니다.
            </span>
            <div class="copyright">
               <span id="copyright">
                  &copy; DREAM Corp
               </span>
            </div>
         </div>
      </div>

   </div>
   <%-- <div id="footer"> 끝  --%>

</body>
</html>


<%--======================================== 모달 모음 ========================================--%>

<%-- 검수기준 모달 시작 --%>
<div class="modal fade footer_modal" id="modal_check_standard">
   <div class="modal-dialog modal-dialog-scrollable">
   <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
      <div class="modal-content">
         
         <%-- Modal header --%>
         <div class="modal-header">
         <h5 class="modal-title">검수기준</h5>
         </div>
         
         <%-- Modal body --%>
         <div class="modal-body">
         <h3>Some text to enable scrolling..</h3>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
         </div>
         
         <%-- Modal footer --%>
      </div>
   </div>
   </div>
   <%-- 검수기준 모달 끝 --%>

   <%-- 이용정책 모달 시작 --%>
   <div class="modal fade footer_modal" id="modal_policy">
      <div class="modal-dialog modal-dialog-scrollable">
      <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
            <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title">이용정책</h5>
            </div>
            
            <%-- Modal body --%>
            <div class="modal-body">
            <h3>Some text to enable scrolling..</h3>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
            
            <%-- Modal footer --%>
         </div>
      </div>
   </div>
   <%-- 이용정책 모달 끝 --%>

   <%-- 패널티정책 모달 시작 --%>
   <div class="modal fade footer_modal" id="modal_penalty">
      <div class="modal-dialog modal-dialog-scrollable">
      <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
            <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title">패널티 정책</h5>
            </div>
            
            <%-- Modal body --%>
            <div class="modal-body">
            <h3>Some text to enable scrolling..</h3>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
            
            <%-- Modal footer --%>
         </div>
      </div>
   </div>
   <%-- 패널티정책 모달 끝 --%>

   <%-- 커뮤니티 가이드라인 모달 시작 --%>
   <div class="modal fade footer_modal" id="modal_guideline">
      <div class="modal-dialog modal-dialog-scrollable">
      <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
            <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title">커뮤니티 가이드라인</h5>
            </div>
            
            <%-- Modal body --%>
            <div class="modal-body">
            <h3>Some text to enable scrolling..</h3>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
            
            <%-- Modal footer --%>
         </div>
      </div>
   </div>
   <%-- 커뮤니티 가이드라인 모달 끝 --%>


   <%-- 이용약관 모달 시작 --%>
   <div class="modal fade footer_modal" id="modal_term">
      <div class="modal-dialog modal-dialog-scrollable">
      <%-- .modal-dialog-scrollable을 .modal-dialog에 추가하여 페이지 자체가 아닌 모달 내부에서만 스크롤할 수 있습니다. --%>
         <div class="modal-content">
            
            <%-- Modal header --%>
            <div class="modal-header">
            <h5 class="modal-title">이용약관</h5>
            </div>
            
            <%-- Modal body --%>
            <div class="modal-body">
            <h3>Some text to enable scrolling..</h3>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
               <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
            
            <%-- Modal footer --%>
         </div>
      </div>
   </div>
   <%-- 이용약관 모달 끝 --%>

<%--======================================== 모달 모음 ========================================--%>







