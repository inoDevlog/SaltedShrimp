<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <%@ include file="/nav/head.jsp" %>
  
    <script>
	function list(page){
		location.href="senMessage.do?curPage="+page;
	}
  </script>
  
</head>

<body>
   <!-- 상단네비게이션 -->  
  <%@include file="/nav/topNav.jsp" %>


  <!-- 보낸 메세지함 (테이블 형식) -->
  <div class="pt-5">
    <div class="container">
      <div class="row">
        <div class="page-header col-12">
          <h1>보낸 메세지함</h1>
          <hr>
        </div>
      </div>
      <div class="row">
        <div class="col-12 p-3">
          <table class="table table-hover">
            <thead class="thead-default">
              <tr class="bg-primary">
                <th scope="col">받는 사람</th>
                <th scope="col">내용</th>
              </tr>
            </thead>
            <!-- 보낸 메세지 List 꺼내오기 -->
            <tbody>
            	<c:forEach items="${map.msglist }" var="msg">
            		<tr>
						<td>${msg.recv_nick }</td>
						<td align = "left">${msg.message }</td>
					</tr>
				</c:forEach>
				
            </tbody>
          </table>
        </div>
      </div>
      <!-- 페이지 이동 버튼 -->
      <div class="row">
        <div class="col-12 p-3">
          <ul class="pagination justify-content-center">
            <c:if test="${map.mPager.curBlock > 1}">   
            	<li class="page-item">
              		<a class="page-link" href="javascript:list('1')" aria-label="Previous"> <span aria-hidden="true">&#xAB;</span> <span class="sr-only">Previous</span> </a>
              	</li>
              	<li class="page-item">   
              		<a class="page-link" href="javascript:list('${map.mPager.prevPage}')" aria-label="Previous"> <span aria-hidden="true">&#x2039;</span> <span class="sr-only">Previous</span> </a>
              	</li>
            </c:if>
            	
            <c:if test="${map.mPager.curBlock == 1}">
            	<li class="page-item disabled">	
              		<a class="page-link" href="javascript:list('1')" aria-label="Previous" > <span aria-hidden="true">&#xAB;</span> <span class="sr-only">Previous</span> </a>
            	</li>
            	<li class="page-item disabled">	
              		<a class="page-link" href="javascript:list('1')" aria-label="Previous" > <span aria-hidden="true">&#x2039;</span> <span class="sr-only">Previous</span> </a>
            	</li>
            </c:if>
            
            
            
            <c:forEach var="num" begin="${map.mPager.blockBegin}" end="${map.mPager.blockEnd}">
		        <!-- **현재페이지이면 하이퍼링크 제거 -->
		        <c:choose>
		              <c:when test="${num == map.mPager.curPage}">
		              	<li class="page-item disabled">
              				<a class="page-link" href="#">${num}</a>
            			</li>
		                   <%-- <span style="color: red">${num}</span>&nbsp; --%>
		               </c:when>
		               <c:otherwise>
		                   <li class="page-item">
              					<a class="page-link" href="javascript:list('${num}')">${num}</a>
            			   </li>
		               </c:otherwise>
		         </c:choose>
		    </c:forEach>

            <c:if test="${map.mPager.curBlock <= map.mPager.totBlock}">
            	<li class="page-item">
		        	<a class="page-link" href="javascript:list('${map.mPager.nextPage}')" aria-label="Previous"> <span aria-hidden="true">&#x203A;</span> <span class="sr-only">Previous</span> </a>
		        </li>
		        <li class="page-item">
		        	<a class="page-link" href="javascript:list('${map.mPager.totPage}')" aria-label="Previous"> <span aria-hidden="true">&#xbb;</span> <span class="sr-only">Previous</span> </a>
		        </li>
		    </c:if>
		    <c:if test="${map.mPager.curBlock > map.mPager.totBlock}">
            	<li class="page-item disabled">
		        	<a class="page-link" href="javascript:list('${map.mPager.nextPage}')" aria-label="Previous"> <span aria-hidden="true">&#x203A;</span> <span class="sr-only">Previous</span> </a>
		        </li>
		        <li class="page-item disabled">
		        	<a class="page-link" href="javascript:list('${map.mPager.totPage}')" aria-label="Previous"> <span aria-hidden="true">&#xbb;</span> <span class="sr-only">Previous</span> </a>
		        </li>
		    </c:if>
            
          </ul>
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
    