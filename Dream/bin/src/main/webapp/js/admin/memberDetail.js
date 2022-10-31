function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
   
   
   
   
   
   $("span.name_error").hide();
   
   
   
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
    
    
    
    
    
    
     $(document).on("keyup","input#username",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

      
      
       const regExp = /^.{2,50}$/;

       const bool = regExp.test( $(e.target).val() );       


       if(!bool) {
           $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.name_error").show();  //에러문구
           
           
           
           
          
           
       }
       else {
           $(e.target).css("border-bottom","solid 1px #ebebeb");
           $("span.name_error").hide();
           $("#username").css("color","black");  
           
          
           
       }

   });// end of $(document).on("keyup","input#recipient_name",function(e) 
   
   
   
   
      
          
// 모달창을 열어주는 기능   
$("button#edit_info").click( (event)=>{ 
   
   $("span.name_error").hide();
   $("input#username").css("border-bottom","solid 1px #ebebeb");
   
   $('#info_edit').modal('show');   
   
   const target = $(event.target); 
   
   const userid = $("td#userid").text();
    const username = $("td#username").text();
    const mobile = $("td#mobile").text();
    const join_date = $("td#join_date").text();
    const secession = $("td#secession").text();
    const rest_member = $("td#rest_member").text();
    const membership = $("td#membership").text();
    

      
   console.log(userid);
   console.log(username);
   console.log(mobile);
   console.log(join_date);
   console.log(secession);
   console.log(rest_member);
   console.log(membership);
   
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
   console.log("new_rest_member"+new_rest_member);
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
   console.log(new_name);
   console.log("origin_name"+origin_name);
   if(origin_name != new_name){
      isItChange = true;
   }
   console.log(isItChange);
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
      // 변한게 하나도 없다면
      alert("변경된 점이 하나도 없습니다."); 
   }
   else{
      
      
      
      $("input#secessionCHECK").val($("input#secession:checked").val());
      $("input#rest_memberCHECK").val($("input#rest_member:checked").val());
      $("input#membershipCHECK").val($("input#membership:checked").val());
      
      console.log($("input#secessionCHECK").val());
      
      console.log($("input#rest_memberCHECK").val());
      
      console.log($("input#membershipCHECK").val());
      
      
      const frm = document.editFrm;
      
      frm.action = "memberDetailEdit.dream";
      frm.method = "post";
      frm.submit();
      
      $('#info_edit').modal('hide');
      
      
      
   }
   
   
   
   
   
}







// === 회원삭제하기 === //
   function delete_confirm(userid) {      
   
       const username = $("td#username").text();
   //  console.log(pname);
   
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



/*function delete_confirm() {
      
   var result = confirm("정말로 삭제하시겠습니까 ??");
   
   if(result == true) {
      
      alert("회원목록에서 삭제하였습니다.")
      
      

   }
   else {
      alert("취소하셨습니다.")
   }
   
      
}*/