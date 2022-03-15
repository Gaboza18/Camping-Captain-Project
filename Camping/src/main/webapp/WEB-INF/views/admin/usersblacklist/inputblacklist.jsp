<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 취소</title>
<!-- <script type="text/javascript" src="js/admin.js"></script> -->
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function reason_chk() {
	$.ajax({
		type: "GET",
		url: "changestatus?useq="+$("#useq").val(),
		dataType: "html",
		contentType:  "application/json; charset=UTF-8",
		data: $("#black_reason").serialize()
	}).done(function(){
		alert("사유가 등록되었습니다.");
		opener.location.href="users_list";
		self.close();
	}).fail(function(error){
		alert("에러");
	});
}



</script>
<style>
	table th {
		width: 150px;
		text-align: left;
	}
	
	#button {
		margin-top: 30px;
	}
</style>
</head>
<body>
	

	<article>
	    <h2> 블랙리스트 사유 작성하기 </h2>
	      
		<form name="formm" id="black_reason" method="post" action="insertblacklist">
			<fieldset>	
				<legend>Board Info</legend>		
			     <input name="id" value="${blackid}"  size="10" readonly>
			     <input name="useq" id="useq" value="${blackuseq}"  size="10" readonly>
			 
			 <input type="text" class="class" name="id" id="id" value="${UsersVO.id}" style="display:none">
				<label>블랙리스트 사유</label>
			    <textarea rows="8" cols="65" name="blackreason" id="blackreason"></textarea><br>
			</fieldset>   
		<div class="clear"></div>
		 <div id="buttons" style="float:right">
			<!-- <button type="button" value="${UsersVO.useq}" id="changebutton"
			onclick="javascript:location.href=('changestatus?useq=${UsersVO.useq}&id=${UsersVO.id}')">등록완료</button> --> 
			<input type="button" value="등록" onclick="reason_chk()">
				
		  </div>
		</form>
	</article>
</body>
</html>