 let b_flag_zipcodeSearch_click = false;
//"우편번호" 를 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도.





	function getContextPath(){
	  let hostIndex = location.href.indexOf(location.host) + location.host.length;
	  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
	  return contextPath;
	}
	      
                  
      function IsAllOk(){
        
        userid=01234;
        coinmoney=56789;
        
        checked_length = $('input:checkbox[name="li_check"]:checked').length;
        /*console.log(checkAll);*/
        if((checkAll && addressExist)){
		//  주소가 존재하고 체크박스가 모두 체크되면 disabled 를 제거한다
		  
      	/*  $('#payment').attr('href','#');*/
      	  $('#payment').removeClass('disabled');
		  $('#payment').attr('href','#');
         
        }
        else{
          
          $('#payment').removeAttr('href');
		  $('#payment').addClass('disabled');
          
        }
        
      };

      let checkAll = false;
	  let addressExist = true;


    
    $(document).ready(function() {
	  let checked_length = $('input:checkbox[name="li_check"]:checked').length;
	
	  const checkBox = $("input:checkbox[name='li_check']");
      const check_length = $('input:checkbox[name="li_check"]').length;

      checkAll = false;
	  addressExist = true;
	  
      IsAllOk();  
      // let addressExist = false; 나중에는 기본 주소 로딩되면서 자동클릭되도록 하고 체크해주면 될듯


      // 클릭 이벤트
      checkBox.click(function (e) {
        // 전부 체크 됐는지 확인
		
		checked_length = $('input:checkbox[name="li_check"]:checked').length;
		/*console.log(checked_length);*/
        if(checked_length<check_length) {
          checkAll = false;
        }
        else if(checked_length==check_length) {
          checkAll = true;
        }

        IsAllOk();  
      });
      
      
      $('a#payment').click(function(e){
			
		/*const userid = "${sessionScope.loginuser.userid}"; */
		
		userid = $("form#goPurchase>input#userid").val();
		console.log(userid);
		fullPrice = $("form#goPurchase>input#fullPrice").val();
		console.log(fullPrice);
		productName = $("form#goPurchase>input#productName").val();

		//	alert("확인용0 결제할 사용자 아이디 : " + userid);
			
		//  아임포트 결제 팝업창 띄우기 
        const url = getContextPath()+"/member/goPurchase.dream?userid="+userid+"&fullPrice="+fullPrice+"&productName="+productName;
        	
		
		window.open(url, "goPurchase",
				    "left=350px, top=100px, width=1000px, height=600px");

		});
		
		
		
		// 포인트입력칸 숫자만 가능하도록
		$("input#point").keyup(function(){ $(this).val($(this).val().replace(/[^0-9]/gi,"") );  }); //숫자만
	 
		
      	$("input#point").blur(function(){
			if(Number($(this).val()) >  Number($("span.point").text())){
				alert("보유한 포인트보다 많은 포인트를 사용하실 수 없습니다. 다시 입력해주세요!");
				$(this).val('');
			}			
			
			/*if(Number($(this).val() != null && Number($(this).val()) != 0){
				$("#span_point_amount").text($(this).val());
			}
			else{
				$("#span_point_amount").text('-');
			}*/
			
		});
	
		$("button#pointAlluse").click(function(){
			$("input#point").val($("span.point").text());
			$("#span_point_amount").text($("input#point").val());
		});


	
	
		  /* 모달창이 열리면 이름입력부분에 오토포커스를 주는 기능  */
   $('div#add_address').on('shown.bs.modal', function() {   
      
       $("span.name_error").hide();
       $("span.mobile_error").hide();
       $('input#recipient_name').trigger('focus');
       
       
       
       /*
       저장하기 버튼 활성화 기능
       
       $( 'a.btn.btn_save.solid.medium.disabled' ).removeClass( 'disabled' );                     
       $( "#add_address > div > div.layer_btn > a.btn.btn_save.solid.medium" ).attr("disabled", false);  
       */
       
   });  



    }); // end of 다큐먼트
    
    
    // === DB 상의 정보 변경( 구매내역 (userid, product_num, buy_cnt) 
    //                     포인트  (userid, product_num,POINT_AMOUNT(full_price 의 일정 %, 사용 둘다 필요), event_type(기본값은 제품구매)
    //					   구매한 개수만큼 재고 감소 (PRODUCT_NUM, PRODUCT_SIZE, SIZE_CNT) )) === //
	function goUpdate() {
		
		// jsp 에서 보내줘야할 정보
		// userid, product_num, 
		// buy_cnt(SIZE_CNT)의 합(),
		// POINT_AMOUNT(full_price 의 일정 %, 사용량 둘다 필요),
		// event_type,  
		// PRODUCT_SIZE,SIZE_CNT )
		// 전부 히든폼에 담아서 보내자자ㅏ자자자ㅏ자자ㅏ자자ㅏ자자자ㅏ자자자ㅏ자자자자ㅏ자자자ㅏ 
		// 그냥 purchase controller 에서 넘어온값은 그대로 넣어놨고 추가할 값만 여기서 넣어줌
		
		const frm = document.goUpdate;
		if( $("input#span_point_amount").text() == '-'){
			frm.PointMinus.value = $("input#span_point_amount").text();	
		}
		else{
			frm.PointMinus.value = 0;
		}
		let length = $("div#SeletedOption").length;
		frm.length.value = length;// 반복할 횟수 가져가줌
		// 여기서 이벤트타입 분기점 생기면 여기서 나눠서 올리면 된다. 난 몰루겠음 구매말곤
		let event_type = "구매"; 
		frm.event_type.value = event_type;
		
		frm.action = getContextPath()+"/product/goUpdate.dream";
		frm.method = "POST";
		frm.submit(); 
	}

	
	


