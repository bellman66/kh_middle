<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	<!-- ./resources/~ 선언식 -->
	
	<!-- 부트 스트랩 css 선언------------------------------ -->
	
	<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap.css" >
	<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="./resources/bootstrap/css/bootstrap-theme.css" >
	
	<!-- / css  ------------------------------ -->
</head>
<body>

	<h1>DB 확인을 위한 페이지</h1>

	<P>"처음 만든 버튼을 누르셨습니다.</P>
	<P>DB 조회 결과 : ${sampleAttribute}</P>

	<hr>


	<div>
		<form action="/sample.bgn" method="post">
			<button onclick="submit">처음 만든 버튼</button>
		</form>
	</div>


	<!-- 부트 스트랩 js 선언------------------------------ -->

	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>

	<!-- /부트 스트랩 js ------------------------------ -->

</body>
</html>
