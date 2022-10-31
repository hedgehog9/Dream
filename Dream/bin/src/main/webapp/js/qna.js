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
	  let result = faq_delete();
	  const target = $(e.currentTarget);
      const faq_num = target.next().text();
	  
	  if(result){	//컨펌창 확인 눌렀을 때
	    
	  	location.href=getContextPath()+"/admin/faqDelete.dream?num="+faq_num;
		return; 
	  }
	
	});
	
	
  });//end of $(document).ready(function(){}---

      
  // Function Declaration
  
  function faq_delete(){
	
	let result = confirm("정말로 삭제하시겠습니까?");
	
	return result;
  }
