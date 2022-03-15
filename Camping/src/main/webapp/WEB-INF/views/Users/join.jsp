<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>    

<article id="join_form">
    <h2>Join Us</h2>
    <form id="join" action="join" method="get" name="formm">
    	<table>
	    	<tr>
				<th>User ID</th>
		        <td>
			        <input type="text" name="id" id="id" value="${UsersVO.id}" >
			        <input type="hidden" name="reid" id="reid" value="${reid}">
			        <input type="button" id="button" value="중복 체크" class="dup" onclick="idcheck()">
					</td>
					</tr>

	    	<tr>
		        <th>Password</th> 
		        <td><input type="password" name="password" id="password"></td>
	        </tr>
	        
	    	<tr>
		        <th>Retype Password</th> 
		        <td><input type="password" name="passwordCheck" id="passwordCheck"></td>
	        </tr>
	        
	    	<tr>
		        <th>Name</th>
		        <td><input type="text" name="name" id="name"></td>
	        </tr>
	        
	    	<tr>
		        <th>BirthDay</th>
		        <td>
			        <input type="text" name="birth" maxlength="6" class="birth"><span>&nbsp;&nbsp;-&nbsp;</span>
			        <input type="text" name="birth_gen" maxlength="1" class="birth_gen">XXXXXX
		        </td>
	        </tr>
       
	    	<tr>
		        <th>Phone Number</th> 
		        <td><input  type="text" name="phone"></td>
	        </tr>
	        
        </table>
      
      	<div class="clear"></div>
      
      	<div id="buttons">
	        <input type="button"    value="간편회원가입"   class="submit" onclick="go_save1()"> 
	        <input type="reset"      value="취소"     class="cancel">
      	</div>
      	<br>
    </form>
</article>
<%@ include file="../footer.jsp" %>

  