<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<!--  head 기본 정보 -->
<%@ include file="/nav/head.jsp" %>

  <script>
	function list(page){
		location.href="getBoardList.do?curPage="+page+"&searchOption=${map.searchOption}"+"&keyword=${map.keyword}";
	}
  </script>
  
  
</head>

<body>

 <!-- 상단네비게이션 -->  
  <%@include file="/nav/topNav.jsp" %>







  <!-- title -->
  <div class="mt-5">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h4 class="display-4">DUKJIL (${sessionScope.userInfo.email} )</h4>
        </div>
      </div>
    </div>
  </div>

  <!-- Table -->
  <div class="py-4">
    <div class="container">
      <div class="row mx-auto">
        <div class="col-sm-12">
        
        <!-- 검색 부분 -->
        <form name="form1" method="post" action="getBoardList.do">
	        <select name="searchOption">
	            <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
	       <%-- <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >제목+이름+제목</option> --%>
	            <option value="article" <c:out value="${map.searchOption == 'content'?'selected':''}"/> >내용</option>
	            <option value="title" <c:out value="${map.searchOption == 'title'?'selected':''}"/> >제목 </option>
	            <option value="nickname" <c:out value="${map.searchOption == 'nickname'?'selected':''}"/> >작성자</option> 
	        </select>
	        <input name="keyword" value="${map.keyword}">
	        <input type="submit" value="검색">
        
        
          <table class="table table-hover table-striped">
            <thead class="bg-dark text-light">
              <tr>
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">글쓴이</th>
                <th scope="col">등록일</th>
                <th scope="col">조회수</th>
              </tr>
            </thead>
            <tbody>
              <!-- 게시물 예시 페이지와 연결되는 테이블 행 -->
              <c:forEach items="${map.list }" var="board">
				<tr>
					<td>${board.bseq }</td>
					<td align = "left"><a href="getBoard.do?bseq=${board.bseq }&useq=${board.useq}">
										${board.title }</a></td>
				
					<td>${board.nickname }</td>
					<td>${board.datetime }</td>
					<td>${board.hit }</td>
				</tr>
				</c:forEach>
				<tr>
		            <td colspan="5">
		                <!-- **처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
		                <c:if test="${map.boardPager.curBlock > 1}">
		                    <a href="javascript:list('1')">[처음]</a>
		                </c:if>
		                
		                <!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
		                <c:if test="${map.boardPager.curBlock > 1}">
		                    <a href="javascript:list('${map.boardPager.prevPage}')">[이전]</a>
		                </c:if>
		                
		                <!-- **하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
		                <c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
		                    <!-- **현재페이지이면 하이퍼링크 제거 -->
		                    <c:choose>
		                        <c:when test="${num == map.boardPager.curPage}">
		                            <span style="color: red">${num}</span>&nbsp;
		                        </c:when>
		                        <c:otherwise>
		                            <a href="javascript:list('${num}')">${num}</a>&nbsp;
		                        </c:otherwise>
		                    </c:choose>
		                </c:forEach>
		                
		                <!-- **다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
		                <c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
		                    <a href="javascript:list('${map.boardPager.nextPage}')">[다음]</a>
		                </c:if>
		                
		                <!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
		                <c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
		                    <a href="javascript:list('${map.boardPager.totPage}')">[끝]</a>
		                </c:if>
		            </td>
		        </tr>
            </tbody>
          </table>
          </form>
        </div>
      </div>
    </div>
  </div>

  





  
<!-- 하단네비게이션 -->  
  <%@include file="/nav/bottomNav.jsp" %>
  







  <!-- Footer -->
  <div class="py-3 bg-dark">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <p class="lead">Sign up to our newsletter for the latest news</p>
          <form class="form-inline">
            <div class="form-group">
              <input type="email" class="form-control" placeholder="Your e-mail here"> </div>
            <button type="submit" class="btn ml-2 btn-secondary">Subscribe</button>
          </form>
        </div>
        <div class="row">
          <div class="col-md-12 my-3 text-center">
            <p class="text-muted">© Copyright 2017 Pingendo - All rights reserved.</p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- JavaScript dependencies -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>

</html>
    