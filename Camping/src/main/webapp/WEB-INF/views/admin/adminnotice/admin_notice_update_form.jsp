<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>       

	<div id="admin_notice_update_form" align="center">
		<article>
			<h4>관리자 공지사항 등록</h4>
			<form name="formm" method="get" action="admin_notice_update">
				<input type="hidden" name="aseq" value="${adminnoticeVO.aseq}">
				
				<label>제목</label>
				<input type="text" name="title" size="63" value="${adminnoticeVO.title}"><br>	
				
				<label id="content">내용</label>
				<textarea rows="8" cols="65" name="content">${adminnoticeVO.content}</textarea><br>
				
				<input type="submit" value="수정하기" class="submit">
				<button type="button" value="목록" onclick="history.go(-1);">목록</button>
			</form>
		</article>			
	</div>

<%@ include file="../admin_footer.jsp"%>