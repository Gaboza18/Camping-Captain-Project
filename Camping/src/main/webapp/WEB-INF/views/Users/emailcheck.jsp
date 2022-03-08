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
function emailok(){
  opener.formm.email.value="${email}"; 
  opener.formm.reemail.value="${email}";
  self.close();
}
</script>
</head>
<body>
<div id="wrap">
  <h2>email등록</h2>
  <form method=post name=formm id="theform" style="margin-right:0 "
  		action="signUpConfirm" >
    사용하는 이메일에서 인증링크를 눌러주세요<br>

     <input type=text name="email" value="${email}"> 
            <input type=submit value="사용" class="submit" onclick="emailok()"><br>     
    <div style="margin-top: 20px">   
    
        
  
       
        </form>
        </div>
    
  

</body>
</html>