<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../camping_search.jsp"%>
   		
<article id="camp_list">

	<c:forEach items="${campingList}" var="camping">
		<div id="campZoneList" align="center">	
			<form name="formm" id="theform" method="post" action="order_insert_form">
				<input name="camp_id" type="hidden" value="${camping.camp_id}"/>
				<input name="indate" type="hidden" id="indate" value="${indate}"/>
				<input name="outdate" type="hidden" id="outdate" value="${outdate}"/>
				<table>
					<tr>
						<th colspan="2">${camping.camp_name}(${camping.camp_addr})</th>
					</tr>
			        <tr>
			            <td rowspan="4" width="200"><img src="images/${camping.image}" width="170" height="100"></td>
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
      				<input type="submit" value="예약하기">
    			</div>
			</form>
		</div>
	</c:forEach>
</article>
<%@ include file="../footer.jsp" %>