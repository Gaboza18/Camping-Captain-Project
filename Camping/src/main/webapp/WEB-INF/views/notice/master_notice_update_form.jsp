<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin/admin_header.jsp"%>
<%@ include file="../admin/master_admin_menu.jsp"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
			<article>
				<h2>회원 공지사항 수정</h2>
				<form name="formm" method="get" id="master_manage_notice_update" action="master_manage_notice_update">
					<fieldset>
						<input type="hidden" name="nseq" value="${noticeVO.nseq}">
						<label>제목</label>
						<input type="text" name="title" id="title" size="63" value="${noticeVO.title}"><br>	
						<label>내용</label>
						<textarea rows="8" cols="65" id="content" name="content">${noticeVO.content}</textarea><br>
					</fieldset><br>
					<input type="button" value="수정하기" class="submit" onclick="master_notice_update_chk()">
					<button type="button" value="목록" onclick="history.go(-1);">목록</button>
				</form>
			</article>			
		</div>
</body>
<%@ include file="../admin/admin_footer.jsp"%>
</html>