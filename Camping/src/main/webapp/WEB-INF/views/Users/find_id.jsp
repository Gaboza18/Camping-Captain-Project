<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style type="text/css">
.mybtn{
  width:150px;
  height:40px;
  padding:0;
  display:inline; 
  border-radius: 4px; 
  background: #212529;
  color: #fff;
  margin-top: 20px;
  border: solid 2px #212529; 
  transition: all 0.5s ease-in-out 0s;
}
.mybtn:hover .mybtn:focus {
  background: white;
  color: #212529;
  text-decoration: none;
}
</style>
		<title>아이디 찾기</title>
		<script src="jquery/jquery.min.js"></script>
		<script src="js/bootstrap.bundle.min.js"></script>		
	</head>
	<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4 w3-auto" style="width: 382px;height: 456.3px;">
			<div class="w3-center w3-large w3-margin-top">	
				<h3>아이디 찾기</h3>
			</div>
			<form method="post" class="form-signin" action="find_id" name="findform">		
				<p>		
					<label for="name">이름</label>
					<input type="text" id="name" name="name" class="w3-input" placeholder="회원가입한 이름을 입력하세요" required/>
				</p>
				<p>	
					<label for="phone">전화번호</label>	
					<input type="text" id="phone" name="phone" class="w3-input" placeholder="회원가입한 전화번호를 입력하세요" required/>
				</p>	
				<div>
					<input class="btn btn-lg btn-secondary btn-block text-uppercase"
					type="submit" value="check">&nbsp;&nbsp;
					<button type="button" onclick="history.go(-1);">로그인으로</button>					
				</div><br><br>
		
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
					<label>찾으시는 아이디는 '${id}' 입니다.</label>
				</c:if>
			</form>
		</div>
		</div>
	</body>
	<%@ include file="../footer.jsp" %> 
</html>


