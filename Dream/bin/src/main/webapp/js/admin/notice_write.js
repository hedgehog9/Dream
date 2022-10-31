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
      elPlaceHolder: "notice_content", //textarea에서 지정한 id와 일치해야 합니다. 
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
});//end of $(document).ready(function(){})---


function save(){
	oEditors.getById["notice_content"].exec("UPDATE_CONTENTS_FIELD", []);  
    		//스마트 에디터 값을 텍스트컨텐츠로 전달
	//let content = $("#smartEditor").val();
	//const notice_content = $("textarea#notice_content").val();
	const notice_title =  $("input#notice_title").val();
	
	if(notice_title.length > 50){	//제목이 50글자를 넘는다면
		alert("제목은 50글자를 넘을 수 없습니다.");
		$("input#notice_title").focus();
		return;
	}
	else{		//제목이 50글자를 초과하지 않은경우
		const frm = document.writerFrm;
		frm.method = "post";
	    frm.action = getContextPath()+"/admin/notice_write.dream";
	    frm.submit();
		return; 
	}
}

function update(){
	oEditors.getById["notice_content"].exec("UPDATE_CONTENTS_FIELD", []);  
    		//스마트 에디터 값을 텍스트컨텐츠로 전달
	//let content = $("#smartEditor").val();
	//const notice_content = $("textarea#notice_content").val();
	const notice_title =  $("input#notice_title").val();
	
	if(notice_title.length > 50){	//제목이 50글자를 넘는다면
		alert("제목은 50글자를 넘을 수 없습니다.");
		$("input#notice_title").focus();
		return;
	}
	else{		//제목이 50글자를 초과하지 않은경우
		const frm = document.writerFrm;
		frm.method = "post";
	    frm.action = getContextPath()+"/admin/noticeUpdate.dream";
	    frm.submit();
		return; 
	}
	
}

