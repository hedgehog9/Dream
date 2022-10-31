function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function() {
	// 현재 날짜 구하기 시작
	let today = new Date();
	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 2;  // 월
	let date = today.getDate();  // 날짜

	$("span#membership_year").text(year);
	$("span#membership_month").text(month);
	$("span#membership_date").text(date);
	// 현재 날짜 구하기 끝	

	$(document).on("click", "button#regist", function() {
		$.ajax({
			url: getContextPath()+"/member/membershipRegist.dream",
			data: { "userid": 1 }, 
			type: "post",    
			dataType: "json",
			success: function(json) {
				if (json.b_membership) {
					toastr["warning"]("이미 멤버십 가입중인 회원입니다.");
				}
				else {
					toastr["success"]("멤버십 가입에 성공했습니다.");
				}
			},

			error: function(request, status, error) {
				toastr["error"]("에러발생가 발생했습니다.");
				location.href=getContextPath()+"/index.dream"
			}

		});
	});
});