<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet"/>
 
 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/member.css"/>
<title>Login Page</title>
</head>
<body>   
<div class="memberLoginWrapper">
	<h1 class="loginMsg" style="text-decoration:none !important;"><a href="<%=request.getContextPath()%>/member/index.do" >나만의 주유소</a></h1>
	<div class="loginImple">
		<form action="<%=request.getContextPath() %>/member/memberlogin.do" method="post">
				<strong style="font-size:120%;">ID</strong>
					<div>
						<input class="memberLoginUserId" type="text" name="userId" id="userId" style="width:230px"/><br>
					</div>	
				<strong style="font-size:120%;">PW</strong>
					<div>
					<input class="memberLoginUserPw" type="password" name="userPass" id="userPass"style="width:230px" /> 
					</div>			
					<br>
			<div class="buttonWrapper">
				<button type="submit" id="loginBnt" class="loginBnt">로그인</button><button type="button" onclick="location.href='<%=request.getContextPath() %>/member/memberjoin.do'"class="joinBnt">회원가입</button>
			</div>
		</form>
	</div>
		<c:set  var="client_id" value = "c618b7fc9b808f184bdafc0406409848"/>
		<c:set var="redirectURL" value = "http://localhost:7070/middle/member/kakaologin.do"/>
	<div class="socialLogin">
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirectURL }&response_type=code">
			<img src = "${pageContext.request.contextPath}/resources/member/kakao/kakao_login_button.png"/>
		</a>
	</div>
</div>
<script>

 init = function(){
	 
	 <c:if test="${isSuccess eq 'false'}">
		 alert("로그인에 실패했습니다")
	 </c:if>
		 
	<c:if test="${kakaoJoin eq 'true'}">
		alert("카카오 회원가입이 성공했습니다");
	</c:if>
 }
 
init();
</script>


<!-- 네이버아이디로로그인 버튼 노출 영역 -->
</body>
</html>