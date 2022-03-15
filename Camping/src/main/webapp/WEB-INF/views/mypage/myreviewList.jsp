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
<script type="text/javascript">
function black_non_write() {
	alert("블랙리스트 회원은 리뷰작성을 할 수 없습니다.");
}
</script>
</head>


<body>
	<div id="myreview_list_form" align="center">
		<article>
			<h2>MY Real Review !</h2>
			<h4>내가 쓴 리뷰입니다</h4> 
			<h4>내가쓴 리뷰를 확인해보세요!</h4>

			<!-- 검색 파트 -->
			<form name="frm" id="review_list1" method="get" action = "myreview_list">
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
						<th width="250">제목</th>
						<th width="100">작성자</th>
						<th width="130">캠핑장지점이름</th>
						<th width="130">작성일</th>
						<th width="80">조회수</th>
					</tr>
					<c:choose>
						<c:when test="${myreviewListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 리뷰가 없습니다.</td>
							</tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${myreviewList}" var="RealReviewVO">
								<tr>  
									<td height="23" align="center">${RealReviewVO.rseq}
									 <a href="myreview_list${mypageMaker.makeQuery(mypageMaker.criteria.pageNum)}&rseq=${RealReviewVO.rseq}"></a>
									 </td>    
									<td><a href="${path}myreview_detail?rseq=${RealReviewVO.rseq}">${RealReviewVO.title}</a></td>
									
									
									<td>${RealReviewVO.id}</td>
									<td>${RealReviewVO.campingname}</td>
									
									<td><fmt:formatDate value="${RealReviewVO.indate}" type="date" />
									</td>
									<td>${RealReviewVO.count}</td>
									</tr>
							</c:forEach>
							
							<tr><td colspan="6" style="text-align: center;"> ${paging} </td>
							</tr>
								</c:otherwise>
								</c:choose>
				</table>
					
			<c:choose>
				<c:when test="${loginUser.blacklist eq 'y'}">																
					<input class="btn" type="button" name="btn_input" value="새로운 리얼 리뷰작성" onClick="black_non_write()">
				</c:when>
				<c:otherwise>
					<input class="btn" type="button" name="btn_input" value="새로운 리얼 리뷰작성" onClick="location.href='myreview_write'">
				</c:otherwise>
			</c:choose>
				
				
				
				
				
				
			</form>
			<%@ include file="reviewpage_area.jsp"%>
		</article>
	</div>
</body>
<%@ include file="../footer.jsp" %>
</html>