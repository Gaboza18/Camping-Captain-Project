<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 지점, 예약날짜 선택 -->
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
   	
	</form>
	<div class="clear"></div>