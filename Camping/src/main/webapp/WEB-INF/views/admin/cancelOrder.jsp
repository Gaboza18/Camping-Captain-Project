<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="js/admin.js"></script> -->
<script type="text/javascript">
	function reason_chk() {
		var form = document.frmm;
		
		if(form.reason.value == ""){
			alert("반려사유는 반드시 입력해야합니다.");
		} else {
			alert("예약이 반려되었습니다.");
			
			form.target = opener.name;
			form.action = "cancel_order";
			form.submit();
			opener.location.href="cancel_order"
			
			window.close();
		}
	}
</script>
<style>
	table th {
		width: 150px;
		text-align: left;
	}
</style>
</head>
<body>
	<form id="cancel_order" name="frmm" action="cancel_order" method="post">
		<table>
	      	<tr>
				<th>예약번호</th>
				<td><input type="text" name="oseq" value="${campOrder.oseq}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
				<th>회원ID</th>
				<td><input type="text" name="usersid" value="${campOrder.user_id}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
		        <th>캠핑장 지점</th>
		        <td><input type="text" name="camp_name" id="campName" value="${campOrder.camp_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>구역</th>
		        <td><input type="text" name="camp_zone" value="${campOrder.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" value="${campOrder.indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" value="${campOrder.outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약자 이름</th>
	        	<td><input type="text" name="order_name" value="${campOrder.order_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약인원</th>
	        	<td><input type="text" name="order_people" value="${campOrder.order_people}" readOnly="readonly">명</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" value="${campOrder.order_phone}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" value="${campOrder.order_email}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>결제금액</th>
		        <td>
		        	<input type="text" name="total_price" value="${campOrder.total_price}" readOnly="readonly">원
		        	<input type="hidden" name="cancelprice" value="${campOrder.total_price}" readOnly="readonly">
		        </td>
	      	</tr>
	      	<tr>
		        <th>취소사유</th>
		        <td><textarea name="reason" rows="10" cols="40" style="resize: none;"></textarea></td>
	      	</tr>
		</table>
		<input type="button" value="예약 반려" onclick="reason_chk()">
	</form>
</body>
</html>