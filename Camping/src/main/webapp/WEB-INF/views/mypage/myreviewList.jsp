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
<script type="text/javascript" src="js/reviews.js"></script>

</head>


<body>
	<div align="center">
		<article>
			<h2>MY Real Review !</h2>
			<h3>내가 쓴 리뷰입니다</h3> 
			<h3>내가쓴 리뷰를 확인해보세요!</h3>

			<!-- 검색 파트 -->
			<form name="frm" id="review_list" method="get" action = "myreview_list">
				<table>
			  		<tr>
  						<td>
      						제목 
     					<input type="text" name="key" id="key" >
     					<input class="btn" type="button" name="btn_search" value="검색" onClick="go_search()">
			  			</td>
			  		</tr>
			  	</table>
			  </form>
			<br>
			<form name="myreview" method="get">
				<table id="myreviewList"  border="1">
					<tr>
						<th width="40">번호</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="100">캠핑장지점이름</th>
						<th width="130">작성일</th>
						<th width="50">조회수</th>
					</tr>
					<c:choose>
						<c:when test="${myreviewListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 리뷰가 없습니다.</td>
							</tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${myreviewList}" var="myRealReviewVO">
								<tr>  
									<td height="23" align="center">${myRealReviewVO.rseq}
									 <a href="review_list${mypageMaker.makeQuery(mypageMaker.criteria.pageNum)}&rseq=${myRealReviewVO.rseq}"></a>
									 </td>    
									<td><a href="${path}review_detail?rseq=${myRealReviewVO.rseq}">${myRealReviewVO.title}</a></td>
									
									
									<td>${myRealReviewVO.id}</td>
									<td>${myRealReviewVO.campingname}</td>
									
									<td><fmt:formatDate value="${myRealReviewVO.indate}" type="date" />
									</td>
									<td>${myRealReviewVO.count}</td>
									</tr>
							</c:forEach>
							
							<tr><td colspan="6" style="text-align: center;"> ${paging} </td>
							</tr>
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