<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../admin_header.jsp" %>

<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="../master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="../manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<article id="admin_order_list_form">
	<h4>예약 내역 조회</h4>
	<form id="search_orderList" method="get" name="frm">
  		지점
  		<select name="campName" id="campName">
			<c:forEach items="${conditionMap}" var="option">
				<option value="${option.value}"<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
			</c:forEach>
  		</select>
  		
  		<input id="btn" type="button" value="조회하기" onclick="chk()"/>
  	</form>
  	<form id="orderListForm">
  		<input type="hidden" id="camp_idRe" value="${selected}">
		<table id="orderList"> 
			<tr>
				<th width="70">예약번호</th>
				<th width="160">지점</th>
				<th width="50">구역</th>
				<th width="70">회원ID</th>
				<th width="90">예약자이름</th>
				<th width="100">체크인</th>
				<th width="100">체크아웃</th>
				<th width="70">결제금액</th>
				<th width="130">예약자 전화번호</th>
				<th width="140">예약자 이메일</th>
				<th width="150">예약 상태</th>
			</tr>
			<c:forEach items="${orderList}" var="orderList">
				<tr>
					<td><input type="hidden" id="orderSeq" value="${orderList.oseq}">${orderList.oseq}</td> 
					<td>${orderList.camp_name}</td>
					<td>${orderList.camp_zone}</td>
					<td>${orderList.user_id}</td>
					<td>${orderList.order_name}</td>
					<td>${orderList.indate}</td>
					<td>${orderList.outdate}</td>
					<td>${orderList.total_price}</td>
					<td>${orderList.order_phone}</td>
					<td>${orderList.order_email}</td>   
					<td>
						<c:set var="camp_name" value="${orderList.camp_name}"/>
						<c:set var="admin_name" value="${admin_name}"/>
						<c:choose>
							<c:when test="${loginAdmin.status eq 1}"> <!-- 총관리자일 때 -->
								<c:choose>
									<c:when test="${orderList.indate < today}">
										<span style="color: rgb(145,154,157);">지난내역</span>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${orderList.order_status == 'y'}">
												<span style="color: red;">예약 확정 완료</span>
											</c:when>
											<c:when test="${orderList.order_status == 'n'}">
												<input type="button" value="예약완료" onclick="confirm_order(${orderList.oseq})">
												<input type="button" value="취소" onclick="cancel_order(${orderList.oseq})">
											</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${loginAdmin.status eq 2}"> <!-- 지점관리자일 때 -->
								<c:choose>
									<c:when test="${orderList.indate < today}">
										<span style="color: rgb(145,154,157);">지난내역</span>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${fn:contains(camp_name, admin_name)}">
												<c:choose>
													<c:when test="${orderList.order_status == 'y'}">
														<span style="color: red;">예약 확정 완료</span>
													</c:when>
													<c:when test="${orderList.order_status == 'n'}">
														<input type="button" value="예약완료" onclick="confirm_order(${orderList.oseq})">
														<input type="button" value="취소" onclick="cancel_order(${orderList.oseq})">
													</c:when>
												</c:choose>
											</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose> 
					</td>
				</tr>
			</c:forEach>
			<!-- <tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr> -->
		</table>
	</form>
	<%@ include file="order_page_area.jsp"%>
</article>
<%@ include file="../admin_footer.jsp" %>

