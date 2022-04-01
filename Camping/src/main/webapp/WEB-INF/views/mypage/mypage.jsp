<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	 
<link rel="stylesheet" href="css/styles.css" >

<div id="mypage_view_form" align="center">
	<article id="mypage">
		<h2>My Page</h2>
		<form id="mypage_form" method="post" action="mypage">
			<div>
				<b>${sessionScope.loginUser.name}</b> 님 환영합니다! <br><br> 
				<table id="userForm">
			      	<tr>
				        <th width="100">아이디</th>
				        <td width="250"><input type="text" name="id" id="id" value="${users.id}" readOnly="readonly"></td>
			      	</tr>
			      	<tr>
						<th>이름</th>
						<td><input type="text" name="name" value="${users.name}" readOnly="readonly"></td> 
			      	</tr>
			      	<tr>
			        	<th>이메일</th>
			        	<td><input type="text" name="email" value="${users.email}" readOnly="readonly"></td>
			      	</tr>
			      	<tr>
				        <th>전화번호</th>
				        <td><input type="text" name="phone" value="${users.phone}" readOnly="readonly"></td>
			      	</tr>
				</table>
				<div id="button">
					<a href="usermodify" style="width: 110px;">회원정보수정</a>
					<a href="deleteIdView" style="width: 110px;">회원탈퇴</a>
				</div>
			</div>			
		</form>
	</article>
</div>
<%@ include file="../footer.jsp" %>