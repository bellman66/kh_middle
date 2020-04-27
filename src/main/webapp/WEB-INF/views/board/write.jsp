<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 작성</h1>
	<form action="<%=request.getContextPath()%>/board/upload.do" method="post">
		<div>
			제목 : <input type="text" placeholder="제목을 입력하십쇼" name="notice_title" /><br><br> 
			내용 : <input style="width: 600px; height: 350px;" type="textarea" name="notice_content" /><br><br>
			<!-- 파일업로드 : <input name="file" type="file" name="" /><br><br> -->
				<button type="submit">업로드</button>
		</div>
	</form>
</body>
</html>