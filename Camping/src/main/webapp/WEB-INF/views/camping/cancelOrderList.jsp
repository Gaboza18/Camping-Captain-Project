<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<article id="cancel_list_form">

	<div align="center">
		<h2>취소 내역 확인</h2>
		<form>
			<div>
				<table id="cancelList" style="text-align: center;"> 
					<tr>
						<th width="80">예약번호</th>
						<th width="100">예약자이름</th>
						<th width="150">지점</th>
						<th width="50">구역</th>
						<th width="70">결제금액</th>
						<th width="70">환불금액</th>
						<th width="85">취소날짜</th>
						<th width="215">취소 진행상태</th>
						<th width="120"></th>
					</tr>
					<c:forEach items="${cancelOrderList}" var="cancelOrderList">
						<tr>
							<td>${cancelOrderList.oseq}</td> 
							<td>${cancelOrderList.order_name}</td>
							<td>${cancelOrderList.camp_name}</td>
							<td>${cancelOrderList.camp_zone}</td>
							<td>${cancelOrderList.total_price}</td>
							<td>${cancelOrderList.cancelprice}</td>
							<td>${cancelOrderList.cancel_date}</td>
							<td>
								<c:choose>
									<c:when test="${cancelOrderList.status == 'y'}">
										<span style="color: red;">취소 완료</span>
									</c:when>
									<c:when test="${cancelOrderList.status == 'n'}">
										<span>취소 진행 중</span>
									</c:when>
								</c:choose>
							</td>
							<td>
								<a href="myCancel_detail?cseq=${cancelOrderList.cseq}" onclick="window.open(this.href, '_blank', 'toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500'); return false;">취소상세내용보기</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>	
	</div>
	<%@ include file="myCancel_page_area.jsp"%>
</article>

<%@ include file="../footer.jsp" %>