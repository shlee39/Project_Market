/**
 * 
 */

function read(notiNum) {
	$.ajax({
		url:"./readChk",
		method:"POST",	
		data:{notiNum : notiNum},
		success:function() {
			console.log("알림 읽기 완료");
			window.location.replace("/notification/list");
		}
	});
}

function notiDelete(notiNum) {
	$.ajax({
		url:"./notiDelete",
		method:"POST",
		data:{notiNum : notiNum},
		success:function(){
			console.log("알림 삭제 완료");
			window.location.replace("/notification/list");
		}
		
	});
}