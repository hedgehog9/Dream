
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>


<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/notice_sidebar.css" />

<%-- 사이드 바 시작 --%>
    <div class="sidebar ml-5 mt-3">
      <div>
          <h4 class="mb-4" style="font-weight:bold;">고객센터</h4>
      </div>    
      <div class="category-section mb-5">
          <ul class="nav flex-column">
              <li class="nav-item">
                  <a class="nav-link pl-0 text-muted" href="<%=ctxPath %>/notice/notice.dream">공지사항</a>
              </li>
              <li class="nav-item">
                  <a class="nav-link pl-0 text-muted" href="<%=ctxPath %>/notice/qna.dream">자주 묻는 질문</a>
              </li>
          </ul>
      </div>
    </div>
    <%-- 사이드 바 끝 --%>