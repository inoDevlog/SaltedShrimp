<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <%@ include file="/nav/head.jsp" %>
</head>

<body>
  <!-- 상단네비게이션 -->  
  <%@include file="/nav/topNav.jsp" %>






  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-8 mx-auto">
          <div class="card my-4">
            <div class="card-header bg-primary">
              <h5 class="mb-0 text-center">${board.title }</h5>  
            </div>
            <div class="card-body">
              <div class="card-text">
                <label name="writer" id="writer"><b> ${board.nickname }</b></label>
                <label class="float-right">${board.datetime }</label>
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
					${board.article }
              </div>
              <c:if test="${board.bcode==5}"><!-- 유튜브 링크일 경우  -->
              <div class="video-container">
                <iframe width="560" height="315" src="https://www.youtube.com/embed/${board.sublink }" frameborder="0" allowfullscreen></iframe>
              </div>
              </c:if>
              <c:if test="${board.bcode==6}"><!-- 트위터 링크일 경우  -->
              <blockquote class="twitter-tweet" align="center" lang="ko">
              <a href="${board.link}"></a>
              
              </blockquote>
              </c:if>
            </div>
            
          <div class="card-footer bg-white px-1"><!-- 버튼 영역 -->
            <div class="row">
               <div class="col-4 text-center pr-0" id="voteDiv" value="${checkstat}" bseq="${board.bseq }">
                   <a class="p-1 btn" id="upBtn" onclick="clickVoteFunc(this); return false;"><i class="fas fa-long-arrow-alt-up fa-2x"></i></a>
                   <a class="font-weight-bold" id="voteCount">${board.votesum}</a>
                  <a class="p-1 btn" id="downBtn" onclick="clickVoteFunc(this); return false;"><i class="fas fa-long-arrow-alt-down fa-2x"></i></a>
              </div>
              <!-- light보다 진한 색상으로 하나 theme.css에 추가해서 만들기  -->
              <div class="col-4 text-center pr-0"><a class="btn text-grey pl-1" id="commentBtn"><i class="fas fa-comments fa-lg"></i><b> comment</b></a></div>
              <div class="col-4 text-center"><a class="btn text-grey"><i class="fas fa-external-link-alt fa-lg"></i><b> link</b></a></div>
            </div>
          </div>
          
          <div class="card-footer" id="writeComment"><!-- 댓글 입력 영역 -->
           <!--  <form action="" class="form-horizontal" method="POST"> -->
              <div class="row mx-auto">
                <div class="col-10 px-0"> <input type="text" class="form-control" placeholder="댓글을 입력 하세요" id="comment" maxlength=""/></div>
                <div class="col-2 pr-0 "><a class="btn btn-secondary btn-block text-white card-link" id="submitCommBtn">확인</a></div>
              </div>
            <!-- </form> -->
          </div>
          
          <div id="commentDiv">
<c:forEach items="${commentList }" var="comment">
          <div class="card-footer">
             <div class="container">
                <div class="card-subtitle card-deck text-truncate text-info">(${comment.comord })&nbsp;${comment.nickname}&nbsp;&nbsp;<small class="text-dark"> ${comment.datetime}</small></div>
                <div class="card-text">${comment.comment} </div>
             </div>
          </div>
