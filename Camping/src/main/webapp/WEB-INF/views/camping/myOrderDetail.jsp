<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 예약 정보</title>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function btnClose() {
	
		self.close();
			
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
	<table>
      	<tr>
			<th>예약번호</th>
			<td><input type="text" name="oseq" id="oseq" value="${myOrderDetail.oseq}" readOnly="readonly"></td> 
      	</tr>
      	<tr>
	        <th>캠핑장 지점</th>
	        <td><input type="text" name="camp_name" id="camp_name" value="${myOrderDetail.camp_name}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>구역</th>
	        <td><input type="text" name="camp_zone" id="camp_zone" value="${myOrderDetail.camp_zone}" readOnly="readonly"></td>
      	</tr>
    	<tr>
	        <th>체크인</th>
	        <td><input type="text" name="indate" id="indate" value="${myOrderDetail.indate}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>체크아웃</th>
	        <td><input type="text" name="outdate" id="outdate" value="${myOrderDetail.outdate}" readOnly="readonly"></td>
      	</tr>
      	<tr>
        	<th>예약자 이름</th>
        	<td><input type="text" name="order_name" id="order_name" value="${myOrderDetail.order_name}" readOnly="readonly"></td>
      	</tr>
      	<tr>
        	<th>예약인원</th>
        	<td><input type="text" name="order_people" id="order_people" value="${myOrderDetail.order_people}" readOnly="readonly">명</td>
      	</tr>
      	<tr>
	        <th>예약자 전화번호</th>
	        <td><input type="text" name="order_phone" id="order_phone" value="${myOrderDetail.order_phone}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>예약자 이메일</th>
	        <td><input type="text" name="order_email" id="order_email" value="${myOrderDetail.order_email}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>결제금액</th>
	        <td><input type="text" name="total_price" id="total_price" value="${myOrderDetail.total_price}" readOnly="readonly">원 </td>
      	</tr>
	</table>
		<div id="button" align="center">
			<button type="button" onclick="btnClose(${myOrderDetail.oseq})" style="text-align: center;">닫기</button>
		</div>
</body>
</html>