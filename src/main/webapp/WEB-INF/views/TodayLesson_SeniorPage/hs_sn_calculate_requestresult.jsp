<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <script>
   if (${calculateRequestResult} > 0) {
	   alert("신청완료");
	   location.href="${pageContext.request.contextPath}/todaylessonsenior/senior_calculate_requestlist/"+${accountdetalidto.senior_no};
	
   } else {
	   alert("신청실패");
	   location.href="${pageContext.request.contextPath}/todaylessonsenior/senior_calculate_requestlist/"+${accountdetalidto.senior_no};
   } 
   </script>
</body>
</html>