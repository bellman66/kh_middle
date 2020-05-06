<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/board/write.css"
	rel="stylesheet" />
</head>
<body>

	<form action="<%=request.getContextPath()%>/board/upload.do"
		method="post" enctype="multipart/form-data">
		<div class="wrapper">
			<h1 id="h1">게시물 작성</h1>
			<div id="write">
				<div id="write">
					<input id="title" type="text" placeholder="제목을 입력해 주세요"
					name="notice_title" /><br><br>
					<textarea id="content" name="notice_content"></textarea><br> 
					<input id="file" name="noticeFile" type="file" /><br><br>
					<button id="reset" type="button">취소</button>
					<button type="submit">등록</button>
				</div>
			</div>
		</div>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.5.0.js"
		integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		crossorigin="anonymous"></script>
		
	<script>
		$('#reset').click(function() {
			history.back();
		})
	</script>
</body>
</html>