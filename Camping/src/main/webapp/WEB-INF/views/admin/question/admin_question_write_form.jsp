<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>    
<script>
	function form_chk() {
		if($("#question").val() == ""){
			alert("질문을 입력해주세요.");
			return false;
		} else if($("#reply").val() == ""){
			alert("답변을 입력해주세요.");
			return false;
		} else {
			$("#admin_question").attr("action", "question_write").submit();
		}
		return true;
	}
</script>

	<div id="admin_question_write_form" align="center">
		<article>
			<h4>자주묻는 질문 등록</h4>
			<form name="formm" id="admin_question" method="get" action="question_write">
				<fieldset>
					<input type="hidden" value="${questionsVO.qseq}">
					
					<label>질문</label>
					<input type="text" name="question" id="question" size="63" value="${questionsVO.question}"><br>	
					
					<label id="reply_label">답변</label>
					<textarea rows="8" cols="65" name="reply" id="reply">${questionsVO.reply}</textarea><br>
				</fieldset>
				
				<input type="button" value="글쓰기" class="btn" onclick="form_chk()">
				<input type="reset" value="다시쓰기" class="btn">
				<button type="button" value="목록" onclick="history.go(-1);">목록</button>
			</form>
		</article>			
	</div>

<%@ include file="../admin_footer.jsp"%>
