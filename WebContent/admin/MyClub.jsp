<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的社团</title>
<style type="text/css">
.body{
	background-image:url(%E6%A1%86%E6%9E%B63%E8%83%8C%E6%99%AF.jpg);
	background-size:cover;
}
</style>
</head>

<body class="body">
<form>
社团名称：${club.clubName}
<br/>
社团编号：${club.clubId}
<br/>
我的职务：${post}
<br/>
社团人数：${club.number}
<br/>
社团简介：${club.clubContebt}
<br/>
<input type="reset" value="退社"/>
<input type="submit" value="确认"/>


</form>
</body>
</html>