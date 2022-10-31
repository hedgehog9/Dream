function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}

$(document).ready(function() {

	$("div#notfound_notice").hide();
	$("a.btn_find").removeAttr("href");

	$("input#mobile").click(function(e) {
		$(e.target).css("border-bottom", "solid 2px black");
	});

	$("input#mobile").blur(function(e) {
		$(e.target).css("border-bottom", "solid 2px #ebebeb");
	});

	// input 태그 값 변경될때마다 정규표현식을 이용한 값 검사
	$("input#mobile").keyup(function(e) {
		$("a.btn_find").css({ "background-color": "", "color": "" });

		const mobile = $("input#mobile").val();

		const regExp = /^[0]{1}[1]{1}[0]{1}[0-9]{7,8}$/;
		const bool_mobile = regExp.test(mobile);

		if (bool_mobile) {
			$("a.btn_find").css({ "background-color": "black", "color": "white" });
			$("a.btn_find").attr("href", "#");
		}
		else {
			$("a.btn_find").css({ "background-color": "", "color": "" });
		}
	});
	
	$("a#btn_find").click(function(e){
			
			$.ajax({
				 url:getContextPath()+"/login/findEmail.dream",
				 data:{"mobile":$("input#mobile").val()}, // 입력받은 연락처를 POST 방식으로 findEamilController에 전달
				 type:"post",    
				 dataType:"json",
				 async:true,      
				 success:function(json){ 
					 if(json.existMobile) {
						 sessionStorage.setItem('userid', json.userid);
						 location.href=getContextPath()+"/login/findEmailEnd.dream";
					 }
					 else {
						 $("div#notfound_notice").css("display:block");
						 // alert("일치하는 회원정보가 없습니다.");
						 toastr["error"]("일치하는 회원정보가 없습니다.");
					 }
				 },
				 
				 error: function(request, status, error){
					 alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				 }
				 
			});// end of ajax--------------------------------------------------
			
		});// end of $("a#btn_find").click(function(e){---------------------

});// end of $(document).ready(function(){}---------------------

function showNotification(){
	$("div#notfound_notice").addClass('show');
		setTimeout(() => {
			notification.removeClass('show');
		}, 1000)
}