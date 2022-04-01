<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 취소</title>

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
	div#input_blackList_form h2 {
		margin: 2% auto;
		color: rgb(255, 200, 0);
	}
	
	div#input_blackList_form form#black_reason input {
		border-radius: 10px;
		margin-bottom: 20px;
	}
	
	div#input_blackList_form form#black_reason input[name=id] {
		margin-right: 10px;
	}
	
	div#input_blackList_form form#black_reason textarea {
		border-radius: 10px;
		margin-top: 10px;
		resize: none;
		padding: 5px;
	}
	
	div#input_blackList_form div#buttons input {
		background-color: #212529;
		color: white;
		margin-top: 30px;
		border-radius: 10px;
	}
</style>
</head>
<body>
	
	<div id="input_blackList_form" align="center">
		<article>
		    <h2> 블랙리스트 사유 작성하기 </h2>
		      
			<form name="formm" id="black_reason" method="post" action="insertblacklist">
				<label>아이디</label>
			    <input name="id" value="${blackid}"  size="10" readonly>
			    <label>회원번호</label> 
			    <input name="useq" id="useq" value="${blackuseq}"  size="10" readonly><br>
			 
			 	<input type="text" class="class" name="id" id="id" value="${UsersVO.id}" style="display:none">
				<label>블랙리스트 사유</label>
			    <textarea rows="8" cols="65" name="blackreason" id="blackreason"></textarea><br>
			
				 <div id="buttons">
					<input type="button" value="등록" onclick="reason_chk()">
				 </div>
			</form>
		</article>
	</div>
	
</body>
</html>