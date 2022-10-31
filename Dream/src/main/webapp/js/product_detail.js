let objNo = 0;
let optionArray =[];
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


isLargerThan960 = false;

// Function Declaration


function checkVisible( elm, eval ) {
    eval = eval || "object visible";
    var viewportHeight = $(window).height(), // Viewport Height
        scrolltop = $(window).scrollTop(), // Scroll Top
        y = $(elm).offset().top-112.5,
        elementHeight = $(elm).height();   
    
    if (eval == "object visible") return ((y < (viewportHeight + scrolltop)) && (y > (scrolltop - elementHeight)));
    if (eval == "above") return ((y < (viewportHeight + scrolltop)));
}

function isLargerThan960Check (){
    if($(window).width() > 960) { 		
      isLargerThan960	= true;
    } 
    else{
      isLargerThan960 = false;
    }
    // console.log(isLargerThan960);
    // console.log($(window).width());
    // console.log("크기변경!");

  }

  

$.fn.Scrolling = function(obj, tar) {
// 리사이징 시작       
$(window).resize(function() {
$("#productImage").removeClass("fixed");
isLargerThan960Check();

if(isLargerThan960){
  // 화면 크기가 960 이상일때만
  // console.log(isLargerThan960);
  if(($(window).scrollTop() >= obj) ){
    $("#productImage").addClass("fixed");
    $("#productImage").css("padding-top",0);
  }
}
else{
  $("#productImage").removeClass("fixed");
}  

if($(window).scrollTop() >= end && isLargerThan960){
  $("#productImage").css("padding-top",tar);
  $("#productImage").removeClass("fixed");
  
} 


if (checkVisible($('a#column2_btn_buy'))) {
  $('div.floating_price').css("display","none");
}
else{
  $('div.floating_price').css("display","flex");
}
});
// 리사이징 끝


// 스크롤 이벤트


var end = obj + tar;

$(window).scroll(function(e) {
isLargerThan960Check ();
// isLargerThan960Check



// console.log(checkVisible($('a#column2_btn_wish')));
// 관심등록 보이는가 체크용

// column2 의 구매버튼이 보이지 않으면 상단에 플로팅바 나오도록
if (checkVisible($('a.btn_buy'))) {
  $('div.floating_price').css("display","none");
}
else{
  $('div.floating_price').css("display","flex");
} 

// 캐러셀 fixed 적용/해제
/*console.log(isLargerThan960);
*/
if(isLargerThan960){
// 화면 크기가 960 이상일때만
// console.log(isLargerThan960);
if(($(window).scrollTop() >= obj) ){
  $("#productImage").addClass("fixed");
  $("#productImage").css("padding-top",0);
}
}
else{
$("#productImage").removeClass("fixed");
}  

if($(window).scrollTop() >= end && isLargerThan960){
$("#productImage").css("padding-top",tar);
$("#productImage").removeClass("fixed");

} 



});
};  



