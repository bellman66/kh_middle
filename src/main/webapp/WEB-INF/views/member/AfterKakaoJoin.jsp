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
<div class="afterKakaoJoin">
	<form action="<%=request.getContextPath() %>/member/kakaojoin.do" 
	method="post" onsubmit="return validate()">
	
	
	<strong>최초 사용자는 추가 입력 사항이 필요합니다. 하단 입력사항을 기재해주세요 .</strong><br><br>
	
	<input type="hidden" id="user_id" name ="userId" value="${userData.user_id }"/>
	<c:if test="${nickNameFlag eq 'false'}">
		<strong>닉네임</strong>
			<input type="text" id="nickName" name="checkNick"style="width:230px"/>
			<button type="button" onclick="nickNameCheck()">중복체크</button>
			<span id="nickCheck"></span>
	</c:if>
		<input type="hidden" id="submitNick" name ="nickName" value="${userData.nick_name }"/><br>
	<strong>차 유종</strong> <select name="kind-oil" style="width:230px">
		<option value="가솔린">가솔린 </option>
		<option value="경유">경유</option>
		<option value="LPG">LPG</option>
		<option value="고급휘발유">고급휘발유</option>
	</select>
	<br><br>
	<button type="submit">가입하기</button>
	</form>
</div>
<script src="${pageContext.request.contextPath}/resources/member/js/AfterKakaoJoin.js"></script>
</body>
</html>