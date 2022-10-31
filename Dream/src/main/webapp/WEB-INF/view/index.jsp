<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
	//
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.userid != 'admin'}">
<jsp:include page="/WEB-INF/view/header.jsp" />
</c:if>
<c:if test="${sessionScope.userid == 'admin'}">
<jsp:include page="/WEB-INF/view/admin/ad_header.jsp" />
</c:if>


<script type="text/javascript">

	$(document).ready(function() {
		
		displayDiscount("1");
		
		$("button#more_btn").click(function() { 
			
			if($(this).text() == "처음으로") {
				$("div#discountDisplay").empty();
				displayDiscount("1");
				$(this).text("더보기");
			}
			else {
				displayDiscount($(this).val());
			}
			
			
			
		});
		
	});// end of $(document).ready(function() {})----------------------------
	
	// Function Declation
	let lenDiscount = 4;
	// 보여줄 상품의 개수
	
	function displayDiscount(start) { // 할인상품들만 뽑아와서 보여주는 AJAX
		
		$.ajax({
			url:"<%= request.getContextPath() %>/product/discountDisplay.dream",
		//	type:"GET", default 값이 GET 방식
			data:{"start":start,  
				  "len":lenDiscount},  
			dataType:"JSON",
			success: function(json) {
				
				let html = ``;
				
				if( start == "1" && json.length == 0) {
					html = "<span>할인중인 상품이 없습니다.</span>";
				}
				
				else if(json.length > 0) {
				
					$.each(json, function(index, item) {
						html += "<div class='item col-6 col-md-3 d-flex flex-column px-3 py-3 px-lg-4'>"+
						            "<a id='"+item.product_num+"' class='product' href='<%=ctxPath %>/product/detail.dream?num="+item.product_num+"'>"+
						              "<div class='product'>"+
						                "<div class='product_imgbox border'>"+
						                  "<img id='product_img' src='<%= ctxPath %>/images/제품이미지/"+item.product_image+"'>"+
						                "</div>"+
						                "<div id='product_simple_explain' class='pl-2'>"+
						                  <%-- 상품 카테고리 넣을 곳 --%>
						                  "<div id='product_division' class='mt-2'>"+item.category+"</div>"+
						                  <%-- 상품이름 넣을 곳 --%>
						                  "<div id='product_name' class='my-2'>"+item.product_name+"</div>"+
						                  <%-- 상품가격 넣을 곳 --%>
						                  "<div id='product_price' class='d-flex justify-content-between'>"+
						                    <%-- if문!!상품자체할인가격이 있다면 아래태그,할인된가격--%>
						                    "<span id='product_price_discount' style='text-decoration: line-through;'>&#8361;"+item.price.toLocaleString('en')+"원</span>"+
						                    <%-- 상품자체할인 가격 있으면 아래 태그 넣기 태그라이브러리 들어갈 곳 --%>
						                    "<div id='discount_mark' class='d-flex align-items-center'>"+
						                      <%-- 할인율 넣을 곳 --%>
						                      "<span id='discount_percent'>"+item.discount_rate*100+"%</span>"+
						                    "</div>"+ 
						                  "</div>"+
						                  <%-- 상품자체할인 가격 있으면 아래 태그 넣기 태그라이브러리 들어갈 곳--%>
						                  "<div id='product_sale_price'>&#8361;"+item.discount_price.toLocaleString('en')+"<span>원</span></div>"+
						                "</div>"+
						              "</div>"+
						            "</a>"+
						            "<div id='sale_mark' class='border rounded text-center'>SALE</div>"+
						         "</div>";
								
								
						
								
					}); // end of $.each(json, function(index, item) {}) -----------------
					
					
					$("div#discountDisplay").append(html);
					
					$("button#more_btn").val( Number(start) + lenDiscount);
					
					$("input#countDiscount").val( Number($("input#countDiscount").val()) + json.length );
					
					//console.log($("input#countDiscount").val())
					//console.log($("input#totalDiscount").val())
					
					if( $("input#countDiscount").val() == $("input#totalDiscount").val()) {
						$("button#more_btn").text("처음으로");
						$("input#countDiscount").val(0);
					}
				}
				
				
				
				
			},
			error: function(request, status, error){
	            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	        }
		});						 
								 
		
	}// end of function displayHIT()------------------

