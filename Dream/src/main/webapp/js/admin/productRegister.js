

$(document).ready(function() {

	$("span.error").hide();
	$("tr#tr_gender").hide();
	$("tr.pajama_size").hide();
	
	$("input#spinnerImgQty > a.corner-tr").trigger("click");
	
	$("input:radio[id='no_gender']").attr("checked", true);
	
	
	/* 할인율 입력시 0 ~ 99%까지만 입력 받도록 설정*/
	$("input#discountRate").blur(function(e){
		if($("input#discountRate") < 0 || $("input#discountRate").val()>100){
			alert("할인율은 1% ~ 99% 까지만 입력이 가능합니다!");
			$("input#discountRate").val("");
		}
	});
	
	
	// 대 카테고리 파자마에서 파자마 클릭시 성별 항목 출력 (없을 경우 기본값 "없음")
	$("select[name=category]").change(function(e){
		
		
		let html ="";
		
		// 카테고리 침구류 선택시 
		if($("select[name=category]").val()=="1"){
			$("select[name=detail_category]").empty();
			$("tr#tr_gender").hide();
			$("tr.pajama_size").hide();
			$("tr.product_cnt").show();
			
			html =	"<option class=''detail_category'' value=''>상세카테고리</option>"+
					"<option value='1'>매트리스</option>"+
					"<option value='2'>베개</option>"+
					"<option value='3'>베개커버</option>"+
					"<option value='4'>이불</option>"+
					"<option value='5'>침대</option>"+
					"<option value='6'>침대커버</option>";
			$("select[name=detail_category]").append(html);
		}
		
		// 카테고리 조명 선택시 
		else if($("select[name=category]").val()=="2"){
			$("select[name=detail_category]").empty();
			$("tr#tr_gender").hide();
			$("tr.pajama_size").hide();	
			$("tr.product_cnt").show();	
			
			html =	"<option class=''detail_category'' value=''>상세카테고리</option>"+
					"<option value='1'>캔들</option>"+
					"<option value='2'>안대</option>"+
					"<option value='3'>필로우미스트</option>";
			$("select[name=detail_category]").append(html);
			
			
		}
		// 카테고리 파자마 선택시 
		else if($("select[name=category]").val()=="3"){
			$("select[name=detail_category]").empty();
			$("tr#tr_gender").show();
			$("tr.pajama_size").show();
			$("tr.product_cnt").hide();
			$("tr.product_cnt > td > input").val("0");
			
			html =	"<option class=''detail_category'' value=''>상세카테고리</option>"+
					"<option value='1'>수면잠옷</option>"+
					"<option value='1'>동물잠옷</option>"+
					"<option value='1'>커플파자마</option>";
			$("select[name=detail_category]").append(html);
		}
		
		// 카테고리 수면용품 선택시 
		else if($("select[name=category]").val()=="4"){
			$("select[name=detail_category]").empty();
			$("tr#tr_gender").hide();
			$("tr.pajama_size").hide();
			$("tr.product_cnt").show();
			
			html =	"<option class=''detail_category'' value=''>상세카테고리</option>"+
					"<option value='1'>장스탠드</option>"+
					"<option value='2'>북라이트</option>"+
					"<option value='3'>무드등</option>"+
					"<option value='4'>LED양초</option>";
			$("select[name=detail_category]").append(html);
		}
	});


	$("input#spinnerImgQty").click(function(e){
		alert("클릭됨");
		const regExp = /^[1-3]{1}$/;
		
		if(!regExp.test($(e.target).val())){
			// alert("부적합");
			$(e.target).val("1");
			// console.log("$(e.target).val()"+$(e.target).val());
		}
	});


	// 제품수량입력에 스피너 달아주기
	$("input#spinnerImgQty").spinner({
		// spinner에 1 이상만 입력받도록
		spin: function(event, ui) {
			if (ui.value > 3) {
				$(this).spinner("value", 3);
				return false;
			}
			else if (ui.value < 1) {
				$(this).spinner("value", 1);
				return false;
			}
		}
	});// end of $("input#spinnerPqty").spinner({ }-------------------------------
	
	// 제품수량입력에 스피너 달아주기
	$("input#spinnerPqty").spinner({
		// spinner에 1 이상만 입력받도록
		spin: function(event, ui) {
			if (ui.value > 100) {
				$(this).spinner("value", 100);
				return false;
			}
			else if (ui.value < 1) {
				$(this).spinner("value", 1);
				return false;
			}
		}
	});// end of $("input#spinnerPqty").spinner({ }-------------------------------



	
	// 등록버튼 클릭시 비어있는 input 태그 경고 문구 표시 
	$("button#btnRegister").click(function(){
		// alert("확인용 등록버튼 클릭됨");
			
		let flag = false;	
		
		$(".infoData").each(function(){
			const val = $(this).val().trim();
			if(val == ""){
				$("span.error").hide();
				$(this).next().show();
				flag = true;
				return false;
			}
		});
		if(!flag) {
			const frm = document.prodInputFrm;
			frm.submit();
		}
		
	});
	
	
	// 취소 버튼 클릭시 경고 문구 숨기기
	$("input[type=reset]").click(function(){
		$("span.error").hide();
		// $("div#divfileattach").empty();
	});
	
	
	// 상품 이미지 css

     $("input#spinnerImgQty").bind("spinstop", function(){
         
         let html ="";
         const cnt = $(this).val(); // input 태그에 입력된 수량을 알아온다.(반복횟수)
         
         // console.log("확인용 cnt : "+ cnt);
         // console.log("확인용 typeof cnt : "+ typeof cnt);
         // 확인용 typeof cnt : string
         
         for(let i=0; i< Number(cnt); i++){
            html += "<br>";
            html += "<input type='file' name='attach"+i+"' id='attach"+i+"' class='btn btn-default infoData' /> <span class='error'>필수입력</span>";
         }// end of for---------------------------------------
         
         $("div#divfileattach").html(html);
         $("input#attachCount").val(cnt);
      });
      
      
      $("input#btnRegister").click(function(){
         
         let flag = false;
         
         $(".infoData").each(function(){
            const val = $(this).val().trim();
            if(val == ""){
               $(this).next().show();
               flag = true;
               return false;
            }
         });
         if(!flag) {
            const frm = document.prodInputFrm;
            frm.submit();
         }
         
      });
      
      $("input[type=reset]").click(function(){
         $("span.error").hide();
         $("div#divfileattach").empty();
      });

	
	
	


});