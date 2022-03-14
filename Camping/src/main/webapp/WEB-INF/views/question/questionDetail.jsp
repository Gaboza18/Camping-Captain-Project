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
  <div align="center">
	<table>
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
 </div>
	<input class="btn"  type="button" value="목록" onclick="history.go(-1);">
</body>
<%@ include file="../footer.jsp" %>
</html>
