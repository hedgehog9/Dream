function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function() {

	$("span.warning").css("display","none");
	// 이메일 주소 클릭시 
	/*$("input#input_userid").focus((e) => {
		$(e.target).css("outline", "none");
		$(e.target).attr("placeholder", " ");
	});
	// 이메일 주소 커서 상실시 
	$("input#input_userid").blur((e) => {
		$(e.target).css({ "border": "", "border-bottom": "" });
		$(e.target).attr("placeholder", "예)dream@dream.co.kr");
	});*/

	// 아이디 입력받을때마다 정규표현식을 통한 유효성 검사
	let flag_id = false;
	let flag_pwd = false;
	
	$("input#input_userid").keydown(function(e) {
		let userid = $("input#input_userid").val();
		const regExp = new RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
			
			
			
			if (userid!="admin" && !regExp.test(userid)) {
				
				$("input#input_userid").css("border-bottom", "solid 1px red");  //빨간색 밑줄
				$("span#email_warning").css("display","block");  //에러문구
				$("div#div_userid>label").css("color", "red");  //이메일주소 문구 빨간색
				flag_id = false;
			}
			else {
				flag_id = true;
				
				$("span#email_warning").css("display","none");   //에러문구
				$("div#div_userid>label").css("color", "");      //이메일주소 문구 빨간색
				$(e.target).css({ "border": "", "border-bottom": "" });
		

				$("input#input_userid").focus((e) => {
					
					$(e.target).css("outline", "none");
					$(e.target).attr("placeholder", " ");
				});
				// 이메일 주소 커서 상실시 
				$("input#input_userid").blur((e) => {
					
					$(e.target).css({ "border": "", "border-bottom": "" });
					$(e.target).attr("placeholder", "예)dream@dream.co.kr");
					
				});
			}
			
			if(flag_id && flag_pwd){
				$("button#login").css("background-color","black");
			}
			else{
				$("button#login").css("background-color","");
			}
	
		});


	// 비밀번호 클릭시 
	/*$("input#input_passwd").focus((e) => {
		$(e.target).css("outline", "none");
		$(e.target).attr("placeholder", " ");
	});
	// 비밀번호 커서 상실시 
	$("input#input_passwd").blur((e) => {
		$(e.target).css({ "border": "", "border-bottom": "" });
	});*/


	// 비밀번호 입력받을때마다 정규표현식을 통한 유효성 검사
	$("input#input_passwd").keyup(function(e) {
		let passwd = $("input#input_passwd").val();
		const regExp2 = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 


		if (!regExp2.test(passwd)) {
			$(e.target).css("border-bottom", "solid 1px red");  //빨간색 밑줄
			$("span#pwd_warning").css("display","block");       //에러문구
			$("div#div_passwd>label").css("color", "red");      //비밀번호 빨간색
			flag_pwd = false;
		}
		else {
			flag_pwd = true;
			
			$(e.target).css({ "border": "", "border-bottom": "" });
			$("span#pwd_warning").css("display","none"); 
			$("div#div_passwd>label").css("color", "");  

			

			$("input#input_passwd").focus((e) => {
				$(e.target).css("outline", "none");
				$(e.target).attr("placeholder", " ");
			});
			
			// 비밀번호 커서 상실시 
			$("input#input_passwd").blur((e) => {
				$(e.target).css({ "border": "", "border-bottom": "" });
			});
			
			
		}
		if(flag_id && flag_pwd){
				$("button#login").css("background-color","black");
		}else{
			$("button#login").css("background-color","");
		}

	});
	
	
	
	
	 $("input#input_passwd").bind("keydown", (e)=>{
		   if(e.keyCode == 13) { // 검색어에서 엔터를 치면 검색하러 가도록 한다.
			   goLogin();
		   }
	   });
	
	 $("input#input_userid").bind("keydown", (e)=>{
		   if(e.keyCode == 13) { // 검색어에서 엔터를 치면 검색하러 가도록 한다.
			   goLogin();
		   }
	   });
	

	/*$("button#login").click(function(e) {
		goLogin();
	});*/
	
	
	// 비밀번호 보이기 버튼 클릭시 //
	$("i#show_pwd").click(function(){
		
		$("i#show_pwd").toggleClass('fa-eye');
		$("i#show_pwd").toggleClass('fa-eye-slash');
		
		if($("i#show_pwd").hasClass('fa-eye')){
			$("input#input_passwd").attr('type','password');
		}
		else{
			$("input#input_passwd").attr('type','text');
		}
	});
	

});// end of $(document).ready(function(){}--------------------------------------------


