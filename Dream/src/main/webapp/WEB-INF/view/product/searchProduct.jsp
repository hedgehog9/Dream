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
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/searchProduct.css" />
  
  <script type="text/javascript" src="<%= ctxPath%>/js/searchProduct.js"></script>
  
  
  <div class="Main mt-4">
  
  
    <%-- 검색부분 시작 --%>
   <div id="search_area" class="d-flex m-auto rounded">
      <div id="productSearch" class="d-flex justify-content-between">
         <div id="input_keyword">
           <input type="text" placeholder="제품번호,제품명,카테고리,상세카테고리 등" id="keyword" class="pl-3" name="keyword">
         </div>
         <div id="search_btn">
           <button type="button" class="btn btn-white" id="btn_search"><i class="fas fa-xl fa-thin fa-magnifying-glass"></i></button>
         </div>
       </div>
    </div>
    <%-- 검색부분 끝 --%>
    
  <br>
  <br>
  <p class="title">추천검색어</p>
  <%-- 추천검색어 시작 --%>
  <div id="recommend" class="d-flex align-items-center">
    <c:forEach var="recommendKeyword" items="${requestScope.recommendList}" varStatus="status">
    <div class="recommend_keyword mx-2 px-2 border rounded">
      <span class="recommend_keyword">${recommendKeyword}</span>
    </div>
    
    <c:if test="${sessionScope.userid == 'admin'}">
    <div class="keyword_delete_box" style="cursor:pointer;">
 	  <div class="btn_delete_keyword d-flex justify-content-center align-items-center">&times;</div>
    </div>
    </c:if>
    </c:forEach>
    
    <c:if test="${sessionScope.userid == 'admin'}">
    <div class="recommend_keyword_add mx-2 px-2 border rounded" 
                data-toggle="modal" data-target="#insert_recommend_keyword" 
                data-dismiss="modal" style="font-weight:bold;">
                추천검색어 추가
    </div>
    </c:if>
  </div>
  
  <%-- 추천검색어 끝 --%>
  
  <c:if test="${not empty sessionScope.userid}">
  <br>
  <br>
  <p class="title">${requestScope.username}님 맞춤검색어</p>
  <%-- 맞춤검색어 시작 --%>
  <div id="personalized" class="d-flex align-items-center">
    <c:forEach var="personalizedKeyword" items="${requestScope.personalizedList}" varStatus="status">
    <div class="personalized_keyword mx-2 px-2 border rounded">
      <span class="personalized_keyword">${personalizedKeyword}</span>
    </div>
    </c:forEach>
  </div>
  </c:if>
  <%-- 맞춤검색어 끝 --%>
  
  <br>
  <br>
  
  <p class="title">인기검색어 <span id="date">${requestScope.currentHour}:00 기준</span>
  <%-- span#reload 클릭이벤트 잡아서 처리하기 --%>
    <span id="reload" class="px-1 py-1 border rounded"><i class="fa-solid fa-rotate-right"></i></span>
  </p>
  <%-- 인기검색어 시작 --%>
  <div class="d-flex border rounded px-3 py-3">
    <%-- 1~5위 --%>
    <div class="w-50 d-flex flex-column">
      <ul class="best_keyword">
        <%-- span.span_best_keyword 클릭이벤트 잡아서 처리하기 --%>
        <c:forEach var="keyword" items="${requestScope.keywordList1}" varStatus="status">
        <li><strong class="mr-2">${status.count}.</strong><span class="span_best_keyword">${keyword}</span></li>
        </c:forEach>
      </ul>
    </div>
    
    <%-- 6~10위 --%>
    <div class="w-50 d-flex flex-column">
      <ul class="best_keyword">
        <%-- span.span_best_keyword 클릭이벤트 잡아서 처리하기 --%>
        <c:forEach var="keyword" items="${requestScope.keywordList2}" varStatus="status">
        <li><strong class="mr-2">${status.count+5}.</strong><span class="span_best_keyword">${keyword}</span></li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <%-- 인기검색어 끝 --%>
  
  <br>
  <p class="title">카테고리</p>
  <%-- 카테고리 시작 --%>
  <div id="search_category_area" class="d-flex justify-content-between">
    <%-- div.category_item 클릭이벤트 잡아서 처리하기 --%>
    <div class="category_item d-flex flex-column">
      <div class="category_img_box"><img class="category_img rounded" src="<%= ctxPath%>/images/디테일카테고리/카테고리침대.PNG"/></div>
      <p class="category_title m-auto">침구류</p>
    </div>
    
    <div class="category_item d-flex flex-column">
      <div class="category_img_box"><img class="category_img rounded" src="<%= ctxPath%>/images/디테일카테고리/카테고리무드등.PNG"/></div>
      <p class="category_title m-auto">조명</p>
    </div>
    
    <div class="category_item d-flex flex-column">
      <div class="category_img_box"><img class="category_img rounded" src="<%= ctxPath%>/images/디테일카테고리/카테고리커플파자마.PNG"/></div>
      <p class="category_title m-auto">파자마</p>
    </div>
    
    <div class="category_item d-flex flex-column">
      <div class="category_img_box"><img class="category_img rounded" src="<%= ctxPath%>/images/디테일카테고리/카테고리필로우미스트.PNG"/></div>
      <p class="category_title m-auto">수면용품</p>
    </div>
    
  </div>
  
  <%-- 카테고리 끝 --%>
  
  
</div>
  
  
  
  
  
  
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />










<%-- 추천검색어 추가 modal --%>
<div class="modal fade" id="insert_recommend_keyword">
   <div class="modal-dialog">
     <div class="modal-content">
     
       <!-- Modal header -->
       <div class="modal-header">
         <div class="d-flex">
           <h5 class="modal-title" style="font-weight:bold;">추천검색어 추가하기</h5>
         </div>
         <button type="button" class="close" data-dismiss="modal">&times;</button>
       </div>
       
       <!-- Modal body -->
       <div class="modal-body">
         <form id="insertKeywordFrm" name="insertKeywordFrm">
           <input type="text" name="recommend_keyword" id="recommend_keyword" class="border rounded pl-2" placeholder="추가할 추천검색어 입력(10자이내,특수문자제외)" maxlength="20">
         </form>
       </div>
       
       <!-- Modal footer -->
       <div class="modal-footer">
         <button type="button" class="btn btn-white border" id="insert_keyword">추가</button>
         <button type="button" class="btn btn-white border insert_recommend_keywordClose" data-dismiss="modal">닫기</button>
       </div>
     </div>
     
   </div>
 </div>