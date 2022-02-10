<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
</head>
<body>
	<h2>공지사항 상세보기</h2>
	<table border="1">
		<tr>
			<th>번호</th>
			<td width="130">${noticeVO.nseq}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${noticeVO.title}</td>
		</tr>
		<tr>
			<th width="130">내용</th>
			<td>${noticeVO.content}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${noticeVO.admin_name}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${noticeVO.indate}"/></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${noticeVO.count}</td>
		</tr>

	</table>
</body>
</html>