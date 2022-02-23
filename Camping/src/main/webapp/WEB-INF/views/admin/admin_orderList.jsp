<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="admin_header.jsp" %>

<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<article>
	<form id="search_order" method="get" name="frm">
  		지점
  		<select name="campName" id="campName">
			<c:forEach items="${conditionMap}" var="option">
				<option value="${option.value}"<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
			</c:forEach>
  		</select>
  		
  		<input id="btn" type="button" value="조회하기" onclick="chk()"/>
  	</form>
	<table id="orderList"> 
		<tr>
			<th width="50">예약번호</th>
			<th width="140">지점</th>
			<th width="50">구역</th>
			<th width="60">회원ID</th>
			<th width="70">예약자이름</th>
			<th width="90">체크인</th>
			<th width="90">체크아웃</th>
			<th width="60">결제금액</th>
			<th width="130">예약자 전화번호</th>
			<th width="">예약자 이메일</th>
			<th width="150"></th>
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
				<td><input type="button" value="예약완료" onclick="confirm_order()">&nbsp;<input type="button" value="반려" onclick="cancel_order()">
				</td>
			</tr>
		</c:forEach>
		<tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
	</table>
	<%@ include file="order_page_area.jsp"%>
</article>
<%@ include file="admin_footer.jsp" %>

