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

<div id="master_notice_update_form" align="center">
	<article>
		<h4>회원 공지사항 수정</h4>
		<form name="formm" method="get" id="master_manage_notice_update" action="master_manage_notice_update">
			<fieldset>
				<input type="hidden" name="nseq" value="${noticeVO.nseq}">
				<label>제목</label>
				<input type="text" name="title" id="title" size="63" value="${noticeVO.title}"><br>	
				<label id="content_label">내용</label>
				<textarea rows="8" cols="65" id="content" name="content">${noticeVO.content}</textarea><br>
			</fieldset><br>
			<input type="button" value="수정하기" class="submit" onclick="master_notice_update_chk()">
			<input type="button" value="목록" onclick="location.href='master_manage_notice_list'">
		</form>
	</article>			
</div>

<%@ include file="../admin/admin_footer.jsp"%>