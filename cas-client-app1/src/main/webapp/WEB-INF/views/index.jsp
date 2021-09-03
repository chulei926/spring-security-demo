<%--
  Created by IntelliJ IDEA.
  User: leichu
  Date: 2021-09-01
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>APP1 哈哈</title>
	<script src="//cdn.eiduo.com/assets/jquery/jquery.js"></script>
	<script type="text/javascript">
		$(function($) {
			$('#btn').on('click', function() {
				window.location.href = "https://sso.leichu.top/cas/logout?service=https://app1.leichu.top/app1/index";
			})
		})
	</script>
</head>
<body>
<h1>登录成功 APP1</h1>

<h2>principal：${principal}</h2>
<h2>uid：${uid}</h2>
<h2>name：${name}</h2>
<h2>who：${who}</h2>

<button id="btn">退出登录</button>
</body>
</html>
