<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" />
<link href="${pageContext.request.contextPath}/resources/css/board/search.css"
	rel="stylesheet" />
</head>
<body>
  	
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper"> 

			<!-- Header -->
			<header id="header">
			</header>
			
				<!-- Main -->
				<div id="main">
				<section id="content" class="main">
					<div id="write">
						<input type="hidden" id="search" value="${search }">
						<h1>SEARCH RESULT</h1>
						'${search }'에 대한 카카오(다음)검색결과 입니다   
						<button onclick="history.back();">뒤로가기</button>
						<c:if test="${ search eq ''}">
							<h2>검색하신 내용이 없습니다</h2>
						</c:if>
						
						<c:if test="${ search ne ''}">
						<p id="title"></p>
						<p id="contents"></p>
						<p id="url"></p>
						<p id="datetime"></p>
						<table border="1">
							<thead>
								<tr>
									<th>글 제목</th>
									<th>글 내용</th>
								</tr>
							</thead>
							<tbody id="body">
							</tbody>
						</table>
						</c:if>	
						</div>
					</div>
				</section>
				</div>
			</div>
	
	<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(
			function search(searchKeyword){
				$.ajax({
					method : "GET",
					url : "https://dapi.kakao.com/v2/search/web",
					data : {
						query : $('#search').val(),
					},
					headers : { Authorization: "KakaoAK 2a6df2408901e0c1783ade83dd6d7766"}
				})
				.done(function(msg){
					for(var i = 0; i < msg.documents.length; i++){
						$('#body').append("<tr><td><a href='"+msg.documents[i].url+"'>"
								+ msg.documents[i].title+"</a></td>"
								+ "<td>"+msg.documents[i].contents+"</td></tr>");
					 }
				});
		});
	
	</script>
</body>

</html>