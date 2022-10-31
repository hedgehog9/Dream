  let time=180;
  let certificationCode;

$(document).ready(function () {

    let name_modify = "";
    let passwd_store_cnt = 0;
    let mobile_store_cnt = 0;
    $("input:hidden[name='passwd_store_cnt']").val(passwd_store_cnt);
    $("input:hidden[name='mobile_store_cnt']").val(mobile_store_cnt);
    

    $("#div_modifyName").hide();
  	//$("#div_modifyEmail").hide();
  	$("#div_modifyMobile").hide();
    $("#div_modifyPasswd").hide();
    //$("#email_certification").hide();
    //$("button#certification_email_btn").prop("disabled",true);
    $("#mobile_certification").hide();
    $("button#certification_mobile_btn").prop("disabled",true);
    $("button#myInfo_modify").prop("disabled",true);
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    $("#change_id_btn").click(function() { // 아이디 변경 버튼을 클릭했을 경우
       
        $("#change_id_btn").hide();
        $("#user_name").hide();
        $("#div_modifyName").show();

        $("input#modify_name").val('');
        $("input#modify_name").focus();
        $("button#name_store").prop("disabled",true);

        $("#modify_name").keyup(function() {
            
            name_modify = $("#modify_name").val();

            if(name_modify == '') {
                $("button#name_store").prop("disabled",true);
            }
            else {
                $("button#name_store").prop("disabled",false);
            }
        })
    }); // end of $("#change_id_btn").click(function (e) {}


    $(document).on("click","button#name_cancle",function() { // 이름 변경 취소 버튼 클릭이벤트 

        $("#div_modifyName").hide();
        $("#change_id_btn").show();
        $("#user_name").show();
        $("button#name_cancle").css("border-bottom","");  //빨간색 밑줄
        $("#input_name_error").hide();
        $("#new_name").css("color","black");  //라벨 빨간색
        
    });// end of $(document).on("click","button#name_cancle",function(e){}

    $(document).on("click","button#name_store",function() { // 이름 변경 저장 버튼 클릭이벤트 

        const modify_name = $("#modify_name").val();

        $("#div_modifyName").hide();
        $("#change_id_btn").show();
        $("#user_name").show();
        $("span#name").text(modify_name)
        $("input:hidden[name='username']").val(modify_name);
        toastr["success"]("변경사항을 적용하시려면 수정하기 버튼을 눌러주세요.","변경사항이 저장되었습니다.");
        

    }); // end of $(document).on("click","button#name_store",function(e){}

    $(document).on("keyup","input#modify_name",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

        const regExp = /^.{2,50}$/;

        const bool = regExp.test( $(e.target).val() );


        if(!bool) {
            $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
            $("#input_name_error").show();  //에러문구
            $("#new_name").css("color","red");  //라벨 빨간색 
            $("button#name_store").prop("disabled",true);
        }
        else {
            $(e.target).css("border-bottom","");  //빨간색 밑줄
            $("#input_name_error").hide();
            $("#new_name").css("color","black");  //라벨 빨간색 
            $("button#name_store").prop("disabled",false);
        }

    });// end of $(document).on("keydown","input#modify_name",function(e) {}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
    $("#change_email_btn").click(function (e) { // 이메일 변경 버튼을 클릭했을 경우
    
    	   resetEmail();
       
       $("#change_email_btn").hide();
       $("#user_email").hide();
       $("#div_modifyEmail").show();
       $("input#modify_email").prop("disabled",false);

       $("input#modify_email").val('');
       $("input#modify_email").focus();
       $("button#email_store").prop("disabled",true);



       $("#modify_email").keyup(function (e) {
           
           email_modify = $("#modify_email").val();

           if(email_modify == '') {
               $("button#email_store").prop("disabled",true);
           }
           else {
               $("button#email_store").prop("disabled",false);
           }
       })
   }); // end of $("#change_email_btn").click(function (e) {}


   $(document).on("click","button#email_cancle",function(e){ // 이메일 변경 취소 버튼 클릭이벤트 

       $("#div_modifyEmail").hide();
       $("#change_email_btn").show();
       $("#user_email").show();
       $("#email_certification").hide();
       $("input#modify_email").css("border-bottom","none");  //빨간색 밑줄
       $("#input_email_error").hide();
       $("#new_email").css("color","black");  //라벨 빨간색
       
   });// end of $(document).on("click","button#name_cancle",function(e){}




   $(document).on("keyup","input#modify_email",function(e) { // 이메일 유효성 처리
        
        const regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;

        const bool = regExp.test( $(e.target).val() );

        if(!bool) {
            $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
            $("#input_email_error").show();  //에러문구
            $("#new_email").css("color","red");  //라벨 빨간색 
            $("button#email_store").prop("disabled",true);
        }
        else {
            $(e.target).css("border-bottom","");  //빨간색 밑줄
            $("#input_email_error").hide();
            $("#new_email").css("color","black");  //라벨 빨간색 
            $("button#email_store").prop("disabled",false);
        }

    });// end of  $(document).on("keyup","input#modify_email",function(e) {}
    */
    
    
    
    
    
    
    
    
    $("#change_mobile_btn").click(function (e) { // 이메일 변경 버튼을 클릭했을 경우
    
    	   resetMobile();
       
       $("#change_mobile_btn").hide();
       $("#user_mobile").hide();
       $("#div_modifyMobile").show();
       $("input#modify_mobile").prop("disabled",false);

       $("input#modify_mobile").val('');
       $("input#modify_mobile").focus();
       $("button#mobile_store").prop("disabled",true);



       $("#modify_mobile").keyup(function (e) {
           
           mobile_modify = $("#modify_mobile").val();

           if(mobile_modify == '') {
               $("button#mobile_store").prop("disabled",true);
           }
           else {
               $("button#mobile_store").prop("disabled",false);
           }
       })
   }); // end of $("#change_mobile_btn").click(function (e) {}




   $(document).on("keyup","input#modify_mobile",function(e) { // 이메일 유효성 처리
        
        const regExp = /^(\d{2,3})(\d{3,4})(\d{4})$/;

        const bool = regExp.test( $(e.target).val() );

        if(!bool) {
            $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
            $("#input_mobile_error").show();  //에러문구
            $("#new_mobile").css("color","red");  //라벨 빨간색 
            $("button#mobile_store").prop("disabled",true);
        }
        else {
            $(e.target).css("border-bottom","");  //빨간색 밑줄
            $("#input_mobile_error").hide();
            $("#new_mobile").css("color","black");  //라벨 빨간색 
            $("button#mobile_store").prop("disabled",false);
        }

    });// end of  $(document).on("keyup","input#modify_mobile",function(e) {}
    
    
    

   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   $("#change_passwd_btn").click(function (e) { // 비밀번호 변경 버튼을 클릭했을 경우
       
       $("#change_passwd_btn").hide();
       $("#user_passwd").hide();
       $("#div_modifyPasswd").show();

       $("input#modify_passwd").val('');
       $("input#modify_passwd").focus();
       $("button#passwd_store").prop("disabled",true);

       $("#modify_email").keyup(function (e) {
           
           passwd_modify = $("#modify_passwd").val();

           if(passwd_modify == '') {
               $("button#passwd_store").prop("disabled",true);
           }
           else {
               $("button#passwd_store").prop("disabled",false);
           }
       })
   }); // end of $("#change_email_btn").click(function (e) {}


   $(document).on("click","button#passwd_cancle",function(e){ // 비밀번호 변경 취소 버튼 클릭이벤트 

       $("#div_modifyPasswd").hide();
       $("#change_passwd_btn").show();
       $("#user_passwd").show();

       $("input#modify_passwd").css("border-bottom","none");  //빨간색 밑줄
       $("#input_passwd_error").hide();
       $("#new_passwd").css("color","black");  //라벨 빨간색
       
   });// end of $(document).on("click","button#name_cancle",function(e){}

   $(document).on("click","button#passwd_store",function(){ // 비밀번호 변경 저장 버튼 클릭이벤트 

	   passwd_store_cnt++;
       const modify_passwd = $("#modify_passwd").val();

       $("#div_modifyPasswd").hide();
       $("#change_passwd_btn").show();
       $("#user_passwd").show();
       $("span#passwd").text(modify_passwd);
       $("input:hidden[name='passwd']").val(modify_passwd);
       $("input:hidden[name='passwd_store_cnt']").val(passwd_store_cnt);
       toastr["success"]("변경사항을 적용하시려면 수정하기 버튼을 눌러주세요.","변경사항이 저장되었습니다.");
       
       
   }); // end of $(document).on("click","button#name_store",function(e){}

   $(document).on("keyup","input#modify_passwd",function(e) { // 비밀번호 유효성 검사
        
        // 숫자/문자/특수문자 포함 형태의 8 ~ 15자리 이내의 암호 정규 표현식
        const regExp = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);

        const bool = regExp.test( $(e.target).val() );

        if(!bool) {
            $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
            $("#input_passwd_error").show();  //에러문구
            $("#new_passwd").css("color","red");  //라벨 빨간색 
            $("button#passwd_store").prop("disabled",true);
        }
        else {
            $(e.target).css("border-bottom","");  //빨간색 밑줄
            $("#input_passwd_error").hide();
            $("#new_passwd").css("color","black");  //라벨 빨간색 
            $("button#passwd_store").prop("disabled",false);
        }
  
    });// end of $(document).on("keydown","input#modify_name",function(e) {}
    


  const check_length = $("input:checkbox[name='ModalCheckbox']").length;
  const ModalCheckbox = $("input:checkbox[name='ModalCheckbox']");
  
  // 전체체크버튼 클릭
  $("input#check1All").click(function (e) {
    
    const bool = $(e.target).prop("checked");
    ModalCheckbox.prop("checked",bool);
    const checked_length = $("input:checkbox[name='ModalCheckbox']:checked").length;

    
    if(checked_length==check_length) {
     
      $('a#MemberOut').removeClass('disabled');
      $('a#MemberOut').prop("disabled",false);
    }
    else{
      $('a#MemberOut').addClass('disabled');
      $('a#MemberOut').prop("disabled",true);
    }
  });

  // 그냥 버튼 체크시
  ModalCheckbox.click(function (e) {
    const checked_length = $("input:checkbox[name='ModalCheckbox']:checked").length;

    if(checked_length<check_length) {
      $("input#check1All").prop("checked",false);
      $('a#MemberOut').addClass('disabled');
      $('a#MemberOut').prop("disabled",true);
    }
    else if(checked_length==check_length) {
      $("input#check1All").prop("checked",true);
      $('a#MemberOut').prop("disabled",false);
      $('a#MemberOut').removeClass('disabled');
    }
  });


  
  

   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
