/**
 * 
 */

// 조회하기 버튼 클릭 시 필터 적용
function search_chk() {
	if($("#search_camp_name").val() == "0"){
		alert("지점을 선택해주세요.");
	} else if($("#checkin_date").val() == ""){
		alert("체크인 날짜를 선택해주세요.");
	} else if($("#checkout_date").val() == ""){
		alert("체크아웃 날짜를 선택해주세요.");
	} else {
		$("#camp_search").attr('action', 'camp_search').submit();
	}
}

// 캠핑장 예약 시 인원 선택 (버튼 클릭 시 증가/감소)
function minus(){
	var base = $("#base").val();
	var people = $("#people").val();
	var price = parseInt($(".total_price").val());
	var add_price = 10000;
	
	if(people > base){
		people--;
		price -= 10000;
		$("#people").val(people);
		$(".total_price").val(price);
	}
}

function plus(){
	var max = $("#max").val();
	var people = $("#people").val();
	var price = parseInt($(".total_price").val());
	var add_price = 10000;
	
	if(people < max){
		people++;
		price += 10000;
		$("#people").val(people);
		$(".total_price").val(price);
	} 
}

// 예약 완료시 출력
//function complete() {
//	alert("예약이 완료되었습니다.");
//}

// 예약취소 버튼 클릭 시 팝업창 오픈
function myCancel(oseq) {
var url = "go_myOrder_cancel?oseq="+oseq;
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=550");
}