$(document).ready(function(){
   
   
   
   /* 모달창이 열리면 이름입력부분에 오토포커스를 주는 기능  */
   $('div#add_address').on('shown.bs.modal', function() {   
      
      $("span.name_error").hide();
      $("span.mobile_error").hide();
       $('input#recipient_name').trigger('focus');
       
       
       
       /*
       저장하기 버튼 활성화 기능
       
       $( 'a.btn.btn_save.solid.medium.disabled' ).removeClass( 'disabled' );                     
       $( "#add_address > div > div.layer_btn > a.btn.btn_save.solid.medium" ).attr("disabled", false);  
       */
       
   });  
   
   
   

   
   
    

   
   $("input#recipient_name").blur( (e)=>{
       
       const $target = $(e.target);
       
       
       
       const recipient_name = $target.val().trim();
       if(recipient_name == "") { 
          // 입력하지 않거나 공백만 입력했을 경우 
          $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.name_error").show();  //에러문구
           $("#name").css("color","red");  //라벨 빨간색 
           
             
           $("#add_address > input").prop("disabled", true);
           $target.prop("disabled", false);
           
           
                      
         
       }
       else {
          $("#add_address > input").prop("disabled", false);
          
          
          
       }
       
    });  
   
   
   
  $(document).on("keyup","input#recipient_name",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

      
      
       const regExp = /^.{2,50}$/;

       const bool = regExp.test( $(e.target).val() );       


       if(!bool) {
           $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.name_error").show();  //에러문구
           $("#name").css("color","red");  //라벨 빨간색                       
           
           $("#add_address > input").prop("disabled", true);
           
           
          
           
       }
       else {
           $(e.target).css("border-bottom","solid 1px #ebebeb");
           $("span.name_error").hide();
           $("#name").css("color","black");  
           
          
           
       }

   });// end of $(document).on("keyup","input#recipient_name",function(e) {

    
    
   $("input#mobile").blur( (e)=>{
       
       const $target = $(e.target);
       
       const mobile = $target.val().trim();
       
       if(mobile == "") {
          // 입력하지 않거나 공백만 입력했을 경우 
          $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.mobile_error").show();  //에러문구
           $("#mobile").css("color","red");  //라벨 빨간색 
           
           $("#add_address > input").prop("disabled", true);
           $target.prop("disabled", false);
           
           
           
       }
       else {
          $("#add_address > input").prop("disabled", false);
          
          
      }
       
   });  
   



   $(document).on("keyup","input#mobile",function(e) { // 010 을 필수로 포함하면서 11자리 숫자를 입력해야하는 유효성 검사

      const regExp = /^[0]{1}[1]{1}[0]{1}[0-9]{8}$/;

       const bool = regExp.test( $(e.target).val() );       


       if(!bool) {
           $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
           $("span.mobile_error").show();  //에러문구
           $("#mobile").css("color","red");  //라벨 빨간색 
           
          
       }
       else {
           $(e.target).css("border-bottom","");  //빨간색 밑줄
           $("span.mobile_error").hide();
           $("#mobile").css("color","black");  //라벨 빨간색 
           
           
           
       }

   });// end of $(document).on("keydown","input#modify_name",function(e) {}

   
   
   // "우편번호" 를 클릭했을 때 이벤트 처리하기
    $("a#zipcodeSearch").click(function() {
       b_flag_zipcodeSearch_click = true; 
    });
   
   // 페이징처리 post 타입으로 이악물고 하기
   $("a.page-link").click(function(e){
	
		console.log($(e.target));
		console.log($(e.target).attr("p"));
		const p = $(e.target).attr("p");
		$("input#p").val(p);
		
		const frm = document.page;    
	    frm.action = getContextPath()+"/member/purchasePage.dream";
	    frm.method = "post";
	    frm.submit();
	});
   
   	// 주소지 클릭시 hidden input 에 주소넘버 넣어주기
	$("div.adressOption").click(function(e){
	  	let $target = $(e.target).parent().parent();
	  	console.log($target);
	  	let selectedNumber =  $target.find("input#address_num").val();
	  	console.log(selectedNumber);
	  
	   $("input#selected_address_num").val(selectedNumber);
	
		// 클릭 할때마다 선택한 애만 달라지도록
		$("div.adressOption").removeClass('selectedAddress');
		$("div#div"+selectedNumber).addClass('selectedAddress');
	  });
	
    

});   // end of $(document).ready(function(){} -------------------------------   
      
      

      
      

