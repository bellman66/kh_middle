<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/member.css"/>
</head>
<body>
<div class="memberWrapper">
   <c:if test="${sessionScope.userData eq null }">
		<div class="memberTop">
			<a href="<%=request.getContextPath()%>/member/memberloginpage.do">login</a>
			<a href="<%=request.getContextPath()%>/member/memberjoin.do">회원가입</a>
		</div>
	</c:if>
	<c:if test="${sessionScope.userData ne null }">
		<div class="memberTop">
		환영합니다 <c:out value="${sessionScope.userData.nick_name }"/>님 
			<a href="<%=request.getContextPath()%>/member/mypage.do">마이페이지</a>
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
		</div>	
	</c:if>
</div>	
</body>
</html>