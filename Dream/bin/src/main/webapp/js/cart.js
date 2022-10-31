$(document).ready(function () {

    let prd_check_length = $("table input:checkbox[name='prd_check']").length
    
    $(".total_cnt").html(prd_check_length);

    $("input#chk_all").click(function () { // 전체선택 버튼을 클릭하였을시 이벤트처리
        
        const bool = $("input.chk_all").prop("checked");
        $("input:checkbox[name='prd_check']").prop("checked",bool);
        $("input#mobile_chk_all").prop("checked",bool);
        
        let total_price = 0;
        $("table input:checkbox[name='prd_check']:checked").each(function (index,item) {
            total_price += parseInt( $(item).parent().siblings().find("span.total_price").text().split(",").join("") );
        }); // end of $("input:checkbox[name='prd_check']:checked").each(function (index,item)

        // console.log(total_price);

        $("span#prd_price").text(total_price.toLocaleString('en'));
        $("span.payment_price").text( (total_price - Number($("input#point").val())).toLocaleString('en') );

    });// end of $("input:checkbox[name='chk_all']").click(function (e) ----------------

    $("input#mobile_chk_all").click(function () { // 모바일 전체선택 버튼을 클릭하였을시 이벤트처리
        
        const bool = $("input#mobile_chk_all").prop("checked");
        $("input:checkbox[name='prd_check']").prop("checked",bool);
        $("input#chk_all").prop("checked",bool);
        
        let total_price = 0;
        $("table input:checkbox[name='prd_check']:checked").each(function (index,item) {
            total_price += parseInt( $(item).parent().siblings().find("span.total_price").text().split(",").join("") );
        }); // end of $("input:checkbox[name='prd_check']:checked").each(function (index,item)
        
        // console.log(total_price);

        $("span#prd_price").text(total_price.toLocaleString('en'));
        $("span.payment_price").text( (total_price - Number($("input#point").val())).toLocaleString('en') );


    });// end of $("input:checkbox[name='chk_all']").click(function (e) ----------------*/


    $("table input:checkbox[name='prd_check']").click(function (e) {  // 각각 체크박스 선택시 이벤트 처리

        const classname = $(e.target).prop("class"); 

        const prd_check_length = $("table input:checkbox[name='prd_check']").length
        const prd_checked_length = $("table input:checkbox[name='prd_check']:checked").length;

        if(prd_check_length > prd_checked_length) {
            $("input:checkbox[name='chk_all']").prop("checked",false);
        }
        else if(prd_check_length == prd_checked_length){
            $("input:checkbox[name='chk_all']").prop("checked",true);
        }

        const bool = $(e.target).prop("checked");
        $("."+classname).prop("checked",bool); 

    });// end of $("input:checkbox[name='prd_check']").click(function (e) ------------


    $("div > input:checkbox[name='prd_check']").click(function (e) {  // 각각 체크박스 선택시 이벤트 처리

        const classname = $(e.target).prop("class"); 

        const mobile_prd_check_length = $("div > input:checkbox[name='prd_check']").length
        const mobile_prd_checked_length = $("div > input:checkbox[name='prd_check']:checked").length;

        if(mobile_prd_check_length > mobile_prd_checked_length) {
            $("input:checkbox[name='chk_all']").prop("checked",false);
        }
        else if(mobile_prd_check_length == mobile_prd_checked_length){
            $("input:checkbox[name='chk_all']").prop("checked",true);
        }

        const bool = $(e.target).prop("checked");
        $("."+classname).prop("checked",bool); 

    });// end of $("input:checkbox[name='prd_check']").click(function (e) ------------


    $("input:checkbox[name='prd_check']").change(function () {
        // 체크된 상품들의 total price들을 가져와서 합계를 구한것을 아래 html에 넣어주기
        let total_price = 0;
        let halin = 0;
        $("table input:checkbox[name='prd_check']:checked").each(function (index,item) {
            total_price += parseInt( $(item).parent().siblings().find("span.total_price").text().split(",").join("") );
            // halin += Number($(item).parent().next().next().find('.discount_price').text()*$(item).parent().next().next().next().find(".cart_qty").val());
        }); // end of $("input:checkbox[name='prd_check']:checked").each(function (index,item)
		
		$("span#halin").text(halin.toLocaleString('en'));
        $("span#prd_price").text(total_price.toLocaleString('en'));
        $("span.payment_price").text( (total_price - Number($("input#point").val())).toLocaleString('en') );
        
        let length = $("table input:checkbox[name='prd_check']:checked").length;
		
		if(length == 0) {
			$("input#point").val('');
			$("span.sale_point").text('0');
			$("span.payment_price").text('0');
		}
        
    });// end of $(document).on("change","table#tbl_cart", function (e)

    

    $(document).on("DOMSubtreeModified","table#tbl_cart", function () {
        //alert('헿');

        let total_price = 0;
        $("table input:checkbox[name='prd_check']:checked").each(function (index,item) {
            total_price += parseInt( $(item).parent().siblings().find("span.total_price").text().split(",").join("") );
        }); // end of $("input:checkbox[name='prd_check']:checked").each(function (index,item)

		

        $("span#prd_price").text(total_price.toLocaleString('en'));
        $("span.payment_price").text( (total_price - Number($("input#point").val())).toLocaleString('en') );
        
    });// end of $(document).on("DOMSubtreeModified","table#tbl_cart", function (e)
    
    
    
    // 포인트 js
    
      // 포인트입력칸 숫자만 가능하도록
      $("input#point").keyup(function(){ $(this).val($(this).val().replace(/[^0-9]/gi,"") );  }); //숫자만
    
      $("input#point").bind("blur",function() {
	
		const length = $("input:checkbox[name='prd_check']:checked").length;
		
		if(length == 0) {
			alert("제품을 먼저 선택하시고 포인트를 입력하세요");
			$("input#point").val('');
			$("span.sale_point").text('0');
			return false;
		}
		
		if( Number($(".user_point").text()) < $("input#point").val() ) {
			alert("보유포인트가 적습니다.");
			$("input#point").val('');
			$("span.sale_point").text('0');
			return false;
		}
		
		if(Number($("span#prd_price").text().split(",").join("")) < $("input#point").val()) {
			alert("구매가격보다 높은 포인트는 사용할 수 없습니다.");
			$("input#point").val('');
			$("span.sale_point").text('0');
			return false;
		}
		
		if($("input#point").val() == "") {
			$("input#point").val('');
			$("span.sale_point").text('0');
			return false;
		}
		
	
		$("span.sale_point").text( $("input#point").val() );
		$(".payment_price").text( (Number($("span#prd_price").text().split(",").join("")) - Number($("input#point").val())).toLocaleString('en') )
	
	  });
	  
	  
	  $("button.remove_cart_list").click(function(e) { // 삭제하기 버튼

	        const target = $(e.target);
	        const remove_div = target.parent().parent().parent().attr("class");

	        const cart_num = target.parent().parent().find('span#cart_num').text()

	        $.ajax({
		        url : getContextPath()+"/cart/cartDelete.dream",
		        type: "post",
		        data: {"cart_num" : cart_num},
		        dataType:'json',
		        success: function(json) {
			        	
		        		const n = json.n;
		            	
		        		if(n == 1) {
		        			 $("."+remove_div).remove();
					     prd_check_length = $("table input:checkbox[name='prd_check']").length
					     
					     let count = 1;
					     $("table input:checkbox[name='prd_check']").each(function(index,item){
							$(item).parent().prev().find("#cart_number").text(count);
							count++;
						 });
					     
					     if(prd_check_length == 0) {
							$(".table_div").empty();
							$(".table_div").html("<h4 class='text-muted text-center mt-5'>장바구니 구매 내역이 없습니다.</h4>");
							$(".table_div").append("<hr class='my-5' style='border: solid 2px black; background-color: black;'>");
							$(".mobile_cart").empty();
							$(".mobile_cart").html("<h4 class='text-muted text-center mt-5'>장바구니 구매 내역이 없습니다.</h4>");
							$(".mobile_cart").append("<hr class='my-5' style='border: solid 2px black; background-color: black;'>");
						}
						else {
							$(".total_cnt").html(prd_check_length);
						}
		        		}
		        		else {
		        			alert('백엔드 에러 잡아라');
		        		}
		            
		        },
		        error: function(request, status, error){
		            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		        }
		    });

	    });// end of $("button.remove_cart_list").click(function() ------------------



	    $("button#remove_check").click(function () { // 선택삭제 버튼 이벤트 처리
	        
	        let param = []
	        
	        $("input:checkbox[id='purchase_check']:checked").each(function(index,item) {
	        	
		        	let data = {
		        		  cart_num : $(item).parent().parent().find('span#cart_num').text()
		        	};
		        	
		        	param.push(data);
		        	
	        });
	        
	        const jsonData = JSON.stringify(param);
	        
	        $.ajax({
		        url : getContextPath()+"/cart/cartSelectDelete.dream",
		        traditional: true,
		        type: "post",
		        data: {"jsonData" : jsonData},
		        dataType:'json',
		        success: function(json) {
			        	
		        		const n = json.n;
		        		const count_n = param.length
		        		
		            	
		        		if(n == count_n) {
		        			$("input:checkbox[name='prd_check']:checked").parent().parent().parent().remove();
				        if($("input:checkbox[name='prd_check']").length == 0) {
				            $("input#chk_all").prop("checked",false);
				            $("button#remove_check").hide();
				        }
				        else {
				            $("button#remove_check").show();
				        }
				        prd_check_length = $("table input:checkbox[name='prd_check']").length
				        
				        let count = 1;
					     $("table input:checkbox[name='prd_check']").each(function(index,item){
							$(item).parent().prev().find("#cart_number").text(count);
							count++;
						 });
		        			
		        			if(prd_check_length == 0) {
							$(".table_div").empty();
							$(".table_div").html("<h4 class='text-muted text-center mt-5'>장바구니 구매 내역이 없습니다.</h4>");
							$(".table_div").append("<hr class='my-5' style='border: solid 2px black; background-color: black;'>");
							$(".mobile_cart").empty();
							$(".mobile_cart").html("<h4 class='text-muted text-center mt-5'>장바구니 구매 내역이 없습니다.</h4>");
							$(".mobile_cart").append("<hr class='my-5' style='border: solid 2px black; background-color: black;'>");
						}
						else {
							$(".total_cnt").html(prd_check_length);
						}
		        		}
		        		else {
		        			alert('백엔드 에러 잡아라');
		        		}
		            
		        },
		        error: function(request, status, error){
		            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		        }
		    });
	        
	    });// end of $("button#remove_check").click(function (e)
	  
	  
	 //가격대 검색창 입력시 숫자만 입력 가능하게 만들기
     $("input.cart_qty").keyup(function(){ 
		const original = $(this).val();
		$(this).val($(this).val().replace(/^[0-9]+$/,original) );
		
	 });
    

});// end of $(document).ready(function () {} -----------------------------------------------------------------------------------------------------------------------------









  