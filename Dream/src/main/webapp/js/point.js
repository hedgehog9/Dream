
    $(function(){

        $("div.layer").hide();
        
  
        // pointExistChexk(); pointExist 정해주는 함수
        var pointExist = false;
        // 나중에 로그인 정보 받아와서 그사람의 포인트가 0인지 체크해서 부여해줄 것
        // 지금은 테스트를 위해서 위 값을 true/ false 로 바꿔서 나오는지 체크할 예정
  /*
        if(pointExist){
          $('div.point_empty').hide();
          $('div.point_exist').show();
        }
        else{
          $('div.point_empty').show();
          $('div.point_exist').hide();
        }
  */
      // 작은 화면에서 ? 버튼을 눌렀을 때 나오는 레이어에서 닫기 버튼, 검은화면을 눌렀을 때
  
      $('.btn_layer_close').click(function (e) {  
          $("div.layer").hide();  
      });       
  
      $('.layer').click(function (e) {  
        
        if(($(e.target).hasClass("layer"))) {
          $("div.layer").hide();  
        }
        
  
  
        
      }); 
  
      // ? 클릭시 레이어 등장
      $('#layeron').click(function (e) {  
          $("div.layer").show();  
      });       
  
      $('input#pointCode').keydown(function (e) {  
     
       
        if(!$('input#pointCode').val().trim()==""){
          $('button#pointRegisterButton').attr("disabled",false);
          $('button#pointRegisterButton').removeClass('disabled');
    
        }
        else{
          $('button#pointRegisterButton').attr("disabled",true);
          $('button#pointRegisterButton').addClass('disabled');
  
        }
        
      });
  
      $('button#pointRegisterButton').click(function(e){
        
        pointCodeCorrect = true;
        // 나중에는 코드번호가 존재하는지 확인해야함
  
        if(pointCodeCorrect){
          alert("존재하는 번호입니다");
        }
        else{
          alert("존재하지 않는 번호입니다");
        }
        
  
      });
  
      });// end of window.onload(function(){
  
  