<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>DREAM | 편안한 수면을 위한 FLEX</title>
  <%-- Bootstrap CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  <%-- Font Awesome 5 Icons --%>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
  <%-- title Icon --%>
  <link href="<%= ctxPath%>/images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/admin/ad_header.css" />
  <%-- Optional JavaScript --%>
  <script type="text/javascript" src="<%= ctxPath%>/jquery3.6.0/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>

  <%-- JqueryUI JS,CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
  <script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js" ></script> 
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/admin/ad_header.js" ></script>
</head>
<body>
    <%-- header시작 --%>
    <div class="header">
      <%-- aside --%>
      <div id="mask"></div>
      <div id="slide_menu">
        <div id="menu_close"><i type="button" id="btn_menu_close" class="fas fa-light fa-x"></i></div>
        <ul id="menu_list">
          <li class="border-bottom"><a href="<%=ctxPath %>/product/shop.dream">SHOP</a></li>
          <li class="border-bottom"><a href="<%=ctxPath %>/notice/notice.dream">고객센터</a></li>
          <li class="border-bottom"><a href="<%=ctxPath %>/admin/shippingMangement.dream">배송관리</a></li>
          <li class="border-bottom"><a href="<%=ctxPath %>/notice/notice.dream">공지사항</a></li>
          <li class="border-bottom"><a href="<%=ctxPath %>/login/logout.dream">로그아웃</a></li>
        </ul>
      </div>
  
      <div class="header_top border-bottom">
        <ul class="header_top_list list-unstyled d-flex justify-content-between">
          <li class="header_top_item" type="button" onclick="location.href='<%=ctxPath %>/notice/notice.dream'">고객센터</li>
          <li class="header_top_item" type="button" onclick="location.href='<%=ctxPath %>/admin/ad_member/memberManage.dream'">회원관리</li>
          <li class="header_top_item" type="button" onclick="location.href='<%=ctxPath %>/admin/shippingMangement.dream'">배송관리</li>
          <li class="header_top_item" type="button" onclick="location.href='<%=ctxPath %>/login/logout.dream'">로그아웃</li>
        </ul>
      </div>
      <div class="header_main m-auto d-flex justify-content-between align-items-center">
        <div id="img_logo">
          <a href="<%= ctxPath%>/index.dream"><img src="<%= ctxPath%>/images/logo.png"/></a>
        </div>
        <ul class="header_main_list list-unstyled">
          <li class="header_main_list" type="button" onclick="location.href='<%=ctxPath %>/product/shop.dream'">SHOP</li>
          <li class="header_main_list" type="button" onclick="location.href='<%=ctxPath %>/about.dream'">ABOUT</li>
          <li class="header_main_list bg-white" type="button" onclick="location.href='<%=ctxPath %>/product/searchProduct.dream'"><i class="fas fa-xl fa-thin fa-magnifying-glass"></i></li>
        </ul>
        <%-- 768px 이하일때 나타날 아이콘 --%>
        <ul class="hidden_main_list list-unstyled">
          <li class="hidden_main_list bg-white" type="button" onclick="location.href='<%=ctxPath %>/product/searchProduct.dream'"><i class="fas fa-lg fa-thin fa-magnifying-glass"></i></li>
          <li id="menu_open" class="hidden_main_list bg-white" type="button"><i class="fas fa-lg fa-bars"></i></li>
        </ul>
      </div>
    </div>
    <%-- header끝 --%>
    <div id="header_box">
	</div>
	
	
	<%-- 물결 --%>
  		<a type="button" id="btn_scroll_top">
            <div class="wrap">
              <div class="circle">
                <!-- wave -->
                <div class="wave-one"></div>
                <div class="wave-two"></div>
                <div class="wave-three"></div>
                <div class="wave-four"></div>
                
                <!-- moon -->
                <i class="fas fa-moon"></i>
                <i class="fas fa-moon blur"></i>
                
                <!-- star -->
                <div class="star">
                  <i class="fas fa-asterisk star1"></i>
                  <i class="fas fa-asterisk star2"></i>
                  <i class="fas fa-asterisk star3"></i>
                  <i class="fas fa-asterisk star4"></i>
                  <i class="fas fa-asterisk star5"></i>
                </div>
              </div>
            </div>   
  		</a>