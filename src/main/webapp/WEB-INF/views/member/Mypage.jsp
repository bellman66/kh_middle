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


	<table border="1">
		<tr>
			<td>아이디</td>
			<td>${sessionScope.userData.user_id }</td>
		</tr>
		<tr>
			<td>유종</td>
			<td>${sessionScope.userData.kind_oil }</td>
			<td><button>정보 수정</button></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>${sessionScope.userData.nick_name }</td>
			<td><button>정보 수정</button></td>
		</tr>
	</table>
	<form action="<%=request.getContextPath() %>/member/withdraw.do"
	onsubmit="return validate();">
		<button type="button" onclick="withdraw()">회원탈퇴</button>
	
	</form>
	
<script>

var flag = false;

	function withdraw(){
		console.log(flag);
		var withdraw = confirm("정말로 탈퇴하시겠습니까?"); 
		
		console.log(withdraw);
		flag = withdraw;
		console.log(flag);
	}
	
function validate(){
	console.log(flag);
	if(!flag){
		return false;
	}
	
	return true;
}
	



</script>
	
	
</body>
</html>