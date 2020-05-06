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
    <form name="bdto" method="post" action="insert.do">
        <div>제목<input name="s_title" id="s_title" size="80" placeholder="글 제목 입력"></div>
        <div>내용<textarea name="s_content" id="s_content" rows="8" cols="80" placeholder="글 내용 입력"></textarea></div>
        <div>이름<input name="s_writer" id="s_writer" placeholder="이름 입력"></div>
        <div style="width:650px; text-align:center;">
            <input type="submit" value="확인">
        </div>
    </form>
</body>
</html>
