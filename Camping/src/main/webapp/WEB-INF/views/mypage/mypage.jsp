<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  

     
     <div id="logo">
				<a href="index">
					<img alt="camping" src="images/CampingCaptain.jpg" width="250" height="100">
				</a>
			</div>
     
  <article>
    <h1>마이페이지</h1>
    <form method="post" action="mypage">
        <fieldset>
        <legend></legend>
      
          <div>
         ${sessionScope.loginUser.name}님 환영합니다!
          
          <label>회원정보수정</label>
           <li>
			<a href="usermodify"  style="width:110px;">회원정보수정</a>
							</li>
							<li>
			<a href="myreview"  style="width:110px;">나의 후기보기</a>
							</li>
                
                 
            
                     
        </div>
         </fieldset>
    </form>  
  </article>
   
