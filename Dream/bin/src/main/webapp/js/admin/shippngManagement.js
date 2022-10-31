function getContextPath() {
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	let contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
	return contextPath;
}

let totalPage;
let lenHIT = 5;
// HIT 상품 "스크롤" 할때 보여줄 상품의 개수(단위)크기 
let start = 1;



function allCheckBox() {
      let bool = $("#allCheckSend").is(":checked");
      $(".postSend").prop("checked", bool);
}// end of function allCheckBox()-------------------------

function allCheckBox2() {
      let bool = $("#allCheckEnd").is(":checked");
      $(".postEnd").prop("checked", bool);
}// end of function allCheckBox()-------------------------

function checkedSend(){
	let bFlag = false;
         $(".postSend").each(function(){
            let bChecked = $(this).prop("checked");
            if(!bChecked) {
               $("#allCheckSend").prop("checked",false);
               bFlag = true;
               return false;
            }
         });
         
         if(!bFlag) {
            $("#allCheckSend").prop("checked",true);
         }
}

function checkedEnd(){
         let bFlag = false;
         $(".postEnd").each(function(){
            let bChecked = $(this).prop("checked");
            if(!bChecked) {
               $("#allCheckEnd").prop("checked",false);
               bFlag = true;
               return false;
            }
         });
         
         if(!bFlag) {
            $("#allCheckEnd").prop("checked",true);
         }
	
}

