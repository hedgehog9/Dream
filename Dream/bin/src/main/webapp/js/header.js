$("document").ready(function(){
	  $("a#btn_scroll_top").hide();
      // 변수선언
      const menu = $("div#slide_menu");
      const mask = $("div#mask");
      const menu_open = $("li#menu_open");
      const menu_close = $("div#menu_close");
      
      // 메뉴열기버튼 클릭시
      menu_open.click(function(){
        mask.fadeIn(300);
        menu.animate({width:"toggle"},300);
      });
      
      // 메뉴닫기버튼 클릭시
      menu_close.click(function(){
        mask.fadeOut(300);
        menu.animate({width:"toggle"},300);
      });
      
      // 사이드바가 열려있을 때 , 어두운부분을 클릭시
      mask.click(function(){
        mask.fadeOut(300);
        menu.animate({width:"toggle"},300);
      });


      // 스크롤되었을 때 보더바텀을 주고, 다시 맨위로갔을때 보더바텀을 없애기
      window.addEventListener('scroll', function(){
        let scrollTop = $(window).scrollTop();
        if (scrollTop == 0) { //스크롤이 맨 위일때
          $("div.header").css("border","");
          $("a#btn_scroll_top").fadeOut();
        }
        else if (scrollTop > 200){
			$("a#btn_scroll_top").fadeIn();
		} 
        else {  //스크롤을 한다면
          $("div.header").css("border-bottom","solid 1px #DEE2E6");
        }
      });// end of window.addEventListener('scroll', function(){}----
      
      
      //스크롤을 맨위로 올리는 버튼을 클릭한다면 스크롤을 맨위로 올려주는 함수
      $("a#btn_scroll_top").click(function(){
		$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
		return false;
	  });
      
      
  }); // end of $("document").ready(function(){}----
    

    // Function Declaration---
    
    // location 객체를 이용하여 ctxPath를 리턴해주는 함수
    function getContextPath(){
		let hostIndex = location.href.indexOf(location.host) + location.host.length;
		let ctxPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex + 1));
		return ctxPath;
	}
	
	//관심상품 클릭시
    function go_cart(){
      alert("go 관심상품페이지");
      //로그인중이라면 보내고
      //아니면 
      location.href =""; // getContextPath()+"" 관심상품페이지 URL
    } // end of function go_cart()---

    function go_mypage(){
      alert("go 마이페이지");
      location.href =""; //마이페이지 URL
    } // end of function go_mypage()---

    function go_login(){
      alert("go 로그인페이지");
      location.href =""; //로그인페이지 URL
    } // end of function go_login()---

    function logout(){
      alert("go 로그아웃");
      location.href =""; //로그아웃 하고나서 메인페이지 URL
    } // end of function go_login()---
    
