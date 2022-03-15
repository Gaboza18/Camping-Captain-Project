<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp"%>
    <!-- 
<article id="login_form">
	<form id="loginForm" method="post" action="login">
		<h3>로그인</h3>
        <label>아이디</label>
        <input name="id" type="text" value="${id}"><br><br> 
        <label>비밀번호</label> 
        <input name="password" type="password"><br><br>
        
		<div class="clear"></div>
		
        <div id="loginbtn">
            <input type="submit" value="로그인" class="submit">
            <input type="button" value="회원가입" class="cancel"
                 onclick="location='contract'">
            <input type="button" value="아이디 찾기" class="submit"
                 onclick="location='find_id'">
            <input type="button" value="비밀번호 찾기" class="submit"
                 onclick="location='find_pwd'">                                                    
        </div> 
    </form>  
</article>

 --> 

    <section class="page-section" id="login">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Login</h2>
                <h3 class="section-subheading text-muted"></h3>
            </div>
            <!-- * * * * * * * * * * * * * * *-->
            <!-- * * SB Forms Contact Form * *-->
            <!-- * * * * * * * * * * * * * * *-->
            <!-- This form is pre-integrated with SB Forms.-->
            <!-- To make this form functional, sign up at-->
            <!-- https://startbootstrap.com/solution/contact-forms-->
            <!-- to get an API token!-->
            <form id="loginForm" action="login_action" method="post">
                <div class="row align-items-stretch mb-5">
                    <div class="col-md-6">
                        <div class="form-group">
                            <!-- Name input-->
                            <input class="form-control" id="id" name="id" type="text" placeholder="아이디" data-sb-validations="required" />
                            <div class="invalid-feedback" data-sb-feedback="id:required">A name is required.</div>
                        </div>
                        <div class="form-group">
                            <!-- Email address input-->
                            <input class="form-control" id="password" name="password" type="password" placeholder="비밀번호" data-sb-validations="required" />
                            <div class="invalid-feedback" data-sb-feedback="password:required">An password is required.</div>
                        </div>
                    </div>
                </div>
                <!-- Submit success message-->
                <div class="text-center">
                	<input class="btn btn-primary btn-xl text-uppercase" id="submitButton" type="submit" value="로그인" >
                	<input class="btn btn-primary btn-xl text-uppercase" type="button" value="회원가입" onclick="location='contract'">
                	<input class="btn btn-primary btn-xl text-uppercase" type="button" value="아이디 찾기" onclick="location='find_id'">
                	<input class="btn btn-primary btn-xl text-uppercase" type="button" value="비밀번호 찾기" onclick="location='find_pwd'">
                </div>
            </form>
        </div>
    </section>





<%@ include file="../footer.jsp" %>  
   
