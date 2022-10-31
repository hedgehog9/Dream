// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}
//let time=180;
let time=180;
let email_time=180;

let email_setTimer="";
let setTimer = "";

let cnt_mobile_check = 0;
let cnt_email_check = 0;
$(document).ready(function(){
	  
      $("button#btn_join").attr("disabled",true);
      $("button#btn_join").css("background","#EBEBEB");
      $("button#btn_mobile_check").css("background-color","#EBEBEB");
      
      
      $("input#mobile").val("010");	//mobile 칸에 010채워두기
	  $("button#btn_mobile_check").attr("disabled",true);	//핸드폰인증버튼 비활성화
	  $("button#btn_mobile_check").css("color","white");	//핸드폰인증버튼 비활성화
	  
	  
	  
      // 변수 선언
      const input_userid = $("input:text[name='userid']");
      const input_passwd = $("input:password[name='passwd']");
      const input_passwd_check = $("input:password[name='passwd_check']");
      
      let emailConfirmCode;
      let id_ok = false;
      let passwd_ok = false;
      let passwd_check_ok = false;
      let mobile_ok = false;
      let mobileConfirmCode = 0;
      
      
      
	  
      input_userid.focus();	//userid 칸에 자동 focus
	  

	  // 숫자/문자/특수문자 포함 형태의 8 ~ 15자리 이내의 암호 정규 표현식
      // const regExp = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);

      //아이디 인풋박스에 값을 입력시
      input_userid.keyup(function(){
		let userid = input_userid.val().trim(); //사용자가 입력한 유저아이디 값
		
		if(userid==""){
		  $("p#userid_error").css("display","block");	//공백에러메시지 나타내기
		  $("p#userid_error").html("아이디는 필수정보 입니다.");
		  input_userid.css("border-bottom","solid 1px red");  //빨간색 밑줄
		  $("label[for='userid']").css("color","red");  //라벨 빨간색
		  id_ok = false;
		  agree_check();
		  return;
		}
		else{	//사용자가 입력한 유저아이디 값이 공백이 없을경우
		  $("p#userid_error").css("display","none");  //공백에러메시지 감추기
		  input_userid.css("border-bottom",""); //빨간색 밑줄 감추기
		  $("label[for='userid']").css("color",""); // 라벨빨간색 감추기
	      check_userid(userid);	//아이디 형식,중복여부검사함수 호출
		}
		
		
		
      });

      //비밀번호 인풋박스에 값을 입력시
      input_passwd.keyup(function(){
		let passwd = input_passwd.val().trim();  
        check_passwd(passwd);
      });
      //비밀번호확인 인풋박스에 값을 입력시
      input_passwd_check.keyup(function(){  
        passwd_check();
      });//end of input_passwd_check.keydown(function(){})-----
      
      
      //약관 체크박스
      $("input:checkbox[name='agree_age']").change(function(){
		agree_check();	//회원가입 준비 완료인지 아닌지 검사하는 함수 호출
      });
      
      
      //이메일 인증번호 전송을 클릭했을때
      $("button#btn_send_email").click(function(){
	
		const userid = input_userid.val();
		
		$("input#emailConfirmCode").css("display","inline-block");
		$("button#emailConfirmComplete").attr("disabled",false);
        $("button#emailConfirmComplete").css("background-color","");
        $("button#emailConfirmComplete").css("color","");
        
		if($("button#btn_send_email").text() == '재전송'){
			toastr["info"]("이메일 인증코드를 재전송하였습니다.");
			clearInterval(email_setTimer);
			email_time = 180;
		}
		
		email_setTimer = setInterval(email_timer,1000);
	    $("span#send_guide").html(`입력하신 이메일 ${userid}로 <br> 인증번호를 전송하였습니다.`);
	    $("button#btn_send_email").text("재전송");
		$.ajax({ 
			url:getContextPath()+"/member/emailCheck.dream", 
			data:{"userid": userid},
			type:"post",
			dataType:"json",
			success:function(json){
				if(json.sendMailSuccess){	//이메일 전송에 성공했다면
				  emailConfirmCode = json.certificationCode;
				}
				else{	//이메일 전송에 실패했다면
					$("span#send_guide").html("입력하신 이메일로 전송을 실패했습니다.이메일을 다시한번 확인해주세요");
					$("button#emailConfirmComplete").attr("disabled",true);
			        $("button#emailConfirmComplete").css("background-color","#EBEBEB");
			        $("button#emailConfirmComplete").css("color","white");
				}
			},//end of success
			
			//success 대신 error가 발생하면 실행될 코드 
			error: function(request,status,error){
				toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		  });//end of $.ajax({})---
	  });
	  
	  
	  
	  //이메일 인증완료 버튼 클릭시
	  $("button#emailConfirmComplete").click(function(){
		if(emailConfirmCode == $("input:text[name='emailConfirmCode']").val()){ //입력한 코드와 인증코드가 같다면
		  clearInterval(email_setTimer);
		  email_time= 0;
		  const frm = document.joinFrm;
		  frm.action = "join.dream"; // 상대경로이므로 맨 뒤에만 바뀜
		  frm.method = "post"; // get 방식이라면 회원가입 창을 보여주고, post 방식이라면 DB에 데이터 전달
		  frm.submit();
		}
		else{
			toastr["error"]("이메일인증코드가 다릅니다!");	
		}
	  });
	  
	  //가입하기 버튼 클릭시
	  $("button#btn_join").click(function(){
		$("button#btn_send_email").text("인증번호 전송");
		$("span#send_guide").html("");
	  });
	  
	  
	  
      



      // Function Declaration
      
      //타이머 함수 만들기
      const timer = function timer(){
		if(time<0){ // 타임이 0보다 작게된다면
          clearInterval(setTimer);
          toastr["error"]("입력시간이 초과하였습니다. 핸드폰인증을 다시 진행해주세요");
          $("button#btn_mobile_check_complete").attr("disabled",true);
          $("button#btn_mobile_check_complete").css("background-color","#EBEBEB");
          $("button#btn_mobile_check_complete").css("color","white");
          mobile_ok = false;
          agree_check();
        }
        else{
          let minute = parseInt(time/60);
          minute = (minute+"").length<2? "0"+minute : minute; //삼항연산자로 분 자리맞춰주기
          let second = time%60;
          second = (second+"").length<2? "0"+second : second; //삼항연산자로 초 자리맞춰주기
          let text = `${minute} : ${second}`;
          $("div#div_timer").text(text);
          time--;
        }
      }// end of timer-----
      
      
      //타이머 함수 만들기
      const email_timer = function timer(){
		if(email_time<0){ // 타임이 0보다 작게된다면
          clearInterval(email_setTimer);
          toastr["error"]("입력시간이 초과하였습니다. 이메일인증을 다시 진행해주세요");
          $("button#emailConfirmComplete").attr("disabled",true);
          $("button#emailConfirmComplete").css("background-color","#EBEBEB");
          $("button#emailConfirmComplete").css("color","white");
          email_ok = false;
          agree_check();
        }
        else{
          let minute = parseInt(email_time/60);
          minute = (minute+"").length<2? "0"+minute : minute; //삼항연산자로 분 자리맞춰주기
          let second = email_time%60;
          second = (second+"").length<2? "0"+second : second; //삼항연산자로 초 자리맞춰주기
          let text = `${minute} : ${second}`;
          $("div#div_email_timer").text(text);
          email_time--;
        }
      }// end of timer-----

      
      //사용자가 입력한 아이디를 입력받아서 아이디 형식,체크여부를 검사해주는 함수
      function check_userid(userid){
		const regExp= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(!(regExp.test(userid)) ){ //아이디 형식에 맞지않게 입력한경우
		  $("input:text[name='userid']").css("border-bottom","solid 1px red");  //빨간색 밑줄
          $("label[for='userid']").css("color","red");  //라벨 빨간색
          $("p#userid_error").css("color","red");
          $("p#userid_error").css("display","block");  //에러문구
          $("p#userid_error").html("아이디는 이메일 형식에 맞게 입력해주세요");  //에러문구
          id_ok = false;
          agree_check();
          return;
		}
		else{	//아이디 형식에 맞게 입력한 경우 중복여부검사하기
		  $.ajax({ 
			url:getContextPath()+"/member/idDuplicateCheck.dream", 
			data:{"userid": userid},
			type:"post",
			dataType:"json",
			success:function(json){
			  if(json.isExists){	// 입력한 아이디가 이미 사용중이라면
			    $("input:text[name='userid']").css("border-bottom","solid 1px red");  //빨간색 밑줄
			    $("label[for='userid']").css("color","red"); //라벨 빨간색
			    $("p#userid_error").css("color","red");
			    $("p#userid_error").css("display","block");
			    $("p#userid_error").html("이미 사용하고 있는 아이디입니다.");
			    id_ok = false;
			    agree_check();
			  }
			  else{	//입력한 아이디가 사용중이 아니라면
				$("input:text[name='userid']").css("border-bottom","");  //빨간색 밑줄
			    $("label[for='userid']").css("color",""); //라벨 빨간색
			    $("p#userid_error").css("display","block");
			    $("p#userid_error").css("color","blue");
			    $("p#userid_error").html("사용할 수 있는 아이디 입니다.");
			    id_ok = true;
			    agree_check();
			  }
			},//end of success
			
			//success 대신 error가 발생하면 실행될 코드 
			error: function(request,status,error){
				toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		  });//end of $.ajax({})---
		}//end of else--
      }//end of function check_userid(userid){}---
      
  	  
  	  //사용자가 입력한 비밀번호를 입력받아서 비밀번호 형식을 검사해주는 함수
      function check_passwd(passwd){
		const regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 
		if(!(regExp.test(passwd))){	//유효성검사 통과못하면
	        input_passwd.css("border-bottom","solid 1px red");  //빨간색 밑줄
	        $("p#passwd_error").css("display","block");  //에러문구
	        $("label[for='passwd']").css("color","red");  //라벨 빨간색
	        passwd_ok = false;
	        agree_check();
        }
        else{				//유효성검사 
			input_passwd.css("border-bottom","");  //빨간색 밑줄 없애기
	        $("label[for='passwd']").css("color","");  //라벨 빨간색 없애기
	        $("p#passwd_error").css("display","none");  //에러문구 없애기
	        passwd_ok = true;
	        passwd_check();
	    }
	  }
	  
	  
      
      // 비밀번호체크란과 입력한 비밀번호가 같은지 검사하는 함수
      function passwd_check(){
		let passwd = input_passwd.val();  
		let passwd_check = input_passwd_check.val();
	    if(passwd !=passwd_check){
			input_passwd_check.css("border-bottom","solid 1px red");  //빨간색 밑줄 
	        $("label[for='passwd_check']").css("color","red");  //라벨 빨간색 
	        $("p#passwd_check_error").css("display","block");  //에러문구 나타내기
	        passwd_check_ok = false;
	        agree_check();
		}
		else{
			input_passwd_check.css("border-bottom","");  //빨간색 밑줄 없애기
	        $("label[for='passwd_check']").css("color","");  //라벨 빨간색 없애기
	        $("p#passwd_check_error").css("display","none");  //에러문구 없애기
	        passwd_check_ok = true;
	        agree_check();
		}
	  }
	  
	  
	  
	  //체크박스 체크했는지 알아내기
	  function agree_check(){
	  	if(id_ok  && passwd_ok && passwd_check_ok && mobile_ok){	//회원가입 입력 조건이 갖춰지면
		  const chkbox_agree_age = $("input:checkbox[class='agree_required']:checked").length
		  	if(chkbox_agree_age != 2){	//이용약관 여부를 검사하기
				$("button#join").attr("disabled",true);
      			$("button#btn_join").css("background","#EBEBEB");
				return;
			}
			else{	//회원가입 입력 조건이 갖춰지고, 이용약관 동의도 하였다면,
				$("button#btn_join").attr("disabled",false);
      			$("button#btn_join").css("background","");
				return;
			}
		}
		else{	//회원가입 입력 조건이 갖춰지지 않았다면
			$("button#btn_join").attr("disabled",true);
  			$("button#btn_join").css("background","#EBEBEB");
			return;
		}
	  }//end of agree_check
	  
	  
	  
	  
	  //mobile에 숫자만 입력 가능하게 만들기
	  $("input#mobile").keyup(function(){ $(this).val($(this).val().replace(/[^0-9]/gi,"") );  }); //숫자만
      
      //mobile에 숫자 11자가 채워지지 않으면 
      $("input#mobile").keyup(function(){
		if($("input#mobile").val().length != 11){	//11자리 이하일 시
		  $("input#mobile").css("border-bottom","solid 1px red");
		  $("label[for='mobile']").css("color","red");
		  $("p#mobile_ok").css("display","none");
		  $("p#mobile_duplicate").css("display","none");
		  $("p#mobile_error").css("display","block");
		  $("button#btn_mobile_check").css("color","white");
		  $("button#btn_mobile_check").css("background-color","#EBEBEB");
		  $("button#btn_mobile_check").attr("disabled",true);
		}
		else{	//11자리를 다 채웠을 시 ajax로 핸드폰번호 중복검사를 한다.
		  const mobile = $("input#mobile").val();
		  $.ajax({ 
			url:getContextPath()+"/member/mobileCheck.dream", 
			data:{"mobile": mobile},
			type:"post",
			dataType:"json",
			success:function(json){
			  if(json.isExists) { //입력한 핸드폰번호가 이미 있다면
			    $("input#mobile").css("border-bottom","solid 1px red");
			    $("p#mobile_error").css("display","none");
			    $("p#mobile_ok").css("display","none");
			    $("p#mobile_duplicate").css("display","block");  //에러문구
	        	$("label[for='mobile']").css("color","red");  	//라벨 빨간색
	        	$("button#btn_mobile_check").css("color","white");
		  		$("button#btn_mobile_check").css("background-color","#EBEBEB");
		  		$("button#btn_mobile_check").attr("disabled",true);
			  }
			  else{	//입력한 핸드폰번호가 사용할 수 있는 번호라면
			    $("input#mobile").css("border-bottom","");
			    $("label[for='mobile']").css("color","");  		//라벨 빨간색 지우기
				$("p#mobile_error").css("display","none");
			  	$("p#mobile_duplicate").css("display","none");
			  	$("p#mobile_ok").css("display","block");
			  	$("button#btn_mobile_check").css("background-color","");
				$("button#btn_mobile_check").css("color","");
				$("button#btn_mobile_check").attr("disabled",false);
			  }
			},//end of success
			//success 대신 error가 발생하면 실행될 코드 
			error: function(request,status,error){
				toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		  });//end of $.ajax({})---
		  
		}
      });
      
      
      
      
      //핸드폰인증하기 버튼 클릭시 인증번호를 전송해주기
      $("button#btn_mobile_check").click(function(){
	    $("div#mobileConfirm").show();
	    $("button#btn_mobile_check").text("재전송");
		const mobile = $("input#mobile").val();
		if(cnt_mobile_check>0){	
			clearInterval(setTimer);
			time = 180;
		}
		  $.ajax({ 
			url:getContextPath()+"/member/smsSend.dream", 
			data:{"mobile": mobile},
			type:"post",
			dataType:"json",
			success:function(json){
			  if(json.success_count == 1) { // 문자 전송에 성공하였다면
			    const input_mobile = $("input#mobile").val();
			    toastr["info"]("입력하신번호 "+input_mobile+" 로<br>인증번호를 전송하였습니다. <br>인증번호를 입력해주세요");
			    $("button#btn_mobile_check_complete").attr("disabled",false);
          		$("button#btn_mobile_check_complete").css("background-color","");
          		$("button#btn_mobile_check_complete").css("color","");
			    mobileConfirmCode = json.certificationCode;
			    setTimer = setInterval(timer,1000);
			    mobile_ok = false;
			  }
			  else{	//문자 전송에 실패했다면
				toastr["error"]("문자전송에 실패했습니다. 입력하신 전화번호를 확인해주세요");
				mobile_ok = false;
			  }
			},//end of success
			//success 대신 error가 발생하면 실행될 코드 
			error: function(request,status,error){
				toastr["error"]("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		  });//end of $.ajax({})---	
		  cnt_mobile_check++;
	  });
	  
	  
	  
	  
	  // 핸드폰인증완료 버튼 클릭시 이벤트
	  $("button#btn_mobile_check_complete").click(function(){
		const input_mobileConfirmCode = $("input#mobileConfirm").val();
		if(mobileConfirmCode!=input_mobileConfirmCode){	//사용자가 입력한 값과 인증번호가 다르다면
			toastr["error"]("인증실패! 인증번호를 다시 확인해주세요");
			mobile_ok = false;
			agree_check();
		}
		else{	//핸드폰인증번호를 같게 입력하였다면
			clearInterval(setTimer);
			toastr["success"]("인증성공");
			mobile_ok = true;
			agree_check();
		}
	  });
	  
	  // });// end of $("button#btnFind").click(function(e)------------------------
	  
	  
	  
		
		
	
    });//end of  $(document).ready(function(){}-----
    
    
    