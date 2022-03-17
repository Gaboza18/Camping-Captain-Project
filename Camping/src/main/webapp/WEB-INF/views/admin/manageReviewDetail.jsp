<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="admin_header.jsp"%>
<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<script type="text/javascript" src="js/reviews.js"></script>
<script type="text/javascript">


	function admin_delete() {
		
	    alert("관리자가 회원이 쓴 리뷰를 삭제되었습니다");
	    $("#delete3").attr('action','review_list_re').submit(); 
	
	}

</script>


<div id="manager_realreview_detail" align="center">
	<h4>회원 리뷰 상세내용입니다</h4>
	<form name="frm" id="admindetail" method="get" action="review_list_re">
		<table id="detail">
			<tr>
				<th>번호</th>
				<td width="130">${RealReviewVO.rseq}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${RealReviewVO.title}</td>
			</tr>
			<tr>
				<th width="130">내용</th>
				<td>${RealReviewVO.content}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<c:choose>
						<c:when test="${RealReviewVO.id == null}">
							<span style="color: gray;">탈퇴회원이 작성한 리뷰입니다.</span>
						</c:when>
						<c:otherwise>
							${RealReviewVO.id}
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>캠핑장이름</th>
				<td>${RealReviewVO.campingname}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${RealReviewVO.indate}" /></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${RealReviewVO.count}</td>
			</tr>
			
		</table>
		
		<!--위의 td 옆에 input 값주고 readonly로 바꿔줘도 됨  -->
		<input type="text" name="rseq" id="rseq" value="${RealReviewVO.rseq}" style="display:none">
		<!-- <input class="btn"  type="button" value="목록" onClick="go_list()"> -->
		<input class="btn" type="button" value="목록" onClick="location.href='review_list'">
		
		
		<!-- 단순히 admin으로 접속해서 review로 들어갔을 때 삭제버튼만 뜨게하기  -->
		<button type="submit" form="admindetail" class="submit" id="delete3" onClick="admin_delete()">리뷰삭제</button>
	
	</form>
</div>

<%@ include file="admin_footer.jsp"%>
