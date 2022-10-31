function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function() {

	$("div#notfound_notice").hide();

	$("a.btn_find").removeAttr("href");

	// input 태그 클릭시 아래 테두리 진하게 
	$("input#mobile").click(function(e) {
		$(e.target).css("border-bottom", "solid 2px black");
	});

	// input 태그 클릭시 아래 테두리 원래대로 
	$("input#mobile").blur(function(e) {
		$(e.target).css("border-bottom", "solid 2px #ebebeb");
	});

	// input 태그 값 변경될때마다 정규표현식을 이용한 값 검사
	$("input#mobile").keyup(function(e) {
		$("a.btn_find").css({ "background-color": "", "color": "" });

		const mobile = $("input#mobile").val();

		const regExp = /^[0]{1}[1]{1}[0]{1}[0-9]{7,8}$/;
		const bool_mobile = regExp.test(mobile);

		if (bool_mobile) {
			$("a.btn_find").css({ "background-color": "black", "color": "white" });
			$("a.btn_find").attr("href", "#");
		}
		else {
			$("a.btn_find").css({ "background-color": "", "color": "" });
		}
	});
	
	$("a#btn_find").click(function(e){
			
			// alert("버튼 클릭됨.");
			
			$.ajax({
				 url:getContextPath()+"/login/findEmail.dream",
				 data:{"mobile":$("input#mobile").val()}, // data 는 /MyMVC/member/idDuplicateCheck.up 로 전송해야할 데이터를 말한다.
				 type:"post",     // type 을 생략하면 type:"get" 이다.
				 dataType:"json",
				 async:true,      // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
				                  // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다.  
				 success:function(json){ 
					 // dataType:"json" 을 생략하면 
					 // text 는 "{"isExists":true}" 또는 "{"isExists":false}" 되어지는 string 타입이다. 

					 // dataType:"json" 을 생략하지 않고 넣어주면 
					 // text 는 {"isExists":true} 또는 {"isExists":false} 되어지는 object 타입이다. 
					 
					 // const json = JSON.parse(text);
					 // JSON.parse(text); 은 JSON.parse("{"isExists":true}"); 또는 JSON.parse("{"isExists":false}"); 와 같은 것인데
					 // 그 결과물은 {"isExists":true} 또는 {"isExists":false} 와 같은 문자열을 자바스크립트 객체로 변환해주는 것이다. 
					 // 조심할 것은 text 는 반드시 JSON 형식으로 되어진 문자열이어야 한다. 
					 if(json.existMobile) {
						 sessionStorage.setItem('userid', json.userid);
						 location.href=getContextPath()+"/login/findEmailEnd.dream";
					 }
					 else {
						 $("div#notfound_notice").css("display:block");
						 alert("일치하는 회원정보가 없습니다.");
					 }
				 },
				 
				 error: function(request, status, error){
					 alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				 }
				 
			});// end of ajax--------------------------------------------------
			
			
		});// end of $("a#btn_find").click(function(e){---------------------
	
	
	

	// 사용자 정보가 일치하지 않을경우 호출하는 함수 
	// showNotification();

	// 경고창 보이기 
	

});// end of $(document).ready(function(){}---------------------

function showNotification(){
	$("div#notfound_notice").addClass('show');
		setTimeout(() => {
			notification.removeClass('show');
		}, 1000)
}