<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<%
// 후원페이지에서 넘어온 파라미터
String status = (String)request.getParameter("status");
String msg = (String)request.getParameter("msg");
String redirect = (String)request.getParameter("redirect");
String test = (String)request.getParameter("test");


%> 

	<script>
	if("${status}" == "success" ) {
		Swal.fire({
		    text: "${msg}",
		    icon: "success", // 성공버튼 누르면
		  }).then(() => { // 메인 페이지로 이동
		    location.href = "${redirect}";
		  });
	}else if("${status}" == "fail"){
		Swal.fire({
		    text: "${msg}",
		    icon: "error", // 성공버튼 누르면
		  }).then(() => { // 메인 페이지로 이동
		    location.href = "${redirect}";
		  });
	}
	
	// 후원 메세지
 <%-- <% if (status.equals("success")) { %>
		Swal.fire({
		    text: "<%=msg%>",
		    icon: "success", // 성공버튼 누르면
		  }).then(() => { // 메인 페이지로 이동
		    location.href = "<%=redirect%>";
		  });
	<% } else { %>
		Swal.fire({
		    text: "<%=msg%>",
		    icon: "success", // 성공버튼 누르면
		  }).then(() => { // 메인 페이지로 이동
		    location.href = "<%=redirect%>";
		  });
		<% } %>   --%>
	</script>

</body>
</html>