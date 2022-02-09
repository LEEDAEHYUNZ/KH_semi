<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%
	// 성공메시지 뽑기
	String alertMsg = (String)session.getAttribute("alertMsg");
	// 서비스 요청 전 : alertMsg = null
	// 서비스 요청 후 성공시 : alertMsg = 메세지 문구
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>
		// script 태그 내에서도 스크립틀릿과 같은 jsp 요소를 쓸 수 있다.
		var msg = "<%= alertMsg %>";
		
		if(msg != "null"){
			alert(msg);
			
			// session 에 들어있는 alertMsg 키값에 대한 밸류를 지워줘야함!
			// 안그러면 login.jsp 가 로딩될때마다 매번 alert 가 뜰것
			// => xx.removeAttribute("키값")
			<% session.removeAttribute("alertMsg"); %>
		}
	</script>

</body>
</html>