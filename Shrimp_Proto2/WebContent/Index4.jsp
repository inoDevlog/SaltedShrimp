<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="/nav/head.jsp" %>

<style>
  #newBtn {
  display: none;
  position: fixed;
  top: 70px;
  right: 30px;
  z-index: 99;
  font-size: 15px;
  border: none;
  outline: none;
  background-color: red;
  color: white;
  cursor: pointer;
  padding: 10px;
  border-radius: 4px;
}

#newBtn:hover {
  background-color: #555;
}
</style>

<!-- 무한스크롤 관련 js  -->
<script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>



<script>

 	var mode = 0;
	var lastScrollTop_P = 0;
	var lastScrollTop_L = 0;
    var currentPage_P = 1;
    var currentPage_L = 1;
    var sessionNick = "${sessionScope.userInfo.nickname}";
    console.log(sessionNick);

    // 1. 스크롤 이벤트 발생
$(function() {    
	
		
    $(window).scroll(function(){ // ① 스크롤 이벤트 최초 발생
    	if(mode==0) { // mode = 0 POP
		var data = {}
    	data["mode"] = mode;
    	data["currentPage_P"] = currentPage_P;
        var currentScrollTop_P = $(window).scrollTop();
        /* 
            =================   다운 스크롤인 상태  ================
        */
        if( currentScrollTop_P - lastScrollTop_P > 0 ){
            // down-scroll : 현재 게시글 다음의 글을 불러온다.
            console.log("down-scroll_P");
            // 2. 현재 스크롤의 top 좌표가  > (게시글을 불러온 화면 height - 윈도우창의 height) 되는 순간
            if ($(window).scrollTop()+0.8 >= ($(document).height() - $(window).height()) ){ //② 현재스크롤의 위치가 화면의 보이는 위치보다 크다면
                // 3. class가 scrolling인 것의 요소 중 마지막인 요소를 선택한 다음 그것의 data-bseq속성 값을 받아온다.
                //      즉, 현재 뿌려진 게시글의 마지막 bseq값을 읽어오는 것이다.( 이 다음의 게시글들을 가져오기 위해 필요한 데이터이다.)
                // 4. ajax를 이용하여 현재 뿌려진 게시글의 마지막 bseq를 서버로 보내어 그 다음 10개의 게시물 데이터를 받아온다.
                console.log("아작스들어가기전...");
                $.ajax({
                    type : 'post',  // 요청 method 방식
                    url : 'infiniteScrollDown.do',// 요청할 서버의 url
                    contentType : "application/json",
                    dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시하는 것이다.
                    data : JSON.stringify( // 서버로 보낼 데이터 명시
                    	data
                     ),

                    success : function(data){// ajax 가 성공했을시에 수행될 function이다. 이 function의 파라미터는 서버로 부터 return받은 데이터이다.
                    		currentPage_P++;	// 무한 스크롤로 다음 페이지 업로드 할때마다 현재 페이지 +1 해주어야 다음 페이지를 로딩할 수 있다.
                            var str = "";
                            var loading = "";
                            // 5. 받아온 데이터가 ""이거나 null이 아닌 경우에 DOM handling을 해준다.
                            if(data != ""){
                                //6. 서버로부터 받아온 data가 list이므로 이 각각의 원소에 접근하려면 each문을 사용한다.
                                		$(data).each(
    	                                // 7. 새로운 데이터를 갖고 html코드형태의 문자열을 만들어준다.
    	                                function(){
    	                                    console.log(this);     
    	                                    str += "<div class=" + "'card my-4'" + ">"
    							          +              "<div class=" + "'card-header bg-primary'" + ">"
    							          +               "<h5 class=" + "'mb-0 text-center'" + ">" + this.title + "</h5>"
    							          +             "</div>"
    							          +             "<div class=" + "'card-body'" + ">"
    							          +               "<div class=" + "'card-text'" + ">" 
    							          +				    "<label name=" + "'writer'"+ "id=" + "'writer'"+">";
    							          
    	                                   if(sessionNick == this.nickname){
    	           							 str += "<a href="+"'${pageContext.request.contextPath}/mypage.do'"+"><b>"+this.nickname+"</b></a>";
    	           						 	}else if( !sessionNick ){
    	           						 	  str += "<a href="+"'#loginModal'"+"data-toggle="+"'modal'"+"><b>"+this.nickname+"</b></a>";
    	           						 	}else{
    	           						 	  str += "<a href="+"'message/getDialogue.do?recv_nick="+this.nickname+"'"+"><b>"+this.nickname+"</b></a>";
    	           						 
    	           						 	}
    							          
    							          
    						              	str += "</label><label class="+ "'float-right'"+">"+this.datetime+"</label>"
    							          +				  "</div>"
    							          +               "<hr class="+"'my-1'"+">";
    							            if(this.bcode==2 || this.bcode==5 || this.bcode==6){
    							            	str += "<div class="+"'card-text small'"+">URL : <a href='"+this.link+"'>"+this.link+"</a></div>"
    							            		+"<hr class="+"'my-1'"+">";
    							            }
    							          
    							          	str +=               "<div class=" + "'card-text p-2'" + ">" + this.article + "</div>";
    							          	if(this.bcode==5){
    							          		str += "<div class="+"'video-container'"+"><iframe width="+"'560'"+"height="+"'315'"+"src="+"'https://www.youtube.com/embed/"
    							          			+this.sublink+"' frameborder="+"'0'"+"allowfullscreen></iframe></div>";
    							          	}
    							          	if(this.bcode==6){
    							          		str += "<blockquote class="+"'twitter-tweet'"+ "align="+"'center'"+ "lang="+"'ko'"+"><a href="+this.link+"></a></blockquote>";
    							          	}
    							          	str +=             "</div>"
    							          +             "<div class=" + "'card-footer bg-white px-1'" + ">"
    							          +               "<div class="+"'row'"+">"
    							          +                 "<div class="+"'col-4 text-center pr-0'"+ "id="+"'voteDiv'"+ "value='"+this.checkstat+"' bseq='"+this.bseq+"'>"
    							          +                   "<a class="+"'p-1 btn'" +"id="+"'upBtn'"+"onclick="+"'clickVoteFunc(this); return false;'" + "><i class="+"'fas fa-long-arrow-alt-up fa-2x'"+"></i></a>"
    							          +                     "<a class="+"'font-weight-bold'"+ "id="+"'voteCount'"+">"+this.votesum+"</a>"
    							          +                     "<a class="+"'p-1 btn'" +"id="+"'downBtn'"+"onclick="+"'clickVoteFunc(this); return false;'" + "><i class="+"'fas fa-long-arrow-alt-down fa-2x'"+"></i></a>"
    							          +                 "</div>"
    							          +                 "<div class="+"'col-4 text-center pr-0'"+">"
    							          +                   "<a class="+"'btn text-grey pl-1'"+ "id="+"'commentBtn'" +"hr="+"'#'"+"><i class="+"'fas fa-comments fa-lg'"+"></i><b> comment</b></a>"
    							          +                 "</div>"
    							          +                 "<div class="+"'col-4 text-center'"+">"
    							          +                   "<a class="+"'btn text-grey'"+"><i class="+"'fas fa-external-link-alt fa-lg'"+"></i><b> link</b></a>"
    							          +                 "</div>"
    							          +               "</div>"
    							          +             "</div>"
    							          +           "</div>";

    	                                  loading =  "<div class=" + "'pb-5 row justify-content-center'"   + "id="+"'loading'" + ">" + "<i class=" + "'fas fa-spinner fa-pulse fa-2x'" + ">" + "</i>" +"</div>";
    	                            });// each
    	                            
                                $("#loading").delay(1000).remove();
                                $("#popboard").append(str).append(loading);     
                                setVote();

                            }// if : data!=null
                            else{ // 9. 만약 서버로 부터 받아온 데이터가 없으면 그냥 아무것도 하지말까..
                                alert("더 불러올 데이터가 없습니다.");
                            }// else	
                    }// success
                });// ajax
                 
                // 여기서 class가 listToChange인 것중 가장 처음인 것을 찾아서 그 위치로 이동하자.
                var position = $(".listToChange:first").offset();// 위치 값
            }//if : 현재 스크롤의 top 좌표가  > (게시글을 불러온 화면 height - 윈도우창의 height) 되는 순간
            // lastScrollTop을 현재 currentScrollTop으로 갱신해준다.
            lastScrollTop_P = currentScrollTop_P;  // 추가된 스크롤 만큼 스크롤 위치를 갱신해 주어야 다운 스크롤이 작동한다.
        }// 다운스크롤인 상태
    }  // end if mode == 0
    	
    	if( mode == 1) { // mode = 1 LATEST
    		var data = {}
	    	data["mode"] = mode;
	    	data["currentPage_L"] = currentPage_L;
	    	
	        var currentScrollTop_L = $(window).scrollTop();
	         
	        /* 
	            =================   다운 스크롤인 상태  ================
	        */
	        if( currentScrollTop_L - lastScrollTop_L > 0 ){
	            // down-scroll : 현재 게시글 다음의 글을 불러온다.
	            console.log("down-scroll_L");
	            console.log(($(document).height()-$(window).height()));
	            console.log($(window).scrollTop());
	            
	            // 2. 현재 스크롤의 top 좌표가  > (게시글을 불러온 화면 height - 윈도우창의 height) 되는 순간
	            if ($(window).scrollTop()+0.8 >= ($(document).height() - $(window).height()) ){ //② 현재스크롤의 위치가 화면의 보이는 위치보다 크다면
	                // 3. class가 scrolling인 것의 요소 중 마지막인 요소를 선택한 다음 그것의 data-bno속성 값을 받아온다.
	                //      즉, 현재 뿌려진 게시글의 마지막 bseq값을 읽어오는 것이다.( 이 다음의 게시글들을 가져오기 위해 필요한 데이터이다.)
 						console.log("아작스 들어가기전");
	                // 4. ajax를 이용하여 현재 뿌려진 게시글의 마지막 bseq를 서버로 보내어 그 다음 20개의 게시물 데이터를 받아온다.
	                $.ajax({
	                    type : 'post',  // 요청 method 방식
	                    url : 'infiniteScrollDown.do',// 요청할 서버의 url

	                    contentType : "application/json",

	                    dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시하는 것이다.
	                    data : JSON.stringify( // 서버로 보낼 데이터 명시
	                    	data
	                     ),
	                    
	                    success : function(data){// ajax 가 성공했을시에 수행될 function이다. 이 function의 파라미터는 서버로 부터 return받은 데이터이다.
	                    		currentPage_L++;
	                    		console.log("최신순 현재 페이지 번호" + currentPage_L);

	                            var str = "";
	                            var loading = "";
	                            // 5. 받아온 데이터가 ""이거나 null이 아닌 경우에 DOM handling을 해준다.
	                            if(data != ""){
	                                //6. 서버로부터 받아온 data가 list이므로 이 각각의 원소에 접근하려면 each문을 사용한다.
	                                		$(data).each(
	    	                                // 7. 새로운 데이터를 갖고 html코드형태의 문자열을 만들어준다.
	    	                                function(){
	    	                                    console.log(this);     
	    	                                    str += 
	    							            "<div class=" + "'card my-4'" + ">"
	    							          +              "<div class=" + "'card-header bg-primary'" + ">"
	    							          +               "<h5 class=" + "'mb-0 text-center'" + ">" + this.title + "</h5>"
	    							          +             "</div>"
	    							          +             "<div class=" + "'card-body'" + ">"
	    							          +               "<div class=" + "'card-text'" + ">" 
	    							          +				    "<label name=" + "'writer'"+ "id=" + "'writer'"+">";
	    	                                    if(sessionNick == this.nickname){
	       	           							 str += "<a href="+"'${pageContext.request.contextPath}/mypage.do'"+"><b>"+this.nickname+"</b></a>";
	       	           						 	}else if( !sessionNick ){
	       	           						 	str += "<a href="+"'#loginModal'"+"data-toggle="+"'modal'"+"><b>"+this.nickname+"</b></a>";
	       	           						 	}else{
	       	           						 	str += "<a href="+"'message/getDialogue.do?recv_nick="+this.nickname+"'"+"><b>"+this.nickname+"</b></a>";
	       	           						 	}
	    							          
	    							          
	    						              str += "</label><label class="+ "'float-right'"+">"+this.datetime+"</label>"
	    							          +				  "</div>"
	    							          +               "<hr class="+"'my-1'"+">";
	    	                                    if(this.bcode==2 || this.bcode==5 || this.bcode==6){
	    							            	str += "<div class="+"'card-text small'"+">URL : <a href='"+this.link+"'>"+this.link+"</a></div>"
	    							            		+"<hr class="+"'my-1'"+">";
	    							            }
	    							          
	    							          	str +=               "<div class=" + "'card-text p-2'" + ">" + this.article + "</div>";
	    							          	if(this.bcode==5){
	    							          		str += "<div class="+"'video-container'"+"><iframe width="+"'560'"+"height="+"'315'"+"src="+"'https://www.youtube.com/embed/"
	    							          			+this.sublink+"' frameborder="+"'0'"+"allowfullscreen></iframe></div>";
	    							          	}
	    							          	if(this.bcode==6){
	    							          		str += "<blockquote class="+"'twitter-tweet'"+ "align="+"'center'"+ "lang="+"'ko'"+"><a href="+this.link+"></a></blockquote>";
	    							          	}
	    							          	
	    							          	str +=             "</div>"
	    							          +             "<div class=" + "'card-footer bg-white px-1'" + ">"
	    							          +               "<div class="+"'row'"+">"
	    							          +                 "<div class="+"'col-4 text-center pr-0'"+ "id="+"'voteDiv'"+ "value='"+this.checkstat+"' bseq='"+this.bseq+"'>"
	    							          +                   "<a class="+"'p-1 btn'" +"id="+"'upBtn'"+"onclick="+"'clickVoteFunc(this); return false;'" + "><i class="+"'fas fa-long-arrow-alt-up fa-2x'"+"></i></a>"
	    							          +                     "<a class="+"'font-weight-bold'"+ "id="+"'voteCount'"+">"+this.votesum+"</a>"
	    							          +                     "<a class="+"'p-1 btn'" +"id="+"'downBtn'"+"onclick="+"'clickVoteFunc(this); return false;'" + "><i class="+"'fas fa-long-arrow-alt-down fa-2x'"+"></i></a>"
	    							          +                 "</div>"
	    							          +                 "<div class="+"'col-4 text-center pr-0'"+">"
	    							          +                   "<a class="+"'btn text-grey pl-1'"+ "id="+"'commentBtn'" +"hr="+"'#'"+"><i class="+"'fas fa-comments fa-lg'"+"></i><b> comment</b></a>"
	    							          +                 "</div>"
	    							          +                 "<div class="+"'col-4 text-center'"+">"
	    							          +                   "<a class="+"'btn text-grey'"+"><i class="+"'fas fa-external-link-alt fa-lg'"+"></i><b> link</b></a>"
	    							          +                 "</div>"
	    							          +               "</div>"
	    							          +             "</div>"
	    							          +           "</div>";
	    							          
	    							          
	    	                                  //loading =  "<div class=" + "'pb-5 row justify-content-center'"   + "id="+"'loading'" + ">" + "<i class=" + "'fas fa-spinner fa-pulse fa-2x'" + ">" + "</i>" +"</div>";
	    	                            });// each   
	                                // 8. 이전까지 뿌려졌던 데이터를 비워주고, <th>헤더 바로 밑에 위에서 만든 str을  뿌려준다.
	                                
	                                $("#loading").delay(1000).remove();
	                                $("#latestboard").append(str).append(loading);    
	                                setVote();
	                                // $('input').prop('disabled', false);
	                                     
	                            }// if : data!=null
	                            if(data==""){ // 9. 만약 서버로 부터 받아온 데이터가 없으면 그냥 아무것도 하지말까..
	                                alert("띠용띠용!");
	                            }// else
	                    }// success
	                });// ajax
	                 
	                // 여기서 class가 listToChange인 것중 가장 처음인 것을 찾아서 그 위치로 이동하자.
	                var position = $(".listToChange:first").offset();// 위치 값
	            }//if : 현재 스크롤의 top 좌표가  > (게시글을 불러온 화면 height - 윈도우창의 height) 되는 순간
	             
	            // lastScrollTop을 현재 currentScrollTop으로 갱신해준다.
	            lastScrollTop_L = currentScrollTop_L;
	        }// 다운스크롤인 상태
	         
		}
    	
	});// scroll event
	
	

	
});
 
