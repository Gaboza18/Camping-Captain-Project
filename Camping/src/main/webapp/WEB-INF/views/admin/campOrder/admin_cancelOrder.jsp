<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 취소</title>
<!-- <script type="text/javascript" src="js/admin.js"></script> -->
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function reason_chk() {
		if($("#can_reason").val() == ""){
			alert("취소사유는 반드시 입력해야합니다.");
		} else {
			$.ajax({
				type: "GET",
				url: "go_cancel",
				dataType: "html",
				contentType:  "application/json; charset=UTF-8",
				data: $("#cancel_order").serialize()
			}).done(function(){
				alert("예약이 취소되었습니다.");
	
				if($("#camp_Reid").val() == 0) {
					opener.location.href="search_order";
				} else {
					opener.location.href="search_orderList?campName="+$("#camp_Reid").val();
				}
				self.close();
			}).fail(function(error){
				alert("에러");
			});
		}
	}
</script>
<style>

	body {
		background-color: #212529;
		color: white;
	}

	table {
		margin-top: 8%;
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
	
	table td textarea {
		border-radius: 12px;
		padding: 3px 8px;
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
	<form id="cancel_order" name="frmm" method="post" action="cancel_order">
		<input type="hidden" name="camp_Reid" id="camp_Reid" value="${selected}">
		<table>
	      	<tr>
				<th>예약번호</th>
				<td><input type="text" name="oseq" id="can_oseq" value="${campOrder.oseq}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
				<th>회원ID</th>
				<td><input type="text" name="usersid" id="can_usersid" value="${campOrder.user_id}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
		        <th>캠핑장 지점</th>
		        <td><input type="text" name="camp_name" id="can_camp_name" value="${campOrder.camp_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>구역</th>
		        <td><input type="text" name="camp_zone" id="can_camp_zone" value="${campOrder.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="can_indate" value="${campOrder.indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="can_outdate" value="${campOrder.outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약자 이름</th>
	        	<td><input type="text" name="order_name" id="can_order_name" value="${campOrder.order_name}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
	        	<th>예약인원</th>
	        	<td><input type="text" name="order_people" id="can_order_people" value="${campOrder.order_people}" readOnly="readonly">명</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" id="can_order_phone" value="${campOrder.order_phone}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" id="can_order_email" value="${campOrder.order_email}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>결제금액</th>
		        <td>
		        	<input type="text" name="total_price" id="can_total_price" value="${campOrder.total_price}" readOnly="readonly">원
		        	<input type="hidden" name="cancelprice" id="can_cancelprice" value="${campOrder.total_price}" readOnly="readonly">
		        </td>
	      	</tr>
	      	<tr>
		        <th>취소사유</th>
		        <td><textarea name="reason" id="can_reason" rows="5" cols="30" style="resize: none;"></textarea></td>
	      	</tr>
		</table>
		<div id="button" align="center">
			<button type="button" id="btn_reset" onclick="reason_chk()">예약취소</button>
		</div>
	</form>
</body>
</html>  