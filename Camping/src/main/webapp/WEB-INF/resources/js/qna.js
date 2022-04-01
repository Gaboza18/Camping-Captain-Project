/*
 * 1대1 문의 게시판   
 */
function qna_chk() {

	if ($("#subject").val() == "") {
		alert("제목을 입력해주세요.");
		$("#subject").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#qna").attr('action', 'qna_write').submit();
	}
}
