<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div id="join_form" align="center">
	<article>
		<h2>Join Us</h2>
		<form id="join2" action="emailjoin" method="get" name="formm">
			<h2>${message}</h2>
			<h4>이메일 인증을 하지 않으시면 회원님의 &lt;비밀번호 찾기&gt;와 &lt;캠핑장 예약&gt;기능을 이용하실수 없습니다.</h4>
			<br>
			<h4>사용하시는 E-mail을 인증해주세요!</h4>
			<table>
				<tr>
					<th>ID</th>
					<td><input type="text" name="id" id="id" value="${id}" readonly></td>
				</tr>
	
				<c:if test="${status eq 'n'}">
					<tr>
						<th>E-Mail</th>
						<!--이메일 인증 -->
						<td>
							<input type="text" name="email" id="email"> 
							<input type="button" value="이메일 인증" class="dup" onclick="emailcheck()">
						</td>
					</tr>
				</c:if>
				
				<c:if test="${status eq 'y'}">
					<tr>
						<th>Email</th>
						<td><input type="text" name="email" id="email" value="${email}" readonly></td>
					</tr>
				</c:if>
			</table>
	
			<div class="clear"></div>
			<!-- js를 이용해서 status값이 n이면  email아이디 리턴 -->
			<div id="buttons">
				<input type="hidden" name="status" id="status" value="${status}">
				<input type="button" value="이메일 인증하기" class="submit" onclick="go_save2()">
				<c:if test="${status eq 'n'}">
					<input type="hidden" name="status" id="status" value="${status}">
					<input type="button" value="다음에 하기" class="cancel" onclick="go_save3()">
				</c:if>
			</div>	
		</form>
	</article>
</div>
<%@ include file="../footer.jsp"%>