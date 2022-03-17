<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../master_admin_menu.jsp"%>
<script type="text/javascript">
	/*function users_status_list() {
		location.href="users_status_list";
		return false;
	}*/
</script>
<div id="black_list_form" align="center">
	<article>
		<h2>회원 조회 </h2>
		<h4>캠핑족장 회원조회 입니다</h4>
		
		<!-- 검색 파트 -->
		<form name="searchfrm" id="uesrs_list" method="get"
			action="users_list">
			<table>
				<tr>
					<td>회원 ID <!-- @RequestParam의 네임이름 = key --> <input
						type="text" name="key" id="key"> <input class="btn"
						type="button" name="btn_search" value="검색" onClick="go_search()">
					</td>
				</tr>
			</table>
		</form>
		<br>
		<ul class="userslist">
			<!-- 인자값(입력해서 요청보내는 값) -->
			<li>
				<input type="radio" id="allSearch" name="searchUserlist" value="전체 회원보기 " 
					<c:if test="${searchUserlist == null}">checked</c:if>
						onclick="location.href='users_list'">전체보기 
							<input type="radio" id="member" name="searchUserlist" value="n"
					<c:if test="${searchUserlist eq 'n'}">checked</c:if>
						onclick="location.href='users_status_list?searchUserlist=n'">일반회원
							<input type="radio" id="blackMember" name="searchUserlist" value="y"
					<c:if test="${searchUserlist eq 'y'}">checked</c:if>
						onclick="location.href='users_status_list?searchUserlist=y'">블랙리스트
			</li>
		</ul>
		<br><br>
		<form name="statusfrm" id="statusfrm" method="get" action="changestatus">
			<div id="users_content">
				<c:choose>
					<c:when test="${searchUserlist == null}">
						<table id="usersList" border="1">
							<tr>
								<th width="50">번호</th>
								<th width="120">아이디</th>
								<th width="85">이름</th>
								<th width="200">이메일</th>
								<th width="120">가입일</th>
								<th width="80">회원상태</th>
								<th width="150">블랙리스트 등록</th>
								<th width="150">사유</th>
							</tr>
						<c:choose>
							<c:when test="${usersListSize<=0}">
								<tr>
									<td width="100%" colspan="7" align="center" height="23">등록된 블랙리스트가 없습니다.</td>
								</tr>
							</c:when>
								<c:otherwise>
									<c:forEach items="${usersList}" var="UsersVO">
										<tr>
											<!-- 리턴,반환값 -->
											<td height="23" align="center">${UsersVO.useq}</td>
											<td>${UsersVO.id}</td>
											<td>${UsersVO.name}</td>
											<td>${UsersVO.email}</td>
											<td id="regdate"><fmt:formatDate value="${UsersVO.regdate}" type="date" /></td>
											<td>${UsersVO.blacklist}</td>
											<td>
												<c:choose>
													<c:when test="${UsersVO.blacklist eq 'y'}">
														<span style="color: red;">블랙리스트</span>
													</c:when>
													<c:when test="${UsersVO.blacklist eq 'n'}">
														<a href="insertblacklist?id=${UsersVO.id}" onclick="window.open(this.href, '_blank', 'toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500'); return false;">
															<input type="button" value="블랙리스트추가">
														</a>
													</c:when>
												</c:choose>
											</td>
											<td>${UsersVO.blackreason}</td>
											<!--  <input type="hidden" name="id" id="id" value="${UsersVO.id}" > -->
											<!-- <button type="submit" form="formm" class="btn-success" id="changebutton" onClick="userschange()">블랙리스트추가</button> -->
											<!-- <button type="button" id="" onclick="insertblacklist(${UsersVO.id})">블랙리스트등록</button> -->
											<!-- 버튼값을 바로 변경해주는것			
											<button type="button" value="${UsersVO.useq}" id="changebutton"
											onclick="javascript:location.href=('changestatus?useq=${UsersVO.useq}&id=${UsersVO.id}')">블랙리스트추가</button>--> 		
										</tr>
									</c:forEach>
										<tr>
											<td colspan="6" style="text-align: center;">${paging}</td>
										</tr>
								</c:otherwise>
						</c:choose>
					</table>
				</c:when>
				<c:when test="${searchUserlist != null}">
					<table id="usersList" border="1">
						<tr>
							<th width="50">번호</th>
							<th width="100">아이디</th>
							<th width="100">이름</th>
							<th width="200">이메일</th>
							<th width="130">가입일</th>
							<th width="150">회원상태</th>
								<c:choose>
									<c:when test="${searchUserlist eq 'n'}">
										<th width="150">블랙리스트 등록</th>
									</c:when>
									<c:when test="${searchUserlist eq 'y'}">
										<th width="200">사유</th>
										<th width="200">블랙리스트 해제</th>
									</c:when>
								</c:choose>
						</tr>
							<c:choose>
								<c:when test="${usersListSize<=0}">
									<tr>
										<td width="100%" colspan="7" align="center" height="23">등록된 블랙리스트가 없습니다.</td>
									</tr>
								</c:when>
							<c:otherwise>
								<c:forEach items="${usersList}" var="UsersVO">
									<tr>
									<!-- 리턴,반환값 -->
									<td height="23" align="center">${UsersVO.useq}</td>
									<td>${UsersVO.id}</td>
									<td>${UsersVO.name}</td>
									<td>${UsersVO.email}</td>
									<td id="regdate"><fmt:formatDate value="${UsersVO.regdate}" type="date" /></td>
									<td>${UsersVO.blacklist}</td>
										<c:choose>
											<c:when test="${UsersVO.blacklist eq 'n'}">
												<td><a href="insertblacklist?id=${UsersVO.id}" onclick="window.open(this.href, '_blank', 'toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=500'); return false;">
												<input type="button" value="블랙리스트추가"></a></td>
											</c:when>
											<c:when test="${UsersVO.blacklist eq 'y'}">
												<td>${UsersVO.blackreason}</td>
												<td><input type="button" name="cancelBlack" id="cancelBlack" value="블랙리스트 해제" onclick="location.href='cancelBlack?useq=${UsersVO.useq}'"></td>
											</c:when>
										</c:choose>
									</tr>
								</c:forEach>
									<tr>
										<td colspan="6" style="text-align: center;">${paging}</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
					</c:when>
				</c:choose>
			</div>
		</form>
		<br><br>
		<div id="page_area">
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
				<a href="users_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&useq=${UsersVO.useq}">${num}</a>
			</c:forEach>
		</div>
	</article>
</div>
<%@ include file="../admin_footer.jsp"%>