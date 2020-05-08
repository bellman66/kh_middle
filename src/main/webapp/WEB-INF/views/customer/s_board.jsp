<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- fmt를 사용하기위한 태그 라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale= 1.0 ">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/pat_market.css">

<script
	src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<title>고객게시판</title>

</head>

<body>
	<!-- 메뉴페이지를 포함시켜준다. -->
	<%@ include file="../customer/include/menu.jsp"%>

	<div>
		<form name="searchForm" action="<c:url value="${path}/customer/s_board.do" />"
			method="GET">
			<select name="searchType">
				<option value="all"
					<c:out value="${searchType =='all'? 'selected':'' }"/>>제목+이름+내용</option>
				<option value="writer"
					<c:out value="${searchType =='writer'? 'selected':'' }"/>>이름</option>
				<option value="content"
					<c:out value="${searchType =='content'? 'selected':'' }"/>>내용</option>
				<option value="title"
					<c:out value="${searchType =='title'? 'selected':'' }"/>>제목</option>
			</select> <input type="text" name="keyword" value="${keyword}"
				placeholder="검색"> <input id="submit" type="submit"
				value="검색">
		</form>
	</div>
	<br>
	<b>${count}</b>개의 게시물이 있습니다.
	<br><br>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
		<!-- forEach 문은 리스트 객체 타입을 꺼낼때 많이 활용된다. -->
		<c:forEach var="row" items="${list}">
			<tr>
				<!-- 컨트롤러에서 넘겨준 list 모델 객체를 쓰는 방법을 잘 익혀두자 -->
				<td>${row.s_no}</td>
				<!-- 게시물 조회를 위해서 get방식으로 게시물번호 값을 넘겨주자 -->
				<td><a href="${path}/customer/s_read.do?s_no=${row.s_no}">${row.s_title}</td>
				<td>${row.s_writer}</td>
				<td>
					<!-- 데이터 타입을 사용하는 방법 --> <fmt:formatDate value="${row.s_regDate}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>${row.s_viewCnt}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이징 처리하는 방법 -->
	<table>
		<tr>
			<c:if test="${pageMaker.prev}">
				<td><a
					href='<c:url value="${path}/customer/s_board.do?page=${pageMaker.startPage-1}"/>'>&laquo;</a>
				</td>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<td><a
					href='<c:url value="${path}/customer/s_board.do?page=${idx}"/>'>${idx}</a>
				</td>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<td><a
					href='<c:url value="${path}/customer/s_board.do?page=${pageMaker.endPage+1}"/>'>&raquo;</a>
				</td>
			</c:if>
		</tr>
	</table>
	<div style="text-align: left;">
		<a href="${path}/customer/writer_page.do">글쓰기</a>
	</div>

</body>
</html>
