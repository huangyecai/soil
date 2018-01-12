<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<form action="user/login">
		<label>用户名</label><input name="account"> <label>密码</label><input
			name="password" /> <br>
		<button type="submit">确认</button>
	</form>
	<h1>欢迎你,${cookie.user.value }</h1>
	<h2>22-${cookie.sysUserId.value }</h2>
</body>
</html>
