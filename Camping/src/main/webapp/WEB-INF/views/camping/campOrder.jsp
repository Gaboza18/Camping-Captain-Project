<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	 
 
<article id="campOrder">
	<h3>캠핑장 예약</h3>    
	<form name="formm" method="post" action="order_insert">
		<input name="camp_id" type="hidden" value="${camp.camp_id}"/>
		<table id="orderForm">
	      	<tr>
				<th>캠핑장 이름</th>
				<td><input type="text" name="camp_name" value="${camp.camp_name}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
	        	<th>캠핑장 주소</th>
	        	<td><input type="text" name="camp_addr" value="${camp.camp_addr}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>선택구역</th>
		        <td><input type="text" name="camp_zone" value="${camp.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="in" value="${indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="out" value="${outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 성함</th>
		        <td><input type="text" name="order_name" value="${users.name}"></td>
	      	</tr>
	      	<tr>
		        <th>인원선택</th>
		        <td>
		        	<input name="max" id="max" type="hidden" value="${camp.max_people}"/>
		        	<input name="base" id="base" type="hidden" value="${camp.base_people}"/>
			        <button type="button" onclick="minus();">-</button>
			        <input type="text" id="people" name="order_people" value="${camp.base_people}" readonly="readonly"/>
			        <button type ="button" onclick="plus();">+</button>
	    		</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" value="${users.phone}"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" value="${users.email}"></td>
	      	</tr>
	      	<tr>
		        <th>결제</th>
		        <td>
		        <c:choose>
					<c:when test="${day eq '금' || day eq '토'}"><input type="text" name="total_price" value="${camp.weekend_price}"></c:when>
					<c:otherwise><input type="text" name="total_price" value="${camp.weekdays_price}"></c:otherwise>
				</c:choose>
		        </td>
	      	</tr>
		</table>
		<div id="buttons">
			<input type="submit" value="예약" class="submit"> 	
		</div>
		<div class="clear"></div>
	</form>
</article>
<%@ include file="../footer.jsp" %>

