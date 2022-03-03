<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>
<style>
table#calculateList {
	border-collapse: collapse; /* border 사이의 간격 없앰 */
	border-top: 2px solid #333;
	border-bottom: 1px solid #333;
	width: 60%; /* 전체 테이블 길이 설정 */
	margin-left: 100px;
	margin-bottom: 20px;
}

th, td {
	padding: 8px 5px;
}

#calculateList td { /* 테이블의 th 와 td 마진과 패딩 지정 */
	padding-right: 40px;
	text-align: right;
}
</style>
<div align="center">
	<h1>캠핑족장 - 연 정산</h1>
	<form id="camp_order_year_search" action="admin_master_calculate_year" method="get">
		<div>
			<h4>연도 선택</h4>
			<select name="startYear" id="startYear">
				<c:forEach items="${conditionMapYear}" var="option">
					<option value="${option.value}"
						<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
				</c:forEach>
			</select>
			~ 
			<select name="endYear" id="endYear">
				<c:forEach items="${conditionMapYear}" var="option">
					<option value="${option.value}"
						<c:if test="${option.value == selected}">selected</c:if>>${option.key}</option>
				</c:forEach>
			</select> <input id="btn" type="button" value="조회하기" onclick="admin_search_chk()" />
		</div><br>
	</form>
	
	<form>
		<article>
			<table id="calculateList">
				<tr>
					<th>년 도</th>
					<th>지점이름</th>
					<th>총합</th>
				</tr>
				<c:forEach items="${calculateList}" var="calculateList">
					<tr>
						<td height="23" align="center">${calculateList.indate}</td>
						<td
							style="text-align: left; padding-left: 50px; padding-right: 0px;">${calculateList.camp_name }</td>
						<td>${calculateList.total_price}원</td>
					</tr>
				</c:forEach>
			</table>
		</article>
	</form>
</div>

<%@ include file="../admin_footer.jsp"%>
</body>
</html>