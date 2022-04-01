<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="css/styles.css" >
    
<div id="qna_view_form" align="center">
	<article>
		<h2> 1:1 고객 게시판 </h2>
		<h5> 고객님의 질문에 대해서 관리자가 1:1 답변을 드립니다.</h5>
		<form name="formm" method="post">
			<table id="qnaDetail" border="1">
			  	<tr>
         			<th>제목</th>
         			<td>${qnaVO.subject}</td> 
				</tr>
    			<tr>
      				<th>등록일</th>
      				<td> <fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
    			</tr>
    			<tr>
      				<th>질문내용</th>
      				<td>${qnaVO.content} 
    			</tr>
    			<tr>
      				<th>답변 내용</th>
      				<td>${qnaVO.reply}  
    			</tr>
			</table><br>
			<div class="clear"></div>
			<div id="buttons" style="float:center">
 				<input type="button"  value="목록보기" class="submit" onclick="location.href='qna_list'">
			</div>	 
		</form>
	</article>				
</div>

<%@ include file="../footer.jsp" %>