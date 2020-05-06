<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet" />
</head>
<body>

		<div class="header">
			<a class="logo">나만의 주유소</a>
			<a class="join">로그인</a>
			<a class="join">회원가입</a>
			<div class="inheader">
				<a class="menu">메인메뉴</a>
				<a class="menu" href="${pageContext.request.contextPath }/board/index.do?pageNum=1">게시판</a>
				<a class="menu">문의게시판</a>
			</div>
	</div>

</html>