<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- PAGE settings -->
  <link rel="icon" href="https://templates.pingendo.com/assets/Pingendo_favicon.ico">
  <title>새우젓 클럽</title>
  <meta name="description" content="Free Bootstrap 4 Pingendo Flat template made for app and softwares.">
  <meta name="keywords" content="Pingendo app flat free template bootstrap 4">
  <!-- CSS dependencies -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="resources/css/theme.css">
  <link rel="stylesheet" href="resources/css/logindropdown.css">
  <!-- font awesome(한글) -->
  <link rel="stylesheet" href="resources/css/font-awesome.min.css" media="screen" title="no title" charset="utf-8">
  <!-- Script: Make my navbar transparent when the document is scrolled to top -->
   <script src="resources/js/navbar-ontop.js"></script>
  <!-- Script: Animated entrance -->
  <script src="resources/js/animate-in.js"></script>
</head>

<body>
  <!-- restraurant.html Navbar -->
  <div id="shrimpNav">
  <nav class=" nav navbar fixed-top bg-white">  <!--navbar-expand-md  -->
    <div class="container justify-content-center">
      <div class="navbar-nav text-center ">  <!-- collapse  navbar-collapse    -->
        <ul class="nav">
          <li class="nav-item mx-2">
            <a class="nav-link" href="index3.html"><b>MAIN</b></a>
          </li>
          <li class="nav-item mx-2">
            <a class="nav-link" href="getBoardList.do"><b>DUKJIL</b></a>
          </li>
          <li class="nav-item mx-2">
            <a class="nav-link" href="#"><b>YOUTUBE</b></a>
          </li>
          <li class="nav-item mx-2">
            <a class="nav-link" href="#"><b>TWITTER</b></a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>










  <!-- 인기순/최신순 탭 버튼 -->
  <div class="pt-5">
    <div class="container">
      <ul class="nav nav-tabs justify-content-center">
        <div class="row ">
          <div class="col-6">
            <li class="nav-item">
              <a href="#pop" class="nav-link active" aria-controls="home" aria-selected="true" data-toggle="tab" id="pop-tab" role="tab">Popular</a>
            </li>
          </div>
          <div class="col-6">
            <li class="nav-item">
              <a class="nav-link" href="#lat" aria-controls="profile" aria-selected="false" data-toggle="tab" id="lat-tab" role="tab">Latest</a>
            </li>
          </div>
        </div>
      </ul>
      <div class="row">
        <div class="col-12">
          <div class="tab-content my-2" id="myTabContent">
            <!-- 인기순 탭 -->
            <div class="tab-pane fade show active" id="pop" role="tabpanel" aria-labelledby="pop-tab">
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
              <!-- 인기순 탭 div -->
              <div class="py-4">
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="card my-4">
                        <div class="card-header bg-primary">
                          <h5 class="mb-0 text-center">인기순</h5>
                        </div>
                        <div class="card-body">
                          <p class="card-text">제목 : 예시 제목입니다.</p>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card&apos;s content. dfser sgfgs ghdfg r favicon</p>
                        </div>
                        <div class="card-footer">댓글 : </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--End 인기순 탭-->
            <!-- 최신순 탭 -->
            <div class="tab-pane fade show" id="lat" role="tabpanel" aria-labelledby="lat-tab">
              <p>Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
              <!-- 최신순 탭 div -->
              <div class="py-4">
                <div class="container">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="card my-4">
                        <div class="card-header bg-primary">
                          <h5 class="mb-0 text-center">최신순</h5>
                        </div>
                        <div class="card-body">
                          <p class="card-text">제목 : 예시 제목입니다.</p>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card&apos;s content. dfser sgfgs ghdfg r favicon</p>
                        </div>
                        <div class="card-footer">댓글 : </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!--End 최신순 탭 div-->
            </div>
            <!--End 최신순 탭-->
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- 자리차지용코드(추후 삭제) -->
  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="card my-4">
            <div class="card-header bg-primary">
              <h5 class="mb-0 text-center">WELCOME ${userInfo.nickname} </h5>
            </div>
            <div class="card-body">
              <p class="card-text">제목 : 예시 제목입니다.</p>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card&apos;s content. dfser sgfgs ghdfg r favicon</p>

            </div>
            <div class="card-footer">댓글 : </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>

  <!-- snap -->
  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="card my-4">
            <div class="card-header bg-primary">
              <h5 class="mb-0 text-center">A Card Header</h5>
            </div>
            <div class="card-body">
              <p class="card-text">제목 : 예시 제목입니다.</p>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card&apos;s content. dfser sgfgs ghdfg r favicon</p>

            </div>
            <div class="card-footer">댓글 : </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>

  <!-- snap -->
  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="card my-4">
            <div class="card-header bg-primary">
              <h5 class="mb-0 text-center">A Card Header</h5>
            </div>
            <div class="card-body">
              <p class="card-text">제목 : 예시 제목입니다.</p>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card&apos;s content. dfser sgfgs ghdfg r favicon</p>

            </div>
            <div class="card-footer">댓글 : </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>

  <!-- snap -->
  <div class="py-4">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="card my-4">
            <div class="card-header bg-primary">
              <h5 class="mb-0 text-center">A Card Header</h5>
            </div>
            <div class="card-body">
              <p class="card-text">제목 : 예시 제목입니다.</p>
              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card&apos;s content. dfser sgfgs ghdfg r favicon</p>

            </div>
            <div class="card-footer">댓글 : </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>


