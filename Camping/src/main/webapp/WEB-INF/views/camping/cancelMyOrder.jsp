<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 예약 취소하기</title>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function deleteOrder() {
	$.ajax({
		type: "GET",
		url: "cancel_myOrder?oseq="+$("#oseq").val(),
		dataType: "html",
		contentType:  "application/json; charset=UTF-8",
		data: $("#cancel_myOrder").serialize()
	}).done(function(){
		alert("예약이 취소되었습니다.");
		opener.location.href="my_reservation";
		self.close();
	}).fail(function(error){
		alert("에러");
	});
}
</script>
<style>

	body {
		background-color: #212529;
		color: white;
	}

	table {
		margin-top: 12.5%;
		margin-left: 12.5%;
	}

	table th {
		width: 150px;
		text-align: left;
	}
	
	table td input {
		border-radius: 12px;
		padding: 2px 8px;
		text-align: center;
	}
	
	div#button button {
		margin-top: 30px;
		background-color: rgb(255, 200, 0);
		border-radius: 12px;
		padding: 1px 8px;
		line-height: 1.7;
	}
	
	div#button button:hover {
		background-color:  rgb(230, 177, 0);
	}
</style>
</head>
<body>
	<form id="cancel_myOrder" name="frmm" method="get" action="cancel_myOrder">
		<input type="hidden" name="usersid" id="usersid" value="${campOrder.user_id}">
		<table>
	      	<tr>
				<th>예약번호</th>
				<td><input type="text" name="oseq" id="oseq" value="${campOrder.oseq}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
		        <th>캠핑장 지점</th>
		        <td><input type="text" name="camp_name" id="camp_name" value="${campOrder.camp_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>구역</th>
		        <td><input type="text" name="camp_zone" id="camp_zone" value="${campOrder.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="indate" value="${campOrder.indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="outdate" value="${campOrder.outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약자 이름</th>
	        	<td><input type="text" name="order_name" id="order_name" value="${campOrder.order_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약인원</th>
	        	<td><input type="text" name="order_people" id="order_people" value="${campOrder.order_people}" readOnly="readonly">명</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" id="order_phone" value="${campOrder.order_phone}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" id="order_email" value="${campOrder.order_email}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>결제금액</th>
		        <td><input type="text" name="total_price" id="total_price" value="${campOrder.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>환불금액</th>
		        <td>
		        	<fmt:parseDate value="${today}" var="now" pattern="yyyy-MM-dd"/>
		        	<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="to_day" />
		        	<fmt:parseDate value="${campOrder.indate}" var="checkin" pattern="yyyy-MM-dd"/>
		        	<fmt:parseNumber value="${checkin.time / (1000*60*60*24)}" integerOnly="true" var="check_in" />
		        	
		        	<fmt:parseNumber var="oneday_ago" value="${campOrder.total_price * 0.2}" integerOnly="true" />
		        	<fmt:parseNumber var="threeday_ago" value="${campOrder.total_price * 0.5}" integerOnly="true" />
		        	<fmt:parseNumber var="fiveday_ago" value="${campOrder.total_price * 0.7}" integerOnly="true" />
		        	<fmt:parseNumber var="sevenday_ago" value="${campOrder.total_price * 0.9}" integerOnly="true" />
		        	
		        	<c:choose>
		        		<c:when test="${check_in - to_day == 0 || check_in - to_day == 1}">
		        			<input type="number" name="cancelprice" id="cancelprice" value="${oneday_ago}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day == 2 || check_in - to_day == 3}">
		        			<input type="number" name="cancelprice" id="cancelprice" value="${threeday_ago}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day == 4 || check_in - to_day == 5}">
		        			<input type="number" name="cancelprice" id="cancelprice" value="${fiveday_ago}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day == 6 || check_in - to_day == 7}">
		        			<input type="number" name="cancelprice" id="cancelprice" value="${sevenday_ago}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day > 7}">
		        			<input type="number" name="cancelprice" id="cancelprice" value="${campOrder.total_price}" readOnly="readonly">원
		        		</c:when>
		        	</c:choose>
		        </td>
	      	</tr>
    		<tr>
    			<th>위약금</th>
    			<td>
    				<c:choose>
		        		<c:when test="${check_in - to_day == 0 || check_in - to_day == 1}">
		        			<fmt:parseNumber var="oneday" value="${campOrder.total_price * 0.8}" integerOnly="true" />
		        			<input type="number" name="cancelnet" id="cancelnet" value="${oneday}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day == 2 || check_in - to_day == 3}">
		        			<fmt:parseNumber var="threeday" value="${campOrder.total_price * 0.5}" integerOnly="true" />
		        			<input type="number" name="cancelnet" id="cancelnet" value="${threeday}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day == 4 || check_in - to_day == 5}">
		        			<fmt:parseNumber var="fiveday" value="${campOrder.total_price * 0.3}" integerOnly="true" />
		        			<input type="number" name="cancelnet" id="cancelnet" value="${fiveday}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day == 6 || check_in - to_day == 7}">
		        			<fmt:parseNumber var="sevenday" value="${campOrder.total_price * 0.1}" integerOnly="true" />
		        			<input type="number" name="cancelnet" id="cancelnet" value="${sevenday}" readOnly="readonly">원
		        		</c:when>
		        		<c:when test="${check_in - to_day > 7}">
		        			<input type="number" name="cancelnet" id="cancelnet" value="0" readOnly="readonly">원
		        		</c:when>
		        	</c:choose>
    			</td>
    		 </tr>
		</table>
		<div id="button" align="center">
			<button type="button" id="delete_btn" onclick="deleteOrder()">예약취소</button>
		</div>
	</form>
</body>
</html>