<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<nav id="#" class="admin_side_menu">
	

	    <ul>    
	      <li>
	      	<a href="#">예약관리</a>
	      	<ul class="header_submenu">
	      		<li><a href="#">예약내역 조회</a></li>
	      		<li><a href="#">예약취소 관리</a></li>
	      	</ul>
	      </li>
	      <!-- response.sendRedirect("이동할 jsp 페이지"); -->
	      
	      <li>
	      	<a href="">게시판</a>
	      	<ul class="header_submenu">
	      		<li><a href="#">관리지 게시판</a></li>
	      		<li><a href="#">회원 게시판 관리</a></li>
	      		
	      		
	      		 <!-- get 
    <form method="get" action="response_sendRedirect.jsp">
        response.sendRedirect 이름 : <input type=text name="name2">
        <input type=submit value="확인">
    </form>   --> 

	      		
	      			
	      		
	      	</ul>
	      
	      </li>
	      <li><a href="#">각 지점 정산</a></li> 
	    </ul>
	    	
    </nav>
   

<%@ include file="admin_footer.jsp" %>
