
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
 * 회원 게시판 수정(지점관리자)
 */
function notice_update_chk() {

	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#admin_manage_notice_update").attr('action', 'admin_manage_notice_update').submit();
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
