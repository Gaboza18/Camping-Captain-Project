<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp"%>
     
  <article>
    <form method="post" action="login">
        <fieldset>
          <legend>로그인</legend>
          <label>아이디</label>
          <input name="id" type="text" value="${id}"><br><br> 
          <label>비밀번호</label> 
          <input name="password" type="password"><br><br>
      <div class="clear"></div>
        <div id="buttons">
            <input type="submit" value="로그인" class="submit">
            <input type="button" value="회원가입" class="cancel"
                 onclick="location='contract'">
            <input type="button" value="아이디 찾기" class="submit"
                 onclick="location='find_id'">
            <input type="button" value="비밀번호 찾기" class="submit"
                 onclick="location='find_pwd'">
            <input type="button" value="회원탈퇴" class="submit"
                 onclick="location.href='deleteIdView'">                                                        
        </div> 
        </fieldset>
        </div>
    </form>  
  </article>
<%@ include file="../footer.jsp" %>  
   
