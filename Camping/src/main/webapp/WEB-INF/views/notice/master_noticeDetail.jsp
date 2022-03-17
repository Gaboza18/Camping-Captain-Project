<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../admin/admin_header.jsp"%>

<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="../admin/master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="../admin/manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<div id="master_notice_detail_form" align="center">
	<h4>회원 공지사항 상세보기</h4>
	<form name="frm" id="detail_form" method="post">
		<table>
			<tr>
				<th width="90">번호</th>
				<td>${noticeVO.nseq}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${noticeVO.title}</td>
			</tr>
			<tr>
				<th width="90">내용</th>
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
		<c:set var="admin_name" value="${noticeVO.admin_name}"/>
		<c:set var="admin" value="${admin}"/>
		<c:choose>
			<c:when test="${loginAdmin.status eq 1}"> <!-- 총관리자일 때 -->
				<a href="${path}master_manage_notice_update_form?nseq=${noticeVO.nseq}"><input type="button" value="수정"></a>
			    <a href="${path}master_manage_notice_delete?nseq=${noticeVO.nseq}"><input type="button" value="삭제"></a>
			</c:when>
			<c:when test="${loginAdmin.status eq 2}"> <!-- 지점관리자일 때 -->
				<c:choose>
					<c:when test="${fn:contains(admin_name, admin)}">
						<a href="${path}master_manage_notice_update_form?nseq=${noticeVO.nseq}"><input type="button" value="수정"></a>
					    <a href="${path}master_manage_notice_delete?nseq=${noticeVO.nseq}"><input type="button" value="삭제"></a>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose> 
	</form>
</div>

<%@ include file="../admin/admin_footer.jsp" %>