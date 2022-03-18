<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../manager_admin_menu.jsp"%>

<style>
	div#manager_calculate_year {
		margin-left: 17.5%;
		padding: 0;
		min-height: 520px;
	}

	div#manager_calculate_year article h4 {
		margin: 2% auto;
		color: rgb(255, 200, 0);
	}

	div#manager_calculate_year article table#calculateList {
		border: none;
		color: #212529;
		text-align: center;
	}
	
	div#manager_calculate_year article table#calculateList th {
		border: none;
		border-bottom: 1px solid #212529;
	}

	div#manager_calculate_year article table#calculateList td {
		border: none;
	}
</style>

	<div id="manager_calculate_year" align="center">
		<article>
			<h4>${loginAdmin.name} - 연도 별 정산</h4>
			<table id="calculateList">
				<tr>
					<th width="150">년 도</th> <th width="300">지점이름</th> <th width="170">총합</th>
				</tr>
				<c:forEach items="${GwcalculateList}" var="GwcalculateList">
					<tr>
						<td>${GwcalculateList.indate }</td>
						<td>${GwcalculateList.camp_name }</td>
						<td>${GwcalculateList.total_price } 원</td>
					</tr>	
				</c:forEach>	
			</table>
		</article>
	</div>
	
<%@ include file="../admin_footer.jsp"%>