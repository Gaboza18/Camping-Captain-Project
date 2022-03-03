<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 확정</title>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function confirm_chk() {

		$.ajax({
			type: "GET",
			url: "confirm_order",
			dataType: "html",
			contentType:  "application/json; charset=UTF-8",
			data: $("#confirm_order").serialize()
		}).done(function(){
			alert("예약이 확정되었습니다.");
			opener.location.href="search_orderList?campName="+$("#camp_Reid").val();
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
	<form id="confirm_order" name="frmm" method="post" action="confirm_order">
		<input type="hidden" name="camp_Reid" id="camp_Reid" value="${selected}">
		<table>
	      	<tr>
				<th>예약번호</th>
				<td><input type="text" name="oseq" id="confirm_oseq" value="${campOrder.oseq}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
				<th>회원ID</th>
				<td><input type="text" name="usersid" id="confirm_usersid" value="${campOrder.user_id}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
		        <th>캠핑장 지점</th>
		        <td><input type="text" name="camp_name" id="confirm_camp_name" value="${campOrder.camp_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>구역</th>
		        <td><input type="text" name="camp_zone" id="confirm_camp_zone" value="${campOrder.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="confirm_indate" value="${campOrder.indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="confirm_outdate" value="${campOrder.outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약자 이름</th>
	        	<td><input type="text" name="order_name" id="confirm_order_name" value="${campOrder.order_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약인원</th>
	        	<td><input type="text" name="order_people" id="confirm_order_people" value="${campOrder.order_people}" readOnly="readonly">명</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" id="confirm_order_phone" value="${campOrder.order_phone}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" id="confirm_order_email" value="${campOrder.order_email}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>결제금액</th>
		        <td><input type="text" name="total_price" id="confirm_total_price" value="${campOrder.total_price}" readOnly="readonly">원 </td>
	      	</tr>
		</table>
		<div id="button" align="center">
			<button type="button" onclick="confirm_chk()" style="text-align: center;">예약확정</button>
		</div>
	</form>
</body>
</html>