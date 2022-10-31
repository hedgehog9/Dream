<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();
%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0 shrink-to-fit=no">
  <title>DREAM | 편안한 수면을 위한 FLEX</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.0-dist/css/bootstrap.min.css" > 
  
  <%-- title Icon --%>
  <link href="<%= ctxPath%>/images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  <!-- 직접 만든 CSS -->
  <link rel="stylesheet" href="<%= ctxPath%>/css/about.css">
  
  <!-- Optional JavaScript -->
  <script type="text/javascript" src="<%= ctxPath%>/jquery3.6.0/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>

  <!-- fullpage 라이브러리 -->
  <link href="<%= ctxPath%>/fullpagelib/jquery.fullPage.css" rel="stylesheet"> 
  <script type="text/javascript" src="<%= ctxPath%>/fullpagelib/jquery.fullPage.js"></script>

  <!-- 직접만든 javascript -->
  <script type="text/javascript" src="<%= ctxPath%>/js/about.js" ></script>
  
</head>
<body>
  <div class="header">
    <div class="header_main d-flex justify-content-between align-items-end">
      <div>
        <a href="<%= ctxPath%>/index.dream"><h1 id="logo">DREAM</h1></a>
      </div>
      <nav>
        <ul>
          <li data-menuanchor="section1" style="display:none;"><a href="#section1">드림</a></li>
          <li class="mt-2 btn" style="font-weight:bold;" data-menuanchor="section2"><a href="#section2">최현우</a></li>
          <li class="mt-2 btn" style="font-weight:bold;" data-menuanchor="section3"><a href="#section3">장진영</a></li>
          <li class="mt-2 btn" style="font-weight:bold;" data-menuanchor="section4"><a href="#section4">조상운</a></li>
          <li class="mt-2 btn" style="font-weight:bold;" data-menuanchor="section5"><a href="#section5">황광빈</a></li>
          <li class="mt-2 btn" style="font-weight:bold;" data-menuanchor="section6"><a href="#section6">김진석</a></li>
        </ul>
      </nav>
      <ul class="hidden_main_list list-unstyled mt-2">
        <li class="hidden_main_list bg-white" type="button" onclick="window.open('https://www.facebook.com/')"; target="_blank"><i class="fa-brands fa-facebook fa-2x"></i></li>
        <li class="hidden_main_list bg-white" type="button" onclick="window.open('https://www.instagram.com/')"; target="_blank"><i class="fa-brands fa-2x fa-instagram"></i></li>
      </ul>
    </div>
  </div>



  
  <main id="fullpage">
    <div class="section bg-white">
      <div class="slide">
        <h1 id="project_dream" class="text-center" style="font-weight:bold">Project Dream</h1>
      </div>
      <div class="slide">
        <h1 id="" class="text-center" style="font-weight:bold">그냥만들었어요</h1>
      </div>
      <div class="slide">
        <h1 class="text-center">슬라이드 세번째</h1>
      </div>
      <div class="slide">
        <h1 class="text-center">슬라이드 네번째</h1>
      </div>
    </div>
    <div class="section bg-dark">
      <h2 class="text-center">최현우</h2>
    </div>
    <div class="section bg-danger">
      <h2 class="text-center">장진영</h2>
    </div>
    <div class="section bg-info">
      <h2 class="text-center">조상운</h2>
    </div>
    <div class="section bg-primary">
      <h2 class="text-center">황광빈</h2>
    </div>
    <div class="section bg-success">
      <h2 class="text-center">김진석</h2>
    </div>
  </main>
</body>
</html>