$(document).ready(function() {
	    
	
	optionArray =[];
	deleteImageCount = 0;
	
	

	// 제품 페이지 로딩에 필요한 값들 가져오기
	 


	$("#productImage").Scrolling(0 , ($("#column_2").height() - $("#productImage").height()-16));
	// 마지막 p 타입의 마진 16을 더빼줘야 글이랑 위치가 맞음




	$('a.dropdown-item').click(function(e){
		
		const $target = $(e.target);
		const ProductOption= $target.text();
		let alreadyExist = false;
		
		
		if(optionArray.length>0){
			
			optionArray.forEach((item, index)=>{
				
				if(item == ProductOption){
				// 
					toastr["success"]("이미 선택한 옵션입니다. 고르신 옵션의 수량을 1 추가하였습니다.");
			
					Selected = $(`div#optionSelected`).children().eq(index);
					
					SelectedAmount = parseInt(Selected.find("span#optionAmount").text())+1;
					alreadyExist = true;
					Selected.find("span#optionAmount").text(SelectedAmount)	;
				}
		
			});	
			if(!alreadyExist){
				// 반복문 끝나고도 중복이 없다면
				
				$("div#optionSelected").append(
				`<div id='added_option'>
					<span id="optionAmount" class="optionAmount" name="optionAmount">1</span>
					<span id="optionName" name="optionName">${ProductOption}</span>
					<button id="deleteObj${objNo}" class="btn">x</button>
					<button id="minus${objNo}" class="btn">-</button>
					<button id="plus${objNo}" class="btn">+</button>
				</div>`);			
				
				calcbutton(objNo);
				objNo++;	
				optionArray.push(ProductOption);
				alreadyExist = false;
				
				
			}
			
		
		}//end of if(optionArray.length>0){} -------		
		else{	
			
			$("div#optionSelected").append(
				`<div id='added_option'>
					<span name = "optionAmount" id="optionAmount" class="optionAmount">1</span>
					<span name="optionName" id="optionName">${ProductOption}</span>
					<button id="deleteObj${objNo}" class="btn">x</button>
					<button id="minus${objNo}" class="btn">-</button>
					<button id="plus${objNo}" class="btn">+</button>
				</div>`);	
		//	console.log($("div#optionSelected"));
			calcbutton(objNo);
			objNo++;	
			
			optionArray.push(ProductOption);
		}
		calcbutton(objNo);
		HowMuchIsit();
		
	});	
	
	// 제품 정보수정 모달에서 파일이름 클릭시 그에 맞는 이미지 띄우기
	$("a.modalimage").click(function(e){
		$("button#ximage").show();
		const $target = $(e.target);
		let src = $target.text();	
		let newimage = getContextPath()+"/images/제품이미지/"+src;

	// PNG 확장자 대문자

		$("#modalImage>img").attr("src",newimage);

	});
	
	// 제품 정보수정 모달에서 이미지 우측상단 x 클릭시 그에 해당하는 파일이름 없애고, hiddeninput 에 없앨 값 추가하기
	$("button.ximage").click(function(e){
		const $target = $(e.target);
		let src = $target.text();
		let length = $("a.modalimage").not('a.hide').length;
		let real_length = $("a.modalimage").length;
	//	console.log("length"+length);
		if(length > 1){
			
			let modalImage = $("#modalImage>img").attr("src");	
	
			last = modalImage.lastIndexOf('/');
			FindVlaue = modalImage.substring(last+1);

			let selected =  document.getElementsByName("src");
			
			let findsrc ; 
			
			let html="";
			html +=`<input type="hidden" name="delete${deleteImageCount}" value="${FindVlaue}"  />`;
			
	//		console.log("html"+html);
			
			$("div.delete_image").append(html);

			deleteImageCount ++;
			$("input#delete_length").val(deleteImageCount);
		//	console.log(deleteImageCount);
			$("button#ximage").hide();
			
			for(let i = 0; i < real_length; i++) {

			 if (FindVlaue == selected[i].textContent){
					findsrc = selected[i].getAttribute("id");
				//   console.log(findsrc);
				    $("a#"+findsrc).addClass('hide');
					return false;
				}
			}// end of for
			
			
		}
		else{// 갯수가 1개 이하
			
			toastr["success"]("최소 1개의 이미지는 가지고 있어야 합니다.");
			
		}

	});
	
	      $("span.error").hide();
      
      // 제품수량에 스피너 달아주기
      $("input#spinnerPqty").spinner({ 
         // spinner에 1 이상만 입력받도록
         spin:function(event,ui){
               if(ui.value > 100) {
                  $(this).spinner("value", 100);
                  return false;
               }
               else if(ui.value < 1) {
                  $(this).spinner("value", 1);
                  return false;
               }
            }
      });// end of $("input#spinnerPqty").spinner({ }-------------------------------
            
      // 추가이미지파일에 스피너 달아주기
      $("input#spinnerImgQty").spinner({ 
         // spinner에 1 이상만 입력받도록
         spin:function(event,ui){
               if(ui.value > 10) {
                  $(this).spinner("value", 10);
                  return false;
               }
               else if(ui.value < 0) {
                  $(this).spinner("value", 0);
                  return false;
               }
            }
      });// end of $("input#spinnerImgQty").spinner({ }-------------------------------

	  $("input#spinnerImgQty").bind("spinstop", function(){
         
         let html ="";
         const cnt = $(this).val(); // input 태그에 입력된 수량을 알아온다.(반복횟수)
         
         // console.log("확인용 cnt : "+ cnt);
         // console.log("확인용 typeof cnt : "+ typeof cnt);
         // 확인용 typeof cnt : string
         
         for(let i=0; i< Number(cnt); i++){
            html += "<br>";
            html += "<input type='file' name='attach"+i+"' class='btn btn-default' />";
         }// end of for---------------------------------------
         
         $("div#divfileattach").html(html);
         $("input#attachCount").val(cnt);
      });
      
      // 수정 버튼 클릭시
      $("a.btn_edit").click(function(e){
	  	// 입력가능한 모든칸을 다 비워주기
	 	 $("input#product_name").val("");
	 	 $("input#price").val("");
	 	 $("input#discount_rate").val("");
	 	 $("input#product_content").val("");
		 $("input#spinnerImgQty").val("0");
		 $("input#attachCount").val("0");
		 $("div#divfileattach").empty();
		 		 
		 // error 전부 지워주기
		 $("span.modal_error").hide();
	  
	  })// end of    $("button.btn_edit").click(function(e){} -----
  	
	// 유효성 검사 (정규식)
	
	$(document).on("keyup","input#product_name",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

	    const regExp =  /^.{0,30}$/;    
	    const bool = regExp.test( $(e.target).val() );       

	    if(!bool) {
		
	        $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
	        $("span.product_name_error").show();  //에러문구
	        $("input#product_name").css("color","red");  //라벨 빨간색 	        	        
	    }
	    else {
	        $(e.target).css("border-bottom","solid 1px #ebebeb");
	        $("span.product_name_error").hide();
	        $("input#product_name").css("color","black");  
 
	    }

	});// end of $(document).on("keyup","input#product_name",function(e) {
	
	$(document).on("keyup","input#price",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

	    const regExp = /^[0-9]{0,20}$/;   
	    const bool = regExp.test( $(e.target).val() );       

	    if(!bool) {
		
	        $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
	        $("span.price_error").show();  //에러문구
	        $("input#price").css("color","red");  //라벨 빨간색 	        	        
	    }
	    else {
	        $(e.target).css("border-bottom","solid 1px #ebebeb");
	        $("span.price_error").hide();
	        $("input#price").css("color","black");  
 
	    }

	});// end of $(document).on("keyup","input#product_name",function(e) {
	
	$(document).on("keyup","input#discount_rate",function(e) { // 성명 2글자 이상 50글자 이하 유효성 검사 처리

	    const regExp = /^(0|0[.][0-9]{1,2})$/;   
	    
	    const bool = regExp.test( $(e.target).val() );       

	    if(!bool) {
		
	        $(e.target).css("border-bottom","solid 1px red");  //빨간색 밑줄
	        $("span.discount_rate_error").show();  //에러문구
	        $("input#discount_rate").css("color","red");  //라벨 빨간색 	        	        
	    }
	    else {
	        $(e.target).css("border-bottom","solid 1px #ebebeb");
	        $("span.discount_rate_error").hide();
	        $("input#discount_rate").css("color","black");  
 
	    }

	});// end of $(document).on("keyup","input#product_name",function(e) {
	
	
		
}); // end of doc어쩌구

