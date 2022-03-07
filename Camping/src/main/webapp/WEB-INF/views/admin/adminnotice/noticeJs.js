/**
 * 
 */

// 제목으로 조회
function go_search(){
	$("#admin_notice_form").attr("action", "admin_notice_list").submit();
	$("#admin_notice_form").attr("search_form", "review_list").submit();
}

// 공지사항 목록으로 이동
function go_list(){
	$("#admin_detail_form").attr("action", "admin_notice_list").submit();
}

// 공지사항 등록
function go_insert(){
	$("#admin_notice_form").attr("action", "admin_notice_write_form").submit();
}