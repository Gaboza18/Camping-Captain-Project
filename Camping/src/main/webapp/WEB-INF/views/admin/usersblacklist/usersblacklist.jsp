<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../admin_header.jsp"%>
<%@ include file="../manager_admin_menu.jsp"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="css/camping.css">
<title>관리자 블랙리스트</title>

<script type="text/javascript">



//document 는 html이 다 실행되었을때 버튼 누르면 작동되게 하는것
/* function getFormatDate(date) {
	var year = date.getFullYear();
	var month = (1 + date.getMonth());
	month = month>=10 ? month : '0'+month;
	var day = date.getDate();
	day = day>=10 ? day : '0'+day;
	
	return year+'.'+month+'.'+day;
}

$(document).ready(function(){
	$("input[name='userslist']:radio").change(function(){
	 var area=this.value; 

	 console.log("users="+users);

	 $.ajax({
			url: "userslist?id="+users, //radio name=arealist 액션값 넣기
			type: "GET", //get or post방식 정하기	
			dataType: "json", //응답받는 데이터타입(제이슨이니까 제이슨)
	 		success:function(data){//응답 값 '
	 			console.log("응답받기 성공");
	 			alert(area+"회원을 조회합니다");
	 		
	 	
	 			var htmlOut = '<table id="usersList" border="1">';
	 			htmlOut += '<tr><th width="40">번호</th><th width="200">제목</th><th width="100">작성자</th><th width="100">캠핑장지점이름</th><th width="130">작성일</th><th width="50">조회수</th></tr>';
		 		$.each(data, function() {
		 			var conv_date = getFormatDate(new Date(this.regdate));
		 			//var conv_rseq = Int(new rseq(this.rseq));
		 			
		 			htmlOut += 
		 				'<tr><td>'+ this.useq+'</td><td><a href="review_detail?rseq='+this.rseq+'">'+this.title+'</a></td>'
		 					+'<td>'+ this.id+'</td><td>'+this.campingname+'</a></td>'+
		 					'<td>'+ conv_date+'</td><td>'+this.count+'</td></tr>';
		 		});
	 			
	 			$("#users_content").html(htmlOut);
	 		
			},
			error : function(request, status, error){
				alert("code:"+request.status+"\n"+
						"message:"+request.responseText+"\n"+
						"error:"+error);
			}
	 		
		});
		})
		
	}); */
	
function userschange(useq) {
		var useq = $("#hiddenuseq");
		console.log(useq);
		alert("회원상태가 변경되었습니다");
		 $("#change").attr('action','changestatus').submit(); 

				}



       	

       	


	



</script>

</head>


<body>
	<div align="center">
		<article>
			<h2>회원상태 조회 !</h2>
			<h3>캠핑족장 회원조회 입니다</h3>


			<!-- 검색 파트 -->
			<form name="frm" id="uesrs_list" method="get" action="users_list">
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

			<form name="formm" id="change" method="get" action="changestatus">

				<ul class="userslist">

					<!-- 인자값(입력해서 요청보내는 값) -->
					<input type="radio" id="radio1" name="arealist" value="전체 회원보기 "
						checked onClick="location.href='users_list'">전체보기

					<input type="radio" id="radio2" name="arealist" value="일반회원">
					<label for="radio2">일반회원</label>

					<input type="radio" id="radio3" name="arealist"
						value="E-mail 인증 안된 회원">
					<label for="radio3">E-mail 인증 안된 회원</label>


					<input type="radio" id="radio4" name="arealist" value="블랙리스트">
					<label for="radio4">블랙리스트</label>


				</ul>

				<br>
				<br>



				<div id="users_content">
					<table id="usersList" border="1">
						<tr>
							<th width="40">번호</th>
							<th width="200">아이디</th>
							<th width="100">이름</th>
							<th width="100">이메일</th>
							<th width="130">가입일</th>
							<th width="50">블랙리스트상태</th>
							<th width="30">블랙리스트추가버튼</th>
						</tr>



						<c:choose>
							<c:when test="${usersListSize<=0}">
								<tr>
									<td width="100%" colspan="7" align="center" height="23">
										등록된 블랙리스트가 없습니다.</td>
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
										<td id="regdate"><fmt:formatDate
												value="${UsersVO.regdate}" type="date" /></td>
										<td>${UsersVO.blacklist}</td>


										<td><input type="hidden" name="useq" id="hiddenuseq"
											value="${UsersVO.useq}" id="useq"> <!-- <button type="submit" form="formm" class="btn-success" id="changebutton" onClick="userschange()">블랙리스트추가</button> -->

											<button type="button" value="UsersVO.useq" id="changebutton"
												onClick="userschange()">블랙리스트추가</button> <!-- 
	<a href="changestatus?id=${UsersVO.id}"> 블랙리스트추가 </a>
	 --></td>

									</tr>
								</c:forEach>
								<tr>
									<td colspan="6" style="text-align: center;">${paging}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
				</div>
			</form>

			<div>
				<ul>
					<c:forEach begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}" var="num">
						<li><a
							href="users_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&useq=${UsersVO.useq}">${num}</a></li>
					</c:forEach>
				</ul>
			</div>





		</article>
	</div>
</body>
<%@ include file="../admin_footer.jsp"%>

</html>