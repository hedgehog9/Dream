//"우편번호" 를 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도.
let b_flag_zipcodeSearch_click = false;




$(document).ready(function(){
		 
	
	 $("input#basic_address").hide();
     $("label#basic_label").hide();
	
	
	
	/* 모달창이 열리면 이름입력부분에 오토포커스를 주는 기능  */
	$('div#add_address').on('shown.bs.modal', function() {	
		
		$("span.name_error").hide();
		$("span.mobile_error").hide();
	    $('input#recipient_name').trigger('focus');
	    
	    $("#name").css("color","black");
        $("#recipient_name").css("border-bottom","solid 1px #ebebeb");
	    
	    
	    /*
	    저장하기 버튼 활성화 기능
	    
	    $( 'a.btn.btn_save.solid.medium.disabled' ).removeClass( 'disabled' );	    	    	    
	    $( "#add_address > div > div.layer_btn > a.btn.btn_save.solid.medium" ).attr("disabled", false);  
	    */
	    
	});  
	
	
	
 /*  
   주소입력창이 공백이 아닐때 저장하기 버튼이 활성화 (보류)
   $("input#detailAddress").blur( (e)=>{
    	
    	const $target = $(e.target);
	
    	const detailAddress = $target.val().trim();
    	
    	if(detailAddress == "") { 		
    	}
    	else {    		
    		$( 'a.btn.btn_save.solid.medium' ).removeClass( 'disabled' );  		    		
    	}
    	
    });   
 */
	
	
	
    

	
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

	});// $(document).on("keyup","input#mobile",function(e) {}--------------------------

	
	
	// "우편번호" 를 클릭했을 때 이벤트 처리하기
    $("a#zipcodeSearch").click(function() {
   	 b_flag_zipcodeSearch_click = true; 
    });
	
	
	
	/* 삭제버튼 클릭시 해당부분 div를 삭제해준다.  */
    $("a#delete").click( (event)=>{ 
	
	    let address_num ="";
	    	
    	
    	const $target = $(event.target);    	
    	
    	
    	
    	address_num = $target.parent().parent().find("#address_num").val();
    	    	  	
    	
    	
    	$("input#address_num").val(address_num);
    	
    	const frm = document.delete_add;
		frm.action = "addressdelete.dream"
		frm.method = "post";
	    frm.submit();   	
    	
    	$target.parent().parent().remove(".my_item_is_active"); 
    });
    
    
    
    // 기본 배송지 버튼 클릭시 호출되는 함수
	$("a#go_basic").click( (event)=>{ 
		
		
		
		
	
	    let address_num ="";	    	
    	
    	const $target = $(event.target);    	   	   	
    	
    	address_num = $target.parent().parent().find("#address_num").val();   	    	  	   	
    	
    	$("input#address_num").val(address_num);  	  
    	
    	  	    	
    	
    	const frm = document.delete_add;    
	    frm.action = "addressgobasic.dream";
	    frm.method = "post";
	    frm.submit();	
   
    
	
});
    
   

	
    
    $("a#basic_delete").click( (event)=>{ 

        /*alert("다른 주소를 기본 배송지로 변경 후, 삭제할 수 있습니다.");*/
    	toastr["error"]("다른 주소를 기본 배송지로 <br>변경 후, 삭제할 수 있습니다.");	
    });
    
    
    
    
    
     // 기본배송지 아닌 수정버튼 클릭 이벤트
    $("a#edit").click( (event)=>{ 
	
	
	   $("input#basic_address").show();
       $("label#basic_label").show();
    	
    	let address_num ="";	
    		
    	const target = $(event.target);    	
    	
    	
    	const zipcode = target.parent().prev().children().children("div.address_box").children("span.zipcode").text();
    	const address = target.parent().prev().children().children("div.address_box").children("span.address").text();
    	const detail_address = target.parent().prev().children().children("div.address_box").children("span.detail_address").text();
    	
    	
    	
    	$("input#postcode").val(zipcode);
    	$("input#address").val(address);
    	$("input#detailAddress").val(detail_address);    	    	
    	
    	address_num = target.parent().parent().find("#address_num").val(); 
    	
    	
    		
		$("input#address_num_modal").val(address_num);	 	
		
		
		
    	
    });
    
    
    
  
    // 기본배송지 수정버튼 클릭 이벤트
    $("a#edit2").click( (event)=>{     
	
	  
       $("input#basic_address").hide();
       $("label#basic_label").hide();

    	
    	let address_num ="";		
    	const target = $(event.target);    	
    	
    	const zipcode = target.parent().prev().children().children("div.address_box").children("span.zipcode").text();
    	const address = target.parent().prev().children().children("div.address_box").children("span.address").text();
    	const detail_address = target.parent().prev().children().children("div.address_box").children("span.detail_address").text();
    	
    	
    	
    	$("input#postcode").val(zipcode);
    	$("input#address").val(address);
    	$("input#detailAddress").val(detail_address);
    	
    	 address_num = target.parent().parent().find("#address_num").val(); 
    	
    	
    		
		$("input#address_num_modal").val(address_num);	 
		
		
		$("input#address").val();
		
		
		
		
		
      
      
 });  


    

});	// end of $(document).ready(function(){} -----------------------------------	




