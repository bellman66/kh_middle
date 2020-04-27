<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>



<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>게시판</title>

</head>

<body>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">게시판</h1>
					<%-- <h1>${noticeList }</h1> --%>
				</div>
				<!-- /.col-lg-12 -->
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
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
									<td><a href="<%= request.getContextPath()%>/board/detail.do?noticeNum=${notice.notice_num}">${notice.notice_title }</a></td>
									<td>${notice.notice_id }</td>
									<td>${notice.notice_date }</td>
									<td>${notice.notice_count }</td>
									<td>${notice.notice_recommend }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<a href="<%=request.getContextPath()%>/board/write.do"><button>게시물작성</button></a>
			</div>
		</div>
	</div>

</body>

</html>
