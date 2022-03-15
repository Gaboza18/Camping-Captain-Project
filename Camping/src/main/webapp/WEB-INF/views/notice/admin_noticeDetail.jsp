<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin/admin_header.jsp"%>
<%@ include file="../admin/manager_admin_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
</head>
<body>
	<h2>회원 공지사항 상세보기</h2>
	<div align="center">
	<form name="frm" id="detail_form" method="post">
	<table>
		<tr>
			<th width="75">번호</th>
			<td>${noticeVO.nseq}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${noticeVO.title}</td>
		</tr>
		<tr>
			<th width="75">내용</th>
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
	</table><br>
		<input class="btn"  type="button" value="목록" onclick="history.go(-1);">
		<a href="${path}admin_manage_notice_update_form?nseq=${noticeVO.nseq}"><input type="button" value="수정"></a>
	    <a href="${path}admin_manage_notice_delete?nseq=${noticeVO.nseq}"><input type="button" value="삭제"></a>
	</form>
	</div>
</body>
</html>
<%@ include file="../admin/admin_footer.jsp" %>