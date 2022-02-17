<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

    
<!DOCTYPE html>
	<html>
		<head>
			<title>1:1 고객 게시판 작성</title>
		</head>
		<div align="center">
			<article>
				<h2>1:1 고객 게시판 글쓰기</h2>
				<h3>고객님의 질문에 대해서 관리자가 1:1 답변을 드립니다</h3>
				<form name="formm" method="post" action="qna_write">
					<fieldset>
						<legend>Board Info</legend>
						<label>제목</label>
						<input type="text" name="subject" size="63"><br>
						<label>내용</label>
						<textarea rows="8" cols="65" name="content"></textarea><br>
					</fieldset>
					<input type="submit" value="글쓰기" class="submit">
					<input type="reset" value="취소" class="cancel">
				</form>
			</article>			
		</div>
		<%@ include file="../footer.jsp" %>
	</html>