<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>캠핑족장 관리자 공지사항</title>
</head>
<body>
	<div align="center">
		<article>
			<h2>공지사항</h2>
			<h3>캠핑족장 관리자 공지사항 입니다</h3>

			<!-- 검색 파트 -->
			<form name="frm" id="admin_notice_form" method="get">
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
				<table id="noticeList" border="1">
					<tr>
						<th width="40">번호</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="130">작성일</th>
						<th width="50">조회수</th>
					</tr>
					<c:choose>
						<c:when test="${adminnoticeListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 공지사항이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${adminnoticeList}" var="adminnoticeVO">
								<tr>
									<td height="23" align="center">${adminnoticeVO.aseq}
									 <a href="admin_notice_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&aseq=${adminnoticeVO.aseq}"></a>
									 </td>    
									<td><a href="${path}admin_notice_details?aseq=${adminnoticeVO.aseq}">${adminnoticeVO.title}</a></td>
									<td>${adminnoticeVO.admin_name}</td>
									<td><fmt:formatDate value="${adminnoticeVO.indate}" type="date" />
									</td>
									<td>${adminnoticeVO.count}</td>
								</tr>
							</c:forEach>
							<tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
						</c:otherwise>
					</c:choose>
				</table>
			</form>
			<%@ include file="page_area.jsp"%>
		</article>
	</div>
</body>
</html>
<%@ include file="../admin_footer.jsp"%>