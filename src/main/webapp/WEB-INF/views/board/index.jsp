<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
	ul {
		list-style: none;
		margin: 0;
		padding: 0;
	}
	 
	li {
		list-style: none;
		margin: 0 0 0 0;
		padding: 0 0 0 0;
		border: 0;
		float: left;
	}
</style>
<head>
<link
	href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css"
	rel="stylesheet" />

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>게시판</title>

</head>

<body>
	<div>
		<h1><a href="<%=request.getContextPath()%>/board/index.do?pageNum=1">게시판</a></h1>
		<%-- <h1>${noticeList }</h1> --%>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/board/write.do"><button>게시물작성</button></a>
		<table>
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회</th>
					<th>추천</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noticeList}" var="notice">
					<tr>
						<td>${notice.notice_num }</td>
						<td><a
							href="<%= request.getContextPath()%>/board/detail.do?noticeNum=${notice.notice_num}">${notice.notice_title }</a></td>
						<td>${notice.notice_id }</td>
						<td>${notice.notice_date }</td>
						<td>${notice.notice_count }</td>
						<td>${notice.notice_recommend }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- ======== Paging ========= -->
		<div>
			<ul>
				<li class="paginate_button previous">
				<c:if test="${pageMaker.prev}">
				<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.startPage -1 }">
					Prev </a>
				</c:if>
				<c:if test="${!pageMaker.prev}">
				<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.startPage }">
					Prev </a>
				</c:if>
				</li>
						
				<c:forEach var="num" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<li class='paginate_button'>
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${num }">${num }</a>
					</li>
				</c:forEach>

				<li class="paginate_button next">
				<c:if test="${ pageMaker.next}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.endPage + 1 }">
						Next </a>
				</c:if>
				<c:if test="${ !pageMaker.next}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.endPage }">
						Next </a>
				</c:if>
				</li>
			</ul>
		</div>

	</div>

</body>

</html>
