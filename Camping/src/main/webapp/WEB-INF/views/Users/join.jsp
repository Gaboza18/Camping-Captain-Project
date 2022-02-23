<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>    


  <article>
    <h2>Join Us</h2>
    <form id="join" action="join" method="post" name="formm">
      <fieldset>
        <legend>Basic Info</legend>
        <label>User ID</label>
        <input type="text"      name="id"  id="id" value="${id}"  size="12"  >
        <input type="hidden"    name="reid" id="reid" value="${reid}">
        <input type="button"    value="중복 체크"  class="dup" onclick="idcheck()"><br>
        <label>Password</label> 
        <input type="password"  name="password" id="password"><br> 
        <label>Retype Password</label> 
        <input type="password"  name="passwordCheck" id="passwordCheck"><br> 
        <label>Name</label>
        <input type="text" name="name" id="name"><br> 
        주민번호 앞 (7자리)입력 <input type="text" name="birth1" id="birth1" maxlength='7' size="10"><br>

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
        <input type="button"    value="회원가입"   class="submit" onclick="go_save()"> 
        <input type="reset"      value="취소"     class="cancel">
      </div>
      <br>
    </form>
  </article>

  