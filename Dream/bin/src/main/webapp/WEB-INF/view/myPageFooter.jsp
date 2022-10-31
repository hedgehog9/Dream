<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>
<%-- Bootstrap CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.0-dist/css/bootstrap.min.css" >
<%-- Font Awesome 5 Icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<%-- Optional JavaScript --%>
<%--<script type="text/javascript" src="<%= ctxPath%>/jquery3.6.0/jquery-3.6.0.min.js"></script>--%>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>

<style>

a { text-decoration:none !important }
a:hover { text-decoration:none !important }

</style>

<script>
   $(document).ready(function(){
      /* 사이드바 script 시작 */
      /*
      $("div.category-section > ul > li").click(function(e){
         //  클릭할 경우 글자 css 변경
         $("div#myPageFooter > a").css({"font-weight":"","color":""});
         $(e.target).css({"font-weight":"700","color":"black"});
      });
      */
      /* 사이드바 script 끝  */
      
	  let url = window.document.location.href;
      url = url.substr(27);
      // console.log("확인용 : "+url.substr(27));
      
    switch (url) {
    
	case "/cart/cart.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#cart_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/buylist.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#buylist_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/myInfo.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#profile_info_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/point.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#point_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/membership.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#membership_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/member/address.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#address_page").css({"font-weight":"700","color":"black"});
		break;
		
	case "/cart/cart.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#cart_page").css({"font-weight":"700","color":"black"});
		break;
	
	case "/member/mypage.dream" :
		$("div#myPageFooter > a").css({"font-weight":"","color":""});
		$("a#my_page").css({"font-weight":"700","color":"black"});
		break;	

	default:
		break;
	
	} 
     
      

   });// end of $(document).ready(function()----------------------------------
</script>

	<div class="container">
       <%-- <div id="myPageFooter"> 시작  --%>
	   <div class="my-5" id="myPageFooter" style="width: 100%">
			<h5 class="category-title font-weight-bold mb-3">내 계정</h5>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/mypage.dream" class="text-muted" id="my_page">마이 페이지</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/buylist.dream" class="text-muted" id="buylist_page">구매 내역</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/cart/cart.dream" class="text-muted" id="cart_page">장바구니</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/myInfo.dream" class="text-muted" id="profile_info_page">프로필 정보</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/address.dream" class="text-muted" id="address_page">주소록</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/payInfo.dream" class="text-muted" id="payment_page">결제 정보</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/point.dream" class="text-muted" id="point_page">포인트</a></div>
			<div class="footerMenu my-1 pb-2 border-bottom"><a href="<%= ctxPath%>/member/membership.dream" class="text-muted" id="membership_page">멤버쉽 정보</a></div>
	   </div>   
	</div>




