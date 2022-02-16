<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>자주묻는질문</title>
</head>
<body>
	<div align="center">
		<article>
			<h2>자주묻는질문</h2>
			<p>고객님들 께서 자주 질문해신 내용을 정리해 놓은 게시판 입니다</p>
		  <br>
			<form name="formm" method="get">
				<table id="questionList" border="1">
					<tr>
						<th width="50">번호</th>
						<th width="350">질문</th>
					</tr>
						<c:forEach items="${questionList}" var="questionsVO">
							<tr>
								<td>${questionsVO.qseq}</td>
								<td><a href="${path}question_detail?qseq=${questionsVO.qseq}">${questionsVO.question}</a>${questionVO.question}</td>
							</tr>
						</c:forEach>
				</table>
			</form>
		</article>
	</div>
</body>
</html>