/* 새 배송지 추가 버튼을 클릭시 모달창에있는 내용들을 초기화 해주는 기능  */
function new_add() {
   
   $("a#add_edit").hide();   
   $("a#add_save").show();
   
   $('#add_address').modal('show');

    $("h2.title2").hide();
    $("h2.title1").show();   
    
  
   
    $("div.input_item > input").val("");
    $("span.name_error").hide();
   $("span.mobile_error").hide();
   $("#name").css("color","black");
   $("#mobile").css("color","black");
   $("div.input_item > input").css("border-bottom","solid 1px #ebebeb"); 
   
}// end of function new_add() {}-----------------------
   

/* 수정 버튼을 클릭시 모달창에있는 내용들을 초기화 해주는 기능  */
function Revise_add() {

   
   $("a#add_save").hide();   
   $("a#add_edit").show();
   
   $('#add_address').modal('show');

    $("h2.title1").hide();   
    $("h2.title2").show();
    
    
    
    $("span.name_error").hide();
   $("span.mobile_error").hide();
   $("#name").css("color","black");
   $("#mobile").css("color","black");
   $("div.input_item > input").css("border-bottom","solid 1px #ebebeb"); 
   
   /* 수정버튼 클릭시 이름,전화번호란은 비워주면서 저장되어있는 밸류값을 가져와준다  */
    $("div.input_item > input").val("");
   
    
   /*              여기까지                                */
   
   /* console.log($("span.address").text()); */
   
   
   
   
       $("input#address").val("주소 가져와본다");
       $("input#postcode").val("우편번호 가져와본다");    
       $("input#detailAddress").val("상세주소밸류 가져와본다");    
   


}// end of function Revise_add() {}------------------------   






