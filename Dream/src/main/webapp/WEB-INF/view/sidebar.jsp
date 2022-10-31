
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>

<!-- <style>

	a#mypagelink:link { color: red; text-decoration: none;} 
	a#mypagelink:visited { color: black; text-decoration: none;} 
	a#mypagelink:hover { color: blue; text-decoration: underline;}

</style> -->

<script>
   $(document).ready(function(){
      /* 사이드바 script 시작 */
      /*
      $("div.category-section > ul > li").click(function(e){
         //  클릭할 경우 글자 css 변경
         $("div.category-section > ul > li > a").css({"font-weight":"","color":""});
         $(e.target).css({"font-weight":"700","color":"black"});
      });
      */
      /* 사이드바 script 끝  */
      
	  let url = window.document.location.href;
      url = url.substr(27);
      // console.log("확인용 : "+url.substr(27));
      
    switch (url) {
    
	case "/cart/cart.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#cart_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/buylist.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#buylist_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/myInfo.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#profile_info_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/point.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#point_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/membership.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#membership_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/address.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#address_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/cart/cart.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#cart_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/payInfo.dream" :
		$("div.category-section > ul > li > a").css({"font-weight":"","color":""});
		$("a#payment_page").css({"font-weight":"700","color":"black"});
		break;

	default:
		break;
	
	} 
     
    $("a#mypagelink").hover(function () {
		$(this).css("color","black");
	});
      

   });// end of $(document).ready(function()----------------------------------

</script>


</head>
<body>
   
<!-- 내용물 시작 -->

   <!-- 사이드바 시작 -->
   <div id="sidebar" class="sidebar" style="width: 180px; justify-content:space-between; margin-top: 22px">
      <div>
         <h4 class="mb-4" style="font-weight:bold; padding-left: 0"><a id="mypagelink" href="<%= ctxPath%>/member/mypage.dream" style="text-decoration: none;">마이페이지</a></h4>
            
      </div>    

      <div class="category-section mb-5">
         <h5 class="category-title font-weight-bold">쇼핑 정보</h5>
         <ul class="nav flex-column">
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/member/buylist.dream" id="buylist_page">구매내역</a>
            </li>
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/cart/cart.dream" id="cart_page">장바구니</a>
            </li>
         </ul>
      </div>

      <div class="category-section">
         <h5 class="category-title font-weight-bold">내 정보</h5>
         <ul class="nav flex-column">
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/member/myInfo.dream" id="profile_info_page">프로필 정보</a>
            </li>
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/member/address.dream" id="address_page">주소록</a>
            </li>
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/member/payInfo.dream" id="payment_page">결제정보</a>
            </li>
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/member/point.dream" id="point_page">포인트</a>
            </li>
            <li class="nav-item">
                  <a class="nav-link pl-0 color_gray" href="<%= ctxPath%>/member/membership.dream" id="membership_page">멤버십 정보</a>
            </li>
         </ul>
      </div>
   </div>
   <!-- 사이드바 끝 -->
</body>
