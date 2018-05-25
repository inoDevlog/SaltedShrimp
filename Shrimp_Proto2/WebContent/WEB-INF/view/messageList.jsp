<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="/nav/head.jsp"%>

<script>
	function list(page) {
		location.href = "inbox.do?curPage=" + page;
	}
</script>

</head>

<body>
	<!-- 상단네비게이션 -->
	<%@include file="/nav/topNav.jsp"%>




	<!-- ver.2 -->
	<div class="mt-5">
		<div class="container">
			<div class="row">
				<div class="col-md-8 mx-auto">
					<!-- 새로운 메세지 보내는 버튼 -->
					<div class="font-weight-bold text-center h4 mb-0 pt-2 pb-3">
						<a class="text-blue card-link" href="#writeNewMessageModal"
							data-toggle="modal"> <i class="fas fa-plus-circle fa-fw"></i>
							NEW MESSAGE
						</a>
					</div>
					<!-- 메세지 List 영역 -->
					<div class="list-group" style="cursor: pointer;">

						<c:forEach items="${map.msglist }" var="msg">
		                     <a href="getDialogue.do?recv_nick=${msg.recv_nick }" class="list-group-item list-group-item-light p-3">${msg.recv_nick } <span
		                        class="h5"><b>${msg.recv_nick } </b></span> 
		                        <span class="badge badge-secondary">2</span> 
		                        <span class="fas fa-trash-alt fa-lg fa-pull-right" style="color: red;" onclick="location.href='msgdelete.do?recv_nick=${msg.recv_nick}'; return false;"></span>
		                     </a>
                  </c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 페이지 이동 버튼 -->
	<div class="mb-5 mt-2">
		<div class="container">
			<div class="row">
				<div class="col-12 p-3">
					<ul class="pagination justify-content-center">
						<c:if test="${map.mPager.curBlock > 1}">
							<li class="page-item"><a class="page-link"
								href="javascript:list('1')" aria-label="Previous"> <span
									aria-hidden="true">&#xAB;</span> <span class="sr-only">Previous</span>
							</a></li>
							<li class="page-item"><a class="page-link"
								href="javascript:list('${map.mPager.prevPage}')"
								aria-label="Previous"> <span aria-hidden="true">&#x2039;</span>
									<span class="sr-only">Previous</span>
							</a></li>
						</c:if>

						<c:if test="${map.mPager.curBlock == 1}">
							<li class="page-item disabled"><a class="page-link"
								href="javascript:list('1')" aria-label="Previous"> <span
									aria-hidden="true">&#xAB;</span> <span class="sr-only">Previous</span>
							</a></li>
							<li class="page-item disabled"><a class="page-link"
								href="javascript:list('1')" aria-label="Previous"> <span
									aria-hidden="true">&#x2039;</span> <span class="sr-only">Previous</span>
							</a></li>
						</c:if>



						<c:forEach var="num" begin="${map.mPager.blockBegin}"
							end="${map.mPager.blockEnd}">
							<!-- **현재페이지이면 하이퍼링크 제거 -->
							<c:choose>
								<c:when test="${num == map.mPager.curPage}">
									<li class="page-item disabled"><a class="page-link"
										href="#">${num}</a></li>
									<%-- <span style="color: red">${num}</span>&nbsp; --%>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="javascript:list('${num}')">${num}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${map.mPager.curBlock <= map.mPager.totBlock}">
							<li class="page-item"><a class="page-link"
								href="javascript:list('${map.mPager.nextPage}')"
								aria-label="Previous"> <span aria-hidden="true">&#x203A;</span>
									<span class="sr-only">Previous</span>
							</a></li>
							<li class="page-item"><a class="page-link"
								href="javascript:list('${map.mPager.totPage}')"
								aria-label="Previous"> <span aria-hidden="true">&#xbb;</span>
									<span class="sr-only">Previous</span>
							</a></li>
						</c:if>
						<c:if test="${map.mPager.curBlock > map.mPager.totBlock}">
							<li class="page-item disabled"><a class="page-link"
								href="javascript:list('${map.mPager.nextPage}')"
								aria-label="Previous"> <span aria-hidden="true">&#x203A;</span>
									<span class="sr-only">Previous</span>
							</a></li>
							<li class="page-item disabled"><a class="page-link"
								href="javascript:list('${map.mPager.totPage}')"
								aria-label="Previous"> <span aria-hidden="true">&#xbb;</span>
									<span class="sr-only">Previous</span>
							</a></li>
						</c:if>

					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 하단네비게이션 -->
	<%@include file="/nav/bottomNav.jsp"%>
	
<!-- write New Message Modal -->
<div class="modal fade" id="writeNewMessageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none; text-align: center">
  <div class="modal-dialog col-md-8 mt-5 section-light">
    <div class="modal-content bg-light">
      <div class="modal-body">
        <div class="text-left">
          <form class="p-3" id="sendMessageForm" method="post" action="send.do">
            
            <div class="form-group my-2">
              <label class="form-control-label" for="nicknameInput">받는 사람</label>
              <!-- ajax 이용시 사용자가 존재하지 않으면 이 태그에 is-invalid 클래스 추가  -->
              <input type="text" class="form-control" id="nicknameInput" name="recv_nick" placeholder="닉네임">
              <small class="invalid-feedback">닉네임이 존재하지 않습니다. 다시 입력해 주세요.</small>
            </div>
            <div class="form-group my-2"> <label>TEXT</label>
              <textarea class="form-control" style="resize:none;" name="message" rows="8" cols="80" placeholder="메세지를 입력하세요" required></textarea>
            </div>
            <input type="submit" class="btn mt-4 btn-block p-2 btn-secondary text-white font-weight-bold" value="SEND"/>
 
          </form>
        </div>
      </div>
      <div class="modal-footer">
        <a class="text-primary font-weight-bold card-link" data-dismiss="modal" href>x close</a>
      </div>
    </div>
  </div>
</div>


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

<script>


$("#nicknameInput").focusout(function(){
            $(this).removeClass("is-invalid");
            var inputNickname = $(this).val();
            var ajaxResult = "";
            
            if( inputNickname === ""){
                $(this).val("");
                $(this).addClass("is-invalid");
                return false;
              }
            
            //통신하기
            $.ajax({
			type: "GET",
			url: "msgNickCheck.do",
			dataType: "text",
			data: {"nickname" : inputNickname },
			success: function(data){  // 메세지 입력 창에서 메세지를 받을 사용자 닉네임을 입력하면
				console.log(data)     //  존재하는 닉네임인지(1), 존재하지 않는 닉네임인지 (0) 리턴한다.
				if(data === "0"){
		              $("#nicknameInput").val("");
		              $("#nicknameInput").addClass("is-invalid");
		         }else{
		        	 $("#nicknameInput").removeClass("is-invalid");
		         }
			}
		});
});


$("#sendMessageForm").submit(function(event){
	  if ($('#nicknameInput').val() === "") {
	    return false;
	  }
	});

</script>
</body>

</html>
