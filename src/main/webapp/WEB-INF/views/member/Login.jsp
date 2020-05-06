<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>   
	<form action="<%=request.getContextPath() %>/member/memberlogin.do" method="post">
		<div>
			id : <input type="text" name="userId" id="userId"/><br>
			pw : <input type="password" name="userPass" id="userPass"/> 
		</div>
		<button type="submit">로그인</button>
	</form>
	<c:set  var="client_id" value = "c618b7fc9b808f184bdafc0406409848"/>
	<c:set var="redirectURL" value = "http://localhost:7070/middle/member/kakaologin.do"/>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=${client_id}&redirect_uri=${redirectURL }&response_type=code">
		<img src = "/middle/resources/member/kakao/kakao_login_button.png"/>
	</a>
	<c:out value="${sessionScope.userInfo}"/>
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