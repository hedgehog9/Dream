<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 관리자 전용 header 호출 --%>
<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/admin/notice_write.css" />

<%-- SmartEditor 텍스트편집기 --%>>
<script type="text/javascript" src="<%= ctxPath%>/smarteditor2/js/service/HuskyEZCreator.js"></script>

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/admin/notice_write.js"></script>



<%-- 글쓰기 페이지 시작 --%>
<div class="container d-flex flex-column m-auto">
   	<div id="page_title" class="d-flex">
    	<h4 style="font-weight:bold;">공지사항 등록</h4>
   	</div>
	<form method="post" class="mt-3" name="writerFrm">
		<input type="text" name="notice_title" id="notice_title" class="form-control mt-4 mb-2" placeholder="제목을 입력해주세요." value="${requestScope.notice_title }" required>
		<div class="form-group mt-3">
			<textarea id="notice_content" class="form-control" rows="10" name="notice_content" placeholder="내용을 입력해주세요" required>${requestScope.notice_content }</textarea>
		</div>
		<div id="btn_area" class="d-flex">
			<div id="btn_box" class="m-auto">
				<c:if test="${empty requestScope.notice_num}">
				<button type="button" class="btn btn-secondary" onclick="save()">작성</button>
				</c:if>
				<c:if test="${not empty requestScope.notice_num}">
				<button type="button" class="btn btn-secondary" onclick="update()">수정</button>
				</c:if>
				<button type="button" class="btn btn-secondary" onclick="location.href=document.referrer">뒤로가기</button>
			</div>
		</div>
		<input type="hidden" name="notice_num" value="${requestScope.notice_num}"/>
	</form>
</div>


<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />