<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리얼리뷰 상세보기</title>

<script type="text/javascript" src="js/reviews.js"></script>
</head>

<script type="text/javascript">
function delete_reviews() {
			
			      alert("내가 쓴 리뷰가 삭제되었습니다");
			      $("#delete").attr('action', 'review_list_re').submit(); 

				 
}




			      </script>
<body>
	<br>
	<div align="center">
		<h2>리얼리뷰! 상세보기</h2>
		<form name="frm" id="detail" method="get" action="review_list_re">
			<table border="1" >
				<tr>
					<th>번호</th>
					<td width="130">${RealReviewVO.rseq}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${RealReviewVO.title}</td>
				</tr>
				<tr>
					<th width="130">내용</th>
					<td>${RealReviewVO.content}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input id="id1" value="${RealReviewVO.id}"></td>
				</tr>
				<tr>
					<th>캠핑장이름</th>
					<td>${RealReviewVO.campingname}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td><fmt:formatDate value="${RealReviewVO.indate}" /></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${RealReviewVO.count}</td>
				</tr>
				
			</table>
			
			<!--위의 td 옆에 input 값주고 readonly로 바꿔줘도 됨  -->
			<input type="text" name="rseq" id="rseq" value="${RealReviewVO.rseq}" style="display:none">
			<!-- <input class="btn"  type="button" value="목록" onClick="go_list()"> -->
			<input class="btn" type="button" value="목록"
				onClick="location.href='review_list'">
			
				<c:if test="${userid eq RealReviewVO.id}">
				<a href="modi">
			<input class="btn" type="button" id="modi" value="수정" onClick="location.href='modifyreview'">
			</a>  </c:if><br><br>
			 </form>
			<c:if test="${userid eq RealReviewVO.id}">
				<button class="btn-success" id="delete" onclick="delete_reviews()">리뷰삭제</button>
			</c:if>
			
		
	</div>
</body>
<%@ include file="../footer.jsp"%>
</html>