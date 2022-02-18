<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	 
<head>

</head>

<body>
	<article>
	    <h2> 리얼 리뷰 작성하기 </h2>
	    <h3> 고객님의 소중한 후기에 부족들은 감사하고 있습니다.</h3>    
		<form name="formm" method="post" action="review_write">
			<fieldset>	
				<legend>Board Info</legend>		
			    <label>Title</label>
			    <input type="text" name="title"  size="20">
			      <select>
  				<option value="${RealReviewVO.campingname}">서울지점</option>
  				<option value="${RealReviewVO.campingname}">대전지점</option>
  				<option value="${RealReviewVO.campingname}">대구지점</option>
  				<option value="${RealReviewVO.campingname}">부산지점</option>
  				<option value="${RealReviewVO.campingname}">찍고지점</option>
  			
							</select><br>
			 
				<label >Content</label>
			    <textarea rows="8" cols="65" name="content"></textarea><br>
			</fieldset>   
		<div class="clear"></div>
		 <div id="buttons" style="float:right">
			<input type="submit"  value="글쓰기"     class="submit"> 
			<input type="reset"   value="취소"     class="cancel">
				
		  </div>
		</form>
	</article>
	</body>
<%@ include file="../footer.jsp" %>