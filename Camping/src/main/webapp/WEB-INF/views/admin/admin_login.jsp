<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>  


<article id="admin_loginForm">
	<div style=center>
    <form id="admin_login" method="post" action="admin_login">
		<h3>관리자 로그인</h3>
		<label>아이디</label>
		<input name="id" type="text"><br><br> 
		<label>비밀번호</label> 
		<input name="password" type="password"><br><br>
		
		<div class="clear"></div>
		
        <div id="admin_login_buttons">
            <input type="submit" value="로그인" class="submit">                                                     
        </div> 
    </form>
    </div>  
  </article>

<%@ include file="../footer.jsp"%>  