// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}


$(document).ready(function(){
    // 변수선언
    const headline = $("tr.headline");
    
    // 헤드라인 클릭시 자주묻는질문 내용물 보여주기
    headline.click(function(e){
      const target = $(e.currentTarget);
      target.next().toggle();
      target.children("td.upIcon").toggle();
      target.children("td.downIcon").toggle();
    });
    
    $("button#btn_delete").click(function(e){
	  const target = $(e.currentTarget);
      const faq_num = target.next().text();
	  faq_delete(faq_num);
	});
	
	
  });//end of $(document).ready(function(){}---

      
  // Function Declaration
  
  
  function faq_delete(faq_num){
	Swal.fire({
	  title: '글을 삭제하시겠습니까?',
	  text: "삭제하시면 다시 복구시킬 수 없습니다.",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: 'dark',
	  confirmButtonText: '삭제',
	  cancelButtonText: '취소'
	}).then((result) => {
	  if (result.value) {
        location.href=getContextPath()+"/admin/faqDelete.dream?num="+faq_num;
		return;
	  }
	  
	})
}
