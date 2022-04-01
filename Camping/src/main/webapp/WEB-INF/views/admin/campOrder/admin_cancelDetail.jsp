<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취소 확정</title>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function closeBtn() {
	
		self.close();
			
	}
</script>
<style>

	body {
		background-color: #212529;
		color: white;
	}

	table {
		margin-top: 11%;
		margin-left: 12.5%;
	}
	
	table th {
		width: 150px;
		text-align: left;
	}
	
	table td input {
		border-radius: 12px;
		padding: 3px 8px;
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
	<form id="confirm_cancel_order" name="frmm" method="post" action="confirm_order">
		<input type="hidden" name="cseq" id="cseq" value="${cancelDetail.cseq}">
		<input type="hidden" name="camp_Reid" id="camp_Reid" value="${selected}">
		<table>
	      	<tr>
				<th>예약번호</th>
				<td><input type="text" name="oseq" id="oseq" value="${cancelDetail.oseq}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
				<th>회원ID</th>
				<td><input type="text" name="usersid" id="usersid" value="${cancelDetail.usersid}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
		        <th>캠핑장 지점</th>
		        <td><input type="text" name="camp_name" id="camp_name" value="${cancelDetail.camp_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>구역</th>
		        <td><input type="text" name="camp_zone" id="camp_zone" value="${cancelDetail.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="indate" value="${cancelDetail.indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="outdate" value="${cancelDetail.outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약자 이름</th>
	        	<td><input type="text" name="order_name" id=order_name value="${cancelDetail.order_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약인원</th>
	        	<td><input type="text" name="order_people" id="order_people" value="${cancelDetail.order_people}" readOnly="readonly">명</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" id="order_phone" value="${cancelDetail.order_phone}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" id="order_email" value="${cancelDetail.order_email}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>결제금액</th>
		        <td><input type="text" name="total_price" id="total_price" value="${cancelDetail.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>환불금액</th>
		        <td><input type="text" name="cancelprice" id="cancelprice" value="${cancelDetail.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>위약금</th>
		        <td><input type="text" name="cancelnet" id="cancelnet" value="${cancelDetail.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>취소날짜</th>
		        <td><input type="text" name="cancel_date" id="cancel_date" value="${cancelDetail.cancel_date}" readOnly="readonly"> </td>
	      	</tr>
	      	<c:if test="${cancelDetail.reason != null}">
	      		<tr>
			        <th>취소사유</th>
			        <td><textarea name="reason" id="reason" rows="5" cols="30" style="resize: none;" readOnly="readonly">${cancelDetail.reason}</textarea></td>
	      		</tr>
	      	</c:if>
		</table>
		<div id="button" align="center">
			<button type="button" onclick="closeBtn()" style="text-align: center;">닫기</button>
		</div>
	</form>
</body>
</html>