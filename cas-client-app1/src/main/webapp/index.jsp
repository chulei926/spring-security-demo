<%--
  Created by IntelliJ IDEA.
  User: leichu
  Date: 2021-08-31
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World! app1</h2>

<button id="btn">退出登录</button>

<script src="//cdn.eiduo.com/assets/jquery/jquery.js"></script>
<script type="text/javascript">
	$(function($) {
		$('#btn').on('click', function() {
			window.location.href ="https://leichu.com:8443/sso/logout?service=http%3A%2F%2Fapp1.com%3A8080%2Fapp1%2F";
		})
	})
</script>
</body>
</html>
