<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	/* 헤더 사진 넘기기 */
  /* Make the image fully responsive */
  .carousel-inner img {
    width: 100%;
    /* height: 100%; */
    height: 150px;
  }
</style>
<div class="jumbotron text-center" style="margin-bottom:0">
  <div id="demoView" class="carousel slide" data-ride="carousel">

	  <!-- Indicators -->
	  <ul class="carousel-indicators">
	    <li data-target="#demoView" data-slide-to="0" class="active"></li>
	    <li data-target="#demoView" data-slide-to="1"></li>
	    <li data-target="#demoView" data-slide-to="2"></li>
	  </ul>
	  
	  <!-- The slideshow -->
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="<%=request.getContextPath()%>/images/1.jpg" alt="봄의 왈츠" width="100%" height="150px">
	    </div>
	    <div class="carousel-item">
	      <img src="<%=request.getContextPath()%>/images/2.jpg" alt="여름향기" width="100%" height="150px">
	    </div>
	    <div class="carousel-item">
	      <img src="<%=request.getContextPath()%>/images/3.jpg" alt="가을동화" width="100%" height="150px">
	    </div>
	    <div class="carousel-item">
	      <img src="<%=request.getContextPath()%>/images/4.jpg" alt="겨울연가" width="100%" height="150px">
	    </div>
	  </div>
	  
	  <!-- Left and right controls -->
	  <a class="carousel-control-prev" href="#demoView" data-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </a>
	  <a class="carousel-control-next" href="#demoView" data-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </a>
	</div>
</div>