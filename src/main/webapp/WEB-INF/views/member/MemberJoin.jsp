<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.0.js"
		integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		crossorigin="anonymous"></script>
	<form action="<%=request.getContextPath()%>/member/memberjoinimple.do"
		method="post" onsubmit="return validate();">
		<div class="loginWrapper">
			<div>
				id : <input class="user_id" id="user_id" type="text" name="user_id" />
			</div>
			<div id="idcheck"></div>
			<button type="button" onclick="idcheck()">중복 확인</button>
			<div>
				pw : <input id="user_pw" type="password" name="user_pw">
			</div>
			<div>
				pw재확인 : <input id="user_pw2" type="password" name="user_pw" />
			</div>
			<div id="pwCheck"></div>
			<div id="pwCheck2"></div>
			<div>
				닉네임 : <input type="text" name="checkNick" id="nickname" />
				<button type="button" onclick="nickNameCheck()">중복체크</button>
				<div id="nickCheck"></div>
			</div>
			<div>
				차 유종 : <select name="oil_kind">
					<option value="가솔린" checked>가솔린</option>
					<option value="경유">경유</option>
					<option value="LPG">LPG</option>
					<option value="고급휘발유">고급휘발유</option>
				</select>
			</div>
			<div>
				이메일 : <input type="email" name="email" id="email" />
			</div>
			<div>
				휴대번호 :<input type="text" name="user_tell" id="usertell" />
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
				<input type="text" id="capctharRes" name="capctharRes" />
			</div>
			<div id="captcharAnswer"></div>
			<button type="button" onclick="sertiCaptchar()" id="captchar">인증</button>
			<div id="captchar_certi"></div>


			<button type="button" id="reflash" onclick="reflashImg()">이미지변경</button>
		</div>
		<button type="submit">회원가입하기</button>
	</form>

<script src="${pageContext.request.contextPath}/resources/member/js/memberJoin.js"></script>


</body>
</html>