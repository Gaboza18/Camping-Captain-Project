/**
 * 
 */

// 조회하기 버튼 클릭시 필터
function admin_search_chk() {
	if($("#startYear").val()>$("#endYear").val()){
		alert("조회할 '시작 년도'는 '끝나는 년도' 보다 클수 없습니다.");
	}else{
		$("#camp_order_year_search").attr('action', 'admin_master_calculate_year').submit();	
	}
}