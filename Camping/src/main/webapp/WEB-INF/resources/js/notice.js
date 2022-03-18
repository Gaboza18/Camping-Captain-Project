// 회원이 공지사항 검색
function go_search(){
	$("#notice_form").attr("action", "notice_list").submit();
}

// 관리자가 회원공지사항 관리하는 페이지에서 검색 시 
function go_search_user_notice(){
	$("#notice_form").attr("action", "master_manage_notice_list").submit();
}

//공지사항 등록
function go_insert(){
	$("#admin_notice_form").attr("action", "admin_notice_write_form").submit();
}

/*
 * 회원 게시판 등록(지점관리자)
 */
function notice_chk() {

	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요!");
		$("#content").focus();
		return false;

	} else {
		$("#admin_notice_manage").attr('action', 'admin_notice_manage').submit();
	}
}

/*
 * 회원 게시판 등록(총관리자)
 */
function master_notice_chk() {

	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#master_notice_manage").attr('action', 'master_notice_manage').submit();
	}
}

/*
 * 회원 게시판 수정(총관리자)
 */
function master_notice_update_chk() {

	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#master_manage_notice_update").attr('action', 'master_manage_notice_update').submit();
	}
	return true;
}

/*
 * 관리자 게시판 등록창 이동
 */
function go_insert(){
	$("#admin_notice_insert").attr("action", "admin_notice_write_form").submit();
}

/*
 * 관리자 게시판 등록(총관리자)
 */
function admin_notice_chk() {

	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#admin_notice_write").attr('action', 'admin_notice_write').submit();
	}
}

/*
 *  관리자 게시판 검색(총관리자)
 */
function  go_search_admin(){
	$("#admin_notice_form").attr("action", "admin_notice_list").submit();
}

/*
 *  관리자 게시판 검색(지점관리자)
 */
function  go_search_manager(){
	$("#admin_notice_form").attr("action", "manager_admin_notice_list").submit();
}