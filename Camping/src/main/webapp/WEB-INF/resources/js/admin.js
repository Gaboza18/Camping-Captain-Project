/**
 * 
 */

function chk() {
	if($("#campName").val() == "0"){
		alert("지점을 선택해주세요.");
	} else {
		$("#search_order").attr("action", "search_orderList").submit();
	}
}

// 전체 예약 현황 리스트에서 예약완료 벼튼 클릭 시 확정 alert출력
function confirm_order(){
	
}

// 전체 예약 현황 리스트에서 반려 버튼 클릭시 취소 사유 입력하는 창 오픈
function cancel_order() {
	var url = "insert_cancel_reason?oseq="+$("#orderSeq").val();
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500");
}
