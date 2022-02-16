<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는질문 상세보기</title>
</head>
<body>
	<h2>자주묻는질문 상세보기</h2>
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
	</table>
</body>
</html>