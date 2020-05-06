<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="../customer/include/menu.jsp" %>
    <form name="updateform" method="POST" action="${path}/customer/s_update.do">
        <div>글번호</div>
        <div><input name="s_no" value="${data.s_no}" type="text" readonly="readonly"/></div>
        <div>작성자</div>
        <div><input name="s_writer" value="${data.s_writer}" type="text" readonly="readonly"/></div>
        <div>게시글제목 : </div>
        <div><input name="s_title" value="${data.s_title}" type="text"/></div>
        <div>게시글내용 : </div>
        <div><textarea name="s_content" rows="5" cols="50">${data.s_content}</textarea></div>
        <div>조회수</div>
        <div><input name="s_viewCnt" value="${data.s_viewCnt}" type="text" readonly="readonly"/></div>
        <div>작성일자</div>
        <div><fmt:formatDate value="${data.s_regDate}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
        <div>
            <input type="submit" value="글수정"/>
            <input type="reset" value="리셋"/>
        </div>
    </form>
    
</body>
</html>