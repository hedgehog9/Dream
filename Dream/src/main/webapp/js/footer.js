  $(document).ready(function(){

  }); // end of $(document).ready(function(){}-----------------------------

  
  // footer 의 사업자 등록 버튼을 누를경우 함수 호출
  function func_show_business_info(){

     if($("i#btn_b_info").hasClass("fa-chevron-down")) {
        $("i#btn_b_info").addClass("fa-chevron-up").removeClass("fa-chevron-down");
        $("div.company_info").css("display","block");
     } else {
        $("i#btn_b_info").addClass("fa-chevron-down").removeClass("fa-chevron-up");
        $("div.company_info").css("display","none");
     }

  }// end of function func_show_business_info(e){------------------