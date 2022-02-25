<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불 규정</title>
<style>
	table {
		margin-bottom: 30px;
		outline: 1px solid #000;
		padding: 5px;
		line-height: 25px;
		text-align: center;
	} 
	
	#button {
		margin-top: 30px;
	}
	
	a {
		border: 1px solid #000;
		border-radius: 10px;
		text-decoration:none;
    	color: #000;
    	padding: 5px 10px;
	}
</style>
</head>
<body>
	<div align="center">
		<h4>환불 규정</h4>
		<table>
			<tr>
				<td>사용예정일 10일 전 또는 예약 당일</td>
				<th>결제 금액 전액 환불</th>
			</tr>
			<tr>
				<td>사용예정일 7일 전</td>
				<th>결제금액의 90% 환불</th>
			</tr>
			<tr>
				<td>사용예정일 5일 전</td>
				<th>결제금액의 70% 환불</th>
			</tr>
			<tr>
				<td>사용예정일 3일 전</td>
				<th>결제금액의 50% 환불</th>
			</tr>
			<tr>
				<td>사용예정일 1일 전 또는 입실날짜 당일</td>
				<th>결제금액의 20% 환불</th>
			</tr>
		</table>
		<div align="center">
			<input type="hidden" name="oseq" value="${oseq}">
			<a href="insert_myOrder_cancel?oseq=${oseq}">확인</a>
		</div>
	</div>
</body>
</html>