<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp" %>    

<section class="page-section" id="join_us">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">JOIN US</h2>
            <h3 class="section-subheading text-muted"></h3>
        </div>

        <form id="join_Form" action="join" method="post" name="formm">
            <div class="row align-items-stretch mb-5">
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" id="id" name="id" type="text" placeholder="User ID *" value="${id}" data-sb-validations="required" />
			        	<input type="hidden" name="reid" id="reid" value="${reid}">
			        	<input class="btn btn-primary btn-xl text-uppercase" type="button" id="button" value="중복 체크" onclick="idcheck()">
                        <div class="invalid-feedback" data-sb-feedback="id:required">아이디는 필수 입력사항 입니다.</div>
                    </div>
                    
                    <div class="form-group">
                        <input class="form-control" id="password" type="password" name="password" placeholder="Password *" data-sb-validations="required" />
                        <div class="invalid-feedback" data-sb-feedback="password:required">비밀번호는 필수 입력사항 입니다.</div>
                    </div>
                    
                    <div class="form-group">
                        <input class="form-control" id="passwordCheck" type="password" name="passwordCheck" placeholder="Retype Password *" data-sb-validations="required" />
                        <div class="invalid-feedback" data-sb-feedback="passwordCheck:required">비밀번호 확인은 필수 입니다.</div>
                    </div>
                    
                    <div class="form-group">
                        <input class="form-control" id="name" type="text" name="name" placeholder="Name *" data-sb-validations="required" />
                        <div class="invalid-feedback" data-sb-feedback="name:required">회원 이름은 필수 입력사항 입니다.</div>
                    </div>
                    
                    <div class="form-group">
                        <input class="form-control" type="text" id="birth" name="birth" placeholder="BirthDay *" maxlength="6" data-sb-validations="required"><span style="color: white;">-</span>
			        	<input class="form-control" type="text" id="birth_gen" name="birth_gen" maxlength="1" data-sb-validations="required"><span style="color: white;">●●●●●●</span>
                        <div class="invalid-feedback" data-sb-feedback="birth:required">생년월일을 입력해주세요</div>
                        <div class="invalid-feedback" data-sb-feedback="birth_gen:required">주민번호 뒷자리 맨 앞글자를 입력해주세요</div>
                    </div>
                    
                    
                    <div class="form-group">
                        <input class="form-control" id="phone" type="text" name="phone" placeholder="Phone Number *" data-sb-validations="required" />
                        <div class="invalid-feedback" data-sb-feedback="phone:required">전화번호를 입력해주세요.</div>
                    </div>
                </div>
            </div>
            <!-- Submit Button-->
            <div class="text-center">
            	<input class="btn btn-primary btn-xl text-uppercase" type="button" value="회원가입" onclick="go_save1()">
	        	<input class="btn btn-primary btn-xl text-uppercase" type="reset" value="취소" onclick="location='index'">
            </div>
        </form>
    </div>
</section>

<%@ include file="../footer.jsp" %>

  