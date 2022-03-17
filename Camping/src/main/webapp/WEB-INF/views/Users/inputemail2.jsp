<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>



<div id="join_form" align="center">
	<article>
		<h2>Join Us</h2>
		<form id="join2" action="emailjoin" method="get" name="formm">
	
			<h2>
				<p>${message}</p>
			</h2>
			<h4>
				이메일 인증을 하지 않는다면 회원의
				<비밀번호 찾기>와 <캠핑장 예약>을 할 수 없습니다.
			</h4>
			<br>
			<h4>사용하시는 E-mail을 인증 등록해주세요!</h4>
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" id="id" value="${id}" readonly></td>
				</tr>
	
					<tr>
						<th>Email</th>
						<td><input type="text" name="email" id="email"
							value="${email}" readonly><span></span><a onClick="window.location.reload()" style="cursor: pointer;">[이메일확인버튼]</a><span></span></td>
					</tr>
				
	
			</table>
	
			<div class="clear"></div>
			<!-- js를 이용해서 status값이 n이면  email아이디 리턴 -->
			<div id="buttons">
					
				<input type="hidden" name="status" id="status" value="${status}">
				<input type="button" value="이메일 회원가입" class="submit"
					onclick="go_save2()">
				
			</div>
	
	
	
		</form>
	</article>
</div>
<%@ include file="../footer.jsp"%>

