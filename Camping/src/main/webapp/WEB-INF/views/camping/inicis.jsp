<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>WebStandard example</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">              
<script type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>      
</head>

 <body onload="INIStdPay.pay('SendPayForm_id')">

   <form id="SendPayForm_id" name="" method="POST">
   		<!-- 고정 -->
		<input type="hidden"  name="version" value="1.0" > <!-- 전문 버전 ["1.0" 고정] -->  
        <input type="hidden"  name="gopaymethod" value="Card" > <!-- 요청지불수단  * 빈값으로 세팅 시, 전체 결제수단 표시 -->  
        <input type="hidden"  name="currency" value="WON" > <!-- 	통화구분 ["WON":한화,"USD":달러] * USD는 카드 결제만 가능 -->
        <input type="hidden"  name="returnUrl" value="http://localhost:8181/biz/order_insert?temp_id=${tempOrder.temp_id}" >  <!-- 결과수신 URL -->
        <input type="hidden"  name="closeUrl" value="" > <!-- 	결제창 닫기 URL -->
        <input type="hidden"  name="acceptmethod" value="below1000" ><!-- 에스크로옵션 : useescrow, 빌링(정기과금)옵션 : BILLAUTH(Card) -->
		
		<!-- 예약페이지에서 받아올 거 -->
        <input type="hidden"    name="price" value="${tempOrder.total_price}" > <!-- 결제금액 * 숫자만, 1달러는 100으로 시작 -->
        <input type="hidden"    name="goodname" value="캠핑족장" > <!-- 	상품명 * 40Byte 초과 요청시 37Byte + ...으로 자동 변환 -->
        <input type="hidden"    name="buyername" value="${tempOrder.order_name}" > <!-- 구매자명 * 30Byte 초과 요청시 30Byte로 자동 변환 -->
        <input type="hidden"    name="buyertel" value="${tempOrder.order_phone}" > <!-- 	구매자 휴대폰번호* 숫자와 "-" 만 허용 -->
        <input type="hidden"    name="buyeremail" value="${tempOrder.order_email}" > <!-- 	구매자 이메일주소 * "@", "." 외 특수문자 입력불가 -->
		
		<!-- 그 외 -->
        <input type="hidden"  name="mid" value="${mid}" ><!-- 	상점아이디 --><!-- 에스크로테스트 : iniescrow0, 빌링(정기과금)테스트 : INIBillTst -->
        <input type="hidden"  name="oid" value="${oid}" ><!-- 주문번호 * 반드시 Unique 값으로 생성 (거래추적 시 사용됨) -->
        <input type="hidden"  name="timestamp" value="${timestamp}" >   <!-- 타임스템프 [TimeInMillis(Long형)] -->
        <input type="hidden"  name="signature" value="${signature}" > <!-- SHA256 Hash값 [대상: oid, price, timestamp -->
        <input type="hidden"  name="mKey" value="${mKey}" > <!-- SHA256 Hash값 [대상: mid 와 매칭되는 signkey] -->

   </form>

	<!-- <button onclick="INIStdPay.pay('SendPayForm_id')" style="padding:10px; margin-left:10%">결제요청</button> -->

 </body>
</html>