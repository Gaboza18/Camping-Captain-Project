<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="css/styles.css" >

<div id="qna_write_form" align="center"> 
	<article>
		<h2>1:1 고객 게시판 글쓰기</h2>
		<h5>고객님의 질문에 대해서 관리자가 1:1 답변을 드립니다</h5>
		<form name="formm" id="qna"method="post" action="qna_write">
			<label>제목</label>
			<input type="text" name="subject" id="subject" size="65"><br>
			<label class="contentname">내용</label>
			<textarea rows="8" cols="65" name="content" id="content"></textarea><br>
			<div id="buttons">
				<input type="button" value="글쓰기" class="submit" onclick="qna_chk()">
				<input type="reset" value="취소" class="cancel">
			</div>
		</form>	
	</article>			
</div>

<%@ include file="../footer.jsp" %>

