<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!DOCTYPE HTML>
<html>
	<head>
	
		<title>Stellar by HTML5 UP</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="./resources/steller/assets/css/main.css" />
		<link rel="stylesheet" href="./resources/steller/assets/css/map.css" />
		
		<link rel="stylesheet" href="./resources/steller/bootstrap-3.3.2-dist/css/bootstrap.css" />
		<link rel="stylesheet" href="./resources/steller/bootstrap-3.3.2-dist/css/bootstrap-theme.css" />
		<link rel="stylesheet" href="./resources/steller/bootstrap-3.3.2-dist/css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="./resources/steller/bootstrap-3.3.2-dist/css/bootstrap.min.css" />
		

</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header" class="alt">
						<span class="logo"><img src="./resources/steller/images/logo.svg" alt="" /></span>
						<h1> 나만의 주유소</h1>
						
						<div>
							<a class="login_form" href="login"> login </a>
						</div>
					</header>

				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a href="#intro" class="active"> 오늘자 가격 </a></li>
							<li><a href="#first">First Section</a></li>
							<li><a href="#second">Second Section</a></li>
							<li><a href="#cta">Get Started</a></li>
						</ul>
					</nav>

				<!-- Main -->
					<div id="main">

						<!-- Introduction -->
							<section id="intro" class="main">
								<div class="spotlight">
									<div class="content">
										<header class="major">
										<h2>오늘자 평균 주유 가격</h2>
										</header>

										<table class="table-bordered">
										<thead class="thead-dark">
											<tr>
												<th scope="col">제품명</th>
												<th scope="col">평균가격</th>
												<th scope="col">등락값</th>
											</tr>
										</thead>
										
										<tbody>
										<c:forEach items="${avgAllPrice}" var="vo">
											<tr>
												<th scope="row">${vo.PRODNM}</th>
												<td>${vo.PRICE}</td>
												<td>${vo.DIFF}</td>
											</tr>
										</c:forEach>
										</tbody>
										</table>
					</div>
									<span class="image"><img src="./resources/steller/images/fuel.png" alt="" /></span>
								</div>
							</section>

						<!-- First Section -->
			<section id="first" class="main special">
				<header class="major">
					<h2>지역 찾기</h2>
				</header>
				
						<!-- kakao map.  -->
						<div class="map_wrap">
							<div id="map" style="width: 100%; height: 350px;"></div>
						</div>
						
						<div class="button_wrap">
							<button id="find_button"> 위치 변경 버튼 </button>
						</div>
	
			</section>

						<!-- Second Section -->
							<section id="second" class="main special">
								<header class="major">
									<h2> 개발 페이지 </h2>
								</header>
								
							</section>

						<!-- Get Started -->
							<section id="cta" class="main special">
								<header class="major">
									<h2>Congue imperdiet</h2>
									<p>Donec imperdiet consequat consequat. Suspendisse feugiat congue<br />
									posuere. Nulla massa urna, fermentum eget quam aliquet.</p>
								</header>
								<footer class="major">
									<ul class="actions special">
										<li><a href="generic.html" class="button primary">Get Started</a></li>
										<li><a href="generic.html" class="button">Learn More</a></li>
									</ul>
								</footer>
							</section>

					</div>

				<!-- Footer -->
					<footer id="footer">
						<section>
							<h2>Aliquam sed mauris</h2>
							<p>Sed lorem ipsum dolor sit amet et nullam consequat feugiat consequat magna adipiscing tempus etiam dolore veroeros. eget dapibus mauris. Cras aliquet, nisl ut viverra sollicitudin, ligula erat egestas velit, vitae tincidunt odio.</p>
							<ul class="actions">
								<li><a href="generic.html" class="button">Learn More</a></li>
							</ul>
						</section>
						<section>
							<h2>Etiam feugiat</h2>
							<dl class="alt">
								<dt>Address</dt>
								<dd>1234 Somewhere Road &bull; Nashville, TN 00000 &bull; USA</dd>
								<dt>Phone</dt>
								<dd>(000) 000-0000 x 0000</dd>
								<dt>Email</dt>
								<dd><a href="#">information@untitled.tld</a></dd>
							</dl>
							<ul class="icons">
								<li><a href="#" class="icon brands fa-twitter alt"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon brands fa-facebook-f alt"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon brands fa-instagram alt"><span class="label">Instagram</span></a></li>
								<li><a href="#" class="icon brands fa-github alt"><span class="label">GitHub</span></a></li>
								<li><a href="#" class="icon brands fa-dribbble alt"><span class="label">Dribbble</span></a></li>
							</ul>
						</section>
						<p class="copyright">&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="./resources/steller/assets/js/jquery.min.js"></script>
			<script src="./resources/steller/assets/js/jquery.scrollex.min.js"></script>
			<script src="./resources/steller/assets/js/jquery.scrolly.min.js"></script>
			<script src="./resources/steller/assets/js/browser.min.js"></script>
			<script src="./resources/steller/assets/js/breakpoints.min.js"></script>
			<script src="./resources/steller/assets/js/util.js"></script>
			<script src="./resources/steller/assets/js/main.js"></script>
			
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=732d43eaffe0e338eb5b1d0a7a9710fc&libraries=services,clusterer,drawing"></script>
			<script type="text/javascript" src="./resources/steller/assets/js/map.js" charset="utf-8"> </script> 
			<script type="text/javascript" src="./resources/steller/assets/js/test.js"> </script>
	</body>
</html>