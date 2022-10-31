function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
	
	
	$("span.name_error").hide();	
	$("span.passwd_error").hide();	
			
	$("input#username").blur( (e)=>{
    	
    	const $target = $(e.target); 	
    	const recipient_name = $target.val().trim();
    	if(recipient_name == "") { 
    		// 입력하지 않거나 공백만 입력했을 경우 
    		$(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
	        $("span.name_error").show();  //에러문구
	        $("#username.input").css("color","red");  //라벨 빨간색         	        	        
			
    	}
    	else {
    		$("span.name_error").hide();     		
    		$(e.target).css("border-bottom","solid 1px #ebebeb");
    		
    	}
    	
    	});
   
   
   
   
   
   $("input#username").blur( (e)=>{
       
       const $target = $(e.target);
       
       
       
       const username = $target.val().trim();
       if(username == "") { 
          // 입력하지 않거나 공백만 입력했을 경우 
          $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.name_error").show();  //에러문구
           $("#username.input").css("color","red");  //라벨 빨간색                               
         
       }
       else {
          $("span.name_error").hide();           
          $(e.target).css("border-bottom","solid 1px #ebebeb");
          
       }
       

    });  
    
    
    
    
    
    
     $(document).on("keyup","input#username",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

      
      
       const regExp = /^.{2,50}$/;

       const bool = regExp.test( $(e.target).val() );       


       if(!bool) {
           $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.name_error").show();  //에러문구
           $("h4#name").css("color","red");  
           
           
           
          
           
       }
       else {
           $(e.target).css("border-bottom","solid 1px #ebebeb");
           $("span.name_error").hide();
           $("h4#name").css("color","black");  
           
          
           
       }


	});// end of $(document).on("keyup","input#recipient_name",function(e) 
	
	
	
	
	
	
	 // 비밀번호 유효성 검사	
	 $(document).on("keyup","input#passwd",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

      
      
       const regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 

       const bool = regExp.test( $(e.target).val() );       


       if(!bool) {
           $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.passwd_error").show();  //에러문구      
           $("#passwd").css("color","red");   
          
           
       }
       else {
           $(e.target).css("border-bottom","solid 1px #ebebeb");
           $("span.passwd_error").hide();
           $("#passwd").css("color","black");  
           
          
           
       }


	});// end of $(document).on("keyup","input#recipient_name",function(e
	
	
		
		    
// 모달창을 열어주는 기능   
$("button#edit_info").click( (event)=>{ 
	
	$('#info_edit').modal('show');	
	
	$("span.name_error").hide();
	$("input#username").css("border-bottom","solid 1px #ebebeb");
	$("#name").css("color","black");  
	
	$("span.passwd_error").hide();
	$("input#passwd").css("border-bottom","solid 1px #ebebeb");
	$("#passwd").css("color","black");  
	$("input#passwd").val("");
	
	
	
	const target = $(event.target); 
	
	const userid = $("td#userid").text();
    const username = $("td#username").text();
    const mobile = $("td#mobile").text();
    const join_date = $("td#join_date").text();
    const secession = $("td#secession").text();
    const rest_member = $("td#rest_member").text();
    const membership = $("td#membership").text();
    

		
	
	
	$("input#userid").val(userid);
    $("input#username").val(username);
    $("input#mobile").val(mobile);    	    	
    $("input#join_date").val(join_date);   
    $("h2#title_username").text(username+"님 정보 수정"); 	
    if(secession == "O"){
	  $("input:radio[name='secession']:radio[value='1']").prop('checked', true); 
    }
    else {
	  $("input:radio[name='secession']:radio[value='0']").prop('checked', true); 
    }
    
    if(rest_member == "O"){
	  $("input:radio[name='rest_member']:radio[value='1']").prop('checked', true); 
    }
    else {
	  $("input:radio[name='rest_member']:radio[value='0']").prop('checked', true); 
    }
    
    if(membership == "O"){
	  $("input:radio[name='membership']:radio[value='1']").prop('checked', true); 
    }
    else {
	  $("input:radio[name='membership']:radio[value='0']").prop('checked', true); 
    }
    
   
    
    
	
});








	
	
}); // end of $(document).ready(function(){} ------------------------------------



function delete_confirm(userid){
	
	
   const username = $("td#username").text();
    
     
   Swal.fire({		 
	
     title: username+' 님을 회원에서 삭제하시겠습니까?',
     icon: 'info',
     showCancelButton: true,
     confirmButtonColor: '#3085d6',
     cancelButtonColor: 'dark',
     confirmButtonText: '삭제',
     cancelButtonText: '취소'
   }).then((result) => {
     if (result.value) {
        $.ajax({
           url:getContextPath()+"/admin/memberDelete.dream",
           type: "POST",
           data:{"userid":userid},
           dataType:"JSON",
           success:function(json) {
              // {n:1}
              if(json.n == 1) {
               Swal.fire({
		   	   title: '회원삭제에 성공하였습니다.',
		       icon: 'info',     
		       confirmButtonColor: '#3085d6',
		       cancelButtonColor: 'dark',     
		       confirmButtonText: '확인'		       
		       }).then((result) => {
			if (result.value) {
				location.href=document.referrer;
			}
		     })
              
               }
              
           },
           error: function(request, status, error){
                  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
             }
        });
            return; 
     }
     else  {
	 Swal.fire({
     title: '회원삭제를 취소하셨습니다.',
     icon: 'info',     
     confirmButtonColor: '#3085d6',
     cancelButtonColor: 'dark',     
     confirmButtonText: '확인'
     })
      }
   });
}
  
    



