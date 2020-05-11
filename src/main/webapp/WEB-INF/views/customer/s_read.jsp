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
	background: #2B2D36;
	padding: 5px 10px;
	color: #AAABAF;
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
	background: #D24545;
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
	color: #FFFFFF;
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
							<h2>상세페이지</h2>
						</header>
						<table border="1">
							<th>글 정보</th>
							<th>글 상세 정보</th>
							<tr>
								<td>글번호</td>
								<td>${data.s_no}</td>
							</tr>
							<tr>
								<td>글쓴이</td>
								<td>${data.s_writer}</td>
							</tr>
							<tr>
								<td>글제목</td>
								<td>${data.s_title}</td>
							</tr>
							<tr>
								<td>작성일자</td>
								<td><fmt:formatDate value="${data.s_regDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<tr>
								<td>조회수</td>
								<td>${data.s_viewCnt}</td>
							</tr>
							<tr>
								<td>글내용</td>
								<td>${data.s_content}</td>
							</tr>
						</table>
						<c:if test="${data.s_writer eq sessionScope.userData.nick_name}">
							<input type="button" value="수정"
								onclick='location.href="${pageContext.request.contextPath}/customer/update.do?s_no=${data.s_no}";'>
							<input type="button" value="삭제"
								onclick='location.href="${pageContext.request.contextPath}/customer/delete.do?s_no=${data.s_no}";'>
						</c:if>
					</div>
				</div>
				<br> <br>
				<form
					action="${pageContext.request.contextPath}/customer/comment.do"
					method="post" id="write_comment_form">
					<h4>댓글작성</h4>
					<input id="comment_txt" style="width: 80%;" maxlength="30"
						placeholder="댓글을 입력해 주세요" type="text" name="comment_content" />
					<button class="button"
						onclick="if(${sessionScope.userData == null}){alert('로그인하세요');return false;}"
						id="comment_submit_btn" type="submit">등록</button>
					<input type="hidden" name="s_no" value="${data.s_no }" />
				</form>
				<br> <br>
				<div id="show_comment">
					<h4>댓글 보기(${comment.size() })</h4>
					<c:if test="${ comment != null}">
						<c:forEach var="cmt" items="${comment }">
							<div class="allDiv">
								<span id="comment_id${cmt.comment_num }">${cmt.comment_id }
									: </span> <span id="comment_content${cmt.comment_num }">${cmt.comment_content }</span>
								<c:if
									test="${cmt.comment_id eq sessionScope.userData.nick_name }">
									<form
										action="${pageContext.request.contextPath}/customer/commentmodify.do"
										method="post">
										<input id="comment_num" type="hidden" name="comment_num"
											value="${cmt.comment_num }" /> <input type="hidden"
											name="s_no" value="${cmt.s_no }" /> <input
											id="complete${cmt.comment_num }" class="button" type="hidden"
											value="작성완료" /> <input class="button"
											id="modifybtn${cmt.comment_num }" type="button"
											onclick="modify(this);" value="수정" /> <input
											style="width: 60%;" id="modifytxt${cmt.comment_num }"
											type="hidden" name="comment_content" />
									</form>
									<form
										action="${pageContext.request.contextPath}/customer/commentdelete.do"
										method="post">
										<input type="hidden" name="comment_num"
											value="${cmt.comment_num }" /> <input type="hidden"
											name="s_no" value="${cmt.s_no }" />
										<button class="button" id="deletebtn${cmt.comment_num }"
											onclick="if(!confirm('삭제하시겠습니까?')){return false;}"
											type="submit">삭제</button>
									</form>
								</c:if>
								<p class="clear"></p>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</section>
		</div>
	</div>
	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.5.0.js"
		integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		crossorigin="anonymous"></script>
	<script>
	function modify(e){
		var modifybtn = 'modifybtn';
		var comment_num = e.id.substring(modifybtn.length);
		$('#modifytxt'+comment_num).val($('#comment_content'+comment_num).html().trim());
		$('#modifytxt'+comment_num).attr("type","text");
		
		$('#comment_content'+comment_num).remove();
		$('#comment_id'+comment_num).remove();
		
		$('#modifybtn'+comment_num).attr("type", "hidden");
		$('#complete'+comment_num).attr("type", "submit");
	}
 	</script>
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