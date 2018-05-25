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





	<!-- Link Post Form -->
	<div class="py-5 my-3">
		<div class="container">
			<div class="row">
				<div class="col-md-8 mx-auto">
					<!-- 제목 -->
					<div class="bg-light p-3 form-control-sm">
						<h4 class="display-4 text-center">POST TEXT</h4>
						<hr>
						<!-- 입력폼 -->
						<form class="" method="post" action="insertBoard.do">
						  <input type="hidden" name="btype" value="1"> <!-- 1:텍스트 -->
							<div class="form-group my-2">
								<!-- 게시판 선택 드롭다운 -->
								<div class="dropdown">
									<label>SELECT BOARD</label>
									<select class="form-control" name="ccode">
										<option class="dropdown-item select" value="1" selected="TRUE">DUKJIL</option>
										<option class="dropdown-item" value="2" disabled>YOUTUBE</option>
										<option class="dropdown-item" value="3" disabled>TWITTER</option>
									</select>
								</div>
							</div>
							<!-- 글 제목 -->
							<div class="form-group my-2">
								<label>TITLE</label> <input class="form-control" name="title"
									placeholder="제목">
							</div>
							<!-- 글 내용 -->
							<div class="form-group my-2">
								<label>TEXT</label>
								<textarea class="form-control" name="article" rows="8" cols="80"
									style="resize: none;"></textarea>
							</div>
							<button type="submit"
								class="btn mt-4 btn-block p-2 btn-secondary">
								<b>등록</b>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>





	<!-- Text Post Form -->
<!-- 	<div class="pt-5">
		<div class="container">
			<div class="row">
				<div class="page-header col-12">
					<h1>Text Post</h1>
					<hr>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<form class="p-4 section-light bg-light" method="post"
						action="insertBoard.do">
						<div class="form-group my-2">
							게시판 선택 드롭다운
							<div class="dropdown">
								<label>SELECT BOARD</label> <select class="form-control"
									name="btype">
									<option class="dropdown-item select" value="1" selected="TRUE">DUKJIL</option>
									<option class="dropdown-item" value="2">YOUTUBE</option>
									<option class="dropdown-item" value="3">TWITTER</option>
								</select>
							</div>
						</div>
						제목
						<div class="form-group my-2">
							<label>TITLE</label> <input class="form-control" name="title"
								placeholder="제목">
						</div>
						글 양식
						<div class="form-group my-2">
							<label>TEXT</label>
							<textarea class="form-control" name="article" rows="8" cols="80"></textarea>
						</div>
						<button type="submit" class="btn mt-4 btn-block p-2 btn-secondary">
							<b>등록</b>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div> -->












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
	<!-- Script: Smooth scrolling between anchors in the same page -->


</body>

</html>
