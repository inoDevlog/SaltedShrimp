<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <%@ include file="/nav/head.jsp" %>
</head>

<!-- FORM TAG:: "recv_nick": 메세지 받는 사용자 닉네임, "message":메세지 내용 -->


<body>
 <!-- 상단네비게이션 -->  
  <%@include file="/nav/topNav.jsp" %>
  
  <!-- 제목 -->
  <div class="pt-5">
    <div class="container">
      <div class="row">
        <div class="page-header col-12">
          <h1>Write Message</h1>
          <hr>
        </div>
      </div>
    </div>
  </div>

<div class="pb-5">    
  <div class="container">
    <div class="row">
     <div class="col-md-8 mx-auto"> 
        <div class="section-light bg-light">
          <form class="p-3" method="post" action="send.do">   <!-- FORM START -->
			<!-- 메세지 받는 사용자의 닉네임 -->
            <div class="form-group my-2"> <label>받는 사람</label>
              <input class="form-control" placeholder="닉네임" name="recv_nick">
            </div>
			<!-- 메세지 내용 양식 -->
            <div class="form-group my-2"> <label>TEXT</label>
              <textarea class="form-control" name="message" rows="8" cols="80" placeholder="메세지를 입력하세요"></textarea>
            </div>
            <button type="submit" class="btn mt-4 btn-block p-2 btn-secondary "><b>SEND</b></button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


  <!-- 하단네비게이션 -->  
  <%@include file="/nav/bottomNav.jsp" %>
  
  
  <!-- JavaScript dependencies -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
 
	/* 	$.post("send.do",function(result){
			if(result == "1"){
				swal("메세지 전송 완료", "메세지가 성공적으로 전송되었습니다!", "success");
			}else if(result == "0"){
				
			}
		}, "text"); */
$(document).ready(function() {
	var result = "${param.sendResult}";
	if(result == "1"){
		swal(
			"메세지 전송 완료", "메세지 전송을 완료하였습니다.","success"
		).then(function() {
			window.location.href = "senMessage.do";
			console.log('The Ok Button was clicked.');
			});
	}else if(result == "0"){
		swal("메세지 전송 실패", "받는 사람 닉네임을 확인 해 주세요", "danger");
	}
})
		
		
		
		
	</script>
</body>

</html>
