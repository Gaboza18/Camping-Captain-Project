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
	      		<li><a href="manager_admin_notice_list">관리자 게시판</a></li>
	      		<li><a href="#">회원 게시판 관리</a></li>
	      		<li><a href="#">후기 관리</a></li>
	      	</ul>
	      </li>
	      <li><a href="branch_calculate_year?name=${loginAdmin.name}">연도별 정산</a></li>
	      <li><a href="branch_calculate_month?name=${loginAdmin.name}">월 별 정산</a></li>
	      <li><a href="branch_calculate_day?name=${loginAdmin.name}">일일 별 정산</a></li>    
	    </ul>
    </nav>
   <h2>${loginAdmin.name} 관리자님 환영합니다</h2> 