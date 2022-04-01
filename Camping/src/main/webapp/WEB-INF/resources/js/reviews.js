// 리얼리뷰 목록으로 이동
function go_list() {
	$("#detail").attr("action", "review_list").submit();
}

function go_search_review() {
	$("#review_list1").attr("action", "myreview").submit();
}

// 리뷰 항목 미작성시
function input_review(){
	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#review").attr('action', 'insertReview').submit();
	}
}

function input_myreview(){
	if ($("#title").val() == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;

	} else if ($("#content").val() == "") {
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;

	} else {
		$("#myreview").attr('action', 'insertMyReview').submit();
	}
}