// 모달창에서 수정버튼을 누르면 발생하는 이벤트
function goEditfrm() {
	// 기본 정보창에서 가져온 정보 저장(비교용)
	
	
	
	
	  	 
	
	origin_name = $("td#username").text();
	origin_secession = $("td#secession").text();
	origin_rest_member = $("td#rest_member").text();
	origin_membership = $("td#membership").text();
	
	
	
	// modal 에 입력한 값
	let new_name = $("div.input_item>input#username").val();
	
	if(new_name == ""){
		new_name = origin_name;
	}
	new_secession = $("div.input_box>input#secession:checked").val();
	if(new_secession =="1"){
		new_secession = "O";
	}
	else{
		new_secession= "X"
	}
	
	new_rest_member = $("div.input_box>input#rest_member:checked").val();
	
	if(new_rest_member =="1"){
		new_rest_member = "O";
	}
	else{
		new_rest_member= "X"
	}
	
	new_membership = $("div.input_box>input#membership:checked").val();
	if(new_membership =="1"){
		new_membership = "O";
	}
	else{
		new_membership= "X"
	}
	
	// 변한 거 있나 체크
	let isItChange = false;
	
	if(origin_name != new_name){
		isItChange = true;
	}
	
	if(origin_secession != new_secession){
		/*alert("1");*/
		isItChange = true;
	}
	if(origin_rest_member != new_rest_member){
		/*alert("2");*/
		isItChange = true;
	}
	if(origin_membership != new_membership){
		/*alert("3");*/
		isItChange = true;
	}
	if( $("div.input_item>input#passwd").val() != ""){ // 정규표현식 통화하는지 추가해야함.
		/*alert("4");*/
		isItChange = true;
	}
	
	if(!isItChange){
		/*// 변한게 하나도 없다면
		alert("변경된 점이 하나도 없습니다."); */
		
		Swal.fire({		 
	
     title: ' 변경된 점이 하나도 없습니다. ',
     icon: 'info',     
     confirmButtonColor: '#3085d6',
     cancelButtonColor: 'dark',
     confirmButtonText: '확인',
     
   })
   
	}
	else{
		
		
	if( $("div.input_item>input#passwd").val() != ""){	
	const regExp = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);  
            //  숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성 
            
            const passwd = $("input:text[id='passwd']").val();
            
            const bool = regExp.test(passwd);
            
            if(!bool) {            	
            	Swal.fire({		 
	
			     title: '비밀번호 형식에 맞지 않습니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
			   return ;
			  }
		}	  
			  
	   		  
		
		
		
		$("input#secessionCHECK").val($("input#secession:checked").val());
		$("input#rest_memberCHECK").val($("input#rest_member:checked").val());
		$("input#membershipCHECK").val($("input#membership:checked").val());
		
		
		
		
		const frm = $("#editFrm").serialize();
		
		Swal.fire({		 
	
     title: origin_name+' 님의 정보를 수정하시겠습니까?',
     icon: 'info',
     showCancelButton: true,
     confirmButtonColor: '#3085d6',
     cancelButtonColor: 'dark',
     confirmButtonText: '수정',
     cancelButtonText: '취소'
   }).then((result) => {
     if (result.value) {
        $.ajax({
           url:getContextPath()+"/admin/memberDetailEdit.dream",
           type: "POST",
           data:frm,
           dataType:"JSON",
           success:function(json) {
              // {n:1}
              if(json.n == 1) {
               Swal.fire({
		   	   title: '회원정보 수정에 성공하였습니다.',
		       icon: 'info',     
		       confirmButtonColor: '#3085d6',
		       cancelButtonColor: 'dark',     
		       confirmButtonText: '확인'		       
		       }).then((result) => {
			if (result.value) {
				location.href=document.referrer;

			}
			
		     })
              
               }
             
           },
           error: function(request, status, error){
                  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
             }
        });
            return; 
     }
     else  {
	 Swal.fire({
     title: '회원수정을 취소하셨습니다.',
     icon: 'info',     
     confirmButtonColor: '#3085d6',
     cancelButtonColor: 'dark',     
     confirmButtonText: '확인'
     })
      }
   });
   
   $('#info_edit').modal('hide');
   
   }
		
		
		
		
		
		
	}
	
	
	
	
	






   
   
      




/*
// === 회원삭제하기 === //
   function delete_confirm(userid) {      
   
       const username = $("td#username").text();
   
   
      const bool = confirm(username+" 님을 회원에서 삭제하시겠습니까?");   
      
      if(bool) {
         
        $.ajax({
           url:getContextPath()+"/admin/memberDelete.dream",
           type: "POST",
           data:{"userid":userid},
           dataType:"JSON",
           success:function(json) {
              // {n:1}
              if(json.n == 1) {
               alert(""+username+" 회원을 삭제하였습니다.")
               location.href=document.referrer;
              }
           },
           error: function(request, status, error){
                  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
             }
        });
         
      }
      else {
         alert("회원삭제를 취소하셨습니다.");
      }
      
   }// end of function delete_confirm(userid)-------------------------------------------

*/



