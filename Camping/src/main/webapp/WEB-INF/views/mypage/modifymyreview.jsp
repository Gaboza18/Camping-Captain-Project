<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="css/styles.css" >
<script>
	function modify_myreviews() {
		
	    alert("수정완료되었습니다");
	    $("#modify").attr('action','modifyMyReview').submit(); 
	
	}
</script>

<div id="modify_reivew_form" align="center">
	<h2>리얼 리뷰 수정하기</h2>
	<h5>고객님의 소중한 후기에 부족들은 감사하고 있습니다.</h5>
	<form name="formm" id="modify" method="get" action="modifyMyReview">
		<label>Title</label>
		<input type="text" name="title" value="${RealReviewVO.title}" size="20">

		<input type="text" name="campingname" value="${RealReviewVO.campingname}" readonly="readonly" size="13">
		<br> 
		<input type="text" name="rseq" id="rseq" value="${RealReviewVO.rseq}" style="display: none">
		<input type="text" class="class" name="id" id="id" value="${RealReviewVO.id}" style="display: none">
		<textarea rows="8" cols="65" name="content">${RealReviewVO.content}</textarea>
		<br> 
		<input type="text" name="rseq" id="rseq" value="${RealReviewVO.content}" style="display: none">
		
		<div class="clear"></div>
		
		<div id="buttons">
			<input type="submit" value="수정하기 " form="modify" id="modify1" class="submit" onClick="modify_myreviews()"> 
			<input type="reset" value="취소" class="cancel">
		</div>
	</form>
</div>

<%@ include file="../footer.jsp"%>