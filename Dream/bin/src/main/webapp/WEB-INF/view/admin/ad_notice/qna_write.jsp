<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 관리자 전용 header 호출 --%>
<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/admin/qna_write.css" />

<%-- SmartEditor 텍스트편집기 --%>>
<script type="text/javascript" src="<%= ctxPath%>/smarteditor2/js/service/HuskyEZCreator.js"></script>

<%-- 직접만든 javascript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/admin/qna_write.js" ></script>

<script type="text/javascript">
	sessionStorage.setItem("faq_title",'${requestScope.faq_title}')
</script>


<%-- 글쓰기 페이지 시작 --%>
<div class="container d-flex flex-column m-auto">
   	<div id="page_title" class="d-flex">
    	<h4 style="font-weight:bold;">자주묻는질문 등록</h4>
   	</div>
	<form method="post" class="mt-3" name="writerFrm">
		<select name="faq_title" id="faq_title" class="border rounded my-2">
		  <option>구분</option>
		  <option>이용정책</option>
		  <option>공통</option>
		  <option>구매</option>
		</select>
		<input type="text" name="faq_subject" id="faq_subject" class="form-control mt-4 mb-2" placeholder="제목을 입력해주세요." value="${requestScope.faq_subject }" required>
		<div class="form-group mt-3">
			<textarea id="faq_content" class="form-control" rows="10" name="faq_content" placeholder="내용을 입력해주세요" required>${requestScope.faq_content }</textarea>
		</div>
		<div id="btn_area" class="d-flex">
			<div id="btn_box" class="m-auto">
				<c:if test="${empty requestScope.faq_num}">
				<button type="button" class="btn btn-secondary" onclick="save()">작성</button>
				</c:if>
				<c:if test="${not empty requestScope.faq_num}">
				<button type="button" class="btn btn-secondary" onclick="update()">수정</button>
				</c:if>
				<button type="button" class="btn btn-secondary" onclick="location.href=document.referrer">뒤로가기</button>
			</div>
		</div>
		<input type="hidden" name="faq_num" value="${requestScope.faq_num}"/>
	</form>
</div>


<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />