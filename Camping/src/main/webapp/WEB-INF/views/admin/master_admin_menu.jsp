<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<nav id="#" class="admin_side_menu">
	    <ul>    
	      <li>
	      	<a href="#">예약관리</a>
	      	<ul class="header_submenu">
	      		<li><a href="search_order">예약내역 조회</a></li>
	      		<li><a href="search_cancel">예약취소 관리</a></li>
	      	</ul>
	      </li>
	      <li>
	      	<a href="#">게시판</a>
	      	<ul class="header_submenu">
	      		<li><a href="admin_notice_list">관리자 게시판</a></li>
	      		<li><a href="master_manage_notice_list">회원 게시판 관리</a></li>
	      		<li><a href="adminReview">후기 관리</a></li>
	      		<li><a href="question_write_form">자주 묻는질문</a></li>
	      	</ul>
	      </li>
	      <li><a href="#">회원관리</a></li> 
	      <li><a href="admin_qna_list">1:1 문의 관리</a></li> 
	      <li><a href="admin_notice_list">관리자 게시판 관리</a></li>
	      <li><a href="go_admin_master_calculate_year">캠핑족장 - 연 정산</a></li>
	      <li><a href="go_admin_master_calculate_month">캠핑족장 - 월 정산</a></li>
	      <li><a href="admin_master_calculate_day">캠핑족장 - 일일 정산</a></li>
	      <li><a href="admin_users_gender_ratio">회원 성별 통계</a></li> 
	      <li><a href="admin_users_age_ratio">회원 연령 통계</a></li>
	    </ul>
    </nav>
    <div id="master_main">
    	 <h2>${loginAdmin.name}님 환영합니다</h2>
    	 
    </div>
     


