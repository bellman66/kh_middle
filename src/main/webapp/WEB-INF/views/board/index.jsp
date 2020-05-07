<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>게시판</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" />
		<style>
		.pagination { 
	 	  display: inline-block;
		  transform:translateX(29%);
		}
		
		.pagination a {
		  color: black;
		  float: left;
		  padding: 8px 16px;
		  text-decoration: none;
		}
		
		.pagination a.active {
		  background-color: #4CAF50;
		  color: white;
		}
		</style>
	</head>
	
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

			<!-- Header -->
			<header id="header">
			</header>
					
				<!-- Main -->
				<div id="main">
				<section id="content" class="main">
				<div>
				<h1 style="float:left;">게시판</h1>
				<a style="float:right" href="<%=request.getContextPath()%>/board/write.do">
					<button onclick="if(${sessionScope.userData == null}){alert('로그인하세요');return false;}">게시물작성</button>
				</a>
				</div>
				<br><br>
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
	
			<!-- ==============여기서부터 페이징================== -->
			<div class="pagination">
				<!-- 이전 장 (10번째 이전 페이지)-->
				<c:if test="${pageMaker.prev}">
				<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.startPage -1 }">
					&laquo; </a>
				</c:if>
				<c:if test="${!pageMaker.prev}">
				<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.startPage }">
					&laquo; </a>
				</c:if>
			<!-- ============================================= -->
			  <!-- 이전 페이지 -->
				<c:if test="${pageNum gt 1 }">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum-1}">&lt;</a>
				</c:if>		
				<c:if test="${pageNum eq 1 }">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum }">&lt;</a>
				</c:if>
			<!-- ============================================= -->
				 <!-- 현재 페이지(숫자) -->		
				<c:forEach var="num" begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${num }">${num }</a>
				</c:forEach>
			<!-- ============================================= -->
				  <!-- 다음페이지 -->
				<c:if test="${pageNum lt pageMaker.allPageCnt }">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum+1 }">&gt;</a>
				</c:if>
				<c:if test="${pageNum eq pageMaker.allPageCnt}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageNum }">&gt;</a>
				</c:if>
			<!-- ============================================= -->
			 	 <!-- 다음 장(10번째 이후 페이지) -->
				<c:if test="${ pageMaker.next}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.endPage + 1 }">
						&raquo; </a>
				</c:if>
				<c:if test="${ !pageMaker.next}">
					<a href="<%= request.getContextPath()%>/board/index.do?pageNum=${pageMaker.endPage }">
						&raquo; </a>
				</c:if>
			</div>
			<!-- ============================================= -->

		</div>
		</section>

	</body>
</html>
