<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <article>
    <h2>Join Us</h2>
    <form id="join" action="usermodify" method="post" name="formm">
      <fieldset>
        <legend>Basic Info</legend>
        <h1><label>부족 정보 수정</label></h1>
        
        
        <c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							
						</c:when>
						<c:otherwise>
							  <h3><li>
		${sessionScope.loginUser.name}(${sessionScope.loginUser.id})부족님 반갑습니다.
							</li> </h3>
							<br>
						</c:otherwise>
					</c:choose>
        
       <!--  
       <h3><li>
		${sessionScope.loginUser.name}(${sessionScope.loginUser.id})부족님 반갑습니다.
							</li> </h3>
							<br>   -->
							
      
        <label>Password</label> 
        <input type="password"  name="password" id="password"><br> 
        <label>Retype Password</label> 
        <input type="password"  name="passwordCheck" id="passwordCheck"><br> 
        <label>Name</label>
        <input type="text" name="name" id="name"><br> 
        <label>E-Mail</label>
        <input type="text" name="email"><br>

        
      </fieldset>
      <fieldset>
        <legend>Optional</legend>
        <label>Phone Number</label> 
        <input  type="text"       name="phone"><br>
      </fieldset>
      <div class="clear"></div>
      <div id="buttons">
        <input type="button"    value="회원정보수정 완료"   class="submit" onclick="go_save()"> 
        <input type="reset"      value="취소" class="cancel" onClick="location.href='index'">
      </div>
      <br>
    </form>
  </article>

  