</script>


<script type="text/javascript">  // 새 글 탐색시 버튼 팝업 javascript
	var data = {};
	data["lastbseq"] = "${lastbseq}";
	
$(document).ready(function() {
	
	var num_newBoard = 0;
	setInterval(function new_btn() {
		$.ajax({
			type : "post",
			url : "countNewBoard.do",
			contentType : "application/json",
			dataType : 'json',
			async: false,
			data : JSON.stringify( // 서버로 보낼 데이터 명시
	            	data
			),
			success : function(data) {
				if (data != 0) {  // 받아온 Data가 있을시 버튼 팝업
					  num_newBoard = data;
			          document.getElementById("newBtn").style.display = "block";
			          $("#newBtn").text(num_newBoard);
			      } else {
			          document.getElementById("newBtn").style.display = "none";
			      }
			}
		})
	}, 10000);  //  새 글 갱신 주기 = 10초
});

function addnewBoard() {
	var last_bseq = "${lastbseq}";
	$.ajax({
		type : "post",
		url : "addnewBoard.do",
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
        				str += 
        				    "<div class=" + "'card my-4'" + ">"
					      + "<div class=" + "'card-header bg-primary'" + ">"
						  + "<h5 class=" + "'mb-0 text-center'" + ">" + this.title + "</h5>"
						  + "</div>"
						  + "<div class=" + "'card-body'" + ">"
						  + "<div class=" + "'card-text'" + ">"
						  + "<label name=" + "'writer'" + " id=" + "'writer'" + ">";
						 if(sessionNick == this.nickname){
							 str += "<a href="+"'${pageContext.request.contextPath}/mypage.do'"+"><b>"+this.nickname+"</b></a>";
						 }else if( !sessionNick ){
							 str += "<a href="+"'#loginModal'"+"data-toggle="+"'modal'"+"><b>"+this.nickname+"</b></a>";
						 }else{
							 str += "<a href="+"'message/getDialogue.do?recv_nick="+this.nickname+"'"+"><b>"+this.nickname+"</b></a>";
						 }
						  
						  
						  str += "</label>"
						  + "<label class=" + "'float-right'" + ">" + this.datetime + "</label>"
						  + "</div>"
						  + "<hr class=" + "'my-1'" + ">";
						  
        				if(this.bcode==2 || this.bcode==5 || this.bcode==6){
			            	str += "<div class="+"'card-text small'"+">URL : <a href='"+this.link+"'>"+this.link+"</a></div>"
			            		+"<hr class="+"'my-1'"+">";
        				}
						  
						str += "<div class=" + "'card-text p-2'" + ">"+this.article+ "</div>";
						
						if(this.bcode==5){
			          		str += "<div class="+"'video-container'"+"><iframe width="+"'560'"+"height="+"'315'"+"src="+"'https://www.youtube.com/embed/"
			          			+this.sublink+"' frameborder="+"'0'"+"allowfullscreen></iframe></div>";
			          	}
			          	if(this.bcode==6){
			          		str += "<blockquote class="+"'twitter-tweet'"+ "align="+"'center'"+ "lang="+"'ko'"+"><a href="+this.link+"></a></blockquote>";
			          	}
						
						
						
						
						  
						str += "</div>"
						+ "<div class=" + "'card-footer bg-white px-1'" + ">"
						  + "<div class=" + "'row'" + ">"
                          + "<div class=" + "'col-4 text-center pr-0'" + " id=" + "'voteDiv'" + " value=0 bseq="+this.bseq+">"
                            + "<a class=" + "'p-1 btn'" + " id=" + "'upBtn'" + "><i class=" + "'fas fa-long-arrow-alt-up fa-2x'" + "></i></a>"
                            + "<a class=" + "'font-weight-bold'" + " id=" + "'voteCount'" + ">" + this.votesum + "</a>"
                            + "<a class=" + "'p-1 + btn'" + " id=" + "'downBtn'" + "><i class=" + "'fas fa-long-arrow-alt-down fa-2x'" + "></i></a>"
                          + "</div>"
                          + "<div class=" + "'col-4 text-center pr-0'" + "><a class=" + "'btn text-grey pl-1'" + " id=" + "'commentBtn'" + " hr=" + "'#'" + "><i class=" + "'fas fa-comments fa-lg'" + "></i><b> comment</b></a></div>"
                          + "<div class=" + "'col-4 text-center'" + "><a class=" + "'btn text-grey'" + "><i class=" + "'fas fa-external-link-alt fa-lg'" + "></i><b> link</b></a></div>"
                        + "</div>"
						+ "</div>"
					  + "</div>";
					  last_bseq = this.bseq;
        			});  // each end
        		$("#latestboard").prepend(str);
        		$("body").scrollTop(0);
        		document.getElementById("newBtn").style.display = "none";
        	 }  // if end
         }  // success function end
	}) // ajax end
	data["lastbseq"] = last_bseq;
	mode = 1;  // pop tab에서도 새 글이 들어왔을 시 버튼 클릭하면 lat tab으로 이동하게 끔 mode 변경
	$('#lat-tab').click();  // 새 글업데이트 버튼 클릭시 lat  tab으로 이동
}


