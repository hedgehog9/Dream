
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/notice_sidebar.css" />

<script type="text/javascript">
 $(document).ready(function(){
    /* 사이드바 script 시작 */
    $("a.color_gray").css({"font-weight":"","color":""});
	sidebar_bold();
    
 });// end of $(document).ready(function()----------------------------------
 function sidebar_bold(){
	let url = window.document.location.href;
    const notice_page_idx = url.indexOf("/notice/notice.dream");
    const qna_page_idx = url.indexOf("/notice/qna.dream");
    const noticeDetail_page_idx = url.indexOf("/notice/noticeDetail.dream");
    if(notice_page_idx != -1 ){
		$("a#notice_page").css({"font-weight":"bold","color":"black"});
    }
    if(qna_page_idx != -1 ){
    	$("a#qna_page").css({"font-weight":"bold","color":"black"});
    }
    if(noticeDetail_page_idx != -1 ){
    	$("a#notice_page").css({"font-weight":"bold","color":"black"});
    }
 }

</script>

<%-- 사이드 바 시작 --%>
    <div class="sidebar ml-5 mt-3">
      <div>
          <h4 class="mb-4" style="font-weight:bold;">고객센터</h4>
      </div>    
      <div class="category-section mb-5">
          <ul class="nav">
              <li class="nav-item">
                  <a id="notice_page" class="color_gray nav-link pl-0" href="<%=ctxPath %>/notice/notice.dream">공지사항</a>
              </li>
              <li class="nav-item">
                  <a id="qna_page" class="color_gray nav-link pl-0" href="<%=ctxPath %>/notice/qna.dream">자주 묻는 질문</a>
              </li>
          </ul>
      </div>
    </div>
<%-- 사이드 바 끝 --%>