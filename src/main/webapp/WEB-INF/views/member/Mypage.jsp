<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet"/>


<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/member/css/member.css"/>

</head>
<body>

<div class="myPageWrapper">
<h1 class="loginMsg" style="margin-right:48px;"><a href="<%=request.getContextPath()%>/stle/index.do">나만의 주유소</a></h1>
			
			<div class="mypageMemberWrapper">
			
			<table class="memberTable" style="width:50%; transform:translateX(50%);">
			<caption style="transform:translateX(40%);"><strong>마이페이지</strong></caption>
				<tr>
					<td><strong>아이디</strong></td>
					<td><span class="userId">${sessionScope.userData.user_id }</span></td>
				</tr>
				<tr>
					<td><strong>유종</strong></td>
					<td><span class="userId">${sessionScope.userData.kind_oil }</span></td>
				</tr>
				<tr>
					<td><strong>닉네임</strong></td>
					<td><span class="userId">${sessionScope.userData.nick_name }</span></td>
				</tr>
				<tr>
					<td><strong>회원등급</strong></td>
					<td><span class="userId">${sessionScope.userData.grade }</span></td>
				</tr>
			
			</table>
			
			<form action="<%=request.getContextPath() %>/member/withdraw.do"
				onsubmit="return validate();">
				<button type="submit">회원탈퇴</button>
			
			</form>
	</div>
</div>	
<script>

function validate(){
	
	var flag = confirm("정말로 탈퇴하시겠습니까?");
	
	if(!flag){
		return false;
	}
	
	return true;
}
	



</script>
	
	
	
</body>
</html>