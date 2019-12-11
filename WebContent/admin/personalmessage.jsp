<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" "%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人信息</title>
<style type="text/css">
.body{
	background-image:url(%E6%A1%86%E6%9E%B63%E8%83%8C%E6%99%AF.jpg);
	background-size:cover;
}
</style>
</head>

<body class="body">

<form action="StudentChange" >
用户名：<input type="text" id="usename" name="usename" placeholder="${student.studentName}"/>
</br>
性别：<input type="radio" id="sex1" name="sex" value="男"/>男
<input type="radio" id="sex2" name="sex" value="女"/>女
<br/>
<!-- 学号：<input type="password" id="number" name="number" placeholder=""/>
</br> -->
专业：<input type="text" id="major" name="major" placeholder="${student.studentMajor}"/>
</br>
班级：<input type="text" id="class" name="class" placeholder="${student.studentClass}"/>
</br>
联系方式：<input type="text" id="tel" name="tel" placeholder="${student.studentPhone}"/>
</br>
<!-- 备注：<textarea id="remark" name="remark" cols="20" rows="1"/>
</textarea>
<br/> -->
<input type="reset" value="修改"/>
<input  type="submit" value="确认" >
</form>
</body>
</html>