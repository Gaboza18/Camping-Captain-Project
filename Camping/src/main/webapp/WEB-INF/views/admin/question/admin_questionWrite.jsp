<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>

<script>
	function insert_ques() {
		$("#admin_question_manage").attr("action", "admin_question_write_form").submit();
	}
</script>

	<div id="admin_question_write_form" align="center">
		<h4>고객님들의 자주 묻는 질문관리</h4>
		<form name="admin_question_manage" id="admin_question_manage" method="get">
			<table id="questionList">
				<tr>
					<th width="80">번호</th>
					<th width="350">질문</th>
				</tr>
				<c:forEach items="${questionList}" var="questionsVO">
					<tr>
						<td>${questionsVO.qseq}</td>
						<td>
							<a href="${path}admin_question_detail?qseq=${questionsVO.qseq}">${questionsVO.question}</a>${questionVO.question}
						</td>
					</tr>	
				</c:forEach>
			</table><br>
			<div align="center">
          		<input type="button" value="등록" class="submit" onclick="insert_ques()">
          	</div>  
		</form>
	</div>

<%@ include file="../admin_footer.jsp"%>