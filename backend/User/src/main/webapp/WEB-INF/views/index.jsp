<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <c:if test="${userId eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=80cd070cb08cc0e6318122f78e29d26b&redirect_uri=http://localhost:8880/login/kakao&response_type=code">
            <img src="/img/kakaologin.png">
        </a>
    </c:if>
    
<%--     <c:if test="${userId eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=80cd070cb08cc0e6318122f78e29d26b&redirect_uri=http://localhost:8880/api/login/kakao&response_type=code">
            <img src="/api/img/kakaologin.png">
        </a>
    </c:if> --%>
    
    <c:if test="${userId ne null}">
        <h1>로그인 성공입니다</h1>
    </c:if>
    
    
</body>
</html>
