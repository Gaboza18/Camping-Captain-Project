<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>    

<div id="admin_question_detail_form" align="center">
	<h4>자주 묻는 질문 상세내용</h4>
	<table>
		<tr>
			<th>번호</th>
			<td width="130">${questionsVO.qseq}</td>
		</tr>
		<tr>
			<th>질문</th>
			<td width="130">${questionsVO.question}</td>
		</tr>
		<tr>
			<th>답변</th>
			<td width="130">${questionsVO.reply}</td>
		</tr>
	</table><br>
	<input class="btn"  type="button" value="목록" onclick="history.go(-1);">
	<a href="${path}admin_question_update_form?qseq=${questionsVO.qseq}"><input type="button" value="수정"></a>
    <a href="${path}admin_question_delete?qseq=${questionsVO.qseq}"><input type="button" value="삭제"></a>
</div>

<%@ include file="../admin_footer.jsp"%>
