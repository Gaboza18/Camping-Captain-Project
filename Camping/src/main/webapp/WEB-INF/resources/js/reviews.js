// 리얼리뷰 목록으로 이동
function go_list() {
	$("#detail").attr("action", "review_list").submit();
}

function go_search_review() {
	$("#review_list1").attr("action", "myreview").submit();
}
