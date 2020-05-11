<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- fmt를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>

<html>
<head>
<title>고객게시판</title>

<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />

<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css"
	rel="stylesheet" />


<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	if(${sessionScope.userData!=null}) {
		sessionStorage.setItem("user_id","${sessionScope.userData.user_id}");
		sessionStorage.setItem("kind_oil","${sessionScope.userData.kind_oil}");
		sessionStorage.setItem("nick_name","${sessionScope.userData.nick_name}");
	}
</script>

<style>
.overlaybox {
	position: relative;
	width: auto;
	height: auto;
	/* background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png') no-repeat; */
	background-color: #48494B;
	padding: 15px 10px;
	border: 1px solid red;
	border-radius: 10px;
}

.overlaybox div, ul {
	overflow: hidden;
	margin: 0;
	padding: 0;
}

.overlaybox li {
	list-style: none;
}

.overlaybox .boxtitle {
	color: #fff;
	font-size: 16px;
	font-weight: bold;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png')
		no-repeat right 120px center;
	margin-bottom: 8px;
	float: left;
}

.overlaybox .first {
	position: relative;
	width: 247px;
	height: 136px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumb.png')
		no-repeat;
	margin-bottom: 8px;
}

.first .text {
	color: #fff;
	font-weight: bold;
}

.first .triangle {
	position: absolute;
	width: 48px;
	height: 48px;
	top: 0;
	left: 0;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png')
		no-repeat;
	padding: 6px;
	font-size: 18px;
}

.first .movietitle {
	position: absolute;
	width: 100%;
	bottom: 0;
	background: rgba(0, 0, 0, 0.4);
	padding: 7px 15px;
	font-size: 14px;
}

.overlaybox ul {
	width: 247px;
	max-width: 247px;
}

.overlaybox li {
	position: relative;
	margin-bottom: 2px;
	background: #2b2d36;
	padding: 5px 10px;
	color: #aaabaf;
	line-height: 1;
}

.overlaybox li span {
	display: inline-block;
}

.overlaybox li .number {
	font-size: 14px;
	font-weight: bold;
	left: 2px;
}

.overlaybox li .title {
	font-size: 13px;
	left: 20px;
}

.overlaybox ul .arrow {
	position: absolute;
	margin-top: 8px;
	right: 25px;
	width: 5px;
	height: 3px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png')
		no-repeat;
}

.overlaybox li .up {
	background-position: 0 -40px;
}

.overlaybox li .down {
	background-position: 0 -60px;
}

.overlaybox li .count {
	position: absolute;
	margin-top: 5px;
	right: 15px;
	font-size: 10px;
}

.overlaybox li:hover {
	color: #fff;
	background: #d24545;
}

.overlaybox li:hover .up {
	background-position: 0 0px;
}

.overlaybox li:hover .down {
	background-position: 0 -20px;
}

.titleWrapper {
	width: auto;
	height: auto;
}

.close_btn {
	position: relative;
	border: 1px solid red;
	float: left;
	margin: 0 0 0 0;
	top: 0;
	right: 5px;
	color: #ffffff;
	width: 10px !important;
	height: 10px !important;
	max-width: 15px;
	max-height: 15px;
	background-size: auto;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png')
		no-repeat;
}

.close_btn:hover {
	cursor: pointer;
}

.buttonWrapper {
	width: auto;
	height: auto;
	float: left;
}

.buttonWrapper #product {
	width: auto;
	height: auto;
	float: left;
}

.buttonWrapper #text {
	width: 100px;
	height: auto;
	float: left;
}

#star a {
	text-decoration: none;
	color: gray;
}

#star a.on {
	color: red;
}
</style>

</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<span class="logo"><img
				src="${pageContext.request.contextPath}/resources/steller/images/logo.svg"
				alt="" /></span>
			<h1>나만의 주유소</h1>

		</header>

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/steller/index.do">홈화면</a></li>
				<li><a
					href="${pageContext.request.contextPath}/board/index.do?pageNum=1">자유게시판</a></li>
				<li><a
					href="${pageContext.request.contextPath}/customer/s_board.do">고객게시판</a></li>
			</ul>
		</nav>

		<!-- Main -->
		<div id="main">

			<!-- Introduction -->
			<section id="intro" class="main">
				<div class="spotlight">
					<div class="content">
						<header class="major">
							<h2>질문 및 건의사항</h2>
						</header>
						<form name="bdto" method="post" action="insert.do">
							<div>
								제목<br>
								<input name="s_title" id="s_title" size="80"
									placeholder="글 제목 입력">
							</div>
							<div>
								내용<br>
								<textarea name="s_content" id="s_content" rows="8" cols="80"
									placeholder="글 내용 입력"></textarea>
							</div>
							<div>
								<input type="submit" value="확인">
								<input type="reset" value="취소">
							</div>
						</form>
					</div>
				</div>
			</section>
		</div>
	</div>

	<!-- Scripts -->

	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/jquery.scrollex.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/jquery.scrolly.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/browser.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/breakpoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/util.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/steller/assets/js/main.js"></script>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=732d43eaffe0e338eb5b1d0a7a9710fc&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/steller/assets/js/map.js"
		charset="utf-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/steller/assets/js/test.js"></script>
</body>
</html>

