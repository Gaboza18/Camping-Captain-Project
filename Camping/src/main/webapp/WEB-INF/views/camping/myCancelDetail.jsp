<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	body {
		background-color: #212529;
		color: white;
		text-align: center;
	}
	
	body h4 {
		margin: 15px auto;
	}

	table {
		margin: 2px auto;
	}
	
	table th {
		width: 150px;
		text-align: left;
	}
	
	table td {
		width: 200px;
		text-align: left;
	}
	
	table td input {
		border-radius: 12px;
		padding: 3px 8px;
		text-align: center;
	}
	
	table td textarea {
		border-radius: 12px;
		padding: 1px 8px;
	}
	
	body span {
		text-align: center;
		margin: 10px auto;
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
	<c:if test="${myCancelDetail.status == 'y'}">
		<h4><span style="color: rgb(148, 170, 255);">취소 완료</span>입니다.</h4>
	</c:if>
	<c:if test="${myCancelDetail.status == 'n'}">
		<h4><span style="color: rgb(255, 140, 140);">취소 진행 중</span>입니다.</h4>
	</c:if>
	
	<table>
      	<tr>
			<th>예약번호</th>
			<td><input type="text" name="oseq" id="oseq" value="${myCancelDetail.oseq}" readOnly="readonly"></td> 
      	</tr>
      	<tr>
	        <th>캠핑장 지점</th>
	        <td><input type="text" name="camp_name" id="camp_name" value="${myCancelDetail.camp_name}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>구역</th>
	        <td><input type="text" name="camp_zone" id="camp_zone" value="${myCancelDetail.camp_zone}" readOnly="readonly"></td>
      	</tr>
    	<tr>
	        <th>체크인</th>
	        <td><input type="text" name="indate" id="indate" value="${myCancelDetail.indate}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>체크아웃</th>
	        <td><input type="text" name="outdate" id="outdate" value="${myCancelDetail.outdate}" readOnly="readonly"></td>
      	</tr>
      	<tr>
        	<th>예약자 이름</th>
        	<td><input type="text" name="order_name" id="order_name" value="${myCancelDetail.order_name}" readOnly="readonly"></td>
      	</tr>
      	<tr>
        	<th>예약인원</th>
        	<td><input type="text" name="order_people" id="order_people" value="${myCancelDetail.order_people}" readOnly="readonly">명</td>
      	</tr>
      	<tr>
	        <th>예약자 전화번호</th>
	        <td><input type="text" name="order_phone" id="order_phone" value="${myCancelDetail.order_phone}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>예약자 이메일</th>
	        <td><input type="text" name="order_email" id="order_email" value="${myCancelDetail.order_email}" readOnly="readonly"></td>
      	</tr>
      	<tr>
	        <th>결제금액</th>
	        <td><input type="text" name="total_price" id="total_price" value="${myCancelDetail.total_price}" readOnly="readonly">원 </td>
      	</tr>
      	<tr>
	        <th>환불금액</th>
	        <td><input type="text" name="cancelprice" id="cancelprice" value="${myCancelDetail.cancelprice}" readOnly="readonly">원 </td>
      	</tr>
      	<tr>
	        <th>위약금</th>
	        <td><input type="text" name="cancelnet" id="cancelnet" value="${myCancelDetail.cancelnet}" readOnly="readonly">원 </td>
      	</tr>
      	<tr>
	        <th>취소날짜</th>
	        <td><input type="text" name="cancel_date" id="cancel_date" value="${myCancelDetail.cancel_date}" readOnly="readonly">원 </td>
      	</tr>
      	<c:if test="${myCancelDetail.reason != null}">
      		<tr>
		        <th>취소사유</th>
		        <td><textarea name="reason" id="reason" rows="5" cols="30" style="resize: none;" readOnly="readonly">
		        		${myCancelDetail.reason}
		        	</textarea>
		        </td>
      		</tr>
      	</c:if>
	</table>
	<c:if test="${myCancelDetail.reason != null}">
		 <span style="color: rgb(148, 170, 255);">캠핑장 사정으로 인해 예약이 취소 된 점에 사과드립니다.</span>
    </c:if>
	<div id="button" align="center">
		<button type="button" onclick="btnClose()" style="text-align: center;">닫기</button>
	</div>
</body>
</html>