</script>










<%--직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/index.css" />

  <%-- Main시작 --%>
  <div class="Main">
    <%-- Main > 캐러셀 시작 --%>
    <div id="carousel" class="carousel slide carousel-fade" data-ride="carousel">
      <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators2" data-slide-to="0" class="active"></li> <%-- active는 맨 처음에 보여줄 타겟을 정한다.--%>
          <li data-target="#carouselExampleIndicators2" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators2" data-slide-to="2"></li>
          <li data-target="#carouselExampleIndicators2" data-slide-to="3"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="<%= ctxPath%>/images/dream.jpg" class="d-block w-100">
          <div class="carousel-caption d-none d-md-block"></div>
        </div>
        <div class="carousel-item">
          <img src="<%= ctxPath%>/images/고양이꿀잠.jpg" class="d-block w-100">
          <div class="carousel-caption d-none d-md-block"></div>
        </div>
        <div class="carousel-item">
            <img src="<%= ctxPath%>/images/babysleep.png" class="d-block w-100">
            <div class="carousel-caption d-none d-md-block"></div>            
        </div>
        <div class="carousel-item">
            <img src="<%= ctxPath%>/images/womensleep.jpg" class="d-block w-100">
            <div class="carousel-caption d-none d-md-block"></div>            
        </div>
      </div>
      <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    <%-- Main > 캐러셀 끝 --%>
      
    
    <%-- Main > content 시작 --%>
    <div class="content mt-5">
      <div class="shortcut d-flex justify-content-between">
        <div class="shortcut_item" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=조명&detail_category=LED양초'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리LED양초.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">LED양초</p>
        </div>
        
        <div class="shortcut_item" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=파자마&detail_category=동물잠옷'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리동물잠옷.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">동물잠옷</p>
        </div>
        
        <div class="shortcut_item" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=침구류&detail_category=매트리스'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리매트리스.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">매트리스</p>
        </div>
        
        <div class="shortcut_item" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=조명&detail_category=무드등'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리무드등.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">무드등</p>
        </div>
        
        <div class="shortcut_item" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=침구류&detail_category=침대'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리침대.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">침대</p>
        </div>
      </div>
      <div class="shortcut d-flex justify-content-between">
      
        <div class="shortcut_item mb-2" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=침구류&detail_category=베개'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리베개.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">베개</p>
        </div>
        
        <div class="shortcut_item mb-2" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=파자마'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리커플파자마.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">파자마</p>
        </div>
        
        <div class="shortcut_item mb-2" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=침구류&detail_category=이불'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리이불.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">이불</p>
        </div>
        
        <div class="shortcut_item mb-2" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=수면용품&detail_category=필로우미스트'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리필로우미스트.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">필로우미스트</p>
        </div>
        
        
        <div class="shortcut_item mb-2" onclick="location.href='<%=ctxPath %>/product/shop.dream?category=수면용품&detail_category=안대'">
          <div class="shortcut_img">
            <img src="<%= ctxPath%>/images/디테일카테고리/카테고리안대.PNG" alt="">
          </div>
          <p class="category_text text-center" style="font-weight:bold;">안대</p>
        </div>
      </div>




      <%-- 카드리스트 시작 --%>
      <div class="productList">
        <div id="card_title" class="font-weight-bold">Discount</div>
        <p id="Released_product">할인상품</p>
        <%-- item_box 시작 --%>
        <div class="item_box row mt-4" id="discountDisplay">



        </div>
        <%-- item_box 끝 --%>
      </div>
      <%-- productList 끝 --%>
      <%-- 더보기버튼 시작 --%>
      <div id="more_btn" class="mt-4">
        <button id="more_btn" type="button" class="border rounded" value="">더보기</button>
        <input type="hidden" id="totalDiscount" value="${requestScope.totalDiscount}" />
        <input type="hidden" id="countDiscount" value="" />
      </div>
      <%-- 더보기버튼 끝 --%>
    </div>
    <%-- Main > content 끝 --%>
  </div>
  <%-- Main끝 --%>
  
  

  <%-- footer 호출 --%>
  <jsp:include page="/WEB-INF/view/footer.jsp" />
  
  
  
  
      
  
  
