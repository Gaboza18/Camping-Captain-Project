<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>

<style>
	div#admin_all_calculate_year {
		margin-left: 17.5%;
		padding: 0;
		min-height: 520px;
	}

	div#admin_all_calculate_year h4 {
		margin: 2% auto;
		color: rgb(255, 200, 0);
	}
	
	div#admin_all_calculate_year form#camp_order_year_search {
		color: #212529;
	}
	
	div#admin_all_calculate_year form#camp_order_year_search div {
		margin-bottom: 2%;
	}
	
	div#admin_all_calculate_year form#camp_order_year_search div select {
		border-radius: 10px;
	}
	
	div#admin_all_calculate_year form#camp_order_year_search div input#btn {
		margin-left: 1%;
		border-radius: 10px;
		background-color: #212529;
		color: white;;
	}
	
	div#admin_all_calculate_year form#camp_order_year_search div input#btn:hover {
		background-color: #464f57;
	}

	div#admin_all_calculate_year form[name=formm] article table#calculateList {
		border: none;
		color: #212529;
		text-align: center;
	}
	
	div#admin_all_calculate_year form[name=formm] article table#calculateList th {
		border: none; 
		border-bottom: 1px solid #212529;
	}

	div#admin_all_calculate_year form[name=formm] article table#calculateList td {
		border: none;
	}
</style>

<div id="admin_all_calculate_year" align="center">
	<h4>캠핑족장 - 연 정산</h4>
	<form id="camp_order_year_search" action="admin_master_calculate_year" method="get">
		<div>
			<h5>연도 선택</h5>
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
			</select> 
			<input id="btn" type="button" value="조회하기" onclick="admin_search_chk_year()" />
		</div>
	</form>
	
	<form name="formm">
		<article>
			<table id="calculateList">
				<tr>
					<th width="150">년 도</th>
					<th width="300">지점이름</th> 
					<th width="170">총합</th>
				</tr>
				<c:forEach items="${calculateList}" var="calculateList">
					<tr>
						<td>${calculateList.indate}</td>
						<td>${calculateList.camp_name }</td>
						<td>${calculateList.total_price}원</td>
					</tr>
				</c:forEach>
			</table>
		</article>
	</form>
</div>

<%@ include file="../admin_footer.jsp"%>