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
	
	if(people > base){
		people--;
		$("#people").val(people);
	}
}

function plus(){
	var max = $("#max").val();
	var people = $("#people").val();
	
	if(people < max){
		people++;
		$("#people").val(people);
	} 
}