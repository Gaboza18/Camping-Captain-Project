// 총관리자 조회(연간 정산)
function admin_search_chk_year() {
	if ($("#startYear").val() > $("#endYear").val()) {
		alert("조회할 '시작 년도'는 '끝나는 년도' 보다 클수 없습니다.");
	} else {
		$("#camp_order_year_search").attr('action','admin_master_calculate_year').submit();
	}
}

// 총관리자 조회(월간 정산)
function admin_search_chk_month() {
	if ($("#startYear").val() > $("#endYear").val()) {
		alert("조회할 '시작 년도'는 '끝나는 년도' 보다 클수 없습니다.");

	} else if ($("#startYear").val() == $("#endYear").val()) {
		if ($("#startMonth").val() > $("#endMonth").val()) {
			alert("조회할 '시작 월'은 '끝나는 월' 보다 클수 없습니다");
		}
	} else {
		$("#camp_order_year_month_search").attr('action', 'admin_master_calculate_yearmonth').submit();
	}
}