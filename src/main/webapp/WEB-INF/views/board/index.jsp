<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
	
</style>
<head>
<%@ include file="header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/board/index.css"
	rel="stylesheet" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<body>
	<div class="wrapper">
		<%-- <h1>${noticeList }</h1> --%>
			<div id="write">
			<h1 id="h1"><a href="<%=request.getContextPath()%>/board/index.do?pageNum=1">게시판</a></h1>
			<a href="<%=request.getContextPath()%>/board/write.do"><button>게시물작성</button></a>
			
			<table>
				<thead>
					<tr>
						<th class="no">글번호</th>
						<th class="title">제목</th>
						<th class="id">글쓴이</th>
						<th class="date">작성일</th>
						<th class="cnt">조회</th>
						<th class="recommend">추천</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${noticeList}" var="notice" varStatus="cnt">
						<tr>
							<!-- 중간 글 삭제해도 글번호 바뀌지않음 -->
							<td class="no">${cnt.count+((pageNum-1)*5) }</td>
							<td class="title"><a
								href="<%= request.getContextPath()%>/board/detail.do?noticeNum=${notice.notice_num}">${notice.notice_title }</a></td>
							<td class="id">${notice.notice_id }</td>
							<td class="date">${notice.notice_date }</td>
							<td class="cnt">${notice.notice_count }</td>
							<td class="recommend">${notice.notice_recommend }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
			<!-- ======== Paging ========= -->
			<div class="paging">
			
					<!-- 이전 장 (10번째 이전 페이지)-->
					<span class="paginate_button previous">
					<c:if test="${pageMaker.prev}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.startPage -1 }">
						Prev </a>
					</c:if>
					<c:if test="${!pageMaker.prev}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.startPage }">
						Prev </a>
					</c:if>
					</span>
					
					<!-- 이전 페이지 -->
					<c:if test="${pageNum gt 1 }">
					<span class="paginate_button prev">
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum-1}"><c:out value="<"/></a>
					</span>
					</c:if>		
					<c:if test="${pageNum eq 1 }">
					<span class="paginate_button prev">
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum }"><c:out value="<"/></a>
					</span>
					</c:if>
					
					<!-- 현재 페이지(숫자) -->		
					<c:forEach var="num" begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }">
					<span class='paginate_button'>
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${num }">${num }</a>
					</span>
					</c:forEach>
					
					<!-- 다음페이지 -->
					<c:if test="${pageNum lt pageMaker.allPageCnt }">
					<span class="paginate_button next">
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum+1 }"><c:out value=">"/></a>
					</span>
					</c:if>
					<c:if test="${pageNum eq pageMaker.allPageCnt}">
					<span class="paginate_button next">
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum }"><c:out value=">"/></a>
					</span>
					</c:if>
	
					<!-- 다음 장(10번째 이후 페이지) -->
					<span class="paginate_button next">
					<c:if test="${ pageMaker.next}">
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.endPage + 1 }">
							Next </a>
					</c:if>
					<c:if test="${ !pageMaker.next}">
						<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.endPage }">
							Next </a>
					</c:if>
					</span>
			</div>
		</div>
	</div>
	
</body>

</html>
