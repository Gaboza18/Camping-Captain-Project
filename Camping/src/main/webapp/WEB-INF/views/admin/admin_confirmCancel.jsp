<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취소 확정</title>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function cancel_chk() {

		$.ajax({
			type: "GET",
			url: "confirm_cancel_order",
			dataType: "html",
			contentType:  "application/json; charset=UTF-8",
			data: $("#confirm_cancel_order").serialize()
		}).done(function(){
			alert("예약 취소가 확정되었습니다.");
			opener.location.href="search_cancelList?campName="+$("#camp_Reid").val();
			self.close();
		}).fail(function(error){
			alert("에러");
		});
		
}
</script>
<style>
	table th {
		width: 150px;
		text-align: left;
	}
	
	#button {
		margin-top: 30px;
	}
</style>
</head>
<body>
	<form id="confirm_cancel_order" name="frmm" method="post" action="confirm_order">
		<input type="hidden" name="cseq" id="cseq" value="${cancelOrder.cseq}">
		<input type="hidden" name="camp_Reid" id="camp_Reid" value="${selected}">
		<table>
	      	<tr>
				<th>예약번호</th>
				<td><input type="text" name="oseq" id="oseq" value="${cancelOrder.oseq}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
				<th>회원ID</th>
				<td><input type="text" name="usersid" id="usersid" value="${cancelOrder.usersid}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
		        <th>캠핑장 지점</th>
		        <td><input type="text" name="camp_name" id="camp_name" value="${cancelOrder.camp_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>구역</th>
		        <td><input type="text" name="camp_zone" id="camp_zone" value="${cancelOrder.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="indate" value="${cancelOrder.indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="outdate" value="${cancelOrder.outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약자 이름</th>
	        	<td><input type="text" name="order_name" id=order_name value="${cancelOrder.order_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약인원</th>
	        	<td><input type="text" name="order_people" id="order_people" value="${cancelOrder.order_people}" readOnly="readonly">명</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" id="order_phone" value="${cancelOrder.order_phone}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" id="order_email" value="${cancelOrder.order_email}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>결제금액</th>
		        <td><input type="text" name="total_price" id="total_price" value="${cancelOrder.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>환불금액</th>
		        <td><input type="text" name="cancelprice" id="cancelprice" value="${cancelOrder.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>위약금</th>
		        <td><input type="text" name="cancelnet" id="cancelnet" value="${cancelOrder.total_price}" readOnly="readonly">원 </td>
	      	</tr>
	      	<tr>
		        <th>취소날짜</th>
		        <td><input type="text" name="cancel_date" id="cancel_date" value="${cancelOrder.cancel_date}" readOnly="readonly"> </td>
	      	</tr>
		</table>
		<div id="button" align="center">
			<button type="button" onclick="cancel_chk()" style="text-align: center;">취소확정</button>
		</div>
	</form>
</body>
</html>