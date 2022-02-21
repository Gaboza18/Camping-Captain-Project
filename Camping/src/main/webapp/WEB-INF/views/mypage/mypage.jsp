<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="logo">
	<a href="index"> <img alt="camping" src="images/CampingCaptain.jpg"
		width="250" height="100">
	</a>
</div>

<article>
	<h1>마이페이지</h1>
	<form method="post" action="mypage">
		<fieldset>
			<legend></legend>

			<div>
				${sessionScope.loginUser.name}님 환영합니다! <br> <br> <label>메뉴를
					선택하세요</label>
				<ul>
					<li><a href="usermodify" style="width: 110px;">회원정보수정</a></li>
					<li><a href="deleteIdView" style="width: 110px;">회원탈퇴</a></li>
				</ul>
			</div>
		</fieldset>
	</form>
</article>

