<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>email인증</title>
<link href="CSS/subpage.css" rel="stylesheet">

<style type="text/css">
	body{   
	 	background-color: #212529;
	  	font-family: Verdana;
	  	color: rgb(255, 200, 0);
	  	text-align: center;
	}
	
	h2 {
		margin-top: 12.5%;
	  	font-family: "Times New Roman", Times, serif;
	  	font-weight: normal;
	}
</style>

</head>
<body>
	<div>
		<input type="hidden" name = "email" value="${users}">
		<input type="hidden" name = "email" value="${id}">
		<input type="hidden" name="status" id="status" value="${status}"> 
	
		<h2> 사용하시는 이메일에 인증링크를 보냈습니다.<br>인증링크를 클릭하시면 됩니다</h2>
		<h2>${message}</h2>
	</div>  
</body>
</html>