function calcbutton(objNo){
	
	let count = objNo;
	// console.log("objNo"+objNo);
	// 플러스 버튼 누르면
	$(`button#plus${count}`).click(function(){
		Selected = $(`button#plus${count}`).parent().children("#optionAmount");
		
		SelectedAmount = parseInt(Selected.text())+1;
		Selected.text(SelectedAmount.toLocaleString('ko-KR'));		
		// 금액 변경 반영	
		HowMuchIsit();
	});
	$(`button#minus${objNo}`).click(function(){
		Selected = $(`button#minus${objNo}`).parent().children("#optionAmount");
		
		SelectedAmount = parseInt(Selected.text())-1;
		if(SelectedAmount <= 0){
			toastr["success"]("0개 이하로 줄일 수 없습니다.");
			
		}	
		else{
			Selected.text(SelectedAmount);	
		}
		// 금액 변경 반영	
		HowMuchIsit();
	});
	
	$(`button#deleteObj${objNo}`).click(function(){
		Selected = $(`button#deleteObj${objNo}`).parent();
		deleteText = $(`button#deleteObj${objNo}`).parent().children("#optionName").text();
		Selected.remove();
	
		for(let i = 0; i < optionArray.length; i++) {
		 	 if(optionArray[i] == deleteText)  {
		    optionArray.splice(i, 1);
		    i--;
	  		}
		}
		// 금액 변경 반영	
		HowMuchIsit();
	
	});
	
}



