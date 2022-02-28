<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp" %>	 
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="css/camping.css">
<title>캠핑족장 리얼리뷰</title>
<script type="text/javascript" src="js/reviews.js">

function getArea() {
	var area = 
	
	
	
}





</script>

</head>


<body>
	<div align="center">
		<article>
			<h2>Real Review !</h2>
			<h3>캠핑족장 리뷰 입니다</h3> 
			<h3>부족원들의 의견을 들어보세요!</h3>

			<!-- 검색 파트 -->
			<form name="frm" id="review_list" method="get" action = "review_list">
				<table>
			  		<tr>
  						<td>
      						제목 
     					<input type="text" name="key" id="key">
     					<input class="btn" type="button" name="btn_search" value="검색" onClick="go_search()">
			  			</td>
			  		</tr>
			  	</table>
			  </form>
			<br>
			<form name="formm" method="get">
			<input type="radio" name="list" value="전체보기 " checked>전체보기 
			<input type="radio" name="list" value="서울" onClick= 'getArea()'>서울 
			<input type="radio" name="list" value="대전" onClick= 'getArea()'>대전
			<input type="radio" name="list" value="대구" onClick= 'getArea()'>대구
			<input type="radio" name="list" value="부산" onClick= 'getArea()'>부산
			<input type="radio" name="list" value="찍고" onClick= 'getArea()'>찍고
				
				<table id="reviewList" border="1">
					<tr>
						<th width="40">번호</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="100">캠핑장지점이름</th>
						<th width="130">작성일</th>
						<th width="50">조회수</th>
					</tr>
					<c:choose>
						<c:when test="${reviewListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 리뷰가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${reviewList}" var="RealReviewVO">
								<tr>
									<td height="23" align="center">${RealReviewVO.rseq}
									 <a href="review_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&rseq=${RealReviewVO.rseq}"></a>
									 </td>    
									<td><a href="${path}review_detail?rseq=${RealReviewVO.rseq}">${RealReviewVO.title}</a></td>
									
									
									<td>${RealReviewVO.id}</td>
									<td>${RealReviewVO.campingname}</td>
									
									<td><fmt:formatDate value="${RealReviewVO.indate}" type="date" />
									</td>
									
									
									
									<td>${RealReviewVO.count}</td>
								</tr>
							</c:forEach>
							<tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
						</c:otherwise>
					</c:choose>
				</table>																	
				<input class="btn" type="button" name="btn_input" value="리얼 리뷰작성" onClick="location.href='review_write'">
			</form>  
			<%@ include file="reviewpage_area.jsp"%>
		</article>
	</div>
</body>
<%@ include file="../footer.jsp" %>
</html>