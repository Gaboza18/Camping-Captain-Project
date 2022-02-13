<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">
  <title>캠핑족장</title>
  <link rel="stylesheet" href="css/shopping.css" >  <!-- 이걸해줘야  로고 생기면서 화면 놔눠짐-->
  <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script> 
  <script type="text/javascript" src="member/member.js"></script> <!-- 왜 memberjs로 해야 잘뜨지?? -->
   <script type="text/javascript" src="member/findIdAndPassword.js"></script> 
   <script type="text/javascript" src="./js/main.js?ver=123"></script> <!--  내가 인터넷 찾아보고 추가한것 -->
  <!-- 
  <script type="text/javascript" src="product/product.js"></script>
 
  <script type="text/javascript" src="mypage/mypage.js"></script> -->
</head>

<body>
<div id="wrap">
<!--헤더파일 들어가는 곳 시작 -->
  <header>
    <!--로고 들어가는 곳 시작--->  
    <div id="logo">
      <a href="index">
        <img src="images/logo.gif	" width="180" height="100" alt="nonageshop">
      </a>  
    </div>
    <!--로고 들어가는 곳 끝-->     
    <nav id="catagory_menu">
     <ul>
       <c:choose>
       <c:when test="${empty sessionScope.loginUser}">
       <li>         
         <a href="login_form" style="width:110px;">LOGIN(CUSTOMER</a>   
	     <a href="admin_login_form" style="width:100px;">| ADMIN)</a>
	   </li>		       
       <li>/</li>
       <li><a href="contract">JOIN</a></li>
       </c:when>
       <c:otherwise>
       <li style="color:orange">
         ${sessionScope.loginUser.name}(${sessionScope.loginUser.id})
       </li>
       <li><a href="logout">LOGOUT</a></li>
       </c:otherwise>       
       </c:choose>
       <li>/</li>
       <li>
         <a href="cart_list">CART</a>
       </li><li>/</li>
       <li>
         <a href="mypage">MY PAGE</a>
       </li><li>/</li>
       <li>
         <a href="qna_list">Q&amp;A(1:1)</a>
       </li>
     </ul>
    </nav>

    <nav id="top_menu">
      <ul>
        <li>
          <a href="category?kind=1">Heels</a>
        </li>  
        <li>
          <a href="category?kind=2">Boots</a>
        </li>  
        <li>
          <a href="category?kind=3">Sandals</a>
        </li> 
        <li>
          <a href="category?kind=4">Sneakers</a>
        </li> 
        <li>
          <a href="category?kind=5">On Sale</a>
        </li>  
      </ul>
    </nav>
    <div class="clear"></div>
    <hr>
  </header>
  <!--헤더파일 들어가는 곳 끝 -->