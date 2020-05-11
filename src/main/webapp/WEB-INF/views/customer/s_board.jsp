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
							<h2>고객게시판</h2>
						</header>
						<div>
							<table border="1">
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>작성일자</th>
									<th>조회수</th>
								</tr>
								<!-- forEach 문은 리스트 객체 타입을 꺼낼때 많이 활용된다. -->
								<c:forEach var="row" items="${list}">
									<tr>
										<!-- 컨트롤러에서 넘겨준 list 모델 객체를 쓰는 방법을 잘 익혀두자 -->
										<td>${row.s_no}</td>
										<!-- 게시물 조회를 위해서 get방식으로 게시물번호 값을 넘겨주자 -->
										<td><a
											href="${pageContext.request.contextPath}/customer/s_read.do${pageMaker.makeQuery( pageMaker.cri.page )}&s_no=${row.s_no}">${row.s_title}</a></td>
										<td>${row.s_writer}</td>
										<td>
											<!-- 데이터 타입을 사용하는 방법 --> <fmt:formatDate
												value="${row.s_regDate}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>${row.s_viewCnt}</td>
									</tr>
								</c:forEach>
							</table>

							<!-- 페이징 처리하는 방법 -->
							<div class="box-footer">
								<div class="text-center">
									<ul class="pagination">
										<c:if test="${pageMaker.prev}">
											<li><a
												href="${pageContext.request.contextPath}/customer/s_board.do${pageMaker.makeQuery(pageMaker.startPage -1) }">이전
											</a></li>
										</c:if>
										<c:forEach var="idx" begin="${pageMaker.startPage }"
											end="${pageMaker.endPage }">
											<li><c:out value="${pageMaker.cri.page == idx?'':''}" />
												<a
												href="${pageContext.request.contextPath}/customer/s_board.do${pageMaker.makeQuery(idx)}">${idx }</a>
											</li>
										</c:forEach>
										<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
											<li><a
												href="${pageContext.request.contextPath}/customer/s_board.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>

						<form
							action="${pageContext.request.contextPath}/customer/writer_page.do">
							<button
								onclick="if(${sessionScope.userData == null}){alert('로그인하세요'); return false;}">글쓰기</button>
						</form>
					</div>
				</div>
			</section>
		</div>
	</div>

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

