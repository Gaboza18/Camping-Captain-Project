<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 예약현황</title>
</head>
<body>
	<div>
		<h1>현재 예약 확인</h1>
		<fieldset>
			<div align="left">
				<c:forEach items="${campOrderList}" var="campOrderList">
					<p>예약번호: ${campOrderList.oseq}</p>
					<p>예약자 이름: ${campOrderList.order_name}</p>
					<p>캠핑장 지점 이름: ${campOrderList.camp_name}</p>
					<p>가격: ${campOrderList.total_price}(원)</p>
					<p>캠핑장 구역: ${campOrderList.camp_zone}</p>
					<p>체크인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;체크아웃</p><p>${campOrderList.indate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${campOrderList.outdate}</p>
				</c:forEach>
			</div>
		</fieldset>	
			<div class="cancelbtn">
				<button id="btn" type="submit">예약취소</button>
			</div>		
	</div>
</body>
</html>