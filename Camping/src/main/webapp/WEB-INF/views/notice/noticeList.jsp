<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/camping.css">
<title>캠핑족장 공지사항</title>
</head>
<body>
	<div align="center">
		<article>
			<h2>공지사항</h2>
			<h3>캠핑족장 공지사항 입니다</h3>
		
			<!-- 검색 파트 -->
			<form action="getNoticeList.do" method="post">
				<table border="1" style="width: 50;">
					<tr>
						<td align="right"><select name="searchCondition">
								<c:forEach items="${conditionMap}" var="option">
									<option value="${option.value}">${option.key}</option>
								</c:forEach>
						</select> <input type="text" name="searchKeyword" /> <input type="submit"
							value="검색" /></td>
					</tr>
				</table>
			</form>
			<br>
			<form name="formm" method="get">
				<table id="noticeList" border="1">
					<tr>
						<th width="40">번호</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="130">작성일</th>
						<th width="50">조회수</th>
					</tr>
					<c:forEach items="${noticeList}" var="noticeVO">
						<tr>
							<td>${noticeVO.nseq}</td>
							<td><a href="${path}notice_detail?nseq=${noticeVO.nseq}">${noticeVO.title}</a></td>
							<td>${noticeVO.admin_name}</td>
							<td><fmt:formatDate value="${noticeVO.indate}" type="date" />
							</td>
							<td>${noticeVO.count}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</article>
	</div>
</body>
</html>