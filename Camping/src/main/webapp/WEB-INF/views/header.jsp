<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Camping Captain</title>
<link rel="stylesheet" href="css/camping.css" >
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/users.js"></script>
</head>
<body>


	<div id="wrap">
		<!-- 헤더파일 시작 -->
		<header>
			<!-- 홈페이지메인 로고  들어가는 라인-->
			<div id="logo">
				<a href="index">
					<img alt="camping" src="images/CampingCaptain.jpg" width="250" height="100">
				</a>
			</div>
			
			<!-- 로고 들어가는 라인 우측(로그인, 회원가입 버튼) -->
			<nav id="users_login_join">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<li>
								<a href="login"  style="width:110px;">로그인</a>
							</li>
							<li>
								<a href="join"  style="width:110px;">회원가입</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								${sessionScope.loginUser.name}(${sessionScope.loginUser.id}) 회원님
							</li>
					       	<li><a href="logout">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<div class="clear"></div>
			<hr>
			<nav id="header_menu">
		      	<ul>
			        <li>
			          	<a href="intro">캠핑족장 소개</a>
			        </li>  
			        <li>
			          	<a href="#">한눈에 보기</a>
			          	<ul class="header_submenu">
			          		<li><a href="#">가족과함께</a></li>
			          		<li><a href="#">애인과함께</a></li>
			          		<li><a href="#">반려동물과함께</a></li>
			          		<li><a href="#">산과함께</a></li>
			          		<li><a href="#">바다와함께</a></li>
			          		<li><a href="#">강과함께</a></li>
			          		<li><a href="#">차박</a></li>
			          	</ul>
			        </li>  
			        <li>
			          	<a href="#">리얼 후기</a>
			        </li> 
			        <li>
			          	<a href="#">고객센터</a>
			          	<ul class="header_submenu">
			          		<li><a href="notice_list">공지사항</a></li>
			          		<li><a href="question_list">자주 묻는 질문</a></li>
			          		<li><a href="qna_list">1:1 문의</a></li>
			          	</ul>
			        </li> 
			        <li>
			          	<a href="#">마이페이지</a>
			          	<ul class="header_submenu">
			          		<li><a href="#">내 정보</a></li>
			          		<li><a href="#">예약내역조회</a></li>
			          		<li><a href="#">나의 후기</a></li>
			          	</ul>
			        </li>  
		      	</ul>
	    	</nav>
	    	<hr>
	    <div class="clear"></div>
		</header>
		<!-- 헤더파일 종료 -->
	