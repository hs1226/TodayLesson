<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
var result=${result}

if (result>0)
	{
	alert('수정이 완료되었습니다.');
	location.href="${pageContext.request.contextPath}/todaylessonmypage/hm_us_mymanage";
	}
else
	{
	alert('수정 실패');
	location.href="${pageContext.request.contextPath}/todaylessonmypage/hm_us_mymanage";
	}
</script>
</body>
</html>