$(document).ready(function() {
	
	
	$(document).on("click","input.postSend",function(e){
         
         let bFlag = false;
         $(".postSend").each(function(){
            let bChecked = $(this).prop("checked");
            if(!bChecked) {
               $("#allCheckSend").prop("checked",false);
               bFlag = true;
               return false;
            }
         });
         
         if(!bFlag) {
            $("#allCheckSend").prop("checked",true);
         }
      });
	
	
	//  $("div#shipping_cnt_left").trigger("click"); 
	
      
      
	//$("span#totalHITCount").hide();
	// $("span#countHIT").hide();
	
	// callAjax(start); // 맨 처음 8개를 출력
	
	// HIT 상품 게시물을 더보기 위하여 "더보기..." 버튼 클릭액션 이벤트 등록하기 
	$("button#btnMoreHIT").click(function(){ 
		   if( $(this).text() == "처음으로" ) {
			   $("div#show_shipping").empty();           // append 된 상품들 삭제 
	           $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
	           $("span#end").empty();
			   callAjax("1");
			   $(this).text("더보기");
		   }
		   else {
			   callAjax($(this).val());
		   }
	});
	
	// DatePicker 한글, 기타 속성 시작
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd' //Input Display Format 변경
		, showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		, showMonthAfterYear: true //년도 먼저 나오고, 뒤에 월 표시
		, changeYear: true //콤보박스에서 년 선택 가능
		, changeMonth: true //콤보박스에서 월 선택 가능    
		// input 태그 옆 달력 아이콘 비활성화           
		// ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
		// ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
		// ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
		, buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
		, yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
		, monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'] //달력의 월 부분 텍스트
		, monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip 텍스트
		, dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 부분 텍스트
		, dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 부분 Tooltip 텍스트
	});


	//input을 datepicker로 선언
	$("input#start_date").datepicker();
	$("input#end_date").datepicker();

	//From의 초기값을 오늘 날짜로 설정
	$('input#start_date').datepicker('setDate', '-2M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
	$('input#end_date').datepicker('setDate', 'today');
	// DatePicker 한글, 기타 속성 끝

	
	// 진행중 클릭시 테두리, 글자 속성 변경 
	$("div#shipping_cnt_left").on("click",function(e) {
		
		$("div#show_shipping").empty();           // append 된 상품들 삭제 
        $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
        $("span#countHIT").text("0");
		
		$("div#shipping_cnt_right").css("border", "");
		$("div#shipping_cnt_middle").css("border", "");
		$("div.shipping_status").css({ "font-weight": "", "color": "" });
		$("div#shipping_cnt_right > div.purchase_count").css("color", "black");
		$("div#shipping_cnt_middle > div.purchase_count").css("color", "black");
		
		$("div#shipping_cnt_left").css("border-bottom", "solid 2px #222");
		$("div#shipping_cnt_left > div.purchase_count").css("color", "#f15746");
		$("div#shipping_cnt_left > div.shipping_status").css({ "font-weight": "700", "color": "#222" });
		
		$("div#show_shipping_completed").hide();
		$("div#show_shipping_processing").hide();
		$("div#show_shipping").show();
		
		// div가 클릭되면 select 해오기 위해 사용되는 배송중 상태를 input태그(type="hidden")에 값 넣기
		$("div#status_button> input#input_shipping").val("0");
		
		$("div.div_postSend").show();
		$("div.div_postEnd").show();
		
		
		// goSearch();
		callAjax(start);
	});
	
	
	// 배송중 클릭시 
	$("div#shipping_cnt_middle").on("click",function(e) {
		
		$("div#show_shipping").empty();           // append 된 상품들 삭제 
        $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
        $("span#countHIT").text("0");
		
		$("div#shipping_cnt_right").css("border", "");
		$("div#shipping_cnt_left").css("border", "");
		$("div.shipping_status").css({ "font-weight": "", "color": "" });
		$("div#shipping_cnt_right > div.purchase_count").css("color", "black");
		$("div#shipping_cnt_left > div.purchase_count").css("color", "black");
		
		$("div#shipping_cnt_middle").css("border-bottom", "solid 2px #222");
		$("div#shipping_cnt_middle > div.purchase_count").css("color", "#f15746");
		$("div#shipping_cnt_middle > div.shipping_status").css({ "font-weight": "700", "color": "#222" });
	
		$("div#show_shipping").show();
		$("div#show_shipping_completed").hide();
		$("div#show_shipping_processing").show();
		// div가 클릭되면 select 해오기 위해 사용되는 배송중 상태를 input태그(type="hidden")에 값 넣기
		$("div#status_button> input#input_shipping").val("1");
		
 		$("div.div_postSend").hide();
 		$("div.div_postEnd").show();
		
		// goSearch();
		callAjax(start);
	});
	
	
	

	// 종료 클릭시 테두리, 글자 속성 변경 
	$("div#shipping_cnt_right").on("click",function(e) {
		
		$("div#show_shipping").empty();           // append 된 상품들 삭제 
        $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
        $("span#countHIT").text("0");
		
		$("div#shipping_cnt_left").css("border", "");
		$("div#shipping_cnt_middle").css("border", "");
		$("div.shipping_status").css({ "font-weight": "", "color": "" });
		$("div#shipping_cnt_left > div.purchase_count").css("color", "black");
		$("div#shipping_cnt_middle > div.purchase_count").css("color", "black");
		$("div#shipping_cnt_right").css("border-bottom", "solid 2px #222");
		$("div#shipping_cnt_right > div.purchase_count").css("color", "#f15746");
		$("div#shipping_cnt_right > div.shipping_status").css({ "font-weight": "700", "color": "#222" });
	
		$("div#show_shipping").show();
		$("div#show_shipping_processing").hide();
		$("div#show_shipping_completed").show();

		// div가 클릭되면 select 해오기 위해 사용되는 배송중 상태를 input태그(type="hidden")에 값 넣기
		$("div#status_button> input#input_shipping").val("2");
		
		$("div.div_postSend").hide();
		$("div.div_postEnd").hide();
		
		// goSearch();
		callAjax(start);

	});

	//구매일 버튼 클릭시 아이콘 변경 
	$("button#btn_purchaseDate").click(function(e) {

		
		 $("div#show_shipping").empty();           // append 된 상품들 삭제 
         $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
		
		
		let sort = $("input#sort_date");

		$("button.result_detail").css("font-weight", "");
		$("button#btn_purchaseDate").css("font-weight", "bold");

		if ($("button#btn_purchaseDate > i").hasClass("fa-sort-up")) {
			$("button#btn_purchaseDate > i").removeClass("fa-sort-up").addClass("fa-sort-down");
			$("input#sort_date").val("desc");
			// alert(sort.val());
		
		}
		else {
			$("button#btn_purchaseDate > i").removeClass("fa-sort-down").addClass("fa-sort-up");
			sort.prop('value', "desc");
			$("input#sort_date").val("asc");
			// alert(sort.val());
		
		}
		// sort.val()에 저장된  asc / desc 전달
		
		//goSearch();
		callAjax(start);
	});

	// 최근 2개월, 4개월 , 6개월 버튼 클릭시 ===========================================================
	// 최근 2개월 버튼 클릭시 발생하는 이벤트
	$("button#two_month").click(function(e) {
		// alert("최근 2개월 버튼 클릭시");
		$('input#start_date').datepicker('setDate', '-2M');
		$('input#end_date').datepicker('setDate', 'today');
		$("button#search_simple").trigger('click'); // 조회버튼 클릭 
	

	});

	// 최근 4개월 버튼 클릭시 발생하는 이벤트
	$("button#four_month").click(function(e) {
		// alert("최근 4개월 버튼 클릭시");
		$('input#start_date').datepicker('setDate', '-4M');
		$('input#end_date').datepicker('setDate', 'today');
		$("button#search_simple").trigger('click'); // 조회버튼 클릭 

	});

	// 최근 6개월 버튼 클릭시 발생하는 이벤트
	$("button#six_month").click(function(e) {
		// alert("최근 6개월 버튼 클릭시");
		$('input#start_date').datepicker('setDate', '-6M');
		$('input#end_date').datepicker('setDate', 'today');

		$("button#search_simple").trigger('click'); // 조회버튼 클릭 

	});
	// 최근 2개월, 4개월 , 6개월 버튼 클릭시 ===========================================================


	// 날짜 입력 후 조회 버튼클릭시
	$("button#search_simple").click(function(e) {
		$("div#show_shipping").empty(); // div 초기화 
			$("div#show_shipping_completed").empty();	// div 초기화 
			$("span#countHIT").text("0");
		
		// goSearch();
		callAjax(start);
	});

	// 태블릿, 모바일에서 기간 조회 버튼 클릭시 
	$("button#search_date").click(function(e) {
		// 선택된 option 태그 value 갖고오기
		let date_selected = $("select#select_month option:selected").val();

		// 올바른 기간이 선택된 경우에만 기간조회 진행 
		if (date_selected != '기간선택') {

			let year = date_selected.substring(0, 4);
			
			let month = date_selected.substring(6, date_selected.indexOf("월"));
			if(month < 10){
				month = "0"+month;
			}
			
			// 해당 월의 마지막일 구하기 
			var date = new Date(year, month, 0);
			date.getDate(); //마지막 날짜
			
			// console.log("확인용"+month+"월의 마지막 날짜 : "+date.getDate());
			
			start_date = year+"/"+month+"/01";
			end_date = year+"/"+month+"/"+date.getDate();
			
			$("input#start_date").val(start_date);
			$("input#end_date").val(end_date);
			
			// alert("모바일에서 start_date => "+start_date);
			// alert("모바일에서 end_date => "+end_date);
		}
		$("button#search_simple").trigger("click");
	});

	// select 태그 클릭 클릭시 기간선택 option hidden 처리하기
	$("select#select_month").click(function(e) {
		$("select#select_month").children("option[value='기간선택']").prop("hidden", true);
	});


	/* 사이드바 script 시작 */
	$("div.category-section > ul > li").click(function(e) {
		//  클릭할 경우 글자 css 변경
		alert("클릭됨.");
		$("div.category-section > ul > li > a").css({ "font-weight": "", "color": "" });
		$(e.target).css({ "font-weight": "700", "color": "black" });
	});
	/* 사이드바 script 끝  */


	/* 현재 날짜 구하기 */
	let today = new Date();

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월

	/* 모바일 화면에서 기간선택 클릭 시 나오는 년도/월 현재날짜 기준으로 입력 */
	for (let i = 2; i < 9; i++) {
		$("select#select_month > option:nth-child(" + i + ")").text(year + "년 " + (month - (i - 2)) + "월");
	}

});// end of $(document).ready(function()----------------------------------

