<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file upload</title>
</head>
<body>
    <form action="./file/fileupload.do" method="post" enctype="multipart/form-data">
        <label>文件上传</label>
        <input type="file" name="file">
        <input type="submit" value="提交">
    </form>
</body>
</html>