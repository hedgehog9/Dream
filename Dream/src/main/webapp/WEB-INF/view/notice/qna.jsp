<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <%--header 호출 --%>
  <c:if test="${sessionScope.userid != 'admin'}">
	<jsp:include page="/WEB-INF/view/header.jsp" />
  </c:if>
  <c:if test="${sessionScope.userid == 'admin'}">
	<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />
  </c:if>
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/qna.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/qna.js" ></script>
  
  <script type="text/javascript">
 	$(document).ready(function(){
 	  $("select#filter").change(function(){
 		 location.href='?title='+$(this).val()+'&p=${requestScope.page}';
 	  });
 	  //셀렉트태그에 선택된값을 타이틀값으로 넣기
 	  $("select#filter").val("${requestScope.title}").prop("selected", true);
 	  
 	  $("button.btn_fillter:contains(${requestScope.title})").addClass("active");
		   
 	  
 	  //필터버튼 클릭시 이벤트
 	  $("button.btn_fillter").click(function(e){
 	 	const target = $(e.target);
 	 	$("button.btn_fillter").css("background-color","");
		$("button.btn_fillter").css("color","");
		
	    target.css("background-color","black");
	    target.css("color","white");
 	  });
 	  
 	  
 	});//end of $(document).ready(function(){})---
  </script>


  
  <div class="Main d-flex">
    <%-- 사이드 바 시작 --%>
    <jsp:include page="/WEB-INF/view/notice/notice_sidebar.jsp" />
    <%-- 사이드 바 끝 --%>


    <%-- 자주묻는질문 시작 --%>
    <div id="qna" class="d-flex flex-column">
      <table class="table">
        <thead>
          <tr>
            <th colspan="3"><h4 style="font-weight:bold;">자주묻는질문</h4></th>
          </tr>
        </thead>
        
        <tbody>
          <tr>
            <td colspan="3" class="selectTag">
              <select id="filter" style="height:50px; font-weight: bold;" class="border rounded">
                <option>전체</option>
                <option>이용정책</option>
                <option>공통</option>
                <option>구매</option>
              </select>
            </td>
            <td colspan="3" class="button">
              <button type="button" class="btn_fillter btn rounded-pill border" onclick="location.href='?title=전체&p=${requestScope.page}'">전체</button>
              <button type="button" class="btn_fillter btn rounded-pill border" onclick="location.href='?title=이용정책&p=${requestScope.page}'">이용정책</button>
              <button type="button" class="btn_fillter btn rounded-pill border" onclick="location.href='?title=공통&p=${requestScope.page}'">공통</button>
              <button type="button" class="btn_fillter btn rounded-pill border" onclick="location.href='?title=구매&p=${requestScope.page}'">구매</button>
            </td>
          </tr>



          <%-- 게시물 시작 --%>
          <c:forEach var="qna" items="${requestScope.qnaList}">
          <tr style="cursor:pointer;" class="headline">
            <td class="title">
              <strong>${qna.faq_title}</strong>
            </td>
            <td class="subject">
              ${qna.faq_subject}
            </td>
            <td class="downIcon">
              <i class="fas fa-thin fa-caret-down border rounded px-2 py-1" style="background-color: white;"></i>
            </td>
            <td class="upIcon">
              <i class="fas fa-regular fa-caret-up border rounded px-2 py-1" style="background-color: white;"></i>
            </td>
          </tr>
          <tr class="content">
            <td colspan="3">
              <div id="content">
                ${qna.faq_content}
                <c:if test="${sessionScope.userid == 'admin'}">
                  <div class="d-flex justify-content-center">
	                <button type="button" id="btn_update" class="btn btn-white border mx-1 my-2" onclick="location.href='<%= ctxPath%>/admin/faqUpdate.dream?num=${qna.faq_num }'">수정</button>
	                <button type="button" id="btn_delete" class="btn btn-white border mx-1 my-2">삭제</button>
	                <div class="faq_num" style="display:none;">${qna.faq_num}</div>
                  </div>
	            </c:if>
              </div>
            </td>
          </tr>
          </c:forEach>
          <%-- 게시물 끝 --%>
          
        </tbody>

      </table>
      <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
        <nav aria-label="...">
		    <ul class="my pagination pagination-md justify-content-center">
		    	<%-- 첫페이지로 이동버튼 --%>
		    	<c:if test="${requestScope.page > requestScope.display_page}">
		    	<li class="page-item">
			      <a class="page-link" href="?title=${requestScope.title}&?p=1">
			      	<i class="fa-solid fa-angles-left"></i>
			      </a>
			    </li>
			    
			    
			    <%-- 전페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a class="page-link" href="?title=${requestScope.title}&?p=${requestScope.startPage-1}">
			      	<i class="fa-solid fa-angle-left"></i>
			      </a>
			    </li>
			    </c:if>
			    
			    <%-- 페이지번호 시작--%>
			    <c:forEach begin="${requestScope.startPage-1}" end="${requestScope.endPage-1}" varStatus="i">
                <c:if test="${requestScope.page == (requestScope.startPage+i.count-1)}">
                <li class="page-item active" aria-current="page">
			    	<a class="page-link" href="?title=${requestScope.title}&p=${requestScope.startPage+i.count-1}">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                
                <c:if test="${requestScope.page != (requestScope.startPage+i.count-1)}">
                <li class="page-item">
			    	<a class="page-link" href="?title=${requestScope.title}&p=${requestScope.startPage+i.count-1}">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                </c:forEach>
                <%-- 페이지번호 끝 --%>
                
                
                
			    <%-- 다음페이지로 이동버튼 --%>
			    <c:if test="${!(requestScope.last_display_page)}">
			    <li class="page-item">
			      <a class="page-link" href="?title=${requestScope.title}&p=${requestScope.startPage+requestScope.display_page}"><i class="fa-solid fa-angle-right"></i></a>
			    </li>
			    <%-- 맨 끝페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a class="page-link" href="?title=${requestScope.title}&p=${requestScope.totalPage}"><i class="fas fa-solid fa-angles-right"></i></a>
			    </li>
			    </c:if>
		  	</ul>
		</nav>
		<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
		<c:if test="${sessionScope.userid == 'admin'}">
		<div class="d-flex justify-content-end">
	      <button type="button" id="btn_notice_write" class="btn btn-white border rounded" onclick="location.href='<%=ctxPath %>/admin/qna_write.dream'">글 작성</button>
	    </div>
	    </c:if>
    </div>
    <%-- 자주묻는질문 끝 --%>
  </div>
  <%-- Main 끝 --%>

  
  
  
  <%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />