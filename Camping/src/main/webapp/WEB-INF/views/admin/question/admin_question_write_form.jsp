<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
			<article>
				<h2>자주묻는 질문 등록</h2>
				<form name="formm" method="get" action="question_write">
					<fieldset>
						<legend>Board Info</legend>
						<input type="hidden" value="${questionsVO.qseq}">
						<label>질문</label>
						<input type="text" name="question" size="63" value="${questionsVO.question}"><br>	
						<label>답변</label>
						<textarea rows="8" cols="65" name="reply">${questionsVO.reply}</textarea><br>
					</fieldset>
					<input type="submit" value="글쓰기" class="submit">
					<input type="reset" value="다시쓰기" class="cancel">
					<button type="button" value="목록" onclick="history.go(-1);">목록</button>
				</form>
			</article>			
		</div>
</body>
<%@ include file="../admin_footer.jsp"%>
</html>