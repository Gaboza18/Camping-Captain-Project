<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 지점, 예약날짜 선택
   	<form id="camp_search" action="camp_search" method="get">
   		<div>
   			<h4>지점</h4>
   			<select name="search_camp_name"id="search_camp_name">
				<c:forEach items="${conditionMap}" var="option">
					<option value="${option.value}"<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
				</c:forEach>
   			</select>
   		</div>
   		<div>
   			<h4>체크인</h4>
   			<input type="text" id="checkin_date" class="indate" name="checkin_date" value="${indate}" readOnly="readonly">
   		</div>
   		<div>
   			<h4>체크아웃</h4>
   			<input type="text" id="checkout_date" class="outdate" name="checkout_date" value="${outdate}" readOnly="readonly">
   		</div>
   		
   		<input id="btn" type="button" value="조회하기" onclick="search_chk()"/>
   	
	</form> -->
<article id="contain_camp_search">	
	<div class="container">
		<div class="text-center">
		    <h2 class="section-heading text-uppercase">예약하기</h2>
		    <h3 class="section-subheading text-muted">캠핑장과 날짜를 선택해주세요.</h3>
		</div>
         
		<form id="camp_search" action="camp_search" method="get">
			<div class="row text-center">
	            <!-- 지점, 예약날짜 선택 -->
	      
				<div class="col-md-4">
					<span class="fa-stack fa-4x">
						<img src="assets/img/camping.png" width="72" height="72">
	                </span>
	                <h4>지점</h4>
	  				<select name="search_camp_name"id="search_camp_name">
						<c:forEach items="${conditionMap}" var="option">
							<option value="${option.value}"<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
						</c:forEach>
					</select>
				</div>
	            <div class="col-md-4">
	            	<span class="fa-stack fa-4x">
	                	<img src="assets/img/checkIn&Out.png">
	                </span>
	             	<h4>체크인</h4>
						<input type="text" id="checkin_date" class="indate" name="checkin_date" value="${indate}" readOnly="readonly">
	            </div>
	            <div class="col-md-4">
	            	<span class="fa-stack fa-4x">
	                	<img src="assets/img/checkIn&Out.png">
	                </span>
	            	<h4>체크아웃</h4>
	   				<input type="text" id="checkout_date" class="outdate" name="checkout_date" value="${outdate}" readOnly="readonly">
	            </div>
	            
	            <input id="btn" class="btn btn-primary btn-xl text-uppercase" type="button" value="조회하기" onclick="search_chk()"/>
			</div>
		</form>
	</div>
</article>

<div class="clear"></div>