<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  

<script type="text/javascript">

			function go_modify() {
			
			    if ($("#password").val() == "") {
			      alert("비밀번호를 입력 하세요");
			      $("#password").focus();
			      return false;
			   } else if ($("#password").val() != $("#passwordCheck").val()) {
			      alert("비밀번호가 일치하지 않습니다");
			      $("#password").focus();
			      return false;
			   } else if ($("#name").val() == "") {
			      alert("이름을 입력해 주세요");
			      $("#name").focus();
			      return false;
			   } else {
			      $("#usermodify").attr("action", "usersUpdate").submit();
			      alert("회원 정보가 변경되었습니다. 다시 로그인해주세요" )// 회원정보 변경요청
			   }
			
			}

</script>

<article id="userUpdate">
	<h2>부족 정보 수정</h2>
    <form id="usermodify" action="usermodify" method="post" name="formm">
		<h3>${sessionScope.loginUser.name}(${sessionScope.loginUser.id})부족님 반갑습니다.</h3><br>
        					
  		<input type="hidden" name = "id" value="${sessionScope.loginUser.id}">
        
        <label>Password</label> 
        <input type="password"  name="password" id="password"><br> 
        
        <label>Retype Password</label> 
        <input type="password"  id="passwordCheck"><br> 
        
        <label>Name</label>
        <input type="text" name="name" id="name" value="${users.name}"><br>

        <label>E-Mail</label>
        <input type="text" name="email" value="${users.email}"><br>
        
        <label>Phone Number</label> 
        <input  type="text"       name="phone" value="${users.phone}"><br>
        
      	<div class="clear"></div>
      	
      	<div id="btn">
	        <input type="button"    value="회원정보수정 완료"   class="submit" onclick="go_modify()"> 
	        <input type="reset"      value="취소" class="cancel" onClick="location.href='index'">
      	</div>
      	<br>
	</form>
</article>
<%@ include file="../footer.jsp" %>

  