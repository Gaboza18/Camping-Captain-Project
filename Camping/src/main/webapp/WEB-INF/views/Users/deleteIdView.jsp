<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ include file="../header.jsp" %>  
<div id="user_delete_form" align="center">
	<article id="usersDeleteForm">	 
		<section id="container">
			<h4>회원 탈퇴</h4>
			<form id="usersDelete" action="usersDelete" method="post">
				<ul id="deleteRule">
					<li>회원 탈퇴 시 회원님의 회원정보, 거래정보 등은 모두 삭제됩니다.</li>
					<li>회원 탈퇴 후 해지 및 재가입 방지를 목적으로 1개월간 회원의 성명, 생년월일, 아이디, 비밀번호, 이메일, 로그 기록, 접속IP를 보관합니다. 
					         예약정보가 있는 경우 거래 정보관리를 위하여 예약과 관련된 예약정보, 아이디 등에 대한 기본정보는 탈퇴 후 5년 간 보관합니다.</li>
					<li>회원 탈퇴 후 재가입시에는 신규회원 가입으로 처리되며, 탈퇴 전의 회원정보와 거래정보 등은 복구되지 않습니다.</li>
					<li>
						진행중인 예약 내역이 있는 경우에는 즉시 탈퇴가 불가능 합니다.
					</li>	         
				</ul>
				<table>
					<tr>
						<th width="220">즉시 탈퇴가 불가능한 경우</th>
						<th width="220">탈퇴 방법</th>
					</tr>
					<tr>
						<td>진행중인 예약 내역이 있는 경우</td>
						<td>예약 취소 후 탈퇴 가능(취소 확정까지 진행되어야 함)</td>
					</tr>
					<tr>
						<td>취소가 확정되지 않은 내역이 있는 경우</td>
						<td>취소 확정 후 탈퇴 가능</td>
					</tr>
				</table>
				
				<label class="control-label" for="id">아이디</label>
				<input class="form-control" type="text" id="id" name="id" value="${users.id}" readOnly="readonly"/>
				<input class="form-control" type="hidden" id="password" name="password" value="${users.password}" readOnly="readonly"/><br>
				<label>비밀번호 재확인</label> 
				<input type="password"  id="passwordCheck"><br> 
				
				<div class="clear"></div>
				
				<span style="color: red;">
					회원 탈퇴 시 재가입은 30일이 경과된 이후 신규회원으로 가입이 가능합니다.<br>
					탈퇴신청을 하시겠습니까?
				</span>
				
				<input type="hidden" id="OrderCount" value="${OrderCount}"/>
				<input type="hidden" id="CancelCount" value="${CancelCount}"/>
				
				<div id="subbtn">
					<input type="button" id="delete" onclick="go_out()" value="회원탈퇴">
					<input type="button" onclick= "location.href='index'" value="취소">
				</div>
			</form>
		</section>
	</article>
</div>
<%@ include file="../footer.jsp" %>
