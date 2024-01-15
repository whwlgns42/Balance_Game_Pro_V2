<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.save.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 마이 페이지</title>
</head>
<body>
마이페이지<br>
아이디 <input readonly type="text" value="${myPageData.loginId}"><br>
이름 <input  type="text" value="${myPageData.name}"><br>
성별 <input  type="text" value="${myPageData.gender}"><br>
이메일 <input type="email" value="${myPageData.email}"><br>
주소 <input readonly type="text" value="${myPageData.address}"><br>
<button onclick="location.href='makeTitlePage.do';" >문제출제하기</button>
<button onclick="location.href='wishListPage.do';" >찜목록</button>
<button onclick="location.href='mypageUpdate.do';" >변경</button>
</body>
</html>