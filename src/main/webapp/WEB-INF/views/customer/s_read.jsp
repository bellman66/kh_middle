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
    <%@ include file="../customer/include/menu.jsp"%>
    <table border="1">
        <th> 정보 </th>
        <th> 작성 정보 </th>
        <tr>
            <td>작성일자</td>
            <td><fmt:formatDate value="${data.s_regDate}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
        </tr>
        <tr>
            <td>글번호</td><td>${data.s_no}</td>
        </tr>
        <tr>
            <td>글제목</td><td>${data.s_title}</td>
        </tr>
        <tr>
            <td>글내용</td><td>${data.s_content}</td>
        </tr>
        <tr><td>글쓴이</td><td>${data.s_writer}</td></tr>
        <tr><td>조회수</td><td>${data.s_viewCnt}</td></tr>
        
    </table>
    <a href="${path}/customer/update.do?s_no=${data.s_no}">수정</a>
    <a href="${path}/customer/delete.do?s_no=${data.s_no}">삭제</a>
    
</body>
</html>
