// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}
let oEditors = [];
$(document).ready(function(){
  
  nhn.husky.EZCreator.createInIFrame({
      oAppRef: oEditors,
      elPlaceHolder: "faq_content", //textarea에서 지정한 id와 일치해야 합니다. 
      //SmartEditor2Skin.html 파일이 존재하는 경로
      sSkinURI: getContextPath()+'/smarteditor2/SmartEditor2Skin.html',  
      fCreator: "createSEditor2",
	    htParams : { 
	    	// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
	        bUseToolbar : true, 
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : true, 
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger : true 
	    }
  });
  
  
  const faq_title = sessionStorage.getItem("faq_title");
  if(faq_title.trim() != ""){
    $('select#faq_title').val(faq_title).prop("selected",true);
  }
  
  
  
  
  
});//end of $(document).ready(function(){})---


function save(){
	oEditors.getById["faq_content"].exec("UPDATE_CONTENTS_FIELD", []);  
    		//스마트 에디터 값을 텍스트컨텐츠로 전달
	//let content = $("#smartEditor").val();
	//const faq_content = $("textarea#faq_content").val();
	const faq_title = $("select#faq_title").val();
	const faq_subject =  $("input#faq_subject").val();
	if(faq_subject.length > 50){	//제목이 50글자를 넘는다면
		alert("제목은 50글자를 넘을 수 없습니다.");
		$("input#faq_subject").focus();
		return;
	}
	else if(faq_title == '구분' || faq_title.trim() == ""){
		alert("구분을 선택해주세요");
		return;
	}
	else{		//제목이 50글자를 초과하지 않은경우
		const frm = document.writerFrm;
		frm.method = "post";
	    frm.action = getContextPath()+"/admin/qna_write.dream";
	    frm.submit();
		return; 
	}
}

function update(){
	oEditors.getById["faq_content"].exec("UPDATE_CONTENTS_FIELD", []);  
    		//스마트 에디터 값을 텍스트컨텐츠로 전달
	//let content = $("#smartEditor").val();
	//const faq_content = $("textarea#faq_content").val();
	const faq_title =  $("input#faq_title").val();
	
	if(faq_subject.length > 50){	//제목이 50글자를 넘는다면
		alert("제목은 50글자를 넘을 수 없습니다.");
		$("input#faq_subject").focus();
		return;
	}
	else{		//제목이 50글자를 초과하지 않은경우
		const frm = document.writerFrm;
		frm.method = "post";
	    frm.action = getContextPath()+"/admin/faqUpdate.dream";
	    frm.submit();
		return; 
	}
	
}