/* 새 배송지 추가 버튼을 클릭시 모달창에있는 내용들을 초기화 해주는 기능  */
function new_add() {	
	
	
	$("input#basic_address").show();
    $("label#basic_label").show();
	
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
	
	$("input#basic_address").prop('checked', false);
	
	
	
}// end of function new_add() {}-----------------------
	

/* 수정 버튼을 클릭시 모달창에있는 내용들을 초기화 해주는 기능  */
function Revise_add() {


    $('#add_address').modal('show');
	
	$("a#add_save").hide();	
	$("a#add_edit").show();
	
	

    $("h2.title1").hide();	
    $("h2.title2").show();
    
    
    $("div.input_item > input").val("");
    $("span.name_error").hide();
	$("span.mobile_error").hide();
	$("#name").css("color","black");
	$("#mobile").css("color","black");
	$("div.input_item > input").css("border-bottom","solid 1px #ebebeb"); 
	
	/* 수정버튼 클릭시 이름,전화번호란은 비워주면서 저장되어있는 밸류값을 가져와준다  */
	 
	
	 
	/*              여기까지                                */
	
	/* console.log($("span.address").text()); */
	
	 
	


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






// 모달창의 "저장하기" 버튼 클릭시 호출되는 함수  
function goRegister() {
	
	const name_length = $("input:text[name='order_name']").val().trim().length;
	      if(name_length < 2) {
	    	  Swal.fire({		 
	
			     title: '이름 형식에 맞지 않습니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',			     			     			  
			
		     }).then((result) => {
            if (result.isConfirmed) {
                 $("input:text[name='order_name']").val("").focus();
            }
              
            });
	    	  
	    	  return false; // submit 을 하지 않고 종료한다.
	    	  
	}
	      
    const mobile_length = $("input:text[name='mobile']").val().trim().length;
    if(mobile_length < 11) {
	    Swal.fire({		 
	
			     title: '전화번호 형식에 맞지않습니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
	    $("input:text[name='mobile']").val("").focus();
	    return false; // submit 을 하지 않고 종료한다.
    }
    
    const detailAddress_length = $("input:text[name='detail_address']").val().trim().length;
	if(detailAddress_length < 1) {
	    Swal.fire({		 
	
			     title: ' 상세주소를 입력하셔야 합니다.',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
	    $("input:text[name='detailAddress']").val("").focus();
	    return false; // submit 을 하지 않고 종료한다.
    }
    
	// "우편번호찾기" 을 클릭했는지 여부 알아오기 
    if(!b_flag_zipcodeSearch_click) { 
    	// "우편번호찾기" 을 클릭 안 했을 경우 
    	
    	Swal.fire({		 
	
			     title: '우편번호찿기를 클릭하셔서 우편번호를 입력하셔야 합니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
    	return; // 종료
    }
    else {
    	// "우편번호찾기" 을 클릭을 했을 경우 
    	
        //	const regExp = /^\d{5}$/g;  
		//  또는
		    const regExp = new RegExp(/^\d{5}$/g);  
            //  숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성 
            
            const postcode = $("input:text[id='postcode']").val();
            
            const bool = regExp.test(postcode);
            
            if(!bool) {            	
            	Swal.fire({		 
	
			     title: '우편번호 형식에 맞지 않습니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
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






// 모달창의 "수정하기" 버튼 클릭시 호출되는 함수  
function goEdit() {
	
	const name_length = $("input:text[name='order_name']").val().trim().length;
	      if(name_length < 2) {	    	  
	    	  Swal.fire({		 
	
			     title: '이름 형식에 맞지 않습니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
	    	  $("input:text[name='order_name']").val("").focus();
	    	  return false; // submit 을 하지 않고 종료한다.
	}
	      
    const mobile_length = $("input:text[name='mobile']").val().trim().length;
    if(mobile_length < 11) {
	    
	    Swal.fire({		 
	
			     title: '전화번호 형식에 맞지않습니다',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
	    $("input:text[name='mobile']").val("").focus();
	    return false; // submit 을 하지 않고 종료한다.
    }
    
    const detailAddress_length = $("input:text[name='detail_address']").val().trim().length;
	if(detailAddress_length < 1) {	   
	    Swal.fire({		 
	
			     title: ' 상세주소를 입력하셔야 합니다.',
			     icon: 'info',     
			     confirmButtonColor: '#3085d6',
			     cancelButtonColor: 'dark',
			     confirmButtonText: '확인',
			     
			   })
	    $("input:text[name='detailAddress']").val("").focus();
	    return false; // submit 을 하지 않고 종료한다.
	}
	
    const frm = document.registerFrm;    
    
    
    frm.action = "addressedit.dream";
    frm.method = "post";
    frm.submit();
   
    

	
}// end of function goEdit()----------------------