// 좋아요 누르면
function likeCheck() {
	
	const userid = $("input#userid").val();
	if(userid == ""){
		location.href =getContextPath() +"/login/login.dream";
		return false;
	}
	
	const productNum = $("dd#product_num").text();

	$.ajax({
		url:getContextPath()+"/member/likeCheck.dream",
		data:{"userid":userid
			 ,"productNum":productNum},  // data 는 /MyMVC/member/emailDuplicateCheck_2.up 로 전송해야할 데이터를 말한다. 
		type:"post",
		dataType:"json",
    //	async:true,   // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
                      // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다. 
        success:function(json){
        	
        	if(json.resultSuccess=="true") {
        		if(json.resultType == "add"){
					toastr["success"]("상품 좋아요 성공하였습니다.", "좋아요 성공");
					$("span.wish_count_num").text(json.count);
					$("i#likeicon").addClass('pink');
				}else{
					toastr["success"]("상품 좋아요 해제하였습니다.", "좋아요 해제");
					
					$("span.wish_count_num").text(json.count);
					$("i#likeicon").removeClass('pink');
				}
        		
        	}
        	
        	else {
        		
        	}
        	
        },
        error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
		
}// end of function isExistEmailCheck()-------------------------	
	

function goPurchasePage(){
	
	// 일단 선택한 옵션의 사이즈 배열과 갯수 배열을 각각 만들어보자자자자자자자자자ㅏ자자자ㅏ자자
	const userid = $("input#userid").val();
	if(userid != ""){
		let length = $("div#added_option").length;
		
		if(length == 0){
			toastr["success"]("옵션을 선택해주세요!");
		
			return false;
		}
		let added_optionName = document.getElementsByName("optionName");
		//console.log("length"+length);
		const productNum = $("dd#product_num").text();
		let added_optionAmount = document.getElementsByName("optionAmount");
		
		let sizeArray =[];
		let cntArray =[];
		
		let isitOkay = true;
		let option_size = "";
		let count = 0;
		
		// 선택한 옵션의 수 만큼 반복
		for(let i = 0; i < length; i++) {
			 sizeArray.push(added_optionName[i].textContent);
			 //console.log(sizeArray[i]);
			 cntArray.push(added_optionAmount[i].textContent);
			}
			
		// 선택한 옵션의 순서대로 옵션의 사이즈, 개수가 같은 인덱스로 들어감
		// 이 값을 구매버튼 누르는 순간의 재고와 비교할 것 json 쓰자자자자자자ㅏ자자자ㅏㅈ
			//console.log("sizeArray"+sizeArray);
			//console.log("cntArray"+cntArray);
			//console.log(Array.isArray(sizeArray) ); 
	
			
		$.ajax({
			url:getContextPath()+"/product/cntCheck.dream",
			data:{"productNum":productNum},  // data 는 /MyMVC/member/emailDuplicateCheck_2.up 로 전송해야할 데이터를 말한다. 
			type:"post",
			dataType:"json",
	    //	async:true,   // async:true 가 비동기 방식을 말한다. async 을 생략하면 기본값이 비동기 방식인 async:true 이다.
	                      // async:false 가 동기 방식이다. 지도를 할때는 반드시 동기방식인 async:false 을 사용해야만 지도가 올바르게 나온다. 
	        success:function(json){
		
				//console.log((json.size));
				//console.log(json.cnt);
				
				 outer : for(let i = 0; i < length; i++) {
				 option_size = added_optionName[i].textContent;
				 option_cnt = Number(added_optionAmount[i].textContent);
				  
				 // console.log("option_size"+option_size);
				 	for(let n=0; n<(json.size).length; n++){
						if(option_size == json.size[n]){
							count++;
							// console.log(option_size+"의 재고는"+json.cnt[n]+"개 입니다.")
							if(option_cnt > Number(json.cnt[n])){
								
								isitOkay = false;
								toastr["success"](option_size+"의 재고는"+json.cnt[n]+"개 입니다. 그 이하로 주문해주세요.");
								
									
								break outer; 
							}
							
						}
					}
				 
				 }// end of outer
				 
				 if(count != length){
					// 옵션의 갯수만큼 체크하지 않았다면 확인
					// console.log("!!!");
					isitOkay = false;
					toastr["success"]("데이터를 확인하는 중 문제가 발생하였습니다. 잠시후 다시 시도해주세요.");
					
				}
				
				if(isitOkay){
					
					
					// console.log("성공")
					//	frm.asdsadasdas 예정;
					
					let html = "";
					
					for(let i=0; i<length; i++){
						option_size = added_optionName[i].textContent;
				 		option_cnt = Number(added_optionAmount[i].textContent);
				 
				 		html += 
						`<input type="hidden" name="size${i}" value="${option_size}"  />
						 <input type="hidden" name="cnt${i}" value="${option_cnt}"  /> `
						} // end of for
					
					html +=`<input type="hidden" name="length" value="${length}"  />`
					
					// console.log(html);
					
					$("div#asd").empty();
					
					var frm = document.temporaryCart; 
					$("form#temporaryCart>div#asd").append(html);
					frm.method = "post";
					frm.action = getContextPath()+"/member/purchasePage.dream";
			
				    
				    frm.submit();
					
				}// end of if(isitOkay)
		        	
	        }// end of succes
	        ,error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});

	}
	else{
		location.href =getContextPath() +"/login/login.dream";
	}

	
		
	}// end of function goPurchasePage()-----------------
	
		// 선택갯수 * 할인적용금액 된 가격을 구해서 넣어주는 함수
	function HowMuchIsit() {
		
		let added_optionAmount = document.getElementsByName("optionAmount");
		/*let addArr = Array.from(added_optionAmount);
		console.log(typeof(addArr));
		console.log("확인용 배열"+addArr);
		console.log("확인용 배열 길이"+addArr.length);
		*/
		let sum=0;
	
		for(let i=0; i< added_optionAmount.length; i++){
			Selected = Number(added_optionAmount[i].textContent);
			sum+=Selected;
		}
		
		//	Selected = added_optionAmount[1].textContent;
		
		//	sum += Number(Selected);
		let discount_price = Number($("input#discountPrice").val());
		// console.log(discount_price);
			
		let price = sum * discount_price;
		$("input#fullPrice").val(price);
		
		$("em.num").text(price.toLocaleString('ko-KR')+"원");
		
	};
	
	// 장바구니에 추가해주는 버튼
	function updateCart(){
		// 일단 선택한 옵션의 사이즈 배열과 갯수 배열을 각각 만들어보자자자자자자자자자ㅏ자자자ㅏ자자
	const userid = $("input#userid").val();

	if(userid!= ""){
		
		let length = $("div#added_option").length;
		

		if(length == 0){
			toastr["success"]("옵션을 선택해주세요!");
			return false;
		}
		let added_optionName = document.getElementsByName("optionName");
		
		const productNum = $("input#productNum").val();
		
		let added_optionAmount = document.getElementsByName("optionAmount");

		let sizeArray =[];
		let cntArray =[];


		// 선택한 옵션의 수 만큼 반복
		for(let i = 0; i < length; i++) {
			
			
			 sizeArray.push(added_optionName[i].textContent);
			 cntArray.push(added_optionAmount[i].textContent);
			}

		// 선택한 옵션의 순서대로 옵션의 사이즈, 개수가 같은 인덱스로 들어감
		// 이 값을 구매버튼 누르는 순간의 재고와 비교할 것 json 쓰자자자자자자ㅏ자자자ㅏㅈ
		/*
			console.log("sizeArray"+sizeArray);
			console.log("cntArray"+cntArray);
			console.log("productNum"+productNum);
			console.log("userid"+userid);
	*/
	
	   
			let JSONsizeArray= JSON.stringify(sizeArray);
			// console.log("JSONsizeArray"+JSONsizeArray);
			let JSONcntArray= JSON.stringify(cntArray);

			
		$.ajax({
			url:getContextPath()+"/cart/updateCart.dream",
			traditional: true,
			data:{	"productNum":productNum,
					"userid":userid,
					"sizeArray":JSONsizeArray,
					"cntArray":JSONcntArray,
					"length":length},   
					
			type:"post",
			dataType:"json",
			// 카트에 담을만큼 재고가 있는지 먼저 체크
	        success:function(json){
				
				if(json.isItOk){
					toastr["success"]("장바구니에 성공적으로 추가되었습니다.");
				}
				else{
					toastr["success"]("선택하신 제품의 재고가 부족합니다.");
				}
	        }// end of succes
	        ,error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		});
		
	}// end of if(userid != null)
	else{ // 로그인 안한 사람
		location.href =getContextPath() +"/login/login.dream";
	}
	
}

function goUpdateProduct(){
	
	  const regExp1 =  /^.{0,30}$/;    
	  const bool1 = regExp1.test( $("input#product_name").val() );    
	
	  const regExp2 =  /^[0-9]{0,20}$/;    
	  const bool2 = regExp2.test( $("input#price").val() );    
	
	  const regExp3 =  /^(0|0[.][0-9]{1,2})$/;    
	  const bool3 = regExp3.test( $("input#discount_rate").val() );  
	    
	if((bool1 || $("input#product_name").val() == "" )&& (bool2 || $("input#price").val() == "" )&& (bool3 || $("input#discount_rate").val() == "")){// 정규식 다 만족하거나 다 빈칸으로 둔 상태
		let length = $("a.modalimage").not('a.hide').length;
		let real_length = $("a.modalimage").length;
		
		if($("input#product_name").val() == "" && $("input#price").val() == "" && $("input#discount_rate").val() == "" && $("input#product_content").val()==""
			&& length == real_length && $("input#attachCount").val() ==0)
		{// 변경점 없고 추가이미지도 없으며 기존 이미지를 삭제하지도 않는다면
			toastr["success"]("변경 사항이 존재하지 않습니다");
	
		}
		else{
			let html="";
		
	
			let live_image = $("a.modalimage").not('a.hide');
			//console.log(live_image);
			let length =$("a.modalimage").not('a.hide').length;
		
					
			let val = "";
			let count =0;
			for(let i = 0; i < length; i++) {
				val = live_image[i].getAttribute("value");
			//	console.log(live_image[i].getAttribute("value"));
				html +=`<input type="hidden" name="image${i}" value="${val}"/>`;
				count++;
			}// end of for
			html +=`<input type="hidden" name="length" value="${count}"/>`;
				
			// console.log(html);
			$("div.delete_image").append(html);
			
			var frm = document.editProductFrm; 
			// 보내기전 빈값이면 원래값 넣어주기
			if($("input#product_name").val() == ""){
				$("input#product_name").val( $("div.main_title>p#product_name").text());	
			}
		//	console.log("product_name"+$("input#product_name").val());
			
			if($("input#price").val() == ""){
				$("input#price").val( $("div.amount>span#price").text());	
			}
	//		console.log("price"+$("input#price").val());
			
			if($("input#discount_rate").val() == ""){
				$("input#discount_rate").val( $("input#discount_rate_origin").val());	
			}
		//	console.log("discount_rate"+$("input#discount_rate").val());
			
			if($("input#product_content").val() == ""){
				$("input#product_content").val( $("input#product_content_origin").val());	
			}
		//	console.log("product_content"+$("input#product_content").val());
			
			
			
			frm.method = "post";
			frm.action = getContextPath()+"/product/updateProduct.dream";
			
		    
		    frm.submit();
	
		}
	}
	else{
		toastr["success"]("조건에 맞는 값을 입력해주세요!");
		
	}
	
	
	
	
	
	

}


function deleteProduct(){
	
	

	    
		var frm = document.temporaryCart; 
	
		frm.method = "post";
		frm.action = getContextPath()+"/product/deleteProduct.dream";

	    
	    frm.submit();

}


