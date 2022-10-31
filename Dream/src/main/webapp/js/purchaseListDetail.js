$(document).ready(function(){
	
	$("span#goBack").click(function(){
		location.href=document.referrer;
	});
	
})// end of $(document).ready(function(){}----------------------------