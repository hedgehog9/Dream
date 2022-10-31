<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%-- header 호출 --%>
  <c:if test="${sessionScope.userid != 'admin'}">
	<jsp:include page="/WEB-INF/view/header.jsp" />
  </c:if>
  <c:if test="${sessionScope.userid == 'admin'}">
	<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />
  </c:if>
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/notice.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/notice.js" ></script>
  
  
  
<%-- 공지사항 코드 시작 --%>
  <div class="Main d-flex">
  
    <%-- 사이드 바 시작 --%>
    <jsp:include page="/WEB-INF/view/notice/notice_sidebar.jsp" />
    <%-- 사이드 바 끝 --%>
    
    <%-- 공지사항 테이블 시작 --%>
    <div id="notice" class="d-flex flex-column">
      <table class="table table-hover">
        <thead>
          <tr>
            <th><h4 style="font-weight:bold;">공지사항</h4></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="notice" items="${requestScope.noticeList}">
          <tr>
            <td onclick="location.href='<%= ctxPath%>/notice/noticeDetail.dream?num=${notice.notice_num}'" style="cursor:pointer"><span>${notice.notice_title}</span></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
        <nav aria-label="...">
		    <ul class="my pagination pagination-md justify-content-center">
		    	<%-- 첫페이지로 이동버튼 --%>
		    	<c:if test="${requestScope.page > requestScope.display_page}">
		    	<li class="page-item">
			      <a class="page-link" href="?p=1">
			      	<i class="fa-solid fa-angles-left"></i>
			      </a>
			    </li>
			    
			    
			    <%-- 전페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a class="page-link" href="?p=${requestScope.startPage-1}">
			      	<i class="fa-solid fa-angle-left"></i>
			      </a>
			    </li>
			    </c:if>
			    
			    <%-- 페이지번호 시작--%>
			    <c:forEach begin="${requestScope.startPage-1}" end="${requestScope.endPage-1}" varStatus="i">
                <c:if test="${requestScope.page == (requestScope.startPage+i.count-1)}">
                <li class="page-item active" aria-current="page">
			    	<a class="page-link" href="?p=${requestScope.startPage+i.count-1}">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                
                <c:if test="${requestScope.page != (requestScope.startPage+i.count-1)}">
                <li class="page-item">
			    	<a class="page-link" href="?p=${requestScope.startPage+i.count-1}">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                </c:forEach>
                <%-- 페이지번호 끝 --%>
                
                
                
			    <%-- 다음페이지로 이동버튼 --%>
			    <c:if test="${!(requestScope.last_display_page)}">
			    <li class="page-item">
			      <a class="page-link" href="?p=${requestScope.startPage+requestScope.display_page}"><i class="fa-solid fa-angle-right"></i></a>
			    </li>
			    <%-- 맨 끝페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a class="page-link" href="?p=${requestScope.totalPage}"><i class="fas fa-solid fa-angles-right"></i></a>
			    </li>
			    </c:if>
		  	</ul>
		</nav>
		<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
		<c:if test="${sessionScope.userid == 'admin'}">
		<div class="d-flex justify-content-end">
	      <button type="button" id="btn_notice_write" class="btn btn-white border rounded" onclick="location.href='<%=ctxPath %>/admin/notice_write.dream'">공지사항 작성</button>
	    </div>
	    </c:if>
    </div>
    <%--공지사항 끝  --%>
  </div>
  <%--Main 끝  --%> 

  
  
  
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />