# spring-security-demo

## demo1 快速入门
### springboot 集成 spring-security

1. 引入 `pom` 依赖
```xml
<!-- springboot web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- spring security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```
2. 编写登录界面
```html
<!-- login.html -->
<body>
	<form action="/login" method="post">
		用户名：<input name="username" type="text"><br>
		密码：<input name="password" type="password"><br>
		<button type="submit" >登录</button>
	</form>
</body>

<!-- main.html -->
<body>
    登录成功
</body>
```
3. 编写 `LoginController`
```java
@Controller
public class LoginController {
	@RequestMapping(value="/login")
	public String login(){
		return "redirect:main.html";
	}
}
```
4. 启动测试
浏览器访问：http://localhost:8080/login.html

**结论：**
1. 浏览器默认打开的是 spring-security 内置的登录界面，默认用户名 `user`，默认密码在启动日志中 `Using generated security password: a680cf0d-b20f-41aa-ba13-43602c33d3c2`，这里的密码每次启动都不一样。

![ss内置登录](https://leichu-md-img.oss-cn-hangzhou.aliyuncs.com/spring-security/ss-login.png "ss内置登录")

2. 登录后才进入自己写的登录界面

![自定义登录](https://leichu-md-img.oss-cn-hangzhou.aliyuncs.com/spring-security/myss-login.png)


