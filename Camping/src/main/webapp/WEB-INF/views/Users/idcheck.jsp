<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<link href="CSS/subpage.css" rel="stylesheet">
<style type="text/css">
	body{   
		text-align: center;
		background-color:#212529;
		background-image: url("./assets/img/map-image.png");
		background-repeat: no-repeat;
		background-position: center;
	}
	
	#wrap{     
		margin: 70px auto;
	}
	
	form {
		color: yellow;
	}
	
	h1 {
		font-size: 45px;
		color: rgb(254, 209, 54);
		font-weight: normal;
	}
	
	input[type=button], input[type=submit] {
		background-color: rgb(255, 200, 0);
		border-radius: 12px;
	}
	
	input[type=button]:hover, input[type=submit]:hover {
		background-color:  rgb(230, 177, 0);
	}
</style>
<script type="text/javascript">
	function idok(){
		opener.formm.id.value="${id}"; 
	  	opener.formm.reid.value="${id}";
	  	self.close();
	}
</script>
</head>
<body>
	<div id="wrap">
		<h1>ID 중복확인</h1>
		<form method=post name=formm id="theform" style="margin-right:0 " action="id_check" >
	    	User ID <input type=text name="id" value="${id}"> 
			<input type=submit value="검색" class="submit"><br>     
	    	<div style="margin-top: 20px">   
	      		<c:if test="${message == 1}">
	        		<script type="text/javascript">
	          			opener.document.formm.id.value="";
	        		</script>
	       			${id}는 이미 사용중인 아이디입니다.
	      		</c:if>
	      		<c:if test="${message==-1}">
	       			${id}는 사용 가능한 ID입니다.
	        		<input type="button" value="사용" class="cancel" onclick="idok()">
	      		</c:if>
			</div>
		</form>
	</div>  
</body>
</html>