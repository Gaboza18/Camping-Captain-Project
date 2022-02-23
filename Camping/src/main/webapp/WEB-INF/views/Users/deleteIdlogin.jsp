<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp"%>
     
  <article>
    <form method="post" action="deleteIdlogin">
        <fieldset>
        
        <h2>회원탈퇴를 위해 아이디와 비밀번호를 입력해주세요</h2>
          <legend>로그인</legend>
          <label>아이디</label>
          <input name="id" type="text" value="${id}"><br><br> 
          <label>비밀번호</label> 
          <input name="password" type="password"><br>
      <div class="clear"><input type="submit" value="계속진행" class="submit" onclick="location='deleteIdView'"></div>
        
        <br><br>
        <div id="buttons">
            
            <input type="button" value="아이디 찾기" class="submit"
                 onclick="location='find_id'">
            <input type="button" value="비밀번호 찾기" class="submit"
                 onclick="location='find_pwd'">
                                                                 
        </div> 
        </fieldset>
        </div>
    </form>  
  </article>
<%@ include file="../footer.jsp" %>  
   
