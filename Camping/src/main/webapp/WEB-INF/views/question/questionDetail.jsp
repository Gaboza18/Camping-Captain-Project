<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>    
<link rel="stylesheet" href="css/styles.css" >

	
<div id="question_detail_form" align="center">
	<h2>자주묻는질문 상세보기</h2>

  	<div align="center">
		<table>
			<tr>
				<th width="100">번호</th>
				<td width="250">${questionsVO.qseq}</td>
			</tr>
			<tr>
				<th>질문</th>
				<td>${questionsVO.question}</td>
			</tr>
			<tr>
				<th>답변</th>
				<td>${questionsVO.reply}</td>
			</tr>
		</table><br>
	
		<input class="btn"  type="button" value="목록" onclick="history.go(-1);">
	</div>
</div>

<%@ include file="../footer.jsp" %>