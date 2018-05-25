<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="/nav/head.jsp"%>
</head>

<body>

	<!-- 상단네비게이션 -->
	<%@include file="/nav/topNav.jsp"%>


	<!-- title -->
	<div class="pt-5 mt-5">
		<div class="container">
			<div class="row">
				<!--제목 row-->
				<div class="col-12">
					<h4 class="display-4">회원가입</h4>
					<hr>
				</div>
			</div>
		</div>
	</div>

	<!--입력 폼  -->
    <div class="py-5">
      <div class="container">
        <div class="row">
          <div class="col-md-8 mx-auto ">

            <div class="bg-light p-3 form-control-sm">
              <h4 class="display-4">회원가입</h4>
    					<hr>
            <form id="joinform" class="" method="post" id="needs-validation" action="joinmember.do" novalidate>  <!--   -->
              <!-- 이메일 입력 -->
              <div class="form-group mb-3">
                <label class="form-control-label" for="inputEmail">이메일</label>
                <input class="form-control" id="inputEmail" type="text" placeholder="이메일" name="email">
                <div class="invalid-feedback" id="emailResult">
                  입력해주세요
                </div>
              </div>

              <!-- 비밀번호 입력  -->
              <div class="form-group mb-3">
                <label class="form-control-label" for="inputPassword">비밀번호 <small>(영어와 숫자를 조합하여 8-16자 이내로 입력해주세요)</small></label>
                <input class="form-control" id="inputPassword" type="password" placeholder="비밀번호" name="passwd">
                <div class="invalid-feedback" id="passwordResult">
                  입력해주세요
                </div>
              </div>

              <!-- 비밀번호 확인  -->
              <div class="form-group mb-3">
                <label class="form-control-label" for="inputPasswordCheck">비밀번호 확인</label>
                <input class="form-control" id="inputPassword2" type="password" placeholder="비밀번호 확인" name="inputPassword2">
                <div class="invalid-feedback small" id="passwordResult2">
                  비밀번호가 일치하지 않습니다.
                </div>
              </div>

              <!-- 닉네임 입력  -->
              <div class="form-group mb-3">
                <label class="form-control-label" for="inputName">닉네임</label><small>(한글, 영문, 숫자를 조합하여 10자 이내로 입력해주세요)</small>
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="닉네임" name="nickname" id="inputNickname" maxlength="10"/>
                  <span class="input-group-btn">
                    <button class="btn btn-secondary" id="nicknameButton" type="button">닉네임 도금</button>
                  </span>
                </div>
                <div class="" id="nicknameCheck">
                </div>
                <input type="hidden" id="isNicknameCheck" value="N" name="isNicknameCheck"/>
              </div>

              <!-- 핸드폰 번호 입력 -->
              <div class="form-group mb-3">
                <label class="form-control-label" for="inputNumber">휴대폰 번호</label>
                <input type="tel" class="form-control" id="inputMobileNumber" placeholder=" - 없이 입력해 주세요" name="mobile"/>
                <div class="invalid-feedback" id="moobileResult">
                  입력해주세요
                </div>
              </div>

              <div class="form-group py-2">
                <div class="col-sm-12 text-center">
                  <button class="btn btn-primary" id="joinBtn">회원가입</button>
                  <a class="btn btn-danger" href="mainBoard.do">가입취소<i class="fa fa-times spaceLeft"></i></a>
                </div>
              </div>
            </form>
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

	<script>
	$('#nicknameButton').click(function() {
		var deny_char = /[a-zA-Z가-힣0-9ㄱ-ㅎ+]{1,10}$/; //= /^[ㄱ-ㅎ가-힣A-Za-z0-9+]{8,16}$/;    /[^a-zA-Z가-힣]/;
		if (!deny_char.test($("#inputNickname").val())) {
			$("#nicknameCheck").addClass("text-danger").text("닉네임은 한글, 영문, 숫자로만 입력해 주세요.");
			return false;
		}
		//버튼을 클릭시 ajax 실행
		$.ajax({
			type : "GET",
			url : "checkNickname.do",
			dataType : "text",
			data : {
				"nickname" : $("#inputNickname").val()
			},
			success : function(data) {
				if (data === "1") {
					$("input[name=isNicknameCheck]").val("Y");
					$("#nicknameCheck").removeClass();
					$("#nicknameCheck").addClass("text-success");
					$("#nicknameCheck").text("사용 가능합니다.");

				} else {
					//input 값 지우고
					//$("#inputNickname").val("");
					$("input[name=isNicknameCheck]").val("N");
					$("#nicknameCheck").removeClass();
					$("#nicknameCheck").addClass("text-danger");
					$("#nicknameCheck").text("사용할 수 없습니다.");

				}
			}
		});

	})
	
		$("#joinBtn").click(function() {
					//입력 변수 및 검증용 정규식
					var valEmail = $("#inputEmail").val();
					valEmail = $.trim(valEmail);
					var re = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
					var valPassword = $("#inputPassword").val();
					valPassword = $.trim(valPassword);
					var rePassword = /^[A-Za-z0-9+]{8,16}$/;
					var valPassword2 = $("#inputPassword2").val();
					valPassword2 = $.trim(valPassword2);
					var valNickname = $("#inputNickname").val();
					valNickname = $.trim(valNickname);
					var isNicknameCheck = $("#isNicknameCheck").val();
					var valMobileNumber = $("#inputMobileNumber").val();
					valMobileNumber = $.trim(valMobileNumber);
					var reMobileNumber = /^[0-9]{8,11}$/;

					//데이터가 입력 되었는지 확인
					if (valEmail === "") {
						$("#inputEmail").addClass("is-invalid");
						return false;
					}
					if (valPassword === "") {
						$("#inputPassword").addClass("is-invalid");
						return false;
					}
					if (valNickname === "") {
						//nicknameFeedback
						$("#nicknameCheck").addClass("text-danger").text(
								"닉네임을 입력하세요");
						return false;
					}
					if (valMobileNumber === "") {
						$("#inputMobileNumber").addClass("is-invalid");
						return false;
					}

					//유효성 검증
					//inputEmail 태그 값이 null이 아닌지 확인한다.
					if (!re.test(valEmail)) {//이메일 형식에 맞는지 확인 한다.
						$("#inputEmail").addClass("is-invalid");
						$("#emailResult").text("이메일 형식을 확인 해 주세요");
						return false;
					}

					//password
					if (!rePassword.test(valPassword)) {//비밀번호 형식에 맞는지 확인 한다.
						$("#inputPassword").addClass("is-invalid");
						$("#passwordResult").text("비밀번호 형식을 확인 해 주세요");
						return false;
					}

					//password 확인
					if (valPassword != valPassword2) {
						$("#inputPassword2").addClass("is-invalid");
						return false;
					}

					//nickname
					if (isNicknameCheck === "N") {
						$("#nicknameCheck").addClass("text-danger").text(
								"중복확인을 해주세요");
						return false;
					}
					if ( (!reMobileNumber.test(valMobileNumber)) | (valMobileNumber.search("-") != -1)  ) {//비밀번호 형식에 맞는지 확인 한다.
						$("#inputMobileNumber").addClass("is-invalid");
						$("#moobileResult").text(
								'핸드폰 형식이 올바르지 않습니다. ( "-"없이 입력해 주세요)');
						return false;
					}
					$("#needs-validation").submit();
					

				})

		//값이 입력되면 is-invalid 태그 제거
		$("input").keyup(function(event) {
			$(this).removeClass("is-invalid");
			//$(this).removeClass("is-valid");
		})

		//값이 변경되면(키보드가눌릴경우) nickname value 값을 N으로 바꾼다.  isNicknameCheck
		$("#inputNickname").keyup(function(event) {
			$("#isNicknameCheck").val("N");
			$("#nicknameCheck").removeClass().text("");
		})

		//닉네임은 한글, 영문, 숫자로만 입력해 주세요.
		$("#inputNickname").focusout(function() {
			var deny_char = /[a-zA-Z가-힣0-9ㄱ-ㅎ+]{1,5}$/; //= /^[ㄱ-ㅎ가-힣A-Za-z0-9+]{8,16}$/;    /[^a-zA-Z가-힣]/;
			if (!deny_char.test($(this).val())) {
				$("#nicknameCheck").addClass("text-danger").text("닉네임은 한글, 영문, 숫자로만 입력해 주세요.");
			}
		})
	</script>


</body>

</html>
