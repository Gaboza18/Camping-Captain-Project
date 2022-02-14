/**
 * 
 */

// 제목으로 조회
function go_search(){
	$("#notice_form").attr("action", "notice_list").submit();
}

// 공지사항 목록으로 이동
function go_list(){
	$("#detail_form").attr("action", "notice_list").submit();
}