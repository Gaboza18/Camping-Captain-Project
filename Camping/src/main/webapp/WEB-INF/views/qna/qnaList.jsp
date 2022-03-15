<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="css/styles.css" >

<div id="qna_list_form" align="center">
	<article>
		<h2> 1:1 고객 게시판 </h2>
	    <h5> 고객님의 질문에 대해서 관리자가 1:1 답변을 드립니다.</h5>
	  
	    <form name="formm" method="post">
	      <table id="qnaList" class="qnaList" border="1">
		      <tr>
		        <th width="50">번호</th> <th width="250">제목</th> <th width="100">등록일</th> <th width="90">답변 여부</th>    
		      </tr>
	      <c:forEach items="${qnaList}"  var="qnaVO">
	      	<tr>  
		        <td> ${qnaVO.qseq} </td>    
		        <td> <a href="qna_view?qseq=${qnaVO.qseq}"> ${qnaVO.subject} </a></td>      
		        <td> <fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
		        <td> 
		          <c:choose>
		            <c:when test="${qnaVO.rep==1}"> no </c:when>
		            <c:when test="${qnaVO.rep==2}"> yes </c:when>
		          </c:choose>
		        </td>    
	      	</tr>
	      </c:forEach>    
	      </table><br>
   				<input type="button"  value="1:1 질문하기"  class="submit" onclick="location.href='qna_write_form'"> 
	   	</form>
	</article>
</div>	
<%@ include file="../footer.jsp" %>
