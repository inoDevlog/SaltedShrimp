$(document).ready(function() {
  // $('#inputPasswordCheck').focusout(function(){
//	var RegexPw = /^[a-zA-Z0-9]{8,16}$/;
	
  $('#needs-validation').submit(function(event) {
    if ($('#inputPassword').val() != $('#inputPasswordCheck').val()) {
      event.preventDefault();
      event.stopPropagation();
      $('#inputPasswordCheck').val("");
      $("#passwordCheckResult").text("비밀번호가 다릅니다.").show().fadeOut(1500);
    }
//    if ( !RegexPw.text($.trim($('#inputPassword').val() ))){
//    	event.preventDefault();
//    	$().addclass
//    	
//    }
    //닉네입 중복 확인 여부 (N/Y)
    if($("input[name=nickIsCheck").val() === "N"){//N 값이면
	  event.preventDefault();						//제출 불가
	  event.stopPropagation();
	  $("#nicknameCheck").removeClass();
	  $("#nicknameCheck").addClass("text-danger");	
      $("#nicknameCheck").text("닉네임 도금을 해주세요");
      return false;
    }
    
    
  });
  



});

(function() {
  'use strict';
  window.addEventListener('load', function() {
    var form = document.getElementById('needs-validation');
    form.addEventListener('submit', function(event) {
      if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
      }
      form.classList.add('was-validated');
    }, false);
  }, false);
})();
