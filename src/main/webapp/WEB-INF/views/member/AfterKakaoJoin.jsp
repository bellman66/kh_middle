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
<script src="https://code.jquery.com/jquery-3.5.0.js"
		integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		crossorigin="anonymous"></script>
	<form action="<%=request.getContextPath() %>/member/kakaojoin.do" 
	method="post" onsubmit="return validate()">
	
	<input type="hidden" name ="userId" value="${userData.user_id }"/>
	<c:if test="${nickNameFlag eq 'false'}">
		동일한 닉네임이 존재합니다, 새로운 닉네임을 입력해주세요 
			<input type="text" id="nickName" name="checkNick">
			<div id="nickCheck"></div>
			<button type="button" onclick="nickNameCheck()">중복체크</button>
	</c:if>
		<input type="hidden" id="submitNick" name ="nickName" value="${userData.nick_name }"/>
	차 유종 : <select name="kind-oil">
		<option value="가솔린">가솔린 </option>
		<option value="경유">경유</option>
		<option value="LPG">LPG</option>
		<option value="고급휘발유">고급휘발유</option>
	</select>
	<button type="submit">가입하기</button>
	</form>

<script>


	function validate(){
		console.log($("#submitNick").val());
		if($("#submitNick").val() == ""){
			alert("닉네임 중복검사를 진행해주세요");
			return false;
		}
		return true;
	};

	function nickNameCheck(){
		
		$.ajax({
		
			url:"/middle/member/nicknamecheck.do",
			type:"get",
			data:$("#nickName").serialize(),
			success:function(data){
				console.log(data);
				console.log($("#nickName").val());
				if(data == 'true'){
					$("#nickCheck").html("");
					$("#nickCheck").html("사용가능한 닉네임입니다");
					$("#submitNick").val($("#nickName").val());
				}else{
					$("#submitNick").val("");
					$("#nickCheck").html("");
					$("#nickCheck").html("사용불가능한 닉네임입니다");
				}
				
			}
			
			
		});
		
	};


</script>
</body>
</html>