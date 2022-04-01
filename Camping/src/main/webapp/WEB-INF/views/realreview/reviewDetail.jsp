<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>

<link rel="stylesheet" href="css/styles.css" >
<script type="text/javascript" src="js/reviews.js"></script>

<script type="text/javascript">

	function delete_reviews() {
		alert("내가 쓴 리뷰가 삭제되었습니다");
		$("#delete").attr('action','review_list_re').submit(); 
	}
	
	function admin_delete() {
	    alert("회원이쓴 리뷰가 삭제되었습니다");
	    $("#delete1").attr('action','review_list_re').submit(); 
	}
	
	function modi_review() {
		alert("내가쓴 리뷰를 수정합니다");
		console.log(rseq);
		$("#detail").attr('action','modi').submit();
	} 
	
	function non_modi_review() {
		alert("블랙리스트 회원은 리뷰 수정을 할 수 없습니다.");
	}

</script>
			      
<div align="center" id="review_detail_form">
	<h2>리얼리뷰! 상세보기</h2>
	<form name="frm" id="detail" method="get" action="review_list_re">
		<table border="1" id="review_detail">
			<tr>
				<th>번호</th>
				<td>${RealReviewVO.rseq}</td>
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
				<td>
					<c:choose>
						<c:when test="${RealReviewVO.id == null}">
							<span style="color: gray;">탈퇴회원이 작성한 리뷰입니다.</span>
						</c:when>
						<c:otherwise>
							${RealReviewVO.id}
						</c:otherwise>
					</c:choose>
				</td>
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
			
		<input type="text" name="campingname" id="campingname" value="${RealReviewVO.campingname}" style="display:none">
		<input type="text" name="rseq" id="rseq" value="${RealReviewVO.rseq}" style="display:none">

		<input class="btn" type="button" value="목록" onClick="location.href='review_list'">
		
		<c:choose>
			<c:when test="${loginUser.blacklist eq 'y'}">																
				<input class="btn" type="button" id="modi" value="수정" onClick="non_modi_review()">
			</c:when>
			<c:otherwise>
				<c:if test="${userid eq RealReviewVO.id}">
					<input class="btn" type="button" id="modi" value="수정" onClick="modi_review()">
				</c:if><br><br>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${userid eq RealReviewVO.id}">
			<button type="submit" form="detail" class="btn-success" id="delete" onClick="delete_reviews()">리뷰삭제</button>
		</c:if>
	</form>
</div>

<%@ include file="../footer.jsp"%>