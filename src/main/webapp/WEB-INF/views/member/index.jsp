<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
	<c:if test="${sessionScope.userData ne null }">
		환영합니다 <c:out value="${sessionScope.userData.nick_name }"/>님 
		<form action="<%=request.getContextPath()%>/member/logout.do">
			<button type="submit" class="login_form">로그아웃</button>		 
		</form>
		
		<form action="<%=request.getContextPath()%>/member/mypage.do">
			<button type="submit" class="login_form">마이페이지</button>		
		</form> 
	</c:if>
	<c:out value="${sessionScope.userData }'userData'"/>
	<c:out value="${sessionScope.accessToken }'accessScope'"/>
	<c:if test="${sessionScope.userData eq null }">
		<div>
			<form
				action="<%=request.getContextPath()%>/member/memberloginpage.do">
				<button type="submit" class="login_form">login</button>
			</form>
		</div>
		<div>
			<form action="<%=request.getContextPath()%>/member/memberjoin.do">
				<button type="submit" class="login_form">회원가입</button>
			</form>
		</div>
	</c:if>
</body>
</html>