<!-- 하단 고정 네비게이션 바 -->



  <div id="shrimpNav">
  <nav class=" nav navbar fixed-bottom bg-dark px-1">  <!--navbar-expand-md  -->
    <div class="container justify-content-center ">
      <div class="row nav text-center">  <!-- collapse  navbar-collapse    -->
        <ul class="nav">
          <li class="nav-item">
            <a class="btn btn-primary mx-1" href="#writePostModal" data-toggle="modal">글쓰기</a>
          </li>
          <li class="nav-item">
            <a class="btn btn-primary mx-1" href="#searchModal" data-toggle="modal">SEARCH</a>
          </li>
          <li class="nav-item">
            <a class="btn btn-primary mx-1" href="mypage.do">MYPAGE</a>
          </li>
          <li class="nav-item">
            <a class="btn btn-primary mx-1" href="#inboxModal" data-toggle="modal">INBOX</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>
  
  <!-- writePostModal -->
  <div class="modal hide" id="writePostModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
    <div class="modal-body fixed-bottom mb-5">
      <div class="container">
        <div class="row">
          <div class="col-3 btn btn-primary"><a href="writePostLink.html" class="text-white">LINK</a></div>
          <div class="col-3 btn btn-primary"><a href="writePostImage.html" class="text-white">IMAGE</a></div>
          <div class="col-3 btn btn-primary"><a href="writePostVideo.html" class="text-white">VIDEO</a></div>
          <div class="col-3 btn btn-primary"><a href="writePostText.jsp" class="text-white">TEXT</a></div>
        </div>
      </div>
    </div>
    <div class="modal-footer fixed-bottom justify-content-center bg-dark"><button type="button" class="close list-group-item-primary" data-dismiss="modal" aria-hidden="true">×</button>
    </div>
  </div>
  <!-- SearchModal -->
  <div class="modal hide" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
    <div class="modal-body fixed-bottom mb-5">
      <div class="container">
        <div class="row">
          <div class="col-12 justify-content-center">
            <form class="form-inline my-2 my-lg-0">
              <input class="form-control mr-sm-2" type="text" placeholder="Search">
              <button class="btn my-2 my-sm-0 btn-primary" type="submit">Search</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="modal-footer fixed-bottom justify-content-center bg-dark"><button type="button" class="close list-group-item-primary" data-dismiss="modal" aria-hidden="true">×</button>
    </div>
  </div>
  <!-- InboxModal -->
  <div class="modal hide" id="inboxModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
    <div class="modal-body fixed-bottom mb-5">
      <div class="container">
        <div class="row">
          <div class="col-3 btn btn-primary"><a href="writeMessage.html" class="text-white">메세지쓰기</a></div>
          <div class="col-3 btn btn-primary"><a href="recMessage.html" class="text-white">받은메세지함</a></div>
          <div class="col-3 btn btn-primary"><a href="sendMessage.html" class="text-white">보낸메세지함</a></div>
          <div class="col-3 btn btn-primary"><a href="writeMesToAdmin.html" class="text-white">관리자에게</a></div>
        </div>
      </div>
    </div>
    <div class="modal-footer fixed-bottom justify-content-center bg-dark"><button type="button" class="close list-group-item-primary" data-dismiss="modal" aria-hidden="true">×</button>
    </div>
  </div>








  <!-- JavaScript dependencies -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  <!-- Script: Smooth scrolling between anchors in the same page -->
  <script src="js/smooth-scroll.js"></script>

</body>

</html>
    