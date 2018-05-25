<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
 <%@ include file="/nav/head.jsp" %>
</head>

<body>
  
<!-- 상단네비게이션 -->
	<%@include file="/nav/topNav.jsp"%>
  
  
  

  <!-- title -->
  <div class="pt-5">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h4 class="display-4">My Page</h4>
          <hr>
        </div>
      </div>
    </div>
  </div>
  
  
  
  
  <!-- 내 정보 페이지 -->
  <div class="py-3">
    <div class="container">
      <div class="row">
      <div class="col-md-10 mx-auto">
        <div class="form-control-sm bg-light p-3">
        <form class="" method="post" action="">
          <!-- 이메일 정보 ${sessionScope.userInfo.email}-->
          <div class="form-group mb-2">
            <label class="form-control-label" for="inputEmail">이메일</label>
              <input class="form-control form-control-plaintext" id="inputEmail" type="email" placeholder="${sessionScope.userInfo.email }" disabled>
          </div>
          <!-- 닉네임 정보 {sessionScope.userInfo.nickname}-->
          <div class="form-group mb-2">
            <label class="form-control-label" for="inputEmail">닉네임</label>
              <input class="form-control form-control-plaintext" id="inputEmail" type="email" placeholder="${sessionScope.userInfo.nickname}" disabled>
          </div>
          <!-- 핸드폰 번호 정보 ${sessionScope.userInfo.mobile} -->
          <div class="form-group mb-2">
            <label class="form-control-label" for="inputEmail">핸드폰 번호</label>
            <input class="form-control form-control-plaintext" id="inputEmail" type="tel" placeholder="${sessionScope.userInfo.mobile }" disabled>
          </div>
          <!-- article count -->
          <div class="form-group mb-2">
            <label class="form-control-label" for="inputEmail">post count</label>
            <input class="form-control form-control-plaintext" id="inputEmail" type="email" placeholder="100" disabled>
          </div>
          <!-- reply count -->
          <div class="form-group mb-2">
            <label class="form-control-label" for="inputEmail">reply count</label>
            <input class="form-control form-control-plaintext" id="inputEmail" type="email" placeholder="77" disabled>
          </div>
          <!-- vote count -->
          <div class="form-group mb-2">
            <label class="form-control-label" for="inputEmail">vote count</label>
            <input class="form-control form-control-plaintext" id="inputEmail" type="email" placeholder="999" disabled>
          </div>
        </form>
        </div>
        <div class="btn-group-lg pt-3">
          <div class="text-center">
            <a class="btn btn-primary" href="myShrimp.html">My Shrimp <i class="fa fa-check spaceLeft"></i></a>
            <button class="btn btn-danger" href="index3.html">수 정 <i class="fa fa-times spaceLeft"></i></button>
            <a class="btn btn-primary" href="logout.do">LOGOUT <i class="fa fa-check spaceLeft"></i></a>
          </div>
        </div>
      </div>
      </div>
    </div>
  </div>
  



  <!-- 내 정보 페이지 -->
  <%-- <div class="my-3">
    <div class="container">
      <div class="col-md-12">
        <form class="bg-light py-3" method="post" action="">

          <!-- 이메일 정보 ${sessionScope.userInfo.email}-->
          <div class="form-group my-2">
            <label class="col-sm-3 form-control-label" for="inputEmail">이메일</label>
            <div class="col-sm-6">
              <input class="form-control" id="inputEmail" type="email" placeholder="${sessionScope.userInfo.email }" readonly="true">
            </div>
          </div>
          <!-- 닉네임 정보 {sessionScope.userInfo.nickname}-->
          <div class="form-group my-2">
            <label class="col-sm-3 form-control-label" for="inputEmail">닉네임</label>
            <div class="col-sm-6">
              <input class="form-control" id="inputEmail" type="email" placeholder="${sessionScope.userInfo.nickname}" disabled="true">
            </div>
          </div>
          <!-- 핸드폰 번호 정보 ${sessionScope.userInfo.mobile} -->
          <div class="form-group my-2">
            <label class="col-sm-3 form-control-label" for="inputEmail">핸드폰 번호</label>
            <div class="col-sm-6">
              <input class="form-control" id="inputEmail" type="tel" placeholder="${sessionScope.userInfo.mobile }" disabled="true">
            </div>
          </div>
          <!-- article count -->
          <div class="form-group my-2">
            <label class="col-sm-3 form-control-label" for="inputEmail">post count</label>
            <div class="col-sm-6">
              <input class="form-control" id="inputEmail" type="email" placeholder="100" disabled="true">
            </div>
          </div>
          <!-- reply count -->
          <div class="form-group my-2">
            <label class="col-sm-3 form-control-label" for="inputEmail">reply count</label>
            <div class="col-sm-6">
              <input class="form-control" id="inputEmail" type="email" placeholder="77" disabled="true">
            </div>
          </div>
          <!-- vote count -->
          <div class="form-group my-2">
            <label class="col-sm-3 form-control-label" for="inputEmail">vote count</label>
            <div class="col-sm-6">
              <input class="form-control" id="inputEmail" type="email" placeholder="999" disabled="true">
            </div>
          </div>




          <div class="btn-group-lg">
            <div class="col-sm-12 text-center">
              <a class="btn btn-primary" href="myShrimp.html">My Shrimp <i class="fa fa-check spaceLeft"></i></a>
              <button class="btn btn-danger" href="index3.html">수 정 <i class="fa fa-times spaceLeft"></i></button>
              <a class="btn btn-primary" href="logout.do">LOGOUT <i class="fa fa-check spaceLeft"></i></a>   <!-- href="logout.do" -->
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

 --%>

  <!-- 하단네비게이션 -->
	<%@include file="/nav/bottomNav.jsp"%>
  







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
    