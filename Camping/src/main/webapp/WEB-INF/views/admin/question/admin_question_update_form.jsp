<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>    

<div id="admin_question_update_form" align="center">
	<article>
		<h4>자주묻는 질문 등록</h4>
		<form name="formm" method="get" action="admin_question_update">
			<fieldset>
				<input type="hidden" name="qseq" value="${questionsVO.qseq}">
				
				<label>질문</label>
				<input type="text" name="question" size="63" value="${questionsVO.question}"><br>	
				
				<label id="reply_label">답변</label>
				<textarea rows="8" cols="65" name="reply">${questionsVO.reply}</textarea><br>
			</fieldset><br>
			
			<input type="submit" value="수정하기" class="submit">
			<button type="button" value="목록" onclick="history.go(-2);">목록</button>
		</form>
	</article>			
</div>

<%@ include file="../admin_footer.jsp"%>
