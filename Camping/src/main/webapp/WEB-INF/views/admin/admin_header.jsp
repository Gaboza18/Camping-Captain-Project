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
<script type="text/javascript" src="js/camping.js"></script>

<!-- 캘린더 설정구간 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script> 
	// 날짜선택 캘린더 한글설정
	// (아래 $("#datepicker").datepicker({}); 안에 작성해도 상관없음)
 	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년',
        showOtherMonths:true ,   // 이전 다음달의 날짜 표시여부
        minDate: 0,
        autoclose : true //사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
    });

	$(function(){ 
	    
	    $("#checkin_date").datepicker({
	        showOn:"button", 
			buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
			buttonImageOnly: true,
			changeMonth:true,  // 년,월을 셀렉트박스로 표기해서 선택가능하도록 표기
	        changeYear:true,
	        yearRange:"-10:+100", // 연도 선택범위(현재 년도를 기준으로 10년전부터 100년후까지만 표기)
        	onClose: function ( selectedDate ) {
		        $("#checkout_date").datepicker( "option", "minDate", selectedDate );
		    }
	    });
	    
	    $("#checkout_date").datepicker({
	        showOn:"button", 
			buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
			buttonImageOnly: true,
			changeMonth:true,  // 년,월을 셀렉트박스로 표기해서 선택가능하도록 표기
	        changeYear:true,
	        yearRange:"-10:+100" // 연도 선택범위(현재 년도를 기준으로 10년전부터 100년후까지만 표기)
	    });
	}); 
	
</script>

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
			<nav id="admin_logout">
				<a href="admin_logout">LOGOUT</a>
			</nav>
			<div class="clear"></div>
			<hr>
		</header>
		<!-- 헤더파일 종료 -->
	