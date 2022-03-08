<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<style>
	/* 예약성공 멘트 페이지 설정*/
	#successOrder{
		text-align: center;
		height: 410px;
	}
	
	#success {
		width: 500px;
		border: 1px solid rgb(20, 92, 28);
		border-radius: 10px;
		padding: 20px;
		margin: 20px auto;
	}
	
	div#btn a{
		border: 1px solid rgb(20, 92, 28);
		border-radius: 10px;
		text-decoration: none;
		color: black;
		margin: 30px 20px;
		padding: 10px;
	}
</style>

<article id="successOrder">
	<div id="success" align="center">
		<h2>예약이 성공적으로 완료되었습니다.</h2>
	</div>
	<div id="btn" align="center">
		<a href="index">메인 홈으로</a>
		<a href="my_reservation">예약 내역으로</a>
	</div>
</article>

<%@ include file="../footer.jsp" %>