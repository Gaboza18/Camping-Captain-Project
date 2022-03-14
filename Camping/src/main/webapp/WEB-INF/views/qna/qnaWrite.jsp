<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<head></head>    
<article>
	<div align="center">
			<h2>1:1 고객 게시판 글쓰기</h2>
			<h3>고객님의 질문에 대해서 관리자가 1:1 답변을 드립니다</h3>
			<form name="formm" id="qna" method="post" action="qna_write">
				<fieldset><br>
					<label>제목</label>
					<input type="text" name="subject" id="subject" size="63"><br><br>
					<label>내용</label>
					<textarea rows="8" cols="65" id="content" name="content"></textarea><br>
				</fieldset>
				<input type="button" value="글쓰기" class="submit" onclick="qna_chk()">
				<input type="reset" value="다시쓰기" class="cancel">
				<input class="btn"  type="button" value="목록" onclick="history.go(-1);">
			</form>	
	</div>
</article>
		<%@ include file="../footer.jsp" %>