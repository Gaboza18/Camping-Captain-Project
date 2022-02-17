<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>아이디 찾기</title>
		<script src="jquery/jquery.min.js"></script>
		<script src="js/bootstrap.bundle.min.js"></script>		
	</head>
	<body>
	<form method="post" class="form-signin" action="find_id" name="findform">
		<h3>아이디 찾기</h3>
		<p>회원가입시 등록한 이름과 전화번호를 입력해 주세요</p>
		<div class="form-label-name">
			<label for="name">이름</label>
			<input type="text" id="name" name="name" class="form-control"/>
		</div>
		<div class="form-lable-phone">
			<label for="phone">전화번호</label>	
			<input type="text" id="phone" name="phone" class="form-control"/>
		</div>

		<div class="form-label-group">
			<input class="btn btn-lg btn-secondary btn-block text-uppercase"
				type="submit" value="check">
		</div>

		<!-- 이름과 전화번호가 일치하지 않을 때-->
		<c:if test="${check == 1}">
			<script>
				opener.document.findform.name.value = "";
				opener.document.findform.phone.value = "";
			</script>
			<label>일치하는 정보가 존재하지 않습니다.</label>
		</c:if>

		<!-- 이름과 비밀번호가 일치하지 않을 때 -->
		<c:if test="${check == 0 }">
		<label>찾으시는 아이디는' ${id}' 입니다.</label>
		<div class="form-label-group">
				<input class="btn btn-lg btn-secondary btn-block text-uppercase"
					type="button" value="OK" onclick="closethewindow()">
			</div>
		</c:if>
	</form>
	<script type="text/javascript">
		function closethewindow(){
			self.close();
		}
	</script>
	</body>
</html>