/*
function goSearch(){
	const frm = document.purchaseFrm;
	frm.action = getContextPath()+"/admin/shippingMangement.dream"; 
	frm.method = "POST";
	frm.submit();
}
*/



function updateShipping() {

	let ordernum_arr = [];
	for (let i = 0; i < $("input#jsonLength").val(); i++) {

		if ($("input:checkbox[name='send']").eq(i).is(":checked")) {
			ordernum_arr[i] = $("input:checkbox[name='send']").eq(i).val();
		}
	}

	console.log("확인용" + ordernum_arr);

	// 결과 값 찍어주는 ajax
	$.ajax({
		url: getContextPath() + "/admin/shippingMangementUpdate.dream",
		type: "GET",
		dataType: "json",
		traditional: true, //필수
		data: {
			"shipping": "0",
			"ordernum": JSON.stringify(ordernum_arr)
		},
		success: function(json) {
			if (json.result == 1) {
				window.location.reload();
			}

			// 조회결과가 있는 경우 
		}, error: function(request, status, error) {
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	});// end of ajax(){};===========================================================


}// end of function updateShipping(){}--------------------------------------------------

function updateShippingEnd(){
	
	let ordernum_arr = [];
	for (let i = 0; i < $("input#jsonLength").val(); i++) {
		
		if( $("input:checkbox[name='end']").eq(i).is(":checked") ){  
			ordernum_arr[i] = $("input:checkbox[name='end']").eq(i).val();
		}
	}  
	
	    console.log("확인용"+ordernum_arr);
	  
   	// 결과 값 찍어주는 ajax
   	$.ajax({
   		url: getContextPath()+"/admin/shippingMangementUpdate.dream",
   		type: "GET",
   		dataType: "json",
   		traditional : true, //필수
   		data: {"shipping": "1",
   			   "ordernum": JSON.stringify(ordernum_arr)},
   		success: function(json) {
	
			if(json.result == 1){
			   window.location.reload();
   			}
   			// 조회결과가 있는 경우 
   		},error: function(request, status, error) {
   			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
   		}
   	});// end of ajax(){};===========================================================
   				 
	
	
} // end of function updateShippingEnd(){}----------------------------------



function callAjax(start) {
	  
   	// 결과 값 찍어주는 ajax
   	$.ajax({
   		url: getContextPath()+"/admin/shippingMangementView.dream",
   		type: "GET",
   		data: {"end_date": $("input#start_date").val(),
   			   "start_date": $("input#end_date").val(),
   			   "input_shipping": $("input#input_shipping").val(),
   			   "sort": $("input#sort_date").val(),
   			   "userid": $("input#userid").val(),
   			   "start":start,
   			   "lenHIT":lenHIT},
   		dataType: "json",
   		success: function(json) {
   			
   			let html =" ";
   			
   			// 조회결과가 업는 경우 
   			if(start == "1" && json.length == 0){
   				$("div#show_shipping").empty(); // div 초기화 
   				$("div#show_shipping_completed").empty();	// div 초기화 
   				$("div#no_result").html("거래 내역이 없습니다.");
   				$("button#btnMoreHIT").hide();
   			}
   			// 조회결과가 있는 경우 
   			else if(json.length > 0){
   				$("div#show_shipping").empty();
   				$("button#btnMoreHIT").show();
   				
   				$("div#no_result").html("");
   				
   				 $.each(json, function(index, item){
   					$("span#totalHITCount").text(item.totalListCnt);
   					html += "<div class='purchase_item my-1' style=' border-bottom: solid 1px #ebebeb; border-top: solid 1px #ebebeb;'>"+
   		                     "<div id='purchase_detail'  class='d-flex'>"+
	   		                        "<div class='image_box' style='line-height: 110px;'>"+
	   		                           "<img class='product_img' src="+getContextPath()+"/images/제품이미지/"+item.product_image+" alt='...'>"+
	   		                        "</div>"+
	   		                        "<div id = 'name_cnt'>"+
	   		                           "<div class='item_name'>"+item.product_name+" </div>"+
	   		                        "</div>"+
	   		                         "<div class='order_num'>[ D - "+item.order_num+" ]</div>"+
	   		                         "<div class='purchase_cnt' style='width:50px;'>"+item.buy_cnt+" 개 </div>"+
	   		                        "<div class='totalPrice' >"+(item.buy_cnt*item.salePrice).toLocaleString("en")+"</div>"+
	   		                        "<div class='totalPoint' >"+(item.point).toLocaleString("en")+" p</div>"+
   		                     "</div>"+
   		                     "<div id='date_status'>"+
	   		                        "<div class = 'div_shipping_status'>"+
	   		                           "<span class='purchase_status'>"+item.shipping+"</span>"+ 
	   		                        "</div>"+
	   		                        	"<div class='checkboxAndlabel div_postSend'>"+
											"<input value='"+item.order_num+"' id='send"+index+"' class='postSend' type='checkbox' name='send' />"+
										/*	"<input id='send"+index+"' class='postSend' type='checkbox' name='send"+index+"' onclick='checkedSend()' />"+*/
											"<label class='textMiddle' for='send"+index+"'>배송하기</label>"+
										"</div>"+
										"<div class='checkboxAndlabel div_postEnd'>"+
											"<input value='"+item.order_num+"' id='end"+index+"' class='postEnd' type='checkbox' name='end' onclick='checkedEnd()' />"+
											"<label class='textMiddle' for='end"+index+"'>배송완료</label>"+
										"</div>"+
										"<input type='hidden' id='jsonLength' value='"+json.length+"'/>"+
	   		                        
		   		                     "<div class = 'div_purchase_date'>"+
			                           "<span class='purchase_date'>"+item.buy_date+"</span><br>"+
			                         "</div>"+
   		                     "</div>"+
   		                     "<div>"+
								"<div>"+ 
								"</div>"+
   		                     "</div>"+
   		               "</div>";
   		               
   				 })// end of $.each(json, function(index, item){}---------------------------
   			
   				//$("div#show_shipping").empty(); // div 초기화 
   				//$("div#show_shipping_completed").empty();		
   				
   				// 배송상태에 따라 다른 div에 append ///
   					$("div#show_shipping").append(html); // div 값 입력
   					/*
   				if($("input#input_shipping").val()==0){
   					$("div#show_shipping_completed").empty();	
   					
   				}
   				else if($("input#input_shipping").val()==1){
   					$("div#show_shipping").empty(); // div 초기화 
   					$("div#show_shipping_completed").append(html); // div 값 입력
   				}
   					*/
   			 // HIT 상품 결과를 출력하기
		    	// >>> !!! 중요 !!! 더보기... 버튼의 value 속성에 값을 지정하기 <<< //
				$("button#btnMoreHIT").val( Number(start) + lenHIT);
				
				// span#countHIT 에 지금까지 출력된 상품의 개수를 누적해서 기록한다.
				$("span#countHIT").text( Number($("span#countHIT").text()) + json.length );
				
				// 더보기... 버튼을 계속해서 클릭하여 countHIT 값과 totalHITCount 값이 일치하는 경우 
				if( $("span#countHIT").text() == $("span#totalHITCount").text() ) {
					$("button#btnMoreHIT").text("처음으로");
					$("span#countHIT").text("0");
				}
   				
   				
   			}
   		}, // end of success
   		error: function(request, status, error) {
   			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
   		}
   	});// end of ajax(){};===========================================================
   				 
}  //end of function callAjax() {}-----------------------		
		
		
