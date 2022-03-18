<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>

<style>
	div#admin_all_calculate_day {
		margin-left: 17.5%;
		padding: 0;
		min-height: 520px;
	}
	
	div#admin_all_calculate_day article {
		margin: 2% auto;
		color: #212529;
	}

	div#admin_all_calculate_day article h4 {
		color: rgb(255, 200, 0);
	}
	div#admin_all_calculate_day article table#calculateList {
		border: none;
		color: #212529;
		text-align: center;
	}
	
	div#admin_all_calculate_day article table#calculateList th {
		border: none; 
		border-bottom: 1px solid #212529;
	}

	div#admin_all_calculate_day article table#calculateList td {
		border: none;
	}
</style>

	<div id="admin_all_calculate_day" align="center">
		<article>
			<h4>캠핑족장 - 일일 정산</h4>
			<table id="calculateList">
				<tr>
					<th width="150">년 도</th>
					<th width="300">지점이름</th> 
					<th width="170">총합</th>
				</tr>
			<c:forEach items="${calculateList}" var="calculateList">
				<tr>
					<td>${calculateList.indate }</td>
					<td>${calculateList.camp_name }</td>
					<td>${calculateList.total_price } 원</td>
				</tr>	
			</c:forEach>	
			</table>
		</article>
	</div>
	
<%@ include file="../admin_footer.jsp"%>
