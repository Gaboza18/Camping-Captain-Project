<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!-- 지점, 예약날짜 선택 -->
<%--<%@ include file="camping_search.jsp" %> --%>

<!-- 지점, 예약날짜 선택 -->
   	<form id="camp_search" action="camp_search" method="get">
   		<div>
   			<h4>지점</h4>
   			<select name="search_camp_name" class="search_camp_name" onChange="search_chk()">
				<c:forEach items="${conditionMap}" var="option">
					<option value="${option.value}">${option.key}</option>
				</c:forEach>
   			</select>
   		</div>
   		<div>
   			<h4>체크인</h4>
   			<input type="text" id="checkin_date" class="indate" name="checkin_date">
   		</div>
   		<div>
   			<h4>체크인</h4>
   			<input type="text" id="checkout_date" class="outdate" name="checkout_date">
   		</div>
   		
   		<input id="btn" type="submit" value="조회하기" onclick="search_chk()"/>
   	
	</form>
	<div class="clear"></div>

<%@ include file="footer.jsp" %>

