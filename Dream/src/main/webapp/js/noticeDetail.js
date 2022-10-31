// js파일에서 contextPath를 알아내는 함수
function getContextPath(){
  let hostIndex = location.href.indexOf(location.host) + location.host.length;
  let contextPath = location.href.substring(hostIndex, location.href.indexOf('/',hostIndex+1));
  return contextPath;
}
$(document).ready(function(){
	

});
function deleteBoard(seq){
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
        //"삭제" 버튼을 눌렀을 때 작업할 내용을 이곳에 넣어주면 된다. 
		location.href=getContextPath()+'/admin/noticeDelete.dream?num='+seq;
	  }
	  
	})
}