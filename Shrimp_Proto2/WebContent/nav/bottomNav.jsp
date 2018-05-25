    <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.util.Enumeration" %>
<!-- 하단 네비게이션 -->
<!-- ver.2 아이콘으로 변경 -->

    <nav class="navbar navbar-expand-md fixed-bottom bg-dark">
      <div class="container p-0" style="text-overflow : clip; overflow : hidden; white-space : nowrap;">
        <div class="m-auto">
          <!-- 글쓰기 : 로그인 되있는 경우 -->
          <c:if test="${sessionScope.userInfo.email ne null}">
          <a class="btn navbar-btn btn-primary" href="#writePostModal" data-toggle="modal">
            <span class="far fa-edit fa-fw mx-auto mx-sm-2 mx-lg-4"></span>
          </a></c:if>
          <!-- 글쓰기 : 로그인이 되어있지 않은 경우 -->
		  <c:if test="${empty sessionScope.userInfo.email}">
		  <a class="btn navbar-btn btn-primary" href="#loginModal" data-toggle="modal">
            <span class="far fa-edit fa-fw mx-auto mx-sm-2 mx-lg-4"></span>	
          </a></c:if>
          <a class="btn navbar-btn btn-primary" href="#searchModal" data-toggle="modal">
            <span class="fas fa-search fa-fw mx-auto mx-sm-2 mx-lg-4"></span>
          </a>
          <!--  로그인 되있는 경우 -->
          <c:if test="${sessionScope.userInfo.email ne null}">
          <a class="btn navbar-btn btn-primary" href="${pageContext.request.contextPath}/mypage.do">
            <span class="far fa-user fa-fw mx-auto mx-sm-2 mx-lg-4"></span>
          </a></c:if>
          <!--  로그인이 되어있지 않은 경우 -->
		  <c:if test="${empty sessionScope.userInfo.email}">
          <a class="btn navbar-btn btn-primary" href="#loginModal" data-toggle="modal" id="longinbtn">
            <span class="far fa-user fa-fw mx-auto mx-sm-2 mx-lg-4"></span>
          </a></c:if>
          <!--  로그인 되있는 경우 -->
          <c:if test="${sessionScope.userInfo.email ne null}">
          <a class="btn navbar-btn btn-primary" href="${pageContext.request.contextPath}/message/inbox.do">
            <span class="far fa-comments fa-fw mx-auto mx-sm-2 mx-lg-4"></span>
          </a></c:if>
          <!--  로그인이 되어있지 않은 경우 -->
	      <c:if test="${empty sessionScope.userInfo.email}">
		  <a class="btn navbar-btn btn-primary" href="#loginModal" data-toggle="modal">
            <span class="far fa-comments fa-fw mx-auto mx-sm-2 mx-lg-4"></span>
          </a></c:if>
        </div>
      </div>
    </nav>


  <!-- Login Modal -->
  <%
  String path = request.getRequestURI(); //기본 경로
  String forwardPath = (String)request.getAttribute("javax.servlet.forward.request_uri");
  String queryString = request.getQueryString();
  String url;
  if(forwardPath == null){
	  url = path;
  }else{
	  if(queryString == null){
		  url = forwardPath;
	  }else{
		  url = forwardPath+"?"+queryString;
	  }
  }
  String subUrl = url.substring(14);
  
  
  
  %>
  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; text-align: center">
    <div class="modal-dialog col-md-6 mt-5">
      <div class="modal-content">
        <div class="modal-body px-3 pt-3">
          <h2><b>새우젓 클럽 입장</b></h2>
          <div class="social-buttons">
            <a href="#" class="btn btn-goo px-1"><i class="fab fa-google"></i> Google</a>
            <a href="#" class="btn btn-tw px-1"><i class="fab fa-twitter"></i> Twitter</a>
          </div>
          or
          <form class="form" role="form" method="post" action="login.do" accept-charset="UTF-8" id="login-nav" name="loginform">
          <input type="hidden" id="LoginUri" name="LoginUri" value = "<%=subUrl %>" />  <!-- request.getRequestURI().substring(15)  -->
            <div class="form-group my-1">
              <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Email address" name="email" required>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password" name="passwd" required>

            </div>
            <div class="help-block text-right my-1"><a href="">Forget the password ?</a></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-block">Sign in</button>
            </div>
            <div class="checkbox text-right">
               <input type="checkbox"> keep me logged-in

            </div>
          </form>
          <div class="bottom text-center">
            New here ? <a href="${pageContext.request.contextPath}/joinForm.jsp"><b>Join Us</b></a>
         <%--  <h4><%=request.getAttribute("javax.servlet.forward.request_uri") %> </h4>
          <h4><%=request.getQueryString()%> </h4>
          <h4><%=request.getRequestURI() %></h4>
          <h4><%=subUrl %></h4> --%>
          </div>
        </div>
        <div class="modal-footer p-1">
          <a class="text-primary font-weight-bold card-link mr-2" data-dismiss="modal" href>x close</a>
        </div>
      </div>
    </div>
  </div>

  
  
  <!-- writePostModal ver.2 -->
