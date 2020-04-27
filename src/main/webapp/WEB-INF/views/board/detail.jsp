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
	<div>
		<div>
			<h4 class="reset">${notice.noticeTitle }</h4>
		</div>
		<div class="info">
			<span>등록일 : ${notice.notice_date }</span> 
			<%-- <span><a href="/servletBM/notice/noticedownload.do?ofname=${data.notice.original_filepath }
				&rfname=${data.notice.rename_filepath}">첨부파일 : ${data.original_filepath }</a>
			</span> --%>
		</div>
		<div class="text">${notice.noticeContent }</div>
		<div>
			<a href="<%= request.getContextPath()%>/board/index.do"><span>목록</span></a>
		</div>
	</div>
</body>
</html>