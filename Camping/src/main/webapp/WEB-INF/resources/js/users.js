/**
 * 회원 관련 JS 함수
 */

/*
 * 1. 약관 동의 여부 확인
 */

function go_next() {

   if ($(".agree")[0].checked == true) { // 클래스 agree가 배열 0번쨰 항목이 동의함 체크되어
                                 // 있을떄
      $("#join").attr('action', 'join_form').submit(); // 회원가입 페이지로 이동
   } else if ($(".agree")[1].checked == true) { // 배열 1번쨰 항목이 체크 되어 있을때
      alert("약관에 동의해 주세요");
   }

}

/*
 * id 중복확인 화면 출력
 */

function idcheck() {

   if ($("#id").val() == "") { // id 입력하지 않았을떄
      alert("아이디를 입력하세요");
      $("#id").focus();
      return false;
   }

   // id가 입력이 되었으면 id 중복확인 윈도우 창 오픈(윈도우창 크기 및 사이즈 변경 여부)
   var url = "id_check_form?id=" + $("#id").val();
   window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=300");
}


/*
 *  emailcheck 화면출력
 */

function emailcheck() {

	   if ($("#email").val() == "") { // id 입력하지 않았을떄
	      alert("이메일을 입력해주세요");
	      $("#email").focus();
	      return false;
	   }

	   // id가 입력이 되었으면 id 중복확인 윈도우 창 오픈(윈도우창 크기 및 사이즈 변경 여부)
	   var url = "signUpConfirm?email=" + $("#email").val()+"&id="+$("#id").val();
	   window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=350, height=200");
	}

// 수정화면 이메일 보내는 js
function emailcheck2() {

	   if ($("#email").val() == "") { // id 입력하지 않았을떄
	      alert("이메일을 입력해주세요");
	      $("#email").focus();
	      return false;
	   }

	   // id가 입력이 되었으면 id 중복확인 윈도우 창 오픈(윈도우창 크기 및 사이즈 변경 여부)
	   var url = "signUpConfirm?email=" + $("#email").val()+"&id="+$("#id").val();
	   window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=350, height=200");
	}




/*
 *  중복확인 ID 사용
 */

function idok(){
   $("#theform").attr("action", "id_check_confirmed").submit();
}

/*
 * 회원가입시, 필수입력 항목 확인
 */

function go_save1() {

   if ($("#id").val() == "") {
      alert("아이디를 입력 하세요");
      $("#id").focus();
      return false;
   } else if ($("#id").val() != $("#reid").val()) {
      alert("아이디 중복 체크를 하세요");
      $("#id").focus();
      return false;
   } else if ($("#password").val() == "") {
      alert("비밀번호를 입력 하세요");
      $("#password").focus();
      return false;
   } else if ($("#password").val() != $("#passwordCheck").val()) {
      alert("비밀번호가 일치하지 않습니다");
      $("#password").focus();
      return false;
   } else if ($("#name").val() == "") {
      alert("이름을 입력해 주세요");
      $("#name").focus();
      return false;
   } else {
      $("#join_Form").attr("action", "join").submit(); // 1단계 회원가입 요청
   }

}

function go_save2() {

	   if ($("#email").val() == "") {
	      alert("사용하시는 이메일을 인증하지 않으면 캠핑장 예약과 비밀번호를 찾을 수 없습니다.");
	      $("#email").focus();
	      alert("원하지 않는경우 간편회원가입을 눌러주세요");
	     return false;
	     } else if ($("#status").val()=="n") {
		   alert("아직 회원인증이 완료되지 않았습니다");
		   $("#email").focus();
		   return false;
		   
	   } else {
		   alert("축하합니다! 이메일이 인증되었습니다. 다시 로그인을 해주세요. 감사합니다");
		   		$("#join2").attr("action","finaljoin").submit();
	    	  }
	      }


function go_save3() {
	alert("간편 회원가입 상태입니다. 다시 로그인을 해주세요 감사합니다.");
	$("#join2").attr("action","finaljoin").submit();

}


function go_out() {
	if($("#passwordCheck").val() == ""){
		alert("비밀번호를 입력해주세요");
	    $("#password").focus();
	    return false;
	} else if ($("#password").val() != $("#passwordCheck").val()) {
		alert("비밀번호가 일치하지 않습니다");
	    $("#password").focus();
	    return false;
	} 
	
	if($("#OrderCount").val() != 0) {
		alert("진행 중인 예약 내역이 있어 탈퇴하실 수 없습니다.");
	    return false;
	} else if($("#CancelCount").val() != 0) {
		alert("대기 중인 취소 내역이 있어 탈퇴하실 수 없습니다.");
	    return false;
	} else {
		alert("성공적으로 탈퇴되었습니다.");
	    $("#usersDelete").attr("action", "usersDelete").submit(); // 회원가입 요청
	} // 여기에는 틀린 비밀번호로 중복확인하고 탈퇴할때 알람 뜨게 해야됨		 
	
	return true;
}