<div class="modal fade" id="writePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog maodal-lg fixed-bottom justify-content-center my-0">
    <div class="modal-body px-0 pb-0">
      <div class="container mx-auto">
        <div class="row">
          <div class="col-3 px-0 pr-1">
            <a href="${pageContext.request.contextPath}/writePostLink.jsp" class="btn btn-lg btn-light btn-block text-primary font-weight-bold">
              <i class="fas fa-link fa-fw"></i>
            </a>
          </div>
          <div class="col-3 px-0 pr-1">
            <a href="${pageContext.request.contextPath}/writePostImage.jsp" class="btn btn-lg btn-light btn-block text-primary font-weight-bold px-1">
              <i class="far fa-image fa-fw"></i>
            </a>
          </div>
          <div class="col-3 px-0 pr-1">
            <a href="${pageContext.request.contextPath}/writePostVideo.jsp" class="btn btn-lg btn-light btn-block text-primary font-weight-bold px-1">
              <i class="fas fa-film fa-fw"></i>
            </a>
          </div>
          <div class="col-3 px-0">
            <a href="${pageContext.request.contextPath}/writePostText.jsp" class="btn btn-lg btn-light btn-block text-primary font-weight-bold">
              <i class="far fa-file-alt fa-fw"></i>
            </a>
          </div>
        </div>
      </div>
    </div>
    <div class="modal-body text-center pb-2">
    <a class="text-white" data-dismiss="modal" href><i class="far fa-times-circle fa-3x"></i></a>
    </div>
    </div>
  </div>



  <!-- `chModal -->
  <form action="mainsearch.do">
<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg fixed-bottom my-0">
      <div class="modal-body pb-0 px-0">
        <div class="container">
          <div class="row">
            <div class="col-9"> <input name="keyword" class="form-control form-control-lg" type="text" placeholder="Search" ></div>
            <div class="col-3 pl-0"><button class="btn btn-lg btn-primary btn-block px-1" type="submit" >Search</button></div>
          </div>
        </div>
      </div>
      <div class="modal-body text-center pb-2">
        <a class="text-white" data-dismiss="modal" href><i class="far fa-times-circle fa-3x"></i></a>
      </div>
    </div>
  </div>
  </form>

  <!-- InboxModal -->
  <!-- DELETE : LSM -->
  <!--<div class="modal hide" id="inboxModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
    <div class="modal-body fixed-bottom mb-5">
      <div class="container">
        <div class="row">
          <div class="col-3 btn btn-primary"><a href="${pageContext.request.contextPath}/message/write.do" class="text-white">메세지쓰기</a></div>
          <div class="col-3 btn btn-primary"><a href="${pageContext.request.contextPath}/message/recMessage.do" class="text-white">받은메세지함</a></div>
          <div class="col-3 btn btn-primary"><a href="${pageContext.request.contextPath}/message/senMessage.do" class="text-white">보낸메세지함</a></div>
          <div class="col-3 btn btn-primary"><a href="${pageContext.request.contextPath}/writeMesToAdmin.html" class="text-white">관리자에게</a></div>
        </div>
      </div>
    </div>
    <div class="modal-footer fixed-bottom justify-content-center bg-dark"><button type="button" class="close list-group-item-primary" data-dismiss="modal" aria-hidden="true">×</button>
    </div>
  </div>-->
  
<!--     <script>
  $('#loginModal').on('shown.bs.modal', function () {
	  $('#exampleInputEmail2').focus()
	})
  </script> -->
