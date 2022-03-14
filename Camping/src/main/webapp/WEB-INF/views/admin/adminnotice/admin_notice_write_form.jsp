<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<div align="center">
		<article>
			<h2>관리자 공지사항 등록</h2>
			<form name="form" method="get" id="admin_notice_write" action="admin_notice_write">
				<fieldset>
					<input type="hidden" value="${adminnoticeVO.aseq}">
					<label>제목</label>
					<input type="text" id="title" name="title" size="63" value="${adminnoticeVO.title}"><br>
					<label>내용</label>
					<textarea rows="8" cols="65" id="content" name="content">${adminnoticeVO.content}</textarea><br>
					<input type="hidden" name="admin_name" size="63" value="총관리자"><br>
					<input type="button" value="글쓰기" class="submit" onclick="admin_notice_chk()">
					<input type="reset" value="다시쓰기" class="cancel">
					<button type="button" value="목록" onclick="history.go(-1);">목록</button>
				</fieldset>
			</form>
		</article>
	</div>
</body>
<%@ include file="../admin_footer.jsp"%>
</html>