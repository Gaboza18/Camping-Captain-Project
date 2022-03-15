<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	 
 
<article id="campOrder">
	<!-- <h3>캠핑장 예약</h3>    
	<form name="formm" method="post" action="go_payForm">
		<input name="camp_id" type="hidden" value="${camp.camp_id}"/>
		<table id="orderForm">
	      	<tr>
				<th>캠핑장 이름</th>
				<td><input type="text" name="camp_name" value="${camp.camp_name}" readOnly="readonly"></td> 
	      	</tr>
	      	<tr>
	        	<th>캠핑장 주소</th>
	        	<td><input type="text" name="camp_addr" value="${camp.camp_addr}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>선택구역</th>
		        <td><input type="text" name="camp_zone" value="${camp.camp_zone}" readOnly="readonly"></td>
	      	</tr>
	    	<tr>
		        <th>체크인</th>
		        <td><input type="text" name="indate" id="in" value="${indate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>체크아웃</th>
		        <td><input type="text" name="outdate" id="out" value="${outdate}" readOnly="readonly"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 성함</th>
		        <td><input type="text" name="order_name" value="${users.name}"></td>
	      	</tr>
	      	<tr>
		        <th>인원선택</th>
		        <td>
		        	<input name="max" id="max" type="hidden" value="${camp.max_people}"/>
		        	<input name="base" id="base" type="hidden" value="${camp.base_people}"/>
			        <button type="button" onclick="minus();">-</button>
			        <input type="text" id="people" name="order_people" value="${camp.base_people}" readonly="readonly"/>
			        <button type ="button" onclick="plus();">+</button>
	    		</td>
	      	</tr>
	      	<tr>
		        <th>예약자 전화번호</th>
		        <td><input type="text" name="order_phone" value="${users.phone}"></td>
	      	</tr>
	      	<tr>
		        <th>예약자 이메일</th>
		        <td><input type="text" name="order_email" value="${users.email}"></td>
	      	</tr>
	      	<tr>
		        <th>결제</th>
		        <td>
		        <c:choose>
					<c:when test="${day eq '금' || day eq '토'}"><input type="text" class="total_price" name="total_price" value="${camp.weekend_price}" readOnly="readonly"></c:when>
					<c:otherwise><input type="text" class="total_price" name="total_price" value="${camp.weekdays_price}" readOnly="readonly"></c:otherwise>
				</c:choose>
		        </td>
	      	</tr>
		</table>
		<div id="buttons">
			<input type="submit" value="예약" class="submit"> 	
		</div>
		<div class="clear"></div>
	</form>	 -->
		
	<section class="page-section" id="order">
        <div class="container">
            <form name="formm" id="orderForm"  method="post" action="go_payForm">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">예약정보 입력</h2>
                <h3 class="section-subheading text-muted"></h3>
            </div>
            <!-- * * * * * * * * * * * * * * *-->
            <!-- * * SB Forms Contact Form * *-->
            <!-- * * * * * * * * * * * * * * *-->
            <!-- This form is pre-integrated with SB Forms.-->
            <!-- To make this form functional, sign up at-->
            <!-- https://startbootstrap.com/solution/contact-forms-->
            <!-- to get an API token!-->
            	<input name="camp_id" type="hidden" value="${camp.camp_id}"/>
                <div class="row align-items-stretch mb-5">
                    <div class="col-md-6">
                        <div class="form-group">
							<span>캠핑장</span>
                            <input class="form-control" id="camp_name" type="text" name="camp_name" value="${camp.camp_name}" readOnly="readonly"/><br>
                        </div>
                        <div class="form-group">
							<span>캠핑장 주소</span>
                            <input class="form-control" id="camp_addr" type="text" name="camp_addr" value="${camp.camp_addr}" readOnly="readonly"/><br>
                        </div>
                        <div class="form-group">
							<span>선택구역</span>
                            <input class="form-control" id="camp_zone" type="text" name="camp_zone" value="${camp.camp_zone}" readOnly="readonly"/><br>
                        </div>
                        <div class="form-group">
							<span>체크인</span>
                            <input class="form-control" id="in" type="text" name="indate" value="${indate}" readOnly="readonly"/><br>
                        </div>
                        <div class="form-group">
							<span>체크아웃</span>
                            <input class="form-control" id="out" type="text" name="outdate" value="${outdate}" readOnly="readonly"/><br>
                        </div>
                        <div class="form-group">
							<span>예약자 성함(변경가능)</span>
                            <input class="form-control" id="order_name" type="text" name="order_name" value="${users.name}"/><br>
                        </div>
                        <div class="form-group">
							<span>인원선택</span>
							<input name="max" id="max" type="hidden" value="${camp.max_people}"/>
				        	<input name="base" id="base" type="hidden" value="${camp.base_people}"/><br>
					        <button type="button" onclick="minus()">-</button>
					        <input class="form-control_people"  type="text" id="people" name="order_people" value="${camp.base_people}" readonly="readonly"/>
					        <button type ="button" onclick="plus()">+</button>
                        </div>
                        <div class="form-group">
							<br><span>예약자 전화번호(변경가능)</span>
                            <input class="form-control" id="order_phone" type="text" name="order_phone" value="${users.phone}"/><br>
                        </div>
                        <div class="form-group">
							<span>예약자 이메일(변경가능)</span>
                            <input class="form-control" id="order_email" type="text" name="order_email" value="${users.email}"/><br>
                        </div>
                        <div class="form-group">
							<span>결제금액</span>
							<c:choose>
								<c:when test="${day eq '금' || day eq '토'}"><input class="form-control" type="text" id="total_price" name="total_price" value="${camp.weekend_price}" readOnly="readonly"><br></c:when>
								<c:otherwise><input class="form-control" type="text" id="total_price" name="total_price" value="${camp.weekdays_price}" readOnly="readonly"><br></c:otherwise>
							</c:choose>
                        </div>
                    </div>
                </div>
                <!-- Submit Button-->
                <div class="text-center">
                	<input class="btn btn-primary btn-xl text-uppercase" id="submitButton" type="submit" value="예약">
                </div>
            </form>
        </div>
    </section>
</article>
<%@ include file="../footer.jsp" %>

