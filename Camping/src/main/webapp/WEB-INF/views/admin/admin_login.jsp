<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="admin_header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<article>
		<div style="">
			<form method="post" action="admin_login">
				<fieldset>
					<legend>관리자 로그인</legend>
					<label>아이디</label> <input name="id" type="text" value="${id}"><br>
					<br> <label>비밀번호</label> <input name="password"
						type="password"><br>
					<br>
					<div class="clear"></div>
					<div id="buttons">
						<input type="submit" value="로그인" class="submit">
					</div>
				</fieldset>
			</form>
		</div>
	</article>

</body>
</html>
<%@ include file="../footer.jsp"%>
