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
  <%-- Font Awesome 5 Icons --%>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
  <%-- title Icon --%>
  <link href="<%= ctxPath%>/images/titleicon.png" rel="shortcut icon" type="image/x-icon">
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" href="<%= ctxPath%>/css/about.css">
  
  <%-- Optional JavaScript --%>
  <script type="text/javascript" src="<%= ctxPath%>/jquery3.6.0/jquery-3.6.0.min.js"></script>
  <script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js" ></script>

  <%-- fullpage 라이브러리 --%>
  <link href="<%= ctxPath%>/fullpagelib/jquery.fullPage.css" rel="stylesheet"> 
  <script type="text/javascript" src="<%= ctxPath%>/fullpagelib/jquery.fullPage.js"></script>
  
  <%-- TypeIt 라이브러리 --%>
  <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
  
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/about.js" ></script>
  
  
</head>


<body>
  <div class="header d-flex justify-content-between align-items-center">
    <div>
      <a href="<%= ctxPath%>/index.dream"><h1 id="logo">DREAM</h1></a>
    </div>
    <ul class="sns_list list-unstyled d-flex">
      <li class="sns_list mr-3" style="cursor:pointer;" onclick="window.open('https://www.facebook.com/')" target="_blank"><i class="fa-brands fa-facebook fa-2x"></i></li>
      <li class="sns_list" style="cursor:pointer;" onclick="window.open('https://www.instagram.com/dream_kr_official/')" target="_blank"><i class="fa-brands fa-2x fa-instagram"></i></li>
    </ul>
  </div>



  
  <main id="fullpage">
  
    <%-- 시작 section1 --%>
    <div class="section">
      <h1 id="project_dream" class="text-center" style="font-weight:bold">Project Dream</h1>
    </div>
    <%-- 시작 section1 --%>
    
    
    
    <%-- 드림개발목적 section2 시작 --%>
    <div id="develop_reason_section" class="section">
      <h1 id="develop_reason" class="text-center" style="font-weight:bold">
        Dream은 왜 만들어졌나요?
      </h1>
    </div>
    <%-- 드림개발목적 section2 끝 --%>
    
    
    
    <%-- 드림개발목적 section3 시작 --%>
    <div id="develop_reason_section2" class="section">
    
      <div id="develop_reason_text" class="clipText w-75">
        <h1 style="font-weight:bold;">Why was Dream created?</h1>
        <p>팀원들과 공부한 내용인 MVC패턴을 이용하여 어떠한 쇼핑몰을 만들어 볼까 회의하다가 코로나에 관련된 쇼핑몰을 만들고 싶다는 의견이 나왔고</p>
        <p>코로나로 인해 활성화된 온라인 커머스 시장에서 어떤 서비스를 제공하면 좋을까 고민하던 도중</p>
        <p>현대인들에게 부족한 잠을 떠올리게 되었으며, 디자인은 Kream 이라는 사이트를 벤치마킹하여 만들기로 하였습니다.</p>
        <p>그리하여 수면의 질을 높일 수 있는 제품을 제공할 수 있는 수면용품 전문 쇼핑몰인 "Dream"이 탄생하였습니다.</p>
      </div>
    </div>
    <%-- 드림개발목적 section3 끝 --%>
    
    
    
    
    <%-- 드림개발자소개 section4 시작 --%>
    <div id="developer_introduce_section" class="section">
      <h1 id="developer_introduce" class="text-center" style="font-weight:bold">
        Dream 개발자 소개 
      </h1>
    </div>
    <%-- 드림개발자소개 section4 끝 --%>
    
    
    
    
    <%-------------------------------------- 최현우 section5 시작 ------------------------------------%>
    <div id="hyunwoo" class="section">
      <div id="hyunwoo_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="hyunwoo_profile_image" class="profile_image">
            <div class="profile_image_box">
              <img src="<%=ctxPath%>/images/about/최현우.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">최현우</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="hyunwoo_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
                <p style="font-weight:bold; font-style:italic">"세상에 Hello World를 처음 외치던 순간부터 세상을 바꾸는 개발자를 꿈꾸다"</p>
				<p>
				  상상하던 것을 현실로 구현하는 개발자라는 직업에 매력을 느껴 공부하고 있습니다.<br>
				  컴퓨터가 이해할 수 있는 코드보다, 사람이 보고 이해할수 있는 코드를 짜는 것에 중점을 두며 언제나 <br>
				  "사용자는 바보다" 라는 전제하에 UX 관점에서 제대로 된 서비스를 기획하고자 노력합니다.
				  한번 문제에 직면하면 해결할 때 까지 끝을보는성격, 원활한 커뮤니케이션으로 협업하며 저와 함께 개발해요!
				</p>
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="hyunwoo_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath%>/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/oracle.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="hyunwoo_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>010-7423-9713</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/hyunwoocastle</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="hyunwoo_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
            <p>
			팀원들과 첫 웹프로젝트를 진행하면서 여러가지 시행착오를 많이 겪었는데, 그중 첫번째로는<br>
			다들 처음으로 Github를 통해 협업을 진행하여서 충돌이 났을때 대처가 많이 미흡하여 해결하는데 시간이 많이 지체되었습니다.
			또한, 공부할때는 겪을 수 없었던 수많은 문제상황과 직면하였습니다. 예를들어 페이징처리를 먼저 한 뒤에, 상품목록을 가져오는 쿼리문을 작성하였는데
			페이징처리를 a태그에 직접 링크에 걸어 파라미터를 넘겨주는식으로 로직을 짜버리는바람에 다중필터조건을 걸 때 쿼리문을 전부 수정해야하는 등의 문제상황에 직면했지만
			다른 팀원이 저와 비슷한 문제상황에 직면하였다는것을 알게되었고, 팀원에게 도움을 받아 해결하였습니다.
			</p>
			<p>
			이번 프로젝트는 여러가지 문제상황에 직면하고, 그 상황들을 해결하면서 "문제해결능력"이 많이 향상되었던 프로젝트 였습니다.
			처음에 목표했던 쿠폰기능이라던지, 채팅기능, 관리자용 대시보드 등등 구현하지 못한부분도 많지만 배운내용을 좀더 확실하게 다지고, 팀장으로서 팀원들과의 소통으로 커뮤니케이션능력의 향상까지
			이루어낸 값진 "경험"이였습니다. 팀원분들 다들 고생하셨습니다!
			</p>
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 최현우 section5 끝 ------------------------------------%>
    
    
    
    
    
    
    
    <%-------------------------------------- 장진영 section6 시작 ------------------------------------%>
    <div id="jinyoung" class="section">
      <div id="jinyoung_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="jinyoung_profile_image" class="profile_image">
            <div class="profile_image_box">
              <img src="<%=ctxPath%>/images/about/장진영.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">장진영</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="jinyoung_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
                컴퓨터만 있다면 만들고 싶은걸 다 만들 수 있다는 점에 매력을 느껴 개발공부를 시작하게 되었습니다.<br>
				느리지만 꾸준히 성장하는 개발자가 되고싶습니다.
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="jinyoung_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath%>/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/oracle.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="jinyoung_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>010-8866-5389</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/hedgehog9</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="jinyoung_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
            미니 프로젝트때와는 다르게 뷰단부터 직접 만들어보니 
			HTML, CSS, JavaScript 에 익숙해졌고, 
			어떻게 해야 사용자가 더 편하게 사용할 수 있을까 고민하게 되었습니다. 
			이번 프로젝트에서는 깃을 처음 사용했는데 처음에는 충돌도 많이 나고, 오류도 많았지만
			적응을 하고나니 익숙해져서 편하게 작업할 수 있었습니다.
			프로젝트 진행하면서 한달이라는 시간이 너무 짧게 느껴졌고, 
			끝나갈때 쯤에는 조금만 더 할껄 하는 아쉬움을 많이 느꼈습니다.<br>
			프로젝트를 진행하면서 수업때는 몰랐던 개념들을 알게되었고,
			동기,비동기 통신방식과 MVC 모델을 명확하게 이해할 수 있어서 좋았고 성장하게된 계기가 된 것 같습니다.<br>
			마지막으로 한달동안 부족한 저를 이끌어준 다른 조원들에게 감사합니다. 
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 장진영 section6 끝 ------------------------------------%>
    
    
    
    
    
    
    
    
    
    <%-------------------------------------- 조상운 section7 시작 ------------------------------------%>
    <div id="josh" class="section">
      <div id="josh_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="josh_profile_image" class="profile_image">
            <div class="profile_image_box">
              <img src="<%=ctxPath%>/images/about/조상운.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">조상운</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="josh_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
                안녕하세요 이번 드림 프로젝트에 참여하게 된 조원 조상운입니다. 
				컴퓨터를 통하여 무에서 유를 창조할 수 있는 개발이라는 영역에 관심을 
				보이고 시작한 개발공부가 거의 5개월을 향해 가고 있습니다. 항상 개발능력 증진을 
				위해 열정을 보이고 새로운 기술스택 및 다양한 경험들을 즐겁게 받아들일 수 있는 
				개발자가 되고싶습니다.
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="josh_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath%>/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/oracle.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="josh_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>010-7269-6621</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/tkddns6621</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="josh_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content">
            이번 프로젝트를 통해 MVC 패턴의 기본적인 개념을 수업을 진행했을 때보다 직관적으로 
			이해할수 있었고 HTML, JAVASCRIPT, JAVA, ORCLE 등등 다양한 언어들이 하나의 프로세스를 
			위하여 유기적으로 로직을 처리해 나가는 과정이 매우 흥미로웠습니다. 
			미니 프로젝트와는 다르게 비즈니스 로직뿐 아니라 사용자에게 제공하는 User Interface 를 
			제작해보면서 유저의 관점으로 서비스를 바라보고 제작할 수 있는 특별한 경험이 매우 뜻깊다고 
			생각했습니다. 이러한 과정에서 시중에 나와있는 많은 서비스들이 결코 쉽게 만들어진다는게
			아니라는 생각을 하게되었고 이번 프로젝트 이후로 시장에 나와있는 서비스들을 사용하면서 
			프로그래밍적인 관점으로 바라보게 될거같습니다.
			마지막으로 이번 프로젝트를 같이 진행하면서 많은 도움을 주신 4조 조원분들께 감사드리며 
			고생많으셨다는 말씀 드리고 싶습니다. <br>
			감사합니다.
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 조상운 section7 끝 ------------------------------------%>
    
    
    
    
    
    
    
    
    
    
    <%-------------------------------------- 황광빈 section8 시작 ------------------------------------%>
    <div id="hgb" class="section">
      <div id="hgb_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="hgb_profile_image" class="profile_image">
            <div class="profile_image_box">
              <img src="<%=ctxPath%>/images/about/황광빈.jpg"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">황광빈</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="hgb_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
                <p style="font-weight:bold; font-style:italic">"고통이 없으면 얻는 것도 없다."</p>
				<p>
				어려움을 이겨냈던 경험들을 통해 항상 성장하는 개발자가 되기위해 달리고 있습니다.
				어릴적부터 컴퓨터를 너무 좋아했었기 때문에 사용자 입장에서 생각하고 서비스를 제공할수 있도록 노력하겠습니다.
				</p>
              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="hgb_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath%>/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/oracle.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="hgb_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span>010-9020-9305</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/GGWANGBIN</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="hgb_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
            미니 프로젝트를 거쳐 세미프로젝트를 진행해보니 마치 작은호숫가에서 물장난을 치다가 바다로 나온듯한 느낌이였습니다.<br>
			콘솔에서만 코드를 구현하던것을 넘어 웹으로 프로젝트를 진행해보니 평소 너무 당연스럽고 친숙하게 사용하던 인터넷창이
			어떤식으로 작동하고 구현되는지 알아가면서 많은 흥미를 느꼈습니다. 이번 프로젝트를 통해 웹개발에 대해 느끼던
			막연함이 많이 해소된듯하고 팀원들과 여러 매체를 이용한 협업을 통해 컴퓨터를 다루는 일이지만 왜 사람들과의 소통이
			중요한지에 대해 배우게된 좋은 경험이였습니다. <br>
			서로 이끌어주며 열심히 참여해주신 팀원분들께 정말 감사합니다 !

          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 황광빈 section8 끝 ------------------------------------%>
    
    
    
    
    
    
    
    
    
    
    <%-------------------------------------- 김진석 section9 시작 ------------------------------------%>
    <div id="kimjs" class="section">
      <div id="kimjs_container" class="container d-flex flex-column">
        <div class="introduce d-flex">
          <div id="kimjs_profile_image" class="profile_image">
            <div class="profile_image_box">
              <img src="<%=ctxPath%>/images/about/김진석.png"/>
            </div>
            <p class="text-center" style="font-size:18pt; font-weight:bold">김진석</p>
          </div>
          
          <div class="introduct_my_self ml-5">
            <div id="kimjs_about_me" class="about_me">
              <h4 style="font-weight:bold">ABOUT ME</h4>
              <%-- 자기소개 내용물 시작 --%>
              <div class="about_me_content pl-4">
                <p style="font-weight:bold; font-style:italic">"코딩은 말꼬투리 잡기를 즐기는 상대와 말싸움해서 이기는 과정이다"</p>
				<p>
				내가 완벽한 논리로 작성했다고 생각한 코드가 단번에 에러를 띄우면 이제는 오히려 재밌어졌습니다.<br>
				(대부분은 제 논리 문제이긴 하지만) 네까짓 게 감히? 라는 생각이 앞서고,
				구글링으로 답을 얻으면 내가 틀렸다는 게 억울해서 한참 들여다봅니다.
				제가 이길 때까지 싸우면 제가 이긴다는 생각으로 키보드를 두드립니다.
				</p>

              </div>
              <%-- 자기소개 내용물 끝 --%>
            </div>
            
            <div id="kimjs_skill" class="skill mt-2">
              <h4 style="font-weight:bold">SKILL</h4>
              <div class="skill_content">
                <%-- 기술 나열 --%>
                <ul class="skill d-flex">
                  <%-- Java icon --%>
                  <li><img src="<%=ctxPath%>/images/about/java.png"/></li>
                  <%-- JS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/js.png"/></li>
                  <%-- HTML5 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/html5.png"/></li>
                  <%-- CSS icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/css3.png"/></li>
                  <%-- Bootstrap4 icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/bootstrap.png"/></li>
                  <%-- Jquery icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jquery.png"/></li>
                  <%-- JSP icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/jsp-file.png"/></li>
                  <%-- Oracle icon --%>
                  <li class="ml-3"><img src="<%=ctxPath%>/images/about/oracle.png"/></li>
                </ul>
              </div>
            </div>
            
            <div id="kimjs_contact_me" class="contact_me mt-2">
              <h4 style="font-weight:bold">CONTACT ME</h4>
              <div class="contact_me_content">
                <%-- 전화번호,블로그,인스타,깃주소 등 --%>
                <ul class="contact_me">
                  <li>
                    <span class="mr-2"><i class="fa-solid fa-phone fa-lg"></i></span>
                    <span> 010-4446-3319</span>
                  </li>
                  <li>
                    <span class="mr-2"><i class="fa-brands fa-github fa-lg"></i></span>
                    <span>https://github.com/DGJSKIM</span>
                  </li>
                </ul>
              </div>
            </div>
            
            
            
          </div>
        </div>
        
        
        <div id="kimjs_project_review" class="project_review">
          <div class="project_review_header">
            <h1 class="project_review_title">Project Review</h1>
          </div>
          <%-- 프로젝트 후기 내용물 시작 --%>
          <div class="project_review_content mt-2">
            저는 팀 프로젝트를 할 때 의견을 제시하는 걸 즐기는 타입입니다.
			그렇기에 무언가를 바꾸거나 다른 사람과 겹치는 파트에서의 변경점을 설명할 때도
			근거를 바탕으로 왜 이렇게 진행했고 장단점은 어떤지 설명하려는 노력을 더욱 많이 하였습니다.
			
			팀원들이 모두 제가 의견을 내면 또 다른 의견을 제시해주어 더 나은 방향으로 발전시키거나
			제 의견을 같이 발전시켜주는 의견을 내주는 등 무조건적 공감보다는 디벨롭에 집중해주는 팀원들이라
			실력증진에 큰 도움이 되는 시간이었습니다. 팀원들에게 감사합니다.
			
			저번 프로젝트 때는 밤을 새우면서도 시간이 하루만 더 있었더라면 이라는 아쉬움이 있었지만
			이번 프로젝트를 끝내는 지금은 다른 공부를 더 해보고 싶다는 생각이 듭니다.
			java만 쓰던 1차 프로젝트와 달리 쓰는 언어가 많아지며 제가 직접 공부하고 확장할 부분도 많이 늘어난 만큼
			수업 이외의 공부로 더 배우고 싶다고 생각하며 프로젝트를 마무리합니다.
          </div>
          <%-- 프로젝트 후기 내용물 끝 --%>
        </div>
      </div>
    </div>
    <%-------------------------------------- 김진석 section9 끝 ------------------------------------%>
    
    
    
    
    
    
    
    <%-- 끝 section10 시작--%>
    <div id="end_section" class="section">
      <h1 id="section_end" class="text-center" style="font-weight:bold">
        끝까지 봐주신 여러분 다들 사랑합니..
      </h1>
    </div>
    <%-- 끝 section10 끝 --%>
    
    
    
  </main>
</body>
</html>