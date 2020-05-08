<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" />
	<body class="is-preload"> 
  
		<!-- Wrapper -->
			<div id="wrapper">

			<!-- Header -->
			<header id="header">
				<h1>게시물 작성</h1><br>
			</header>
			
				<!-- Main -->
				<div id="main">
				<section id="content" class="main">
					<form action="<%=request.getContextPath()%>/board/upload.do"
						method="post" enctype="multipart/form-data">
						<div class="wrapper">
							<div id="write">
								<div id="write">
									<input id="title" maxlength="30" type="text" placeholder="제목을 입력해 주세요"
									name="notice_title" /><br><br>
									<textarea id="content" name="notice_content"></textarea><br> 
									<input id="file" name="noticeFile" type="file" /><br><br>
									<button id="reset" type="button">취소</button>
									<button id="submit" type="submit">등록</button>
								</div>
							</div>
						</div>
					</form>
				</section>
				</div>
			</div>
	
	<script src="https://code.jquery.com/jquery-3.5.0.js"
		integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
		crossorigin="anonymous"></script>
		
	<script>
	
		$('#reset').click(function() {
			history.back();
		})
		
		$('#content').on('keyup', function() {
			if($(this).val().length > 700) {
				alert("글자수는 700자 이내로 제한됩니다.");
				$(this).val($(this).val().substring(0, 700));
			}
		});
		
		$('#submit').click(function(){
			if($('#title').val().trim() == ''){
				alert('제목을 입력해주세요');
				return false;
			}
		});
	</script>
</body>
</html>