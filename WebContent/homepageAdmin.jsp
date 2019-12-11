<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>社团管理系统主页</title>
		<style type="text/css"></style>
		<meta http-equib="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="keywords" content="社团,管理">
		<meta name="description" content="社团管理系统是一个帮助学生参与社团及活动的系统">
		<meta name="robots" content="index,follow">
		<link rel="stylesheet" type="text/css" href="homepage.css"/>
	</head>
	<body>
		<header class="container">
			<div id="logo">
				<a href="homepage.html"><h1>社团管理系统</h1></a>
			</div>
			<div id="search">
				<form action="homepage.html" method="get">
					<input type="search" placeholder="输入关键词搜索"><input type="submit" value="搜索" >
				</form>
			</div>
		</header>
		<!-- 主要板块 -->
		<div id="nav">
			<nav class="container">
				<ul>
					<li><a href="homepage.html" class="nava1">首页</a></li>
					<li class="navli1"><a href="activity.html" >活动</a>
						<ul>
							<li><a href="#">活动宣传</a></li>
							<li><a href="CreateAct.html">活动创建</a></li>
						</ul></li>
					<li class="navli1"><a href="club.html" >社团风采</a>
						<ul>
							<!-- 社团风采展示界面 -->
							<li><a href="club.html">社团介绍</a></li>
							<!-- 社团报名表界面 -->
							<li><a href="joinclub.html">社团报名</a></li>
							<!-- 社团创建界面 -->
							<li><a href="clubapply.html">社团创建</a></li>
						</ul></li>
					<li class="navli1"><a href="#" >我的信息</a>
						<ul>
							<li><a href="AdminMessage.html">个人主页</a></li>
							<li><a href="PwdChange.html">密码修改</a></li>
						</ul></li>
					<li><a href="about.html">我的申请</a></li>
					<!-- 提交和审批 -->
				</ul>
				
				<div id="cart">
					<span class="icon-cart"></span>
					<!-- 有自己的申请列表还有审批和被审批 -->
					<a href="cart.html">申请列表</a>
				</div>
				</nav>
		</div>
		<!-- 主要界面内容 -->
		<div id="content-wrapper" class="container">
			<main>
					<!-- 宣传图片轮播展示 -->
					<div id="adv">
						<ul>
							<li class="lunbo1">
								<span>1</span>
								<div></div>
							</li>
							<li class="lunbo2">
								<span>2</span>
								<div></div>
							</li>
							<li class="lunbo3">
								<span>3</span>
								<div></div>
							</li>
							<li class="lunbo4">
								<span>4</span>
								<div></div>
							</li>
						</ul>
					</div>
			</main>
			<aside>
			</div>
				<section id="actlist">
					<span class="icon-actlist"></span>
					<h2>活动列表</h2>
						<div class="content">
							<ul>
								<li><a href="#">篮协纳新</a></li>
								<li><a href="#">羽毛球新生杯比赛</a></li>
								<li><a href="#">书画协会纳新</a></li>
								<li><a href="#">营销协会户外拓展</a></li>
								<c:forEach var="U" items="${ActivityAll}" > 
								<li>${U.activityId} ${U.activityName}</li>
							</c:forEach> </ul>
						</div>
				</section>
			</aside>
		</div>
		<!--页脚导航-->
		<div id="footer-wrapper">
		    <footer class="container">
		        <ul>
					<li class="li1"><a href="homepage.html">首页</a></li>
		            <li><a href="about.html">关于我们</a></li>
		            <li><a href="#">服务条款</a></li>
		            <li><a href="#">隐私策略</a></li>
		            <li><a href="contact.html">联系我们</a></li>
		        </ul>
		    </footer>
		</div>
		<!--版权信息-->
		<div id="copyright" class="container">
		    <div>Copyright (C) <a href="homepage.html">社团管理系统</a> 2019-2020, All Rights Reserved | ZUCC Community Management System Web Version Group:8th</div>
		    <address>通讯地址：浙江省杭州市湖州街浙江大学城市学院&nbsp;&nbsp;电话：62781733&nbsp;&nbsp;社联管理信箱：clubadmin@zucc.edu.cn</address>
		</div>
		
	</body>
</html>