<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp" %>	 
<script type="text/javascript" src="js/reviews.js"></script>
<script type="text/javascript">

	function modi_myreview() {
		alert("내가쓴 리뷰를 수정합니다");
		$("#reviewdetail").attr('action','modiMyReview').submit();
	}
	
	function delete_myreview() {
		alert("리뷰가 삭제되었습니다.");
		$("#delete_myreview").attr('action','delete_myreview').submit();
	}

</script>

<div align="center" id="myreview_detail_form">
	<h2>리얼리뷰! 상세보기</h2>
	<form name="RealReviewVO" id="reviewdetail" method="get" action="delete_myreview">
		<table border="1">
			<tr>
				<th>번호</th>
				<td width="130">${RealReviewVO.rseq}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${RealReviewVO.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${RealReviewVO.content}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${userid}</td>
			</tr>
			<tr>
				<th>캠핑장이름</th>
				<td>${RealReviewVO.campingname}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${RealReviewVO.indate}"/></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${RealReviewVO.count}</td>
			</tr>
		</table>
		
		<input type="text" name="campingname" id="campingname" value="${RealReviewVO.campingname}" style="display:none">
		<input type="text" name="rseq" id="rseq" value="${RealReviewVO.rseq}" style="display:none">
		<input class="btn"  type="button" value="목록" onClick="location.href='myreview'">
		<input class="btn" type="button" id="modi" value="수정" onClick="modi_myreview()">
		<input class="btn" type="submit" value="삭제" id="delete_myreview" onclick="delete_myreview()">
	</form>
</div>

<%@ include file="../footer.jsp" %>