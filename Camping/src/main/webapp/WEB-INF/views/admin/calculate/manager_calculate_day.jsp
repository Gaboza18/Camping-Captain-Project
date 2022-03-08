<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../manager_admin_menu.jsp"%>
<style>
	table#calculateList {
		border-collapse: collapse; /* border 사이의 간격 없앰 */
		border-top: 2px solid #333;
		border-bottom: 1px solid #333;
		width: 60%; /* 전체 테이블 길이 설정 */
		margin-left: 100px;
		margin-bottom: 20px;
	}
	
	th, td{ 
		padding: 8px 5px;
	}
	
	#calculateList td{ /* 테이블의 th 와 td 마진과 패딩 지정 */
		padding-right: 40px;
		text-align: right;
	}
</style>
	<div align="center">
		<article>
			<h1>${loginAdmin.name} - 일일 별 정산</h1>
			<table id="calculateList">
				<tr>
					<th>년 도</th> <th>지점이름</th> <th>총합</th>
				</tr>
			<c:forEach items="${GwcalculateList}" var="GwcalculateList">
				<tr>
					<td height="23" align="center">${GwcalculateList.indate }</td>
					<td style="text-align: left; padding-left: 50px; padding-right: 0px;">${GwcalculateList.camp_name }</td>
					<td>${GwcalculateList.total_price } 원</td>
				</tr>	
			</c:forEach>	
			</table>
		</article>
	</div>
	
<%@ include file="../admin_footer.jsp"%>
</body>
</html>