<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/like.css" />
  
  <script type="text/javascript" src="<%= ctxPath%>/js/like.js"></script>
  
  
  
  <div class="container">
    <div id="menu_title">
        <h2 id="menu_title" class="text-center">LIKE</h2>
    </div>
    
    
    <%-- item box 시작 --%>
	<div class="item_box row mt-4 pb-5">
	
	
    <%-- 게시물 시작 --%>
    <c:forEach var="product" items="${requestScope.productList}">
    <div class="item col-6 col-lg-3 d-flex flex-column px-3 py-3 px-lg-4">
       <a id="${product.product_num}" class="product" href="<%=ctxPath %>/product/detail.dream?num=${product.product_num}"><%-- id값에 제품번호 넣기!!!!*** --%>
         <div class="product">
           <div class="product_imgbox border">
             <c:if test="${not empty product.discount_rate && product.discount_rate != 0}">
            <div id="sale_mark" class="border rounded text-center">SALE</div>
          </c:if>
             <img id="product_img" src="<%=ctxPath %>/images/제품이미지/${product.product_image}">
           </div>
           <div id="product_simple_explain" class="pl-2">
             <%-- 상품 카테고리 넣을 곳 --%>
             <div id="product_division" class="mt-2">${product.category}</div>
             <%-- 상품이름 넣을 곳 --%>
             <div id="product_name" class="my-2">${product.product_name }</div>
             <%-- 상품가격 넣을 곳 --%>
             <div id="product_price" class="d-flex justify-content-between">
               <%-- if문!!상품자체할인가격이 없다면 아래태그,상품가격 --%>
               <c:if test="${empty product.discount_rate || product.discount_rate == 0}">
               <span id="product_price_no_discount">&#8361;<fmt:formatNumber value="${product.real_price}" pattern="#,###" />원</span>
				</c:if>
               <%-- if문!!상품자체할인가격이 있다면 아래태그,할인된가격--%>
               <c:if test="${not empty product.discount_rate && product.discount_rate != 0}">
                <span id="product_price_discount" style="text-decoration: line-through;">&#8361;<fmt:formatNumber value="${product.price}" pattern="#,###" />원</span>
                <%-- 상품자체할인 가격 있으면 아래 태그 넣기 태그라이브러리 들어갈 곳 --%>
                <div id="discount_mark" class="d-flex align-items-center">
                  <%-- 할인율 넣을 곳 --%>
                  <span id="discount_percent"><fmt:formatNumber type="number" maxFractionDigits="0" value="${product.discount_rate}" />%</span>
                </div> 
               </c:if>
             </div>
             <%-- 상품자체할인 가격 있으면 아래 태그 넣기 태그라이브러리 들어갈 곳--%>
             <c:if test="${not empty product.discount_rate && product.discount_rate != 0}">
             	<div id="product_sale_price">&#8361;<fmt:formatNumber value="${product.real_price}" pattern="#,###" /><span>원</span></div>
             </c:if>
           </div>
         </div>
       </a>
       <c:if test="${empty sessionScope.userid || product.product_like_cnt == 0}">
         <div type="button" id="btn_like" class="border rounded text-center"><i class="fa-solid fa-heart"></i></div>
       </c:if>
       <c:if test="${not empty sessionScope.userid && product.product_like_cnt != 0}">
         <div type="button" id="btn_like" class="border rounded text-center" style="color:pink;"><i class="fa-solid fa-heart"></i></div>
       </c:if>
     </div>
	</c:forEach>
	
	
	</div>
	<%-- item box 끝 --%>
	
	<%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
	    <nav aria-label="...">
		    <ul class="my pagination pagination-md justify-content-center">
		    	<%-- 첫페이지로 이동버튼 --%>
		    	<c:if test="${requestScope.page > requestScope.display_page}">
		    	<li class="page-item">
			      <a type="button" class="page-link" onclick="goPage('1');">
			      	<i class="fa-solid fa-angles-left"></i>
			      </a>
			    </li>
			    
			    
			    <%-- 전페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a type="button" class="page-link" onclick="goPage('${requestScope.startPage-1}');">
			      	<i class="fa-solid fa-angle-left"></i>
			      </a>
			    </li>
			    </c:if>
			    
			    <%-- 페이지번호 시작--%>
			    <c:forEach begin="${requestScope.startPage-1}" end="${requestScope.endPage-1}" varStatus="i">
                <c:if test="${requestScope.page == (requestScope.startPage+i.count-1)}">
                <li class="page-item active" aria-current="page">
			    	<a type="button" class="page-link" onclick="goPage('${requestScope.startPage+i.count-1}')">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                
                <c:if test="${requestScope.page != (requestScope.startPage+i.count-1)}">
                <li class="page-item">
			    	<a type="button" class="page-link" onclick="goPage('${requestScope.startPage+i.count-1}')">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                </c:forEach>
                <%-- 페이지번호 끝 --%>
                
                
                
			    <%-- 다음페이지로 이동버튼 --%>
			    <c:if test="${!(requestScope.last_display_page)}">
			    <li class="page-item">
			      <a type="button" class="page-link" onclick="goPage('${requestScope.startPage+requestScope.display_page}')"><i class="fa-solid fa-angle-right"></i></a>
			    </li>
			    <%-- 맨 끝페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a type="button" class="page-link" onclick="goPage('${requestScope.totalPage}')"><i class="fas fa-solid fa-angles-right"></i></a>
			    </li>
			    </c:if>
		  	</ul>
		</nav>
          
		<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
  </div>
  
  
  
  <%-- footer 호출 --%>
  <jsp:include page="/WEB-INF/view/footer.jsp" />
