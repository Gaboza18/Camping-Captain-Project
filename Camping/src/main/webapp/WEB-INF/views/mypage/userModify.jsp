<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<link rel="stylesheet" href="css/styles.css" >

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

<div id="user_update_form" align="center">
	<article id="userUpdate">
		<h2>부족 정보 수정</h2>
	    <form id="usermodify" action="usermodify" method="post" name="formm">
			<h4>${sessionScope.loginUser.name}(${sessionScope.loginUser.id})부족님 반갑습니다.</h4><br>
	        				
          <input type="hidden" name="id" value="${users.id}">
          <input type="hidden" name="email" id="id" value="${id}"> 
	        
	        <label>Password</label> 
	        <input type="password"  name="password" id="password"><br>
	        
	        <label>Retype Password</label> 
	        <input type="password"  id="passwordCheck"><br> 
	        
	        <label>Name</label>
	        <input type="text" name="name" id="name" value="${users.name}"><br>
	
	        <label>BirthDay</label>
	        <input type="text" name="birth" maxlength="6" class="birth" value="${birth}"><span style="color: white;">-</span>
			<input type="text" name="birth_gen" maxlength="1" class="birth_gen" value="${birth_gen}"><span id="spanRight" style="color: white;">●●●●●●</span><br> 
	
	      	<c:if test="${loginUser.status eq 'n'}">
			      <label id="email_label">E-mail</label>

		          <input type="text" name="email" id="email">
		          <input type="button" name="email" id="email" value="이메일 인증하기" class="dup" onclick="emailcheck2()"><br>
		          <input type="hidden" name="id" id="id" value="${id}">
		          <input type="hidden" name="id" id="id" value="${users.id}">
			</c:if>

			<c:if test="${loginUser.status eq 'y'}">
				<label>E-mail</label>
				  <input type="text" name="email" id="email" value="${email}"
					readOnly="readonly"><br>
			</c:if>
	        
	        <label>Phone Number</label> 
	        <input  type="text" name="phone" value="${users.phone}"><br>
	        
	      	<div class="clear"></div>
	      	<br><br>
	      	<div id="btn">
		        <input type="button" value="회원정보수정 완료"   class="submit" onclick="go_modify()"> 
		        <input type="reset" value="취소" class="cancel" onClick="location.href='index'">
	      	</div>
	      	<br>
		</form>
	</article>
</div>
<%@ include file="../footer.jsp" %>