function resetEmail() {
   $("#div_modifyEmail").hide();
   $("#change_email_btn").show();
   $("#user_email").show();
   $("#email_certification").hide();
   $("input#modify_email").css("border-bottom","none");  //빨간색 밑줄
   $("#input_email_error").hide();
   $("#new_email").css("color","black");  //라벨 빨간색
}
*/


function resetMobile() {
   $("#div_modifyMobile").hide();
   $("#change_mobile_btn").show();
   $("#user_mobile").show();
   $("#mobile_certification").hide();
   $("input#modify_mobile").css("border-bottom","none");  //빨간색 밑줄
   $("#input_mobile_error").hide();
   $("#new_mobile").css("color","black");  //라벨 빨간색
}



$(document).on("click","button.can_modify",function() {
	// alert("ㅎㅎ");
	$("button#myInfo_modify").prop("disabled",false);
	
})

$(document).on("click","#certification_mobile_btn",function() {
	// alert("ㅎㅎ");
	if($("input:hidden[name='mobile_store_cnt']").val() > 0) {
		$("button#myInfo_modify").prop("disabled",false);
	}
	
})




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/*$(document).on("click","button#email_store",function(){ // 이메일 변경 인증하기 버튼 클릭이벤트 
				  
			      $.ajax({
			        url:getContextPath()+"/member/emailDuplicateCheck.dream",
			        data:{"email":$("input#modify_email").val()},
			        type:"post",
			        dataType:"json",
			        async:true,   
			        success:function(json){
				
					console.log(json.isExists);
			            
			            if(json.isExists) {
			                // 입력한 email 이 이미 사용중이라면
			                alert("이미 사용중인 E-mail 입니다.");
			                $("input#modify_email").val("");
			                return false;
			            }
			            
			            else {
							
						    alert("사용가능한 이메일 입니다. 인증번호를 입력해주세요");
						    $("button#email_store").prop("disabled",true);
					        $("input#modify_email").css("border-bottom","none");
					        $("#modify_email").prop("disabled",true);
					        $("#email_certification").show();
							
							$.ajax({
						        url:getContextPath()+"/member/sendEmail.dream",
						        data:{"email":$("input#modify_email").val()},
						        type:"post",
						        dataType:"json",
						        async:true,   
						        success:function(json){
									if(json.sendMailSuccess) { // 메일 전송에 성공하였다면
			                				// alert("메일을 전송하였습니다. 인증번호를 입력해주세요");
			                				const certificationCode = json.certificationCode;
			                				console.log(certificationCode);
			                				
			                				$("button#certification_email_btn").prop("disabled",false);
			                				
			                				$("button#certification_email_btn").click(function() { // 인증번호 입력버튼 클릭이벤트
					
											const certification_email = $("input#certification_email").val().trim();
											const modify_email = $("#modify_email").val();
											
											if(certification_email == "") {
												alert("인증번호를 입력하세요.");
											}
											else {
												if(certification_email == certificationCode) {
													userid_store_cnt++;
													$("#div_modifyEmail").hide();
											        $("#change_email_btn").show();
											        $("#user_email").show();
											        $("span#userid").text(modify_email);
											        $("input:text[name='userid']").val(modify_email);
											        $("input#certification_email").val("");
											        $("input:text[name='userid_store_cnt']").val(userid_store_cnt);
												}
												else {
													alert("인증번호가 일치하지 않습니다.");
													$("input#certification_email").val("");
												}
											}
											
									   }); // end of $("button#certification_email_btn").click(function() {} -------------------------------------
			            				}
			            				else { // 메일 전송에 실패했다면
										alert("메일전송에 실패하였습니다. 메일주소를 다시 입력해주세요.");
									}
								}
							});		
			                
			            }
			            
			        },
			        error: function(request, status, error){
			            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			        }
			  });
			  
		});*/
		
		
		
		
		$(document).on("click","button#mobile_store",function(){ // 전화번호 변경 인증하기 버튼 클릭이벤트 
				  
			      $.ajax({
			        url:getContextPath()+"/member/mobileDuplicateCheck.dream",
			        data:{"mobile":$("input#modify_mobile").val()},
			        type:"post",
			        dataType:"json",
			        async:true,   
			        success:function(json){
				
					// console.log(json.isExists);
			            
			            if(json.isExists) {
			                // 입력한 전화번호가 이미 사용중이라면
			                toastr["error"]("이미 사용중인 전화번호 입니다.");
			                $("input#modify_mobile").val("");
			                return;
			            }
			            
			            else {
							
						    toastr["success"]("사용가능한 전화번호 입니다.");
						    $("button#mobile_store").prop("disabled",true);
					        $("input#modify_mobile").css("border-bottom","none");
					        $("#modify_mobile").prop("disabled",true);
					        $("#mobile_certification").show();
							
							$.ajax({
						        url:getContextPath()+"/member/smsSend.dream",
						        data:{"mobile":$("input#modify_mobile").val()},
						        type:"post",
						        dataType:"json",
						        async:true,   
						        success:function(json){
									if(json.success_count == 1) { // 문자 전송에 성공하였다면
									
										$(document).on("click","button#mobile_cancle",function(e){ // 모바일 변경 취소 버튼 클릭이벤트 

									       $("#div_modifyMobile").hide();
									       $("#change_mobile_btn").show();
									       $("#user_mobile").show();
									       $("#mobile_certification").hide();
									       $("input#modify_mobile").css("border-bottom","none");  //빨간색 밑줄
									       $("#input_mobile_error").hide();
									       $("#new_mobile").css("color","black");  //라벨 빨간색
									       $("div#div_timer").text('');
									       clearInterval(setTimer);
										   time = 180;
									       return;
									       
									   });// end of $(document).on("click","button#mobile_cancle",function(e){}
									
										toastr["info"]("인증번호를 전송하였습니다.","인증번호를 입력해주세요.");
			                				setTimer = setInterval(timer,1000);
			                				certificationCode = json.certificationCode;
			                				// console.log(certificationCode);
			                				
			                				$("button#certification_mobile_btn").prop("disabled",false);
			                				
			                				$("button#certification_mobile_btn").click(function() { // 인증번호 입력버튼 클릭이벤트
					
											let certification_mobile = $("input#certification_mobile").val().trim();
											let modify_mobile = $("#modify_mobile").val();
											
											if(certification_mobile == "") {
												toastr["info"]("인증번호를 입력하세요.");
											}
											else {
												// console.log(certificationCode);
												if(certification_mobile == certificationCode) {
													mobile_store_cnt++;
													$("#div_modifyMobile").hide();
											        $("#change_mobile_btn").show();
											        $("#user_mobile").show();
											        $("span#mobile").text(modify_mobile);
											        $("input:hidden[name='mobile']").val(modify_mobile);
											        $("input:hidden[name='mobile_store_cnt']").val(mobile_store_cnt);
											        $("div#div_timer").text('');
											        clearInterval(setTimer);
													time = 180;
												}
												else {
													toastr["error"]("인증번호가 일치하지 않습니다.");
													
												}
											}
											
									   }); // end of $("button#certification_email_btn").click(function() {} -------------------------------------
			            				}
			            				else { // 메일 전송에 실패했다면
										toastr["error"]("문자전송에 실패하였습니다.","전화번호를 다시 입력해주세요.");
										return;
									}
								}
							});		
			                
			            }
			            
			        },
			        error: function(request, status, error){
			            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			        }
			  });
			  
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





});// end of $(document).ready(function () {} -----------------------------------------------------------------------------------------------------------------------------


function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

//타이머 함수 만들기
  const timer = function timer(){
  if(time<0){ // 타임이 0보다 작게된다면
      clearInterval(setTimer);
      toastr["error"]("입력시간이 초과하였습니다.", "핸드폰인증을 다시 진행해주세요.");
      $("button#mobile_store").attr("disabled",false);
      $("button#mobile_store").text('재전송');
      mobile_ok = false;
      clearInterval(setTimer);
	  time = 180;
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

