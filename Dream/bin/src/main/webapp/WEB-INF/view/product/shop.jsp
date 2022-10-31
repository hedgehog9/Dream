<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
  <%--header 호출 --%>
  <c:if test="${sessionScope.userid != 'admin'}">
	<jsp:include page="/WEB-INF/view/header.jsp" />
  </c:if>
  <c:if test="${sessionScope.userid == 'admin'}">
	<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />
  </c:if>
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/shop.css" />
  
  <script type="text/javascript">
  if("${requestScope.currentURL}" != "") {
	const URL = decodeURI("${requestScope.currentURL}");
	sessionStorage.setItem("currentURL",URL);
	sessionStorage.setItem("category",'${requestScope.category}');
	sessionStorage.setItem("detail_category",'${requestScope.detail_category}');
	sessionStorage.setItem("bestyn",'${requestScope.bestyn}');
	sessionStorage.setItem("gender",'${requestScope.gender}');
	sessionStorage.setItem("sort",'${requestScope.sort}');
	sessionStorage.setItem("start_price",'${requestScope.start_price}');
	sessionStorage.setItem("end_price",'${requestScope.end_price}');
	sessionStorage.setItem("page",'${requestScope.page}');
	sessionStorage.setItem("keyword",'${requestScope.keyword}');
	<%-- 
	sessionStorage.setItem("display_cnt", '${requestScope.display_cnt}');
	sessionStorage.setItem("display_page", '${requestScope.display_page}');
	sessionStorage.setItem("page", '${requestScope.page}');
	sessionStorage.setItem("total_cnt", '${requestScope.total_cnt}');	
	sessionStorage.setItem("totalPage", '${requestScope.totalPage}');	
	sessionStorage.setItem("startPage", '${requestScope.startPage)}';	
	sessionStorage.setItem("endPage", '${requestScope.endPage}');
	sessionStorage.setItem("last_display_page",'${requestScope.last_display_page}');
	--%>
  }
  </script>
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/shop.js" ></script>
  
  
  
  <%-- container 시작 --%>
  <div id="container">
    <%-- menu 시작 --%>
	    <div class="menu">
	      <c:if test="${empty requestScope.keyword}">
	        <div id="menu_title">
	            <h2 id="menu_title" class="text-center">SHOP</h2>
	        </div>
	      </c:if>
	      <c:if test="${not empty requestScope.keyword}">
	        <%-- 검색부분 시작 --%>
	        <div id="search_box">
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
			</div>
			<%-- 검색부분 끝 --%>
	      </c:if>
	      <div id="btn_category">
	        <button id="btn_fillter_all" type="button" class="btn_fillter btn rounded-pill border">전체</button>
	        <button id="btn_fillter_bedding" type="button" class="btn_fillter btn rounded-pill border">침구류</button>
	        <button id="btn_fillter_light" type="button" class="btn_fillter btn rounded-pill border">조명</button>
	        <button id="btn_fillter_pajamas" type="button" class="btn_fillter btn rounded-pill border">파자마</button>
	        <button id="btn_fillter_sleep_supplies" type="button" class="btn_fillter btn rounded-pill border">수면용품</button>
	      </div>
	    </div>
	    <div class="details_category pt-2 d-flex flex-nowrap">
	    
	    
	    </div>
	    <%-- menu 끝 --%>
	
	
	
	    <%-- Main 시작 --%>
	    <div class="Main d-flex m-auto">
	      <%-- Main > 사이드필터 시작 --%>
	      <div class="sidefilter">
	
	        <div class="filter_status pt-4" style="font-size:medium; font-weight: bold;">
	          필터
	        </div>
	
	        <div class="filter_title pt-4 d-flex justify-content-between align-items-center border-bottom">
	          <div class="title_box d-flex flex-column">
	            <span id="main_title">카테고리</span>
	            <span id="place_holder">카테고리 필터</span>
	          </div>
	          <a type="button" class="btn_toggle_filter">
	            <i id="category_icon_plus" class="plus_icon fa-solid fa-plus" style="color:black;"></i>
	            <i id="category_icon_minus" class="minus_icon fa-solid fa-minus" style="color:black;"></i>
	          </a>
	        </div>
	
	        <%-- 카테고리 토글부분 --%>
	        <form id="category_toggle" class="togglebox" action="">
	          <div class="d-flex flex-column mt-3">
	            <div>
	              <input type="radio" name="category" id="all">
	              <label for="all" class="category_radio">전체</label>
	            </div>
	            <div>
	              <input type="radio" name="category" id="bad">
	              <label for="bad" class="category_radio">침구류</label>
	            </div>
	            <div>
	              <input type="radio" name="category" id="light">
	              <label for="light" class="category_radio">조명</label>
	            </div>
	            <div>
	              <input type="radio" name="category" id="pajama">
	              <label for="pajama" class="category_radio">파자마</label>
	            </div>
	            <div>
	              <input type="radio" name="category" id="sleep_product">
	              <label for="sleep_product" class="category_radio">수면용품</label>
	            </div>
	          </div>
	        </form>
	        <%-- 카테고리 토글부분 끝 --%>
	
	
	
	        <div class="filter_title pt-4 d-flex justify-content-between align-items-center border-bottom">
	          <div class="title_box d-flex flex-column">
	            <span id="main_title">BEST상품</span>
	            <span id="place_holder">BEST상품 필터</span>
	          </div>
	          <a type="button" class=btn_toggle_filter >
	            <i id="bestyn_icon_plus" class="plus_icon fa-solid fa-plus" style="color:black;"></i>
	            <i id="bestyn_icon_minus" class="minus_icon fa-solid fa-minus" style="color:black;"></i>
	          </a>
	        </div>
	
	        <%-- BEST상품 토글부분 --%>
	        <form id="bestyn_toggle" class="togglebox" action="">
	          <div class="d-flex flex-column mt-3">
	            <div>
	              <input type="checkbox" name="bestyn" id="bestyn">
	              <label for="bestyn" class="best_chkbox">베스트상품</label>
	            </div>
	          </div>
	        </form>
	        <%-- BEST상품 토글부분 끝 --%>
	
	
	
	        <div class="filter_title pt-4 d-flex justify-content-between align-items-center border-bottom">
	          <div class="title_box d-flex flex-column">
	            <span id="main_title">성별</span>
	            <span id="place_holder">성별 필터</span>
	          </div>
	          <a type="button" class="btn_toggle_filter">
	            <i id="gender_icon_plus" class="plus_icon fa-solid fa-plus" style="color:black;"></i>
	            <i id="gender_icon_minus" class="minus_icon fa-solid fa-minus" style="color:black;"></i>
	          </a>
	        </div>
	
	        <%-- 성별 토글부분 --%>
	        <form id="gender_toggle" class="togglebox" action="">
	          <div class="d-flex flex-column mt-3">
	            <div>
	              <input type="radio" name="gender" id="man">
	              <label for="man" class="gender_radio">남</label>
	            </div>
	            <div>
	              <input type="radio" name="gender" id="women">
	              <label for="women" class="gender_radio">여</label>
	            </div>
	            <div>
	              <input type="radio" name="gender" id="no_gender">
	              <label for="no_gender" class="gender_radio">없음</label>
	            </div>
	          </div>
	        </form>
	        <%-- 성별 토글부분 끝 --%>
	
	
	
	        <div class="filter_title pt-4 d-flex justify-content-between align-items-center border-bottom">
	          <div class="title_box d-flex flex-column">
	            <span id="main_title">가격</span>
	            <span id="place_holder">모든 가격</span>
	          </div>
	          <a type="button" class="btn_toggle_filter">
	            <i id="price_icon_plus" class="plus_icon fa-solid fa-plus" style="color:black;"></i>
	            <i id="price_icon_minus" class="minus_icon fa-solid fa-minus" style="color:black;"></i>
	          </a>
	        </div>
	
	
	        <%-- 가격 토글부분 --%>
	        <form id="price_toggle"class="togglebox">
	          <div class="d-flex flex-column mt-3">
	            <div>
	              <p id="price_search_info" style="font-size:13px;"><i class="fa-solid fa-circle-info mr-1"></i>  가격대를 입력해주세요<br><span style="font-size:8px; color:darkgray">※ 전체가격대 검색시<br>&nbsp&nbsp&nbsp칸을 비워주세요</span></p>
	            </div>
	            <div>
	              <input type="text" name="start_price" id="start_price" placeholder="최저가격(숫자만입력)">
	              <label for="start_price" class="price_input"></label>
	            </div>
	
	            <div>
	              <input type="text" name="end_price" id="end_price" placeholder="최고가격(숫자만입력)">
	              <label for="end_price" class="price_input"></label>
	            </div>
	            <div class="d-flex">
	              <button type="button" id="btn_price_search" class="price btn border rounded">검색</button>
	              <button type="button" id="btn_price_clear" class="price btn border rounded ml-3">지우기</button>
	            </div>
	          </div>
	        </form>
	        <%-- 가격 토글부분 끝 --%>
	
	
	      </div>
	      <%-- Main > 사이드필터 끝 --%>
	
	
	
	      <%-- Main > productList 시작 --%>
	      <div class="productList pl-md-4 my-4">
	        <%-- 정렬옵션 --%>
	        <div class="sort_option d-flex justify-content-between align-center">
	          <button type="button" class="btn btn-white border rounded" id="btn_clear_filter">필터삭제</button>
	          <div class="text-right">
	            <label id="sort_option_label" class="mr-1">정렬옵션</label>
	            <select name="sort_option" id="sort_option" class="border rounded">
	              <option>전체</option>
	              <option>인기순</option>
	              <option>최신순</option>
	              <option>최저가순</option>
	            </select>
	          </div>
	          <%-- select 쓰지말고 버튼으로 한다음에 모달로 할지 고민중임!!!!!!!!!!!!! --%>
	        </div>
	        <%-- 정렬옵션 끝 --%>
	
	        <%-- item box 시작 --%>
	        <div class="item_box row mt-4 pb-5">
	
	
	
	
	          <%-- 상품 셀렉트 해와서 반복문 돌릴 곳 --%>
	
			  
	          <%-- 관리자로 로그인한 경우 상품등록버튼 --%>
	          <c:if test="${sessionScope.userid == 'admin'}">
	            <div class="item col-6 col-lg-3 d-flex flex-column px-3 py-3 px-lg-4" onclick="location.href='<%=ctxPath %>/admin/productRegister.dream'" style="cursor:pointer;">
	          	  상품등록하기 추후 css로 꾸밀 예정상품등록하기 추후 css로 꾸밀 예정상품등록하기 추후 css로 꾸밀 예정상품등록하기 추후 css로 꾸밀 예정상품등록하기 추후 css로 꾸밀 예정상품등록하기 추후 css로 꾸밀 예정
	            </div>
	          </c:if>
	          
	          
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
	          
              <%-- 게시물 끝 --%>
	
	        </div>
	        <%-- item box 끝--%>
	      </div>
	      <%-- Main > productList 끝 --%>
	    </div>
	    <%-- Main 끝 --%>
	    
	    <%----------------------------------------------------------- 페이지 바 시작 ---------------------------------------------%>
	    <c:if test="${empty requestScope.keyword}">
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
  		</c:if>
          
		<%----------------------------------------------------------- 페이지 바 끝 ---------------------------------------------%>
		
		
		<%----------------------------------------------------------- 키워드 있을시 페이지 바 시작 ---------------------------------------------%>
	    <c:if test="${not empty requestScope.keyword}">
	    <nav aria-label="...">
		    <ul class="my pagination pagination-md justify-content-center">
		    	<%-- 첫페이지로 이동버튼 --%>
		    	<c:if test="${requestScope.page > requestScope.display_page}">
		    	<li class="page-item">
			      <a type="button" class="page-link" onclick="goPage_keyword('1');">
			      	<i class="fa-solid fa-angles-left"></i>
			      </a>
			    </li>
			    
			    
			    <%-- 전페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a type="button" class="page-link" onclick="goPage_keyword('${requestScope.startPage-1}');">
			      	<i class="fa-solid fa-angle-left"></i>
			      </a>
			    </li>
			    </c:if>
			    
			    <%-- 페이지번호 시작--%>
			    <c:forEach begin="${requestScope.startPage-1}" end="${requestScope.endPage-1}" varStatus="i">
                <c:if test="${requestScope.page == (requestScope.startPage+i.count-1)}">
                <li class="page-item active" aria-current="page">
			    	<a type="button" class="page-link" onclick="goPage_keyword('${requestScope.startPage+i.count-1}')">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                
                <c:if test="${requestScope.page != (requestScope.startPage+i.count-1)}">
                <li class="page-item">
			    	<a type="button" class="page-link" onclick="goPage_keyword('${requestScope.startPage+i.count-1}')">${requestScope.startPage+i.count-1}</a>
			    </li>
                </c:if>
                </c:forEach>
                <%-- 페이지번호 끝 --%>
                
                
                
			    <%-- 다음페이지로 이동버튼 --%>
			    <c:if test="${!(requestScope.last_display_page)}">
			    <li class="page-item">
			      <a type="button" class="page-link" onclick="goPage_keyword('${requestScope.startPage+requestScope.display_page}')"><i class="fa-solid fa-angle-right"></i></a>
			    </li>
			    <%-- 맨 끝페이지로 이동버튼 --%>
			    <li class="page-item">
			      <a type="button" class="page-link" onclick="goPage_keyword('${requestScope.totalPage}')"><i class="fas fa-solid fa-angles-right"></i></a>
			    </li>
			    </c:if>
		  	</ul>
		</nav>
  		</c:if>
          
		<%----------------------------------------------------------- 키워드 있을시 페이지 바 끝 ---------------------------------------------%>
		
		
    </div>
    <%-- container 끝 --%>
  
<%--footer 호출 --%>
<jsp:include page="/WEB-INF/view/footer.jsp" />