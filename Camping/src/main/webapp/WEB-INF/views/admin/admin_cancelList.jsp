<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<form id="search_cancelList" method="get" name="frm">
  		지점
  		<select name="campName" id="campName">
			<c:forEach items="${conditionMap}" var="option">
				<option value="${option.value}"<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
			</c:forEach>
  		</select>
  		
  		<input id="btn" type="button" value="조회하기" onclick="check()"/>
  	</form>
  	<form>
  		<input type="hidden" id="camp_idRe" value="${selected}">
		<table id="cancelList"> 
			<tr>
				<th width="60">예약번호</th>
				<th width="140">지점</th>
				<th width="80">구역</th>
				<th width="80">회원ID</th>
				<th width="70">결제금액</th>
				<th width="70">취소금액</th>
				<th width="90">취소날짜</th>
				<th width="70">취소상태</th>
				<th width="90"></th>
			</tr>
			<c:forEach items="${cancelList}" var="cancelList">
				<tr>
					<td><input type="hidden" id="cseq" value="${cancelList.cseq}">${cancelList.oseq}</td> 
					<td>${cancelList.camp_name}</td>
					<td>${cancelList.camp_zone}</td>
					<td>${cancelList.usersid}</td>
					<td>${cancelList.total_price}</td>
					<td>${cancelList.cancelprice}</td>
					<td>${cancelList.cancel_date}</td>
					<td>
						<c:choose>
							<c:when test="${cancelList.status eq 'y'}">
								<span style="color: red;">취소 완료</span>
							</c:when>
							<c:when test="${cancelList.status eq 'n'}">
								<input type="button" value="취소하기" onclick="confirm_check(${cancelList.cseq})">
							</c:when>
						</c:choose>
					</td>
					<td><a href="cancel_detail?cseq=${cancelList.cseq}" onclick="window.open(this.href, '_blank', 'toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500'); return false;">취소내용보기</a></td>
				</tr>
			</c:forEach>
			<!-- <tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr> -->
		</table>
	</form>
	<%@ include file="cancel_page_area.jsp"%>
</article>
<%@ include file="admin_footer.jsp" %>

