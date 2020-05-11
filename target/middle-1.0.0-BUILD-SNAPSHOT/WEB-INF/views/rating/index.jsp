<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<html>
<head>
	<title>Home</title>
	<!-- ./resources/~ 선언식 -->
	
	<!-- 부트 스트랩 css 선언------------------------------ -->
	<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap.css" >
	<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap-theme.css" >
	<link rel="stylesheet" href="./resources/bootstrap/css/pat_market.css" >
	
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- / css  ------------------------------ -->
</head>
<body id="bootstrap-overrides">

	<!--  -->
	Rating Test Page
	<header class="navbar navbar-static-top bs-docs" id="top" role="banner">		
		<div class="container">

			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button"
					data-toggle="collapse" data-target=".bs-navbar-collapse">					
					<span class="sr-only">네비게이션 on/off</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				
				<a href="index.do" class="navbar-brand">Animal Project</a>
			</div>
			
			<nav class="collapse navbar-collapse bs-navbar-collapse">
				<ul class="nav navbar-nav">
					<li>
						<a href="index.do">index</a>
					</li>
					<li>
						<a href="home.do">index</a>
					</li>
					<li>
						<a href="Zombie_Cube_Escape.do">Zombie</a>
					</li>
				</ul>
			</nav>
			
		</div>
	</header>
	<!-- / header end -->
	
	<!-- banner start -->
	
	<div class="bs-docs-header" id="content" tabindex="-1">
		
		<div class="container">
			<h1>Animal project page</h1>
			<p> 동물 관련 이슈 및 정보 제공 </p>
			
			<div id="carbonads-container">
				<div class="carbonad">
					<div id="azcarbon">
						<script type="text/javascript">
							google_ad_client = "ca-pub-5850631189163450";
							google_ad_slot = "3534287123";
							google_ad_width = 320;
							google_ad_height = 100;
						</script>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<!-- / banner end -->

	<!-- main start -->
	
	<div class="main">
		<div class="container">

			<hr>

			<div class="table-page">
				<div class="table-head">
					<h3> 사용자 리스트 </h3>
				</div>

				<div class="table-body">
					<table class="table">
						<thead>
							<tr>
								<th>고유 번호</th>
								<th>카테고리 번호</th>
								<th>아이디</th>
								<th>패스워드</th>
								<th>이름</th>
								<th>나이</th>
								<th>주소</th>
								<th>번호</th>
								<th>링크 고유번호</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${user_list}" var="vo">
								<tr>
									<td>${vo.user_code}</td>
									<td>${vo.category_number}</td>
									<td>${vo.user_id}</td>
									<td>${vo.user_password}</td>
									<td>${vo.user_name}</td>
									<td>${vo.user_age}</td>
									<td>${vo.user_zipcode}</td>
									<td>${vo.user_phone}</td>
									<td>${vo.user_linkcode}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>


<!-- 			db 설정 후 사용
			<form action="home.do" method="post">
				<button onclick="submit">이동 버튼</button>
			</form> -->
			
		</div>
	</div>

	<!-- / main end -->
	
	<!-- dev_part  -->
	
	<div class="markat">
		<div class="container">

			<div class="table_set">
				<h3>장터 리스트</h3>

				<table class="table">
					<thead>
						<tr>
							<th>NO.</th>
							<th>Photo</th>
							<th>Board title </th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${board_product}" var="vo">
							<tr>
								<td>${vo.board_category_count}</td>
								<td>
									<img src= "${vo.board_img_url}" height=100 width=auto align=middle>
								</td>
								<td>
									<table style="height: 100px;">
										<tr align="center">
											<td>제목</td>
											<td>${vo.board_title}</td>
										</tr>
										<tr align="center">
											<td>아이디</td>
											<td>${vo.user_id}</td>
										</tr>
									</table>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


		</div>
	</div>
	
	<!-- / dev_part end -->

	<!-- 부트 스트랩 js 선언------------------------------ -->
	
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
	
	<!-- /부트 스트랩 js ------------------------------ -->
</body>
</html>