/* 모달에서 우편번호 버튼 클릭시 다음주소검색을 띄워주는 기능  */
function openDaumPOST() {
   
   new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let addr = ''; // 주소 변수
            let address2 = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress; 
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다. 
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    address2 += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    address2 += (address2 !== '' ? ', ' + data.buildingName : data.buildingName);
                }                
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(address2 !== ''){
                    address2 = ' (' + address2 + ')';
                }
                
            
            } else {
                
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr + address2;           
            document.getElementById("detailAddress").value = "";
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
            
        }
      
    }).open();       
   
}// end of function openDaumPOST() {}------------------------------------






//"저장하기" 버튼 클릭시 호출되는 함수  
function goRegister() {
   
   const name_length = $("input:text[name='order_name']").val().trim().length;
         if(name_length < 2) {
            alert("이름 형식에 맞지 않습니다!!");
            $("input:text[name='order_name']").val("").focus();
            return false; // submit 을 하지 않고 종료한다.
   }
         
    const mobile_length = $("input:text[name='mobile']").val().trim().length;
    if(mobile_length < 11) {
       alert("전화번호 형식에 맞지않습니다 !!");
       $("input:text[name='mobile']").val("").focus();
       return false; // submit 을 하지 않고 종료한다.
    }

   // "우편번호찾기" 을 클릭했는지 여부 알아오기 
    if(!b_flag_zipcodeSearch_click) { 
       // "우편번호찾기" 을 클릭 안 했을 경우 
       alert("우편번호찿기를 클릭하셔서 우편번호를 입력하셔야 합니다.");
       
       return; // 종료
    }
    else {
       // "우편번호찾기" 을 클릭을 했을 경우 
       
        //   const regExp = /^\d{5}$/g;  
      //  또는
          const regExp = new RegExp(/^\d{5}$/g);  
            //  숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성 
            
            const postcode = $("input:text[id='postcode']").val();
            
            const bool = regExp.test(postcode);
            
            if(!bool) {
               alert("우편번호 형식에 맞지 않습니다.");
               $("input:text[id='postcode']").val("");
               b_flag_zipcodeSearch_click = false;
               return; // 종료
            }                       
    }
   
    const frm = document.registerFrm;    
    frm.action = "addressregister.dream";
    frm.method = "post";
    frm.submit();
   
    

   
}// end of function goRegister()----------------------






//"수정하기" 버튼 클릭시 호출되는 함수  
function goEdit() {
   
   const name_length = $("input:text[name='order_name']").val().trim().length;
         if(name_length < 2) {
            alert("이름 형식에 맞지 않습니다!!");
            $("input:text[name='order_name']").val("").focus();
            return false; // submit 을 하지 않고 종료한다.
   }
         
    const mobile_length = $("input:text[name='mobile']").val().trim().length;
    if(mobile_length < 11) {
       alert("전화번호 형식에 맞지않습니다 !!");
       $("input:text[name='mobile']").val("").focus();
       return false; // submit 을 하지 않고 종료한다.
    }

   // "우편번호찾기" 을 클릭했는지 여부 알아오기 
    if(!b_flag_zipcodeSearch_click) { 
       // "우편번호찾기" 을 클릭 안 했을 경우 
       alert("우편번호찿기를 클릭하셔서 우편번호를 입력하셔야 합니다.");
       
       return; // 종료
    }
    else {
       // "우편번호찾기" 을 클릭을 했을 경우 
       
        //   const regExp = /^\d{5}$/g;  
      //  또는
          const regExp = new RegExp(/^\d{5}$/g);  
            //  숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성 
            
            const postcode = $("input:text[id='postcode']").val();
            
            const bool = regExp.test(postcode);
            
            if(!bool) {
               alert("우편번호 형식에 맞지 않습니다.");
               $("input:text[id='postcode']").val("");
               b_flag_zipcodeSearch_click = false;
               return; // 종료
            }                       
    }
   
    const frm = document.registerFrm;    
    
    
    frm.action = "addressedit.dream";
    frm.method = "post";
    frm.submit();
   
    

 
   
}// end of function goRegister()----------------------

