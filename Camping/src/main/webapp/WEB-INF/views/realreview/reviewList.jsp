<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp" %>	 
<link rel="stylesheet" href="css/styles.css" >

<script type="text/javascript" >

/* function getArea(area1) {
	
	$.ajax({
		url: "review_list", //액션값 넣기
		type: "GET", //get or post방식 정하기
		data: { 
			area: area1 //넘길 인자값
		},
		dataType: "json", //응답받는 데이터타입(제이슨이니까 제이슨)
		success:function(data){//응답 값 
			console.log(data);
		}
		
	});
} */

//document 는 html이 다 실행되었을때 버튼 누르면 작동되게 하는것
function getFormatDate(date) {
	var year = date.getFullYear();
	var month = (1 + date.getMonth());
	month = month>=10 ? month : '0'+month;
	var day = date.getDate();
	day = day>=10 ? day : '0'+day;
	
	return year+'.'+month+'.'+day;
}

$(document).ready(function(){
	$("input[name='arealist']:radio").change(function(){
	 var area=this.value; 

	 console.log("AREA="+area);

	 $.ajax({
			url: "arealist?campingname="+area, //radio name=arealist 액션값 넣기
			type: "GET", //get or post방식 정하기	
			dataType: "json", //응답받는 데이터타입(제이슨이니까 제이슨)
	 		success:function(data){//응답 값 '
	 			console.log("응답받기 성공");
	 			alert(area+"지점 리뷰를 선택하였습니다");
	 		
	 	
	 			var htmlOut = '<table id="reviewlist" border="1">';
	 			htmlOut += '<tr><th width="40">번호</th><th width="200">제목</th><th width="100">작성자</th><th width="100">캠핑장지점이름</th><th width="130">작성일</th><th width="50">조회수</th><th width="50">삭제버튼</th></tr>';
		 		$.each(data, function() {
		 			var conv_date = getFormatDate(new Date(this.indate));
		 			htmlOut += '<tr><td>'+ this.rseq+'</td><td>'+this.title+'</td>'
		 					+'<td>'+ this.id+'</td><td>'+this.campingname+'</td>'+
		 					'<td>'+ conv_date+'</td><td>'+this.count+'</td></tr>';
		 		});
	 			
	 			$("#review_content").html(htmlOut);
	 		
			},
			error : function(request, status, error){
				alert("code:"+request.status+"\n"+
						"message:"+request.responseText+"\n"+
						"error:"+error);
			}
	 		
		});
	})
});


 /* $function() {
$("#radio2").onClick(function(){
alert("radio2 클릭");
$.ajax({
	url: "review_list", //액션값 넣기
	type: "GET", //get or post방식 정하기
	data: { 
		${RealReviewVO.campingname}: $("#radio2").val() //넘길 인자값
	},
	dataType: "json", //응답받는 데이터타입(제이슨이니까 제이슨)
	success:function(data){//응답 값 
		console.log(data);
	}
	
});
})  */

</script>

<article id="real_review">
	<div align="center">
		<h2>Real Review !</h2>
		<h5>캠핑족장 리뷰 입니다</h5> 
		<h5>부족원들의 의견을 들어보세요!</h5>

		<!-- 검색 파트 -->
		<form name="frm" id="review_list" method="get" action = "review_list">
			<table>
				<tr>
 					<td>
     					제목 
     					<!-- @RequestParam의 네임이름 = key -->
    					<input type="text" name="key" id="key">
    					<input class="btn" type="button" name="btn_search" value="검색" onClick="go_search()">
		  			</td>
				</tr>
			</table>
		</form>
		
		<br>
			
		<form name="formm" method="get">
			
			<ul class="arealist">
				<!-- 인자값(입력해서 요청보내는 값) -->
				<li>
					<input type="radio" id="radio1" name="arealist" value="전체보기 " checked onClick="location.href='review_list'">전체보기
					<input type="radio" id="radio2" name="arealist" value="강원도" ><label for="radio2">강원도</label> 
					<input type="radio" id="radio3" name="arealist" value="경기도" ><label for="radio3">경기도</label>
					<input type="radio" id="radio4" name="arealist" value="충청도" ><label for="radio4">충청도</label>
					<input type="radio" id="radio5" name="arealist" value="경상도" ><label for="radio5">경상도</label>
					<input type="radio" id="radio6" name="arealist" value="전라도" ><label for="radio6">전라도</label>
					<input type="radio" id="radio7" name="arealist" value="제주도" ><label for="radio6">제주도</label>
				</li>
			</ul>
			
			<br><br>
			
			<div id="review_content">
				<table id="reviewList" border="1">
					<tr>
						<th width="70">번호</th>
						<th width="250">제목</th>
						<th width="100">작성자</th>
						<th width="130">캠핑장지점이름</th>
						<th width="130">작성일</th>
						<th width="70">조회수</th>
					</tr>
					
					<c:choose>
						<c:when test="${reviewListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 리뷰가 없습니다.
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${reviewList}" var="RealReviewVO">
								<tr>
									<!-- 리턴,반환값 -->
									<td height="23" align="center">${RealReviewVO.rseq}
										<a href="review_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&rseq=${RealReviewVO.rseq}"></a>
									</td>
									     
									<td>
										<a href="${path}review_detail?rseq=${RealReviewVO.rseq}">${RealReviewVO.title}</a>
									</td>
									
									<td>
										<c:choose>
											<c:when test="${RealReviewVO.id == null}">
												<span style="color: gray;">탈퇴회원</span>
											</c:when>
											<c:otherwise>
												${RealReviewVO.id}
											</c:otherwise>
										</c:choose>
									</td>
									
									<td>${RealReviewVO.campingname}</td>
									
									<td id="indate">
										<fmt:formatDate value="${RealReviewVO.indate}" type="date" />
									</td>
									
									<td>${RealReviewVO.count}</td>
								</tr>
							</c:forEach>
							
							<tr>
								<td colspan="6" style="text-align: center;"> ${paging} </td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>																	
			<input class="btn" type="button" name="btn_input" value="리얼 리뷰작성" onClick="location.href='review_write'">
		</form>  
		<%@ include file="reviewpage_area.jsp"%>
	</div>
</article>
	

<%@ include file="../footer.jsp" %>