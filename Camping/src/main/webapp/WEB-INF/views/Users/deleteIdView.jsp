<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!-- 무슨 의미인지 몰라서 지웅ㄴ것 -->
<!-- %@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%  -->
<html>
	<head>

		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	 	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	 	
	 	
		<title>회원탈퇴</title>
	</head>
	<script type="text/javascript">
		
	
	
	
	
	$(document).ready(function(){
			// 취소
			$(".cencle").on("click", function(){
				
				location.href = "login";
						    
			})
		
			$("#submit").on("click", function(){
				if($("#password").val()==""){
					alert("비밀번호 또는 아이디를 확인해주세요.");
					$("#password").focus();
					return false;
				}	
			});
			
				
			
		})
		
	</script>
	<body>
	 
		<section id="container">
			<form id="usersDelete" action="usersDelete" method="post">
				<div class="form-group has-feedback">
					<label class="control-label" for="id">아이디</label>
					<input class="form-control" type="id" id="id" name="id" value="${users.id}" />
				</div>
				<div class="form-group has-feedback">
					<label class="control-label" for="password">패스워드</label>
					<input class="form-control" type="password" id="password" name="password" value="${users.password}" />
				</div>
				
				<div class="form-group has-feedback">
					<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
					<button class="cencle btn btn-danger" type="button">취소</button>
				</div>
			</form>
			<div>
				<c:if test="${msg == false}">
					비밀번호가 맞지 않습니다.
				</c:if>
			</div>
		</section>
	
	</body>