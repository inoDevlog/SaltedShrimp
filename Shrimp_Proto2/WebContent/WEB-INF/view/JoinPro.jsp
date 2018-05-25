<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <%@ include file="/nav/head.jsp" %>
</head>

<body>
  <!-- 상단네비게이션 -->  
  <%@include file="/nav/topNav.jsp" %>

  <!-- User Info section -->
  <div class="mt-5 bg-dark" id="book" style="background-image: url(&quot;resources/img/cover_dark.jpg&quot;); position: relative; background-size: cover; background-position: center;">
    <div class="container">
      <div class="row">
        <div class="section-light col-lg-6 mx-auto my-5">
          <div class="p-4 bg-light">
            <h1 class="mb-4 text-center">WELCOME :) </h1>
            <p class="my-4">새우젓 클럽에 입장 하신 것을 환영합니다. 하고 싶은 이야기, 나누고 싶은 직캠들! 새우젓님들과 같이 즐기세요. 환영합니다!!</p>
            <div class="form-group text-center">
              <p class="m-0">Email</p>
              <h4>${userInfo.email}</h4>  <!-- ${userInfo.email} -->
            </div>
            <div class="form-group text-center">
              <p class="m-0">Nickname</p> <!-- ${userInfo.nickname} -->
              <h4>${userInfo.nickname}</h4>
            </div>
            <div class="form-group text-center">
              <p class="m-0">Phone Number</p>
              <h4>${userInfo.mobile}</h4> <!-- ${userInfo.mobile} -->
            </div>
            <a class="btn mt-4 btn-block p-2 btn-primary" href="mainBoard.do"><b>새우젓 클럽 입장</b></a>
          </div>
        </div>
      </div>
    </div>
  </div>





  <!-- 하단네비게이션 -->  
  <%@include file="/nav/bottomNav.jsp" %>
  






  <!-- Footer -->
  <div class="py-3 bg-dark">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <p class="lead">Sign up to our newsletter for the latest news</p>
          <form class="form-inline">
            <div class="form-group">
              <input type="email" class="form-control" placeholder="Your e-mail here"> </div>
            <button type="submit" class="btn ml-2 btn-secondary">Subscribe</button>
          </form>
        </div>
        <div class="row">
          <div class="col-md-12 my-3 text-center">
            <p class="text-muted">© Copyright 2017 Pingendo - All rights reserved.</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- JavaScript dependencies -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>


</body>

</html>
	