</c:forEach>
          </div>
        </div>
      </div>
    </div>
    
    <div class="row justify-content-center">  <!-- 하단 버튼 영역 -->
          <a class="btn btn-secondary col-3" href="javascript:history.go(-1)">목록으로</a>
        <!-- 글쓴이(board.useq) 와 세션 사용자(sessionScope.userInfo.useq) -->
       <c:if test="${board.useq == sessionScope.userInfo.useq }">
       	<a class="btn btn-primary col-3" href="deleteBoard.do?bseq=${board.bseq }">글삭제</a>
       </c:if>
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
  <script async src="http://platform.twitter.com/widgets.js" charset="utf-8"></script>
  <script>

    //  $("#writeComment").hide();
    //content 클래스를 가진 div를 표시/숨김(토글)
     // $("#commentBtn").click(function()
    //  {
    //    $( "#writeComment" ).slideToggle( "slow" );
    //  });
    
    //초기설정 - #voteDiv태그의 value 초기 값에 따라 색 지정한다.
    /* if( $("#voteDiv").attr("value") === "0"){
    	$("#upBtn").addClass("text-grey");
    	$("#voteCount").addClass("text-grey");
    	$("#downBtn").addClass("text-grey");
    }else if($("#voteDiv").attr("value") === "1"){
    	$("#upBtn").addClass("text-primary");
    	$("#voteCount").addClass("text-primary");
    	$("#downBtn").addClass("text-grey");
    }else if($("#voteDiv").attr("value") === "-1"){
    	$("#upBtn").addClass("text-grey");
    	$("#voteCount").addClass("text-blue");
    	$("#downBtn").addClass("text-blue");
    } */
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
    
    
    
    
    
    //댓글입력 관련 JQuery
    var useq = "${sessionScope.userInfo.useq}";
    var nickname = "${sessionScope.userInfo.nickname}";
    var bseq = "${board.bseq }";
    $("#submitCommBtn").click(function(){
    	if( useq === "" ){//로그인 정보가 없다면 로그인 모달 출력
    		$('#loginModal').modal('show');
    		return false;
    	}else if($("#comment").val()===""){//댓글내용이 비어져 있다면 그냥 동작 안함
    		console.log("입력된 댓글 없음");
    		return false;
    	}else{//이밖에 경우는 댓글이 입력 되도록 aja통신 진행
    		var data = {};
    	data["bseq"] = bseq;
    	data["useq"] = useq;
    	data["comment"] = $("#comment").val();
    	$("#comment").val('');
    		 $.ajax({
    			type:"POST",
    			url:"insertBoardComment.do",
    			contentType : "application/json",
    			dataType: "json",
    			data:JSON.stringify(data),
    			success: function(result){
    					console.log("통신 성공");
    					$("#commentDiv").empty();  //댓글 영역을 지우고
    					var html = '';             //댓글 코드을 넣을 변수
    					$(result).each(function(){ //List Comment들을 반복문을 통해서 뿌린다.
    						console.log(this);
    						html += "<div class="+"'card-footer'"+">";
    						html += "<div class="+"'container'"+">";
    						html += "<div class="+"'card-subtitle card-deck text-truncate text-info'"+">("+this.comord+")&nbsp;"+this.nickname;
    						html += "&nbsp;&nbsp;<small class="+"'text-dark'"+">"+this.datetime+"</small></div>"
    						html += "<div class="+"'card-text'"+">"+this.comment+"</div></div></div>";
    					});
    					$("#commentDiv").after(html);
    			}
    		});
    	}
    });
    
    // 좋아요 싫어요 버튼 관련 JQuery
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
        var checkTable="";

        //로그인이 되어 있지 않으면 모달창 팝업  return false;
    	if(useq === ""){
    		$('#loginModal').modal('show');
      		return false;
    	}
        if( btn_val === "upBtn"){//up 버튼 눌렀을 경우
          console.log("upBtn click!!");

          if(vote_val ==="1"){    //upBtn이 눌려져 있었을 경우 => default 상태로 만든다. (이미 vote 테이블에 데이터가 존재)
            $(obj).removeClass("text-primary").addClass("text-grey");
            voteCount.removeClass("text-primary").addClass("text-grey");
            voteDiv.attr("value",0);
            //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 0 으로 바꾼다.
            //board 테이블에 해당 bseq 데이터의 vote 값에다가 -1을 해준다.
            vote = "0";votesum = "-1";checkTable = "0";

          }else if(vote_val ==="0"){  // default인 경우 => up 상태로 만든다. (VOTE 테이블에 데이터가 있을 수도, 없을 수도 있는 경우)
            $(obj).removeClass("text-grey").addClass("text-primary");
            voteCount.removeClass("text-grey").addClass("text-primary");
            voteDiv.attr("value",1);
            //vote 테이블에 이력이 존재 한다면
            //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 1 으로 바꾼다.
            //board 테이블에 해당 bseq 데이터의 vote 값에다가 +1을 해준다.
            vote = "1";votesum = "+1";checkTable = "1";
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
          }


        }else if( btn_val ==="downBtn"){//down 버튼 눌렀을 경우
          console.log("downBtn click!!");

          if(vote_val === "-1"){//downBtn이 눌려져 있는 경우 => default 상태로 만든다. (이미 VOTE 테이블에 데이터가 존재)
            $(obj).removeClass("text-blue").addClass("text-grey");
            voteCount.removeClass("text-blue").addClass("text-grey");
            voteDiv.attr("value", 0);
            //vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 0 으로 바꾼다.
            //board 테이블에 해당 bseq 데이터의 vote 값에다가 +1을 해준다.
            vote = "0";votesum = "+1";checkTable = "0";

          }else if(vote_val === "0"){//default인 경우 => down 상태로 만든다.  (VOTE 테이블에 데이터가 있을수도, 없을 수도 있는 경우)
            $(obj).removeClass("text-grey").addClass("text-blue");
            voteCount.removeClass("text-grey").addClass("text-blue");
            voteDiv.attr("value", -1);
            //vote 테이블에 이력이 존재 한다면
            	//vote 테이블에 해당 useq, bseq와 일치하는 데이터을 찾아서 vote 값을 -1 으로 바꾼다.
            	//board 테이블에 해당 bseq 데이터의 vote 값에다가 -1을 해준다.
            vote = "-1";votesum = "-1";checkTable = "1";
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
          }
        }
        res = myfunc(vote, votesum, checkTable, bseq, useq);
        voteCount.text(res);
      }
 



  </script>
  <script>
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
