/**
 * 
 */

function chk() {
	if($("#campName").val() == "0"){
		alert("지점을 선택해주세요.");
	} else {
		$("#search_orderList").attr("action", "search_orderList").submit();
	}
}

function check() {
	if($("#campName").val() == "0"){
		alert("지점을 선택해주세요.");
	} else {
		$("#search_cancelList").attr("action", "search_cancelList").submit();
	}
}

// 전체 예약 현황 리스트에서 예약완료 버튼 클릭 시 상세내용확인을 위한 팝업창 오픈
function confirm_order(oseq){
	var confirm_url = "confirm_order_check?oseq="+oseq+"&camp_idRe="+$("#camp_idRe").val();
	
	window.open(confirm_url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500");	
}


// 전체 예약 현황 리스트에서 '취소' 버튼 클릭시 취소 사유 입력하는 창 오픈
function cancel_order(oseq) {
	var cancel_url = "insert_cancel_reason?oseq="+oseq+"&camp_idRe="+$("#camp_idRe").val();
	
	window.open(cancel_url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=550");
}

// 전체 취소내역 리스트에서 '취소하기' 버튼 클릭시 취소 신청이 들어온 예약내역이 담긴 팝업창 오픈
function confirm_check(cseq) {
	var url = "confirm_cancel_check?cseq="+cseq+"&camp_idRe="+$("#camp_idRe").val();
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500");
}