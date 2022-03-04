<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ include file="../header.jsp" %>  

<article id="usersDeleteForm">	 
	<section id="container">
		<form id="usersDelete" action="usersDelete" method="post">
				<label class="control-label" for="id">아이디</label>
				<input class="form-control" type="text" id="id" name="id" value="${users.id}" readOnly="readonly"/>
				<input class="form-control" type="hidden" id="password" name="password" value="${users.password}" readOnly="readonly"/><br>
				<label>비밀번호 재확인</label> 
				<input type="password"  id="passwordCheck"><br> 
			
			<div class="clear"></div>
			
			<div id="subbtn">
				<input type="button" id="delete" onclick="go_out()" value="회원탈퇴">
				<input type="button" onclick= "location.href='index'" value="취소">
			</div>
		</form>
	</section>
</article>
<%@ include file="../footer.jsp" %>
