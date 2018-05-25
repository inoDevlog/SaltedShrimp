<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="/nav/head.jsp"%>
<!-- <script type="text/javascript" src="/Shrimp_Proto2/resources/js/sockjs-0.3.4.js"></script> -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<!-- <script type="text/javascript" src="/Shrimp_Proto2/resources/js/jquery-3.3.1.min.js"></script> -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script> -->

<script>  // Enter key 입력시 메세지 보내기
	function press(f){
		if(f.keyCode == 13){ //javascript에서는 13이 enter키를 의미함
			chatmsg.submit(); //formname에 사용자가 지정한 form의 name입력 
			} 
		}
</script>

<script type="text/javascript">  // 메세지 보내는 ajax
	
function sendMsg() {
	var sendmsg = $("#message").val();
	var appendmsg = "";
	var data={};
	data["send_nick"] = "${send_nick}";
	data["recv_nick"] = "${recv_nick}";
	data["message"] = sendmsg;
		$.ajax({
			type : "post",
			url : "sendchatroom.do",
			contentType : "application/json",
			dataType : 'json',
			data : JSON.stringify( // 서버로 보낼 데이터 명시
	            	data
	         )
	         
		}); // ajax

		appendmsg = "<div class=" + "'from-me'" + ">"
			     	+	"<p>" + sendmsg + "</p>" 
			       	+ 	"</div>"
			       	+ 	"<div class=" + "'clear'" + ">" + "</div>";
		$("#msg").append(appendmsg);
		$("#messages").scrollTop($(document).height());
		$('#message').val('');
}
</script>

<script type="text/javascript">  // 이전메세지 10개 불러오는 ajax

var currentPage = 1;

function moreList() {
	var data = {}
	data["currentPage"] = currentPage;
 	data["recv_nick"] = "${recv_nick}";
	$.ajax({
		type : "post",
		url : "previousMsg.do",
		contentType : "application/json",
		dataType : 'json',
		data : JSON.stringify( // 서버로 보낼 데이터 명시
            	data
         ),
        
         success : function(data) {
        	 currentPage++;
        	 console.log("현재 페이지 번호 : " + currentPage);
        	 var str = "";
        	 var btn = "";
        	 if (data != "") {
        		 $(data).each(
        			function() {
        				console.log(this);
        				if (this.sv_index == 0) {
        					str += "<div class=" + "'from-me'" + ">"
        		            	+	"<p>" + this.message + "</p>" 
        			          	+ 	"</div>"
        			          	+ 	"<div class=" + "'clear'" + ">" + "</div>";
        				}
        				if (this.sv_index == 1) {
        					str += "<div class=" + "'from-them'" + ">"
    		            	+	"<p>" + this.message + "</p>" 
    			          	+ 	"</div>"
    			          	+ 	"<div class=" + "'clear'" + ">" + "</div>";
    					}
        				btn = "<h5 class=" + "'text-center font-weight-bold py-2'" + "id=" + "'addbtn'"
            			+ "><a href=" + "'javascript:moreList()'" + "class=" + "'btn btn-primary'" + ">" + "이전 메세지 보기" + "</a></h5>";
        			}); // each end
        			
        			
        			
        			$("#msg").prepend(str);
        	 } // if data != ""
        	 
        	 else {
        		 $("#prevbtn").remove();
        		 
        	 }  // else
         }// success    
	}); // ajax
}
</script>
</head>

<body>
	<!-- 상단네비게이션 -->
	<%@include file="/nav/topNav.jsp"%>



  <!-- Message -->
  <div class="fixed-top mt-5" style="height: 67%;">
    <div class="container h-100">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <label class="font-weight-bold"> with. <label id="" name="">${recv_nick }</label></label>

        </div>
      </div>
      <div class="row h-100 justify-content-center">
        <div class="col-lg-8 border" style="overflow-x: hidden; overflow-y: scroll;" id="messages">
          <h5 class="text-center font-weight-bold py-2" id="prevbtn"><a href="javascript:moreList()" class="btn btn-primary">이전 메세지 보기</a></h5>
          <!-- 말 풍선들만 들어가는 영역  반복되는 부분-->
          <div id="msg">
          <c:forEach items="${dialogue }" var="dia">
          	<c:if test="${dia.sv_index eq 0 }">
	          <div class="from-me">
	            <p>${dia.message }</p>
	          </div>
	          <div class="clear"></div>
	         </c:if>
	         <c:if test="${dia.sv_index eq 1 }">
	          <div class="from-them">
	            <p>${dia.message }</p>
	          </div>
	          <div class="clear"></div>
	         </c:if>
          </c:forEach>
         </div>
        </div>
      </div>
    </div>
  </div>

  <div class="fixed-bottom mb-5 mt-1">
    <div class="container">
      <div class="row">
        <div class="col-lg-8  mx-auto px-0">
         <form action="javascript:sendMsg()" class="form-horizontal" method="POST" name="chatmsg">
          <!-- ajax로 처리할 예정이므로 form 태그 삭제-->
          <div class="row mx-auto">
            <div class="col-10 px-0"> <input type="text" class="form-control" placeholder="메세지를 입력 하세요" id="message"/></div>
            <div class="col-2 pr-0 "><a class="btn btn-secondary btn-block" id="sendBtn" href="javascript:sendMsg()"><span class="far fa-paper-plane"></span></a></div>
          </div>
          </form>
        </div>
      </div>
    </div>
  </div>




	<!-- 하단네비게이션 -->
	<%@include file="/nav/bottomNav.jsp"%>
	



	<!-- JavaScript dependencies -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
		integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>


</body>

<script type="text/javascript">
	$("#messages").scrollTop($(document).height());
</script>

 
<script type="text/javascript">  // 채팅 수신 ajax 
var data = {};
var test = 0;
data["send_nick"] = "${send_nick}";
data["recv_nick"] = "${recv_nick}";
data["lastmseq"] = "${lastmseq}";

$(document).ready(function() {
	var last_mseq = "${lastmseq}";

	setInterval(function new_Message() {
		console.log(last_mseq);
		$.ajax({
			type : "post",
			url : "addnewMsg.do",
			contentType : "application/json",
			dataType : 'json',
			async: false,
			data : JSON.stringify( // 서버로 보낼 데이터 명시
	            	data
	         ),
	         
	         success : function(data) {
	        	 var str = "";
	        	 if (data != "") {
	        		 $(data).each(
	        			function() {
	             			console.log(this);
	             					str = "<div class=" + "'from-them'" + ">"
	         		            	+	"<p>" + this.message + "</p>" 
	         			          	+ 	"</div>"
	         			          	+ 	"<div class=" + "'clear'" + ">" + "</div>";
	             					last_mseq = this.mseq;
	        			}); // each end
	        			
	        		 $("#msg").append(str);
	        		 $("#messages").scrollTop($(document).height()); 
	        	 } // if data != 
	        	 else {
	        	 }
	         }
		})
		data["lastmseq"] = last_mseq;
	}, 100);
});
</script> 



</html>
