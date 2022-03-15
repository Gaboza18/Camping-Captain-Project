<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin/admin_header.jsp"%>
<%@ include file="../admin/master_admin_menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/camping.css">
<title>캠핑족장 공지사항</title>
<script>
function go_insert(){
	$("#master_manage_notice_insert").attr("action", "master_notice_manage_form").submit();
}
</script>
</head>
<body>
	<div align="center">
		<article>
			<h2>캠핑족장 공지사항 관리</h2>

			<!-- 검색 파트 -->
			<form name="frm" id="notice_form" method="get">
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
				<table id="noticeList">
					<tr>
						<th width="40">번호</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="130">작성일</th>
						<th width="50">조회수</th>
					</tr>
					<c:choose>
						<c:when test="${noticeListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 공지사항이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${noticeList}" var="noticeVO">
								<tr>
									<td height="23" align="center">${noticeVO.nseq}
									 <a href="master_manage_notice_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&nseq=${noticeVO.nseq}"></a>
									 </td>    
									<td><a href="${path}master_manage_notice_detail?nseq=${noticeVO.nseq}">${noticeVO.title}</a></td>
									<td>${noticeVO.admin_name}</td>
									<td><fmt:formatDate value="${noticeVO.indate}" type="date" />
									</td>
									<td>${noticeVO.count}</td>
								</tr>
							</c:forEach>
							<tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
						</c:otherwise>
					</c:choose>
				</table>
			</form>
			<%@ include file="page_area.jsp"%>
		<form id="master_manage_notice_insert">
			<input class="btn" type="button" name="btn_search" value="등록" onClick="go_insert()">
		</form>
		</article>
	</div>
</body>
</html>
<%@ include file="../admin/admin_footer.jsp" %>