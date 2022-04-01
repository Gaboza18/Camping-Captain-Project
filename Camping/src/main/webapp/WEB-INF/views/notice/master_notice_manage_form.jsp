<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin/admin_header.jsp"%>

<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="../admin/master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="../admin/manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<div id="master_motice_manager_form" align="center">
	<article>
		<h2>회원 공지사항 등록</h2>
		<form name="form" id="master_notice_manage" method="get" action="master_notice_manage">
			<fieldset>
				<input type="hidden" value="${noticeVO.nseq}">
				<label>제목</label>
				<input type="text" name="title" id="title" size="63" value="${noticeVO.title}"><br>
				<label id="content_label">내용</label>
				<textarea rows="8" cols="65" id="content" name="content">${noticeVO.content}</textarea><br>
				<input type="hidden" name="admin_name" size="30" value="${loginAdmin.name}"><br>
				<input type="button" value="글쓰기" class="submit" onclick="master_notice_chk()">
				<input type="reset" value="다시쓰기" class="cancel">
				<input type="button" value="목록" onclick="history.go(-1);">
			</fieldset>
		</form>
	</article>
</div>

<%@ include file="../admin/admin_footer.jsp"%>