</script>



</head>

<body>
<button onclick="addnewBoard();" id="newBtn"></button>

	<!-- 상단네비게이션 -->
	<%@include file="/nav/topNav.jsp"%>



	<!-- 인기순/최신순 탭 버튼 -->
	<div class="pt-5">
		<div class="container">
			<ul class="nav nav-tabs justify-content-center">
				<div class="row ">
					<div class="col-6">
						<li class="nav-item" id="pop_li"><a href="#pop" class="nav-link active"
							aria-controls="home" aria-selected="true" data-toggle="tab"
							id="pop-tab" role="tab">Popular</a></li>
					</div>
					<div class="col-6">
						<li class="nav-item" id="lat_li"><a class="nav-link" href="#lat"
							aria-controls="profile" aria-selected="false" data-toggle="tab"
							id="lat-tab" role="tab">Latest</a></li>
					</div>
				</div>
			</ul>
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<div class="tab-content" id="myTabContent">
						<!-- 인기순 탭 -->
						<div class="tab-pane fade show active" id="pop" role="tabpanel"
							aria-labelledby="pop-tab">
							<!-- 인기순 탭 div -->
								<div class="container" id="popboard">
								<c:forEach items="${PopList }" var="popboard">	
										    <!-- Card Set -->
											<div class="card my-4">
										      <div class="card-header bg-primary">
											    <h5 class="mb-0 text-center">${popboard.title }</h5>
											  </div>
											  <div class="card-body">
											  <div class="card-text">
											  <label name="writer" id="writer">
											  
											  <c:choose>
											    <c:when test="${sessionScope.userInfo.nickname == popboard.nickname}">
											      <a href="${pageContext.request.contextPath}/mypage.do"><b> ${popboard.nickname}</b></a>
											    </c:when>
											    <c:when test="${empty sessionScope.userInfo.nickname}">
											      <a href="#loginModal" data-toggle="modal"><b>${popboard.nickname}</b></a>
											    </c:when>
											    <c:otherwise>
											      <a href="message/getDialogue.do?recv_nick=${popboard.nickname}"><b> ${popboard.nickname} </b></a>
											    </c:otherwise>
											  </c:choose>
											  
											  </label>
											  <label class="float-right"> ${popboard.datetime } </label>
											  </div>
											  <hr class="my-1">
											  
											  <!-- link가 있을 경우에만 넣기(bcode=2or5or6) -->
              								  <c:if test="${popboard.bcode==2 || popboard.bcode==5 || popboard.bcode==6}">
              								  <div class="card-text small">
                								URL : <a href="${popboard.link}"> ${popboard.link}</a>
              								  </div>
              								  <hr class="my-1">
              							      </c:if>
											  
											  <div class="card-text p-2">
											  <c:if test="${popboard.bseq == 112 }">
											  <p style="text-align: center;">
											  	<img src="http://saltedshrimp.com:8080/Shrimp_Proto2/images/jenny.jpg" style="max-width: 100%;  height:auto;"><br/>
											  </p>
											  </c:if>
											  ${popboard.article }
											  </div>
											  <c:if test="${popboard.bcode==5}"><!-- 유튜브 링크일 경우  -->
											  <div class="video-container">
											    <iframe width="560" height="315" src="https://www.youtube.com/embed/${popboard.sublink}" frameborder="0" allowfullscreen></iframe> 
											  </div>
											  </c:if>
											  
											  <c:if test="${popboard.bcode==6}"><!-- 트위터 링크일 경우  -->
              								  <blockquote class="twitter-tweet" align="center" lang="ko">
              								  <a href="${popboard.link}"></a>
              								  </blockquote>
              								  </c:if>
              								  
											</div>
											<div class="card-footer bg-white px-1"><!--좋아요 버튼 영역 -->
											  <div class="row">
                                                <div class="col-4 text-center pr-0" id="voteDiv" value="${popboard.checkstat}" bseq="${popboard.bseq}">
                                                  <a class="p-1 btn" id="upBtn" onclick="clickVoteFunc(this); return false;"><i class="fas fa-long-arrow-alt-up fa-2x"></i></a>
                                                  <a class="font-weight-bold" id="voteCount">${popboard.votesum }</a>
                                                  <a class="p-1 btn" id="downBtn" onclick="clickVoteFunc(this); return false;""><i class="fas fa-long-arrow-alt-down fa-2x"></i></a>
                                                </div>
                                                <!-- comment 버튼을 누르면 해당 게시물의 자세한 형식 페이지 (getBoard_D.jsp)로 이동한다. -->
                                                <div class="col-4 text-center pr-0"><a class="btn text-grey pl-1" id="commentBtn" href="${pageContext.request.contextPath}/getBoard.do?bseq=${popboard.bseq}&useq=${popboard.useq}"><i class="fas fa-comments fa-lg"></i><b> comment</b></a></div>
                                                <div class="col-4 text-center"><a class="btn text-grey"><i class="fas fa-external-link-alt fa-lg"></i><b> link</b></a></div>
                                              </div>
											</div>
									      </div>
									      <!-- Card Set END-->
									</c:forEach>
								</div>
								<div class="pb-5 row justify-content-center" id="loading"><i class="fas fa-spinner fa-pulse fa-2x"></i></div>
						</div><!--End 인기순 탭-->
						
						<!-- 최신순 탭 -->
						<div class="tab-pane fade show" id="lat" role="tabpanel"
							aria-labelledby="lat-tab">
							<!-- 최신순 탭 div -->
									<div class="container" id="latestboard">
									  <c:forEach items="${LatestList }" var="board">		
									    <div class="card my-4">
									      <div class="card-header bg-primary">
											    <h5 class="mb-0 text-center">${board.title }</h5>
											  </div>
											  <div class="card-body">
											  <div class="card-text">
											  <label name="writer" id="writer">
											  
											  <c:choose>
											    <c:when test="${sessionScope.userInfo.nickname == board.nickname}">
											      <a href="${pageContext.request.contextPath}/mypage.do"><b> ${board.nickname } </b></a>
											    </c:when>
											    <c:when test="${empty sessionScope.userInfo.nickname}">
											      <a href="#loginModal" data-toggle="modal"><b>${board.nickname }</b></a>
											    </c:when>
											    <c:otherwise>
											      <a href="message/getDialogue.do?recv_nick=${board.nickname }"><b> ${board.nickname } </b></a>
											    </c:otherwise>
											  </c:choose>
											  
											  </label>
											  <label class="float-right"> ${board.datetime } </label>
											  </div>
											  <hr class="my-1">
											  
											  <!-- link가 있을 경우에만 넣기(bcode=2or5or6) -->
              								  <c:if test="${board.bcode==2 || board.bcode==5 || board.bcode==6}">
              								  <div class="card-text small">
                								URL : <a href="${board.link}"> ${board.link}</a>
              								  </div>
              								  <hr class="my-1">
              							      </c:if>
              
											  <div class="card-text p-2">
											  <c:if test="${board.bseq == 112 }">
											  <p style="text-align: center;">
											  	<img src="http://saltedshrimp.com:8080/Shrimp_Proto2/images/jenny.jpg" style="max-width: 100%;  height:auto;"><br/>
											  </p>
											  </c:if>
											  ${board.article }
											  </div>
											  
											  <c:if test="${board.bcode==5}"><!-- 유튜브 링크일 경우  -->
											  <div class="video-container">
											    <iframe width="560" height="315" src="https://www.youtube.com/embed/${board.sublink}" frameborder="0" allowfullscreen></iframe> 
											  </div>
											  </c:if>
											  
											  <c:if test="${board.bcode==6}"><!-- 트위터 링크일 경우  -->
              								  <blockquote class="twitter-tweet" align="center" lang="ko">
              								  <a href="${board.link}"></a>
              								  
              								  </blockquote>
              								  </c:if>
              								  
											</div>
											<div class="card-footer bg-white px-1"><!--좋아요 버튼 영역 -->
											  <div class="row">
                                                <div class="col-4 text-center pr-0" id="voteDiv" value="${board.checkstat}" bseq="${board.bseq}">
                                                  <a class="p-1 btn" id="upBtn" onclick="clickVoteFunc(this); return false;"><i class="fas fa-long-arrow-alt-up fa-2x"></i></a>
                                                  <a class="font-weight-bold" id="voteCount">${board.votesum }</a>
                                                  <a class="p-1 btn" id="downBtn" onclick="clickVoteFunc(this); return false;"><i class="fas fa-long-arrow-alt-down fa-2x"></i></a>
                                                </div>
                                                <!-- comment 버튼을 누르면 해당 게시물의 자세한 형식 페이지 (getBoard_D.jsp)로 이동한다. -->
                                                <div class="col-4 text-center pr-0"><a class="btn text-grey pl-1" id="commentBtn" href="${pageContext.request.contextPath}/getBoard.do?bseq=${board.bseq}&useq=${board.useq}"><i class="fas fa-comments fa-lg"></i><b> comment</b></a></div>
                                                <div class="col-4 text-center"><a class="btn text-grey"><i class="fas fa-external-link-alt fa-lg"></i><b> link</b></a></div>
                                              </div>
											</div>
										  </div>
									    </c:forEach>
									  </div>
				
								<div class="pb-5 row justify-content-center" id="loading"><i class="fas fa-spinner fa-pulse fa-2x"></i></div>
							<!--End 최신순 탭 div-->
						</div>
						<!--End 최신순 탭-->
					</div>
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
	<script async src="http://platform.twitter.com/widgets.js" charset="utf-8">
	</script>
	<!-- Script: Smooth scrolling between anchors in the same page -->
