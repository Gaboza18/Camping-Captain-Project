<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin/admin_header.jsp"%>

<c:choose>
	<c:when test="${loginAdmin.status eq 1}">
		<%@ include file="../admin/master_admin_menu.jsp" %>
	</c:when>
	
	<c:when test="${loginAdmin.status eq 2}">
		<%@ include file="../admin/manager_admin_menu.jsp" %>
	</c:when>
</c:choose> 

<script>
	function go_insert(){
		$("#master_manage_notice_insert").attr("action", "master_notice_manage_form").submit();
	}
</script>

<div id="master_manager_notice_list" align="center">
	<article>
		<h4>캠핑족장 공지사항 관리</h4>

		<!-- 검색 파트 -->
		<form name="frm" id="notice_form" method="get" action="master_manage_notice_list">
			<table>
		  		<tr>
 					<td width="300">
     					제목 
    					<input type="text" name="key" id="key">
    					<input class="btn" type="button" name="btn_search" value="검색" onClick="go_search_user_notice()">
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
					</c:otherwise>
				</c:choose>
			</table>
		</form>
		<%@ include file="master_page_area.jsp"%>
		<form id="master_manage_notice_insert">
			<input class="btn" type="button" name="btn_search" value="등록" onClick="go_insert()">
		</form>
	</article>
</div>

<%@ include file="../admin/admin_footer.jsp" %>