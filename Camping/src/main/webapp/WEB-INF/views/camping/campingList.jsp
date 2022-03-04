<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../camping_search.jsp"%>
   		
<article id="camp_list">
	<div id="images">
		<c:choose>
			<c:when test="${camp_id == 1}">
				<h3>일반캠핑장</h3>
				<img id="campImages" src="images/1/캠핑족장_강원도지점(일반).png" width="580" height="400">
				<h3>차박캠핑장</h3>
				<img id="campImages" src="images/1/캠핑족장_강원도지점(차박).png" width="580" height="400">
			</c:when>
			<c:when test="${camp_id == 2}">
				<img id="campImages" src="images/2/경기도지점 데크3.jpg" width="580" height="400">
			</c:when>
			<c:when test="${camp_id == 3}">
				<img id="campImages" src="images/3/충청도지점 데크.jpg" width="580" height="400">
			</c:when>
			<c:when test="${camp_id == 4}">
				<img id="campImages" src="images/4/경상도지점.PNG" width="580" height="400">
			</c:when>
			<c:when test="${camp_id == 5}">
				<img id="campImages" src="images/5/전라도지점 데크.jpg" width="580" height="400">
			</c:when>
			<c:when test="${camp_id == 6}">
				<img id="campImages" src="images/6/제주도지점 데크.jpg" width="580" height="400">
			</c:when>
		</c:choose>
	</div>

	
	<c:forEach items="${campingList}" var="camping">
		<div id="campZoneList">	
			<form name="formm" id="theform" method="post" action="order_insert_form">
				<input name="camp_id" type="hidden" value="${camping.camp_id}"/>
				<input name="camp_zone" id="campzone" type="hidden" value="${camping.camp_zone}"/>
				<input name="indate" type="hidden" id="indate" value="${indate}"/>
				<input name="outdate" type="hidden" id="outdate" value="${outdate}"/>
				<table>
					<tr>
						<th colspan="2">${camping.camp_name}(${camping.camp_addr})</th>
					</tr>
			        <tr>
			            <td rowspan="4" width="200"><img src="images/${camping.camp_id}/${camping.image}" width="170" height="100"></td>
			            <td>${camping.camp_zone}구역</td>
			        </tr>
			        <tr>
			            <td>기준 ${camping.base_people}인/최대 ${camping.max_people}인</td>
			        </tr>
			        <tr>
				        <c:choose>
							<c:when test="${camping.car_camp == 'y'}">
			            		<td>차박가능</td>
							</c:when>
							<c:when test="${camping.car_camp == 'n'}">
			            		<td >차박불가능</td>
							</c:when>
						</c:choose>
					</tr>
			        <tr>
			            <td>주중 : ${camping.weekdays_price} <br> 주말 : ${camping.weekend_price}</td>
			        </tr>
				</table>
				<div id="button">
					<c:set var="flag" value="false"/>
					<c:forEach var="order" items="${order}">
						<c:if test="${camping.camp_zone eq order.camp_zone and indate eq order.indate}">
			            	<input type="button" value="예약마감"/>
			            	<c:set var="flag" value="true"/>
						</c:if>		
					</c:forEach>
					<c:if test="${not flag}">
	            		<input type="submit" value="예약하기"/>
					</c:if>
    			</div>
			</form>
		</div>
	</c:forEach>
</article>
<%@ include file="../footer.jsp" %>