
$(document).ready(function(){


/* 삭제버튼 클릭시 해당부분 div를 삭제해준다.  */
$("a#delete").click( (event)=>{ 
	
	const $target = $(event.target);    	
	
	$target.parent().parent().remove(".my_item");    	
	
});



$("a#basic_delete").click( (event)=>{ 
    
	toastr["error"]("다른 주소를 기본 배송지로 <br>변경 후, 삭제할 수 있습니다.");	
});

});	// end of $(document).ready(function(){} -------------------------------



function show_modal() {
	
	$('#pay_modal').modal('show');
	
}
		