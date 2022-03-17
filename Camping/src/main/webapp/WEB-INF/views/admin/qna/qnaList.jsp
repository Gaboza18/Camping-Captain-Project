<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>

<script type="text/javascript">
  function go_view(qseq) {
    var theForm = document.frm;
    theForm.qseq.value = qseq;
    theForm.action = "admin_qna_detail";
    theForm.submit();
  }
</script>

<div id="qnalist_form" align="center">
	<article>
		<h4>Q&amp;A 게시글 리스트</h4>
		<form name="frm" method="post">
			<input type="hidden" name="qseq">
				<table id="orderList">
					<tr>
						<th width="130">번호(답변여부)</th>
						<th width="250">제목</th>
						<th width="150">작성자</th>
						<th width="150">작성일</th>
					</tr>
					<c:forEach items="${qnaList}" var="qnaVO">
						<tr>
							<td>${qnaVO.qseq} <c:choose>
									<c:when test='${qnaVO.rep=="1"}'>(미처리)</c:when>
									<c:otherwise>(답변처리완료)</c:otherwise>
								</c:choose>
							</td>
							<td><a href="#" onClick="javascript:go_view('${qnaVO.qseq}')">
									${qnaVO.subject} </a></td>
							<td>${qnaVO.id}</td>
							<td><fmt:formatDate value="${qnaVO.indate}" /></td>
						</tr>
					</c:forEach>
				</table>
			
		</form>
	</article>
</div>	
<%@ include file="../admin_footer.jsp"%>
</body>
</html>
