<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 마이샵 </h1>

<!--기본이미지 -->
<c:if test="${principal.oauth eq true}">
<img src="../resources/upload/upload/member/default.jpg">
</c:if>

<c:if test="${principal.oauth eq false}">
<img src="../resources/upload/upload/member/${file.fileName}">
</c:if>



</body>
</html>