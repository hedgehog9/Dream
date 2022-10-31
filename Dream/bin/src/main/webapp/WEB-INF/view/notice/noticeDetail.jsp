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
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/noticeDetail.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/noticeDetail.js" ></script>
  
  
  
  
  <div class="Main d-flex">
  
  
    <%-- 사이드 바 시작 --%>
    <jsp:include page="/WEB-INF/view/notice/notice_sidebar.jsp" />
    <%-- 사이드 바 끝 --%>
	
	
	
	
	
	
    <%---------------------------------------- 공지사항 상세보기 시작 -----------------------------------------%>
    <div id="noticeDetail" class="d-flex flex-column">
      <table class="table">
        <thead>
          <tr>
            <th><h4 style="font-weight:bold;">공지사항</h4></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <span id="date">${requestScope.write_date}</span>
              <p id="title">${requestScope.notice_title}</p>
            </td>
          </tr>
          <tr>
            <td id="noticeContent">
              ${requestScope.notice_content}
            </td>
          </tr>
        </tbody>
        <tfoot>
          <td class="d-flex justify-content-center">
            <c:if test="${sessionScope.userid == 'admin'}">
            <button type="button" id="btn_update" class="btn btn-white border mx-1" onclick="location.href='<%= ctxPath%>/admin/noticeUpdate.dream?num=${requestScope.notice_num }'" style="cursor:pointer">수정</button>
            <button type="button" id="btn_delete" class="btn btn-white border mx-1" onclick="location.href='<%= ctxPath%>/admin/noticeDelete.dream?num=${requestScope.notice_num }'" style="cursor:pointer">삭제</button>
            </c:if>
            <button type="button" id="btn_close" class="btn btn-white border mx-1" onclick="location.href='<%= ctxPath%>/notice/notice.dream'" style="cursor:pointer">목록보기</button>
          </td>
        </tfoot>
      </table>
    </div>
    <%---------------------------------------- 공지사항 상세보기 끝 -----------------------------------------%>
  </div><%-- end of Main --%>

  
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />