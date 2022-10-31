<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   String ctxPath = request.getContextPath();


%>
 <%--
	session.setAttribute("member_mid", "5235268");
	response.sendRedirect("http://localhost:9090/Dream/index.dream");

 --%>
  <%--header 호출 --%>
  <jsp:include page="/WEB-INF/view/header.jsp" />
  <%-- 직접 만든 CSS --%>
  <link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/login.css" />
  <%-- 직접만든 javascript --%>
  <script type="text/javascript" src="<%= ctxPath%>/js/login.js" ></script>
  
  <%-- 카카오 로그인 api --%>
  <script src = "https://developers.kakao.com/sdk/js/kakao.min.js"></script>
  
  <%-- 네이버 로그인 api --%>
  <script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
  
  <script type='text/javascript'>
  $(document).ready(function(){
  
	Kakao.init('3a474fe7619a36d65f20a558d9a5d257');
	
	
	$("#kakao-login-btn").on("click", function(){
	    //1. 로그인 시도
	    Kakao.Auth.login({
	        success: function(authObj) {
	         
	          //2. 로그인 성공시, API 호출
	          Kakao.API.request({
	            url: '/v2/user/me',
	            success: function(res) {
	              console.log(res);
	              var id = res.id;
				  scope : 'account_email';
				alert('로그인성공');
				<%--     location.href="콜백주소"; --%>
	            location.href="<%=ctxPath%>/index.dream";
	              
	        }
	          })
	          console.log(authObj);
	          var token = authObj.access_token;
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	        
	}); //
  });

</script>
  
  

</head>
<body>
   <div id = "container"> 
      <img src="<%=ctxPath %>/images/logo.png" alt="...">
      <div class="div_login">
         <fieldset>
            <div id="div_userid">
               <label for="userid">이메일 주소</label>
               <input id="input_userid" name="userid" class ="userinfo" type="text" placeholder="예)dream@dream.co.kr" autocomplete="off" >
               <span id="email_warning" class="warning">이메일 주소를 정확히 입력해주세요.</span>
       
            </div>

            <div id="div_passwd">
               <label for="passwd">비밀번호</label>
               <input id="input_passwd" name="passwd" class ="userinfo" type="password" ><i id="show_pwd" class="fa-solid fa-eye"></i>
               <span id="pwd_warning" class="warning">영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)</span>
            </div>

            <button type="button" id="login" class="button" onclick="goLogin()">로그인</button>
            
            <ul class = "login_ul">
               <li><a class="login_a" href="<%= ctxPath%>/member/join.dream">이메일 가입</a></li>
               <li><a class="login_a" href="<%= ctxPath%>/login/findEmail.dream">이메일 찾기</a></li>
               <li><a class="login_a" href="<%= ctxPath%>/login/findPwd.dream">비밀번호 찾기</a></li>
            </ul>

            <button id="login_naver" class="button social"><i class="fa-sharp fa-solid fa-n"></i>네이버로 로그인</button>
            
            <button id="kakao-login-btn" class="button social"><i class="fa-solid fa-k fa-lg"></i>kakao로 로그인</button>
            
         </fieldset>
      </div>
   </div>
   
<%--footer 호출 --%>
  <jsp:include page="/WEB-INF/view/footer.jsp" />