// 로그인 처리 해주는 함수 
function goLogin() {
	//	alert("로그인 시도함");
	
	const input_userid = $("input#input_userid").val().trim();
	const input_passwd = $("input#input_passwd").val().trim();
	
//	console.log(input_userid);
//	console.log(input_passwd);
	
	// ajax 시작 
	
	$.ajax({
			 url:getContextPath()+"/login/login.dream",
			 data:{"userid":input_userid,
			 	   "passwd":input_passwd }, 
			 type:"POST",    
			 dataType:"JSON",
		//	 async:true,      //기본값 : 비동기 (async:true)
		
			 success:function(json){ 
				 
				/* console.log(json.isSecession);
				 console.log(json.isRestMember);
				 console.log(json.isRequirePwdChange);
				 console.log(json.isUserExists);*/
				 
				 if(!json.isUserExists) {
					// alert("아이디 또는 비밀번호가 틀립니다.");
					toastr["warning"]("아이디 또는 비밀번호가 틀립니다.");
					
					$("span#email_warning").css("display","none");   //에러문구
					$("div#div_userid>label").css("color", "");      //이메일주소 문구 빨간색
					$("input#input_passwd").css({ "border": "", "border-bottom": "" });
					
					$("input#input_userid").css({ "border": "", "border-bottom": "" });
					$("span#pwd_warning").css("display","none"); 
					$("div#div_passwd>label").css("color", "");  
					
					// $("input#input_userid").val("");
					// $("input#input_userid").focus();
					$("input#input_passwd").val("");
					$("input#input_passwd").focus();
					// => login 화면 또는 index 로 이동 
					// location.href=getContextPath()+"/login/login.dream";
				 }
				 
				 if(json.isSecession) {
					// alert("탈퇴한 회원입니다.");
					toastr["error"]("탈퇴한 회원입니다.");
					return false;
					// => login 화면 또는 index 로 이동 
				 }
				 
				 if(json.isRestMember) {
					// alert("휴면처리된 회원입니다.");
					toastr["error"]("휴면처리된 회원입니다.");
					// location.href=getContextPath()+"/login/login.dream";
					return false;
					// => login 화면 또는 index 로 이동 
				 }
				 
				 if(json.isRequirePwdChange){
					// alert("마지막 비밀번호 변경일로부터 3개월이 지났습니다.");
					toastr["warning"]("마지막 비밀번호 변경일로부터 3개월이 지났습니다.");
				 	// location.href=getContextPath()+"/index.dream";
				 }
				 
				 if(json.isMembershipGap){
					// alert("멤버십 기간이 만료되어 멤버십 해지되었습니다.");
					toastr["warning"]("멤버십 기간이 만료되어 멤버십 해지되었습니다.");
				}
				 
				 if(json.isUserExists){
					if(json.userid =="admin"){
						// alert("관리자로 로그인되었습니다");
						toastr["info"]("관리자로 로그인되었습니다");
					}
					if(!json.isFirstLogin && json.userid != "admin") {
						// alert("신규회원 전용 포인트 2000P가 지급되었습니다!");
						toastr["info"]("신규회원 전용 포인트 2000P가 지급되었습니다!");
				 	}
					
					// location.href=getContextPath()+"/login/login.dream";
					
					setTimeout("location.href='"+getContextPath()+json.goBackURL+"'",500);
					return;
				}
					//location.href=getContextPath()+"/login/login.dream";
				 
			 },
			 
			 error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
			 
		 });
	

	if (input_userid == "") {
		alert("아이디를 입력하세요!!");
	//	$("input#input_userid").val("");
		$("input#input_userid").focus();
		return; // goLogin() 함수 종료
	}

	if (input_passwd == "") {
		alert("암호를 입력하세요!!");
		$("input#input_passwd").val("");
		$("input#input_passwd").focus();
		return; // goLogin() 함수 종료
	}
	

	/*
		// 아이디 저장시 localStroage 에 저장
		if( $("input:checkbox[id='saveid']").prop("checked") ) {
			localStorage.setItem('saveid', $("input#loginUserid").val());	
		}
		else {
			localStorage.removeItem('saveid');
		}
	*/

/*	const joinFrm = document.joinFrm;
	joinFrm.action = getContextPath() + "/login/login.dream";
	joinFrm.method = "POST";
	joinFrm.submit();*/

}// end of function goLogin()-----------------
