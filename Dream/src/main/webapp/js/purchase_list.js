function getContextPath() {
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	let contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
	return contextPath;
}

let totalPage;
let lenHIT = 5;
// HIT 상품 "스크롤" 할때 보여줄 상품의 개수(단위)크기 
let start = 1;


$(document).ready(function() {
	
	 $("div#shipping_cnt_left").trigger("click"); 
	
	// 화면인식 480px 사이즈 조절
	const mediaViewContent = window.matchMedia(`(max-width: 480px)`)	
	//console.log(mediaViewContent);
    
    const viewChangeHandler = (mediaViewContent) => {  					
    	// alert("확인용 max-width : 480px");
    	$("div#purchase").toggleClass('container');
    	$("div#purchase").css("width","100%");
    }
    
    mediaViewContent.addEventListener("change", viewChangeHandler);
	
	
	$("span#totalHITCount").hide();
	// console.log($("span#totalHITCount").val());
	$("span#countHIT").hide();
	// console.log($("span#countHIT").val());
	
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
	$("div#shipping_cnt_left").click(function(e) {
		
		$("div#show_shipping").empty();           // append 된 상품들 삭제 
        $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
        $("span#countHIT").text("0");
		
		$("div#shipping_cnt_right").css("border", "");
		$("div.shipping_status").css({ "font-weight": "", "color": "" });
		$("div#shipping_cnt_right > div.purchase_count").css("color", "black");
		$("div#shipping_cnt_left").css("border-bottom", "solid 2px #222");
		$("div#shipping_cnt_left > div.purchase_count").css("color", "#f15746");
		$("div#shipping_cnt_left > div.shipping_status").css({ "font-weight": "700", "color": "#222" });
		$("div#show_shipping_completed").hide();
		$("div#show_shipping").show();
		// div가 클릭되면 select 해오기 위해 사용되는 배송중 상태를 input태그(type="hidden")에 값 넣기
		$("div#status_button> input#input_shipping").val("0");
		
		callAjax(start);
	});
	

	// 종료 클릭시 테두리, 글자 속성 변경 
	$("div#shipping_cnt_right").click(function(e) {
		
		$("div#show_shipping").empty();           // append 된 상품들 삭제 
        $("div#show_shipping_completed").empty(); // append 된 상품들 삭제 
        $("span#countHIT").text("0");
		
		$("div#shipping_cnt_left").css("border", "");
		$("div.shipping_status").css({ "font-weight": "", "color": "" });
		$("div#shipping_cnt_left > div.purchase_count").css("color", "black");
		$("div#shipping_cnt_right").css("border-bottom", "solid 2px #222");
		$("div#shipping_cnt_right > div.purchase_count").css("color", "#f15746");
		$("div#shipping_cnt_right > div.shipping_status").css({ "font-weight": "700", "color": "#222" });
		$("div#show_shipping").hide();
		$("div#show_shipping_completed").show();

		// div가 클릭되면 select 해오기 위해 사용되는 배송중 상태를 input태그(type="hidden")에 값 넣기
		$("div#status_button> input#input_shipping").val("1");
		
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
			
			start_date = year+"/"+month+"/01";
			end_date = year+"/"+month+"/"+date.getDate();
			
			$("input#start_date").val(start_date);
			$("input#end_date").val(end_date);
			
		}
		$("button#search_simple").trigger("click");
	});

	// select 태그 클릭 클릭시 기간선택 option hidden 처리하기
	$("select#select_month").click(function(e) {
		$("select#select_month").children("option[value='기간선택']").prop("hidden", true);
	});


	/* 현재 날짜 구하기 */
	let today = new Date();

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월

	/* 모바일 화면에서 기간선택 클릭 시 나오는 년도/월 현재날짜 기준으로 입력 */
	for (let i = 2; i < 9; i++) {
		$("select#select_month > option:nth-child(" + i + ")").text(year + "년 " + (month - (i - 2)) + "월");
	}

});// end of $(document).ready(function()----------------------------------


function callAjax(start) {
	  
   	$.ajax({
   		url: getContextPath()+"/member/buylistView.dream",
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
   				
   				$("button#btnMoreHIT").show();
   				
   				$("div#no_result").html("");
   				
   				 $.each(json, function(index, item){
   					$("span#totalHITCount").text(item.totalListCnt);
   					html += "<a class='purchaseDetail' href='buylistDetail.dream?ordernum="+item.order_num+"'>"+
   						"<div class='purchase_item my-1' style=' border-bottom: solid 1px #ebebeb; border-top: solid 1px #ebebeb;'>"+
   		                     "<div id='purchase_detail'  class='d-flex'>"+
   		                        "<div class='image_box' style='line-height: 110px;'>"+
   		                           "<img class='product_img' src="+getContextPath()+"/images/제품이미지/"+item.product_image+" alt='...'>"+
   		                        "</div>"+
   		                        "<div id = 'name_cnt'>"+
   		                           "<div class='item_name'> "+item.product_name+" </div>"+
   		                           "<div class='purchase_cnt'> 구매수량 : "+item.buy_cnt+" </div>"+
   		                        "</div>"+
   		                     "</div>"+
   		                     "<div id='date_status'>"+
   		                        "<div>"+
   		                           "<span class='purchase_status'>"+item.shipping+"</span>"+ 
   		                        "</div>"+
	   		                     "<div>"+
		                           "<span class='purchase_date'>"+item.buy_date+"</span><br>"+
		                         "</div>"+
   		                     "</div>"+
   		               "</div>"+
   		               "</a>";
   		               
   				 })// end of $.each(json, function(index, item){}---------------------------
   				
   				// 배송상태에 따라 다른 div에 append ///
   				if($("input#input_shipping").val()==0){
   					$("div#show_shipping_completed").empty();	
   					$("div#show_shipping").append(html); // div 값 입력
   					
   				}
   				else if($("input#input_shipping").val()==1){
   					$("div#show_shipping").empty(); // div 초기화 
   					$("div#show_shipping_completed").append(html); // div 값 입력
   				}
   					
				$("button#btnMoreHIT").val( Number(start) + lenHIT);
				
				$("span#countHIT").text( Number($("span#countHIT").text()) + json.length );
				
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
		
		
