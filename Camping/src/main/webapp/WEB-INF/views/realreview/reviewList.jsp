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


<script type="text/javascript">


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

$(document).ready(function(){
	$("input[name='arealist']:radio").change(function(){
	 var area=this.value; 
	 $.ajax({
			url: "arealist?campingname="+area, //radio name=arealist 액션값 넣기
			type: "GET", //get or post방식 정하기	
			dataType: "json", //응답받는 데이터타입(제이슨이니까 제이슨)
	 		
	 		success:function(data){//응답 값 '
	 			var htmlOut = "";
	 			alert("success");
				 $.each(data, function(index,item){
					htmlOut+="<tr>"
					htmlOut+="<td>"+item.campingname+"</td>";
					htmlOut+="<td>"+item.id+"</td>";
					htmlOut+="<td>"+item.title+"</td>";
					htmlOut+="<td>"+item.content+"</td>";
					htmlOut+="<td>"+item.indate+"</td>";
					htmlOut+="<td>"+item.count+"</td>";
					htmlOut+="</tr>"
				});
				$("#reviews").append(htmlOut);
	 		
			},
			error : function(){
				alert("실패함");
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

</head>


<body>
	<div align="center">
		<article>
			<h2>Real Review !</h2>
			<h3>캠핑족장 리뷰 입니다</h3> 
			<h3>부족원들의 의견을 들어보세요!</h3>

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
			<input type="radio" id="radio1" name="arealist" value="전체보기 " checked>전체보기

			<input type="radio" id="radio2"name="arealist" value="서울" ><label for="radio2">서울</label> 
			
			<input type="radio" id="radio3"name="arealist" value="대전" ><label for="radio3">대전</label>
			
			
			<input type="radio" id="radio4"name="arealist" value="대구" ><label for="radio4">대구</label>
			
			
			<input type="radio" id="radio5"name="arealist" value="부산" ><label for="radio5">부산</label>
			
			
			<input type="radio" id="radio6"  name="arealist" value="찍고" ><label for="radio6">찍고</label>
			
			</ul>
			
			<br><br>
			
			<!-- p안에 라디오 내용 넣기 -->
				<p id="reviews"></p>
				
				<table id="reviewList" border="1">
					<tr>
						<th width="40">번호</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="100">캠핑장지점이름</th>
						<th width="130">작성일</th>
						<th width="50">조회수</th>
					</tr>
					
					
					
					<c:choose>
						<c:when test="${reviewListSize<=0}">
							<tr>
								<td width="100%" colspan="7" align="center" height="23">
									등록된 리뷰가 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${reviewList}" var="RealReviewVO">
								<tr>
								
									<!-- 리턴,반환값 -->
									<td height="23" align="center">${RealReviewVO.rseq}
						<a href="review_list${pageMaker.makeQuery(pageMaker.criteria.pageNum)}&rseq=${RealReviewVO.rseq}"></a></td>
									     
									<td><a href="${path}review_detail?rseq=${RealReviewVO.rseq}">${RealReviewVO.title}</a></td>
									
									
									<td>${RealReviewVO.id}</td>
									<td>${RealReviewVO.campingname}</td>
									
									<td><fmt:formatDate value="${RealReviewVO.indate}" type="date" />
									</td>
									
									
									
									<td>${RealReviewVO.count}</td>
								</tr>
							</c:forEach>
							<tr><td colspan="6" style="text-align: center;"> ${paging} </td></tr>
						</c:otherwise>
					</c:choose>
				</table>																	
				<input class="btn" type="button" name="btn_input" value="리얼 리뷰작성" onClick="location.href='review_write'">
			</form>  
			<%@ include file="reviewpage_area.jsp"%>
		</article>
	</div>
</body>
<%@ include file="../footer.jsp" %>
</html>