<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
<div align="center">
		<table border="1">
			<tr>
				<th>번호</th>
				<td width="130">${questionsVO.qseq}</td>
			</tr>
			<tr>
				<th>질문</th>
				<td width="130">${questionsVO.question}</td>
			</tr>
			<tr>
				<th>답변</th>
				<td width="130">${questionsVO.reply}</td>
			</tr>
		</table><br>
		<input class="btn"  type="button" value="목록" onclick="history.go(-1);">
		<a href="${path}admin_question_update_form?qseq=${questionsVO.qseq}"><input type="button" value="수정"></a>
	    <a href="${path}admin_question_delete?qseq=${questionsVO.qseq}"><input type="button" value="삭제"></a>
</div>
</body>
<%@ include file="../admin_footer.jsp"%>
</html>