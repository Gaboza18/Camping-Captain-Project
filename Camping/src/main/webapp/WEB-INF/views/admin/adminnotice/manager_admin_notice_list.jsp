<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../manager_admin_menu.jsp"%>    

<div id="manager_admin_notice_list" align="center">
	<article>
		<h4>캠핑족장 관리자 공지사항 입니다</h4>
		<!-- 검색 파트 -->
		<form name="frm" id="admin_notice_form" method="get" action="manager_admin_notice_list">
			<table>
		  		<tr>
					<td>
   						제목 
    					<input type="text" name="key" id="key">
    					<input class="btn" type="button" name="btn_search" value="검색" onClick="go_search_manager()">
		  			</td>
		  		</tr>
		  	</table>
		  </form>
		<br>
		<form name="formm" method="get">
			<table id="noticeList">
				<tr>
					<th width="80">번호</th>
					<th width="250">제목</th>
					<th width="120">작성자</th>
					<th width="140">작성일</th>
					<th width="90">조회수</th>
				</tr>
				<c:choose>
					<c:when test="${adminnoticeListSize<=0}">
						<tr>
							<td width="100%" colspan="7" align="center" height="23">
								등록된 공지사항이 없습니다.
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${adminnoticeList}" var="adminnoticeVO">
							<tr>
								<td height="23" align="center">
									${adminnoticeVO.aseq}
								 	<a href="admin_notice_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&aseq=${adminnoticeVO.aseq}"></a>
								</td>    
								<td>
									<a href="${path}manager_admin_notice_detail?aseq=${adminnoticeVO.aseq}">${adminnoticeVO.title}</a>
								</td>
								<td>${adminnoticeVO.admin_name}</td>
								<td><fmt:formatDate value="${adminnoticeVO.indate}" type="date" /></td>
								<td>${adminnoticeVO.count}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</form>
		
		<%@ include file="manager_page_area.jsp"%>
		
	</article>
</div>

<%@ include file="../admin_footer.jsp"%>