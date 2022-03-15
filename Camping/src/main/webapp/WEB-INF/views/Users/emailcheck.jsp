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
  background-color:#B96DB5;
  font-family: Verdana;
}
#wrap{     
  margin: 0 20px;
}
h1 {
  font-family: "Times New Roman", Times, serif;
  font-size: 45px;
  color: #CCC;
  font-weight: normal;
}
input[type=button], input[type=submit] {
  float: right;
}
</style>
<script type="text/javascript">

</script>

</head>
<body>
<div>
<input type="hidden" name = "email" value="${users}">
<input type="hidden" name = "email" value="${id}">
<input type="hidden" name="status" id="status" value="${status}"> 

						<h2> 사용하시는 이메일에 인증링크를 보냈습니다. 인증링크를 클릭하시면 됩니다</h2>
							<h2><p>${message}</p></h2>
							
					
 
</div>  


</body>
</html>