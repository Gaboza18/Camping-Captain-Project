<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>  

	<div id="admin_notice_write_form" align="center">
		<article>
			<h4>관리자 공지사항 등록</h4>
			<form name="form" method="get" action="admin_notice_write">
				<input type="hidden" value="${adminnoticeVO.aseq}">
				
				<label>제목</label>
				<input type="text" name="title" size="63" value="${adminnoticeVO.title}"><br>
				
				<label id="content">내용</label>
				<textarea rows="8" cols="65" name="content">${adminnoticeVO.content}</textarea><br>
				
				<input type="hidden" name="admin_name" size="63" value="총관리자"><br>
				
				<div id="notice_btn">
					<input type="submit" value="글쓰기" class="submit">
					<input type="reset" value="다시쓰기" class="cancel">
					<button type="button" value="목록" onclick="history.go(-1);">목록</button>
				</div>
			</form>
		</article>
	</div>
	
<%@ include file="../admin_footer.jsp"%>