<script>
//login=0 이라는 파람값이 붙어서 페이지가 호출 될 경우 
//로그인 모달을 펼친다.
var isLogin = <%=request.getParameter("login")%>;
console.log(isLogin);
if(isLogin != null){
	$('#loginModal').modal('show');
}

</script>
<script type="text/javascript">

$("#pop_li").click(function() {
	mode = 0;
	console.log("mode 값 뭐니 ? " + mode);
});
 
$("#lat_li").click(function() {
	mode = 1;
	console.log("mode 값 뭐니 ? " + mode);
});

</script>

<script>
//버튼 초기 설정
function setVote(){
        $("div[id=voteDiv]").each(function(){
          var a = $(this).attr('value');
          var upBtn= $(this).children("#upBtn");
          var downBtn= $(this).children("#downBtn");
          var voteCount = $(this).children("#voteCount");

          if(a === "0" ){
            $(this).children().addClass("text-grey");
          }else if(a === "1"){
            upBtn.addClass("text-primary");
            voteCount.addClass("text-primary");
            downBtn.addClass("text-grey");
          }else if(a === "-1"){
            upBtn.addClass("text-grey");
            voteCount.addClass("text-blue");
            downBtn.addClass("text-blue");
          }

        });
      }
setVote();

//좋아요 싫어요 버튼 관련 JQuery
function clickVoteFunc(obj){  //ajax : myfunc(vote, votesum, check, bseq, useq)
    
    //--- 사용될 컴포넌트 선택
    var btn_2 = $(obj).siblings(".btn");// 반대 버튼
    var voteCount = $(obj).siblings(".font-weight-bold");// 가운데 voteCount
    var voteDiv = $(obj).parent();

    //--- 사용될 값
    var vote_val = $(obj).parent().attr('value');  //해당 게시물의 vote 상태값 (1, 0, -1)
    var btn_val = $(obj).attr('id');      //클릭된 버튼 종류 (up or down)
    var res = 0;  //ajax 통신 후 결과값 : voteCount 값 변경용
    

    //--- ajax 통신시 사용할 값
    var bseq = $(obj).parent().attr('bseq');   // 해당 게시물의 bseq 값
    var useq = "${sessionScope.userInfo.useq}";
    var vote="";
    var votesum="";
    var check="";

    console.log(bseq, useq, btn_val, vote_val);
    
    //로그인이 되어 있지 않으면 모달창 팝업  return false;
	if(useq === ""){
		$('#loginModal').modal('show');
  		return false;
	}

    if( btn_val === "upBtn"){//up 버튼 눌렀을 경우
      console.log("upBtn click!!");
      //변수 초기화 => 상위 if문으로 빼서 한번에 처리 가능

      if(vote_val ==="1"){    //upBtn이 눌려져 있었을 경우 => default 상태로 만든다. (이미 vote 테이블에 데이터가 존재)
        $(obj).removeClass("text-primary").addClass("text-grey");
        voteCount.removeClass("text-primary").addClass("text-grey");
        voteDiv.attr("value",0);
        //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 0 으로 바꾼다.
        //board 테이블에 해당 bseq 데이터의 vote 값에다가 -1을 해준다.
        vote = "0";votesum = "-1";checkTable = "0";
        res = myfunc(vote, votesum, checkTable, bseq, useq);
        console.log("통신 후 : "+res);
        voteCount.text(res);

      }else if(vote_val ==="0"){  // default인 경우 => up 상태로 만든다. (VOTE 테이블에 데이터가 있을 수도, 없을 수도 있는 경우)
        $(obj).removeClass("text-grey").addClass("text-primary");
        voteCount.removeClass("text-grey").addClass("text-primary");
        voteDiv.attr("value",1);
        //vote 테이블에 이력이 존재 한다면
        //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 1 으로 바꾼다.
        //board 테이블에 해당 bseq 데이터의 vote 값에다가 +1을 해준다.
        vote = "1";votesum = "+1";checkTable = "1";
        res = myfunc(vote, votesum, checkTable, bseq, useq);
        console.log("통신 후 : "+res);
        voteCount.text(res);
        //vote 테이블에 이력이 존재 하지 않으면
        //vote 테이블에 vote 값을  1 으로 insert 한다.
        //board 테이블에 해당 bseq 데이터의 vote 값에다가 +1을 해준다.

      }else{                    // downBtn이 눌려져 있는 경우 => up 상태로 만든다. (이미 VOTE 테이블에 데이터가 존재)
        $(obj).removeClass("text-grey").addClass("text-primary");
        voteCount.removeClass("text-blue").addClass("text-primary");
        btn_2.removeClass("text-blue").addClass("text-grey");
        voteDiv.attr("value", 1);
        //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 1 으로 바꾼다.
        //board 테이블에 해당 bseq 데이터의 vote 값에다가 +2을 해준다.
        vote = "1";votesum = "+2";checkTable = "0";
        res = myfunc(vote, votesum, checkTable, bseq, useq);
        console.log("통신 후 : "+res);
        voteCount.text(res);

      }



    }else if( btn_val ==="downBtn"){//down 버튼 눌렀을 경우
      console.log("downBtn click!!");
      //변수 초기화 => 상위 if문으로 빼서 한번에 처리 가능

      if(vote_val === "-1"){//downBtn이 눌려져 있는 경우 => default 상태로 만든다. (이미 VOTE 테이블에 데이터가 존재)
        $(obj).removeClass("text-blue").addClass("text-grey");
        voteCount.removeClass("text-blue").addClass("text-grey");
        voteDiv.attr("value", 0);
        //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 0 으로 바꾼다.
        //board 테이블에 해당 bseq 데이터의 vote 값에다가 +1을 해준다.
        vote = "0";votesum = "+1";checkTable = "0";
        res = myfunc(vote, votesum, checkTable, bseq, useq);
        voteCount.text(res);

      }else if(vote_val === "0"){//default인 경우 => down 상태로 만든다.  (VOTE 테이블에 데이터가 있을수도, 없을 수도 있는 경우)
        $(obj).removeClass("text-grey").addClass("text-blue");
        voteCount.removeClass("text-grey").addClass("text-blue");
        voteDiv.attr("value", -1);
        //vote 테이블에 이력이 존재 한다면
        	//vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 -1 으로 바꾼다.
        	//board 테이블에 해당 bseq 데이터의 vote 값에다가 -1을 해준다.
        vote = "-1";votesum = "-1";checkTable = "1";
        res = myfunc(vote, votesum, checkTable, bseq, useq);
        voteCount.text(res);
        //vote 테이블에 이력이 존재 하지 않으면
          //vote 테이블에 vote 값을  -1 으로 insert 한다.
        	//board 테이블에 해당 bseq 데이터의 vote 값에다가 -1을 해준다.

      }else{//upBtn이 눌려져 있는 경우 => down 상태로 만든다. (이미 VOTE 테이블에 데이터가 존재)
        $(obj).removeClass("text-grey").addClass("text-blue");
        voteCount.removeClass("text-primary").addClass("text-blue");
        btn_2.removeClass("text-primary").addClass("text-grey");
        voteDiv.attr("value", -1);
        //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 -1 으로 바꾼다.
        //board 테이블에 해당 bseq 데이터의 vote 값에다가 -2을 해준다.
          vote = "-1";votesum = "-2";checkTable = "0";
          res = myfunc(vote, votesum, checkTable, bseq, useq);
          voteCount.text(res);
      }


    }
  }
  
  
//좋아요 버튼 클릭시 ajax 함수
function myfunc(vote, votesum, check, bseq, useq){  //세개의 인자를 받아서 ajax 통신하는 함수 만들기
      	var data = {};
			var ress = 0;
      	data["vote"] = vote;
      	data["votesum"] = votesum;
      	data["checkTable"] = check;
      	data["bseq"] = bseq;
      	data["useq"] = useq;
	  		$.ajax({
      		type:"POST",
  			url:"changeVote.do",
  			async: false,   //동기 방식 으로 해야 값을 받아 올 수 있다.
  			contentType : "application/json",
  			dataType: "json",
  			data:JSON.stringify(data),
  			success: function(result){//board 테이블에 sumVote 값을 가져와야 한다.
  				if(result.result != 2){
  					console.log("데이터 통신 실패");
  					return false;
  				}
  			    console.log("데이터 통신 성공");
  			    console.log(result.result);
					console.log(result.votesum);
					//$("#voteCount").text(result.votesum);
					ress = result.votesum;
      		
      	}});
	  		return ress;
    }
</script>




</body>

</html>
