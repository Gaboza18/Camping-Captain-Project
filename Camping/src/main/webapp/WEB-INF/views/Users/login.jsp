<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="../header.jsp"%>

    <section class="page-section" id="login">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Login</h2>
                <h3 class="section-subheading text-muted"></h3>
            </div>

            <form id="loginForm" action="login_action" method="post">
                <div class="row align-items-stretch mb-5">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" id="id" name="id" type="text" placeholder="아이디" data-sb-validations="required" />
                            <div class="invalid-feedback" data-sb-feedback="id:required">A name is required.</div>
                        </div>
                        <div class="form-group">
                            <input class="form-control" id="password" name="password" type="password" placeholder="비밀번호" data-sb-validations="required" />
                            <div class="invalid-feedback" data-sb-feedback="password:required">An password is required.</div>
                        </div>
                    </div>
                </div>
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