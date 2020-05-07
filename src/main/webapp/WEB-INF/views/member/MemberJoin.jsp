<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet"/>



<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/member.css"/>
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.0.js"
		integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		crossorigin="anonymous"></script>
	<div class="joinWrapper">
	<h1 class="loginMsg"><a href="<%=request.getContextPath()%>/member/index.do">나만의 주유소</a></h1>
	<div class="join">
	<form action="<%=request.getContextPath()%>/member/memberjoinimple.do"
		method="post" onsubmit="return validate();">
		<div class="loginWrapper">
			<strong>ID </strong> 
				<div>
					<input class="user_id" id="user_id" type="text" name="user_id"  style="width:230px"/>
					<button type="button" onclick="idcheck()">중복 확인</button>
					<span id="idcheck" class="addMsg"></span>
				</div>
			
			<strong>PW</strong> 
				<div>
					<input class="user_pw" id="user_pw" type="password" name="user_pw" style="width:230px"/>
					<span id="pwCheck" class="addMsg"></span>
				</div>
			<strong>PW재확인 </strong>
				<div>
					<input class="user_pw2" id="user_pw2" type="password" name="user_pw" style="width:230px" />
					<span id="pwCheck2" class="addMsg"></span>
				</div>
			<strong>닉네임 </strong>
				<div>
					<input class="nick" type="text" name="checkNick" id="nickname" style="width:230px" />
					<button type="button" onclick="nickNameCheck()">중복체크</button>
					<span id="nickCheck" class="addMsg"></span>
				</div>
			<strong>차 유종 </strong>
				<div>
					<select name="oil_kind" class="oil_kind" style="width:230px">
						<option value="가솔린" checked>가솔린</option>
						<option value="경유">경유</option>
						<option value="LPG">LPG</option>
						<option value="고급휘발유">고급휘발유</option>
					</select>
				</div>
		</div>

		<div class="captcharWrapper">
			<div id="captcharWrapper">
				<div id="captcharImg">
					<img id="imgSrc" src=""> <input type="hidden"
						id="captcharImgSrc" name="captcharImgSrc" />
				</div>
			</div>
			<div class="captcharImg" id="captcharImg">
				<input type="hidden" id="key" name="key">
			</div>
			<div class="capctharRes">
				<input class="captchar"type="text" id="capctharRes" name="capctharRes" style="width:230px" />
			<span id="captcharAnswer" class="addMsg"></span>
			</div>
			<button type="button" onclick="sertiCaptchar()" id="captchar">인증</button>
			<button type="button" id="reflash" onclick="reflashImg()">이미지변경</button>
		</div>
		<button type="submit">회원가입하기</button>
	</form>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/member/js/memberJoin.js"></script>


</body>
</html>