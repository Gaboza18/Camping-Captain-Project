<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>


</head>
<script>

function modify_reviews() {
	
    alert("수정완료되었습니다");
    
    $("#modify").attr('action','modifyReview').submit(); 

	 
}



</script>

<body>


	<article>
		<h2>리얼 리뷰 수정하기</h2>
		<h3>고객님의 소중한 후기에 부족들은 감사하고 있습니다.</h3>
		<form name="formm" id="modify" method="get" action="modifyReview">
			<fieldset>
				<legend>Board Info</legend>




				<label>Title</label> <input type="text" name="title"
					value="${RealReviewVO.title}" size="20">
				<table border="1">


				</table>


				<select name="campingname">
					<option name="campingname" value="${RealReviewVO.campingname}">서울지점</option>
					<option name="campingname" value="${RealReviewVO.campingname}">대전지점</option>
					<option name="campingname" value="${RealReviewVO.campingname}">대구지점</option>
					<option name="campingname" value="${RealReviewVO.campingname}">부산지점</option>
					<option name="campingname" value="${RealReviewVO.campingname}">찍고지점</option>

				</select>
				<!-- 이렇게 하니 실행되었음 name이 중요함
  				  <input type="text" name ="campingname" value="${RealReviewVO.campingname}">
  				</input> -->
				<br> <input type="text" name="rseq" id="rseq"
					value="${RealReviewVO.rseq}" style="display: none"> <input
					type="text" class="class" name="id" id="id"
					value="${RealReviewVO.id}" style="display: none">
				<textarea rows="8" cols="65" name="content">${RealReviewVO.content}</textarea>
				<br> <input type="text" name="rseq" id="rseq"
					value="${RealReviewVO.content}" style="display: none">
			</fieldset>
			<div class="clear"></div>
			<div id="buttons" style="float: right">
				<input type="submit" value="수정하기 " form="modify" id="modify1"
					class="submit" onClick="modify_reviews()"> <input
					type="reset" value="취소" class="cancel">

			</div>
		</form>
	</article>


</body>
<%@ include file="../footer.jsp"%>