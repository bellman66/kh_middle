<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/main.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/steller/assets/css/map.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body> 
	<div>
		<div>
			<h4 class="reset">제목 : ${detail.notice_title }</h4>
		</div>
		<div>
			<span>등록일 : ${detail.notice_date }</span> 
			<%-- <span><a href="/servletBM/notice/noticedownload.do?ofname=${data.notice.original_filepath }
				&rfname=${data.notice.rename_filepath}">첨부파일 : ${data.original_filepath }</a>
			</span> --%>
		</div>
		<div>내용 : ${detail.notice_content }</div>
		<div>
			<label>작성자 : ${detail.notice_id }  |  작성일 : ${detail.notice_date }</label>
			<label>조회 ${detail.notice_count } | 추천 ${detail.notice_recommend }</label>
		</div>
		<div>
			<a href="<%= request.getContextPath()%>/board/index.do?pageNum=1"><span>목록</span></a>
		</div>
		
	<%-- <div>
		<label><strong>제목 : ${detail.notice_title }</strong></label>
		<label>작성자 : ${detail.notice_id }  |  ${detail.notice_date }</label>
		<label>조회 ${detail.notice_count } | 추천 ${detail.notice_recommend }</label>
		<label>내용 : ${detail.notice_content }</label>
	</div> --%>
</body>
</html>