# spring-security-demo

## demo4 授权、自定义403处理方案
- 基于权限
- 基于角色
- 基于IP地址

### 一. 基于 权限
1. 修改 `UserDetailServiceImpl.loadUserByUsername(String username)` 
```java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!name.equals(username)){
        throw new UsernameNotFoundException("用户名不存在");
    }
    final String password = passwordEncoder.encode(pwd);
    // 注意这里给登录的用户赋予 admin 的权限
    return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,subAdmin,normal"));
}
```
2. 修改 `SecurityConfig.configure(HttpSecurity http)`
```java
// 表明 只有 拥有 leichu 权限的用户才能访问
http.authorizeRequests().antMatchers("/leichu.html").hasAuthority("leichu") // hasAuthority() 指是否拥有某一个权限
// .antMatchers("/leichu.html").hasAnyAuthority("leichu", "leichu2") // hasAnyAuthority() 指是否拥有多个权限
```
3. 修改 `main.html`，创建 `leichu.html`
```html
<!--main.html-->
<body>
	登录成功 <a href="./leichu.html">跳转</a>
</body>

<!--leichu.html-->
<body>
    leichu才能进入
</body>
```
启动项目，登录后会跳转到`main.html`，再次点击 跳转 按钮，发现没有权限。说明基于权限的访问控制已生效。如下：<br>
![权限403页面](https://leichu-md-img.oss-cn-hangzhou.aliyuncs.com/spring-security/%E6%9D%83%E9%99%90403.png "权限403页面")

4. 修改 `UserDetailServiceImpl.loadUserByUsername(String username)` ，给登录的用户赋予 `leichu` 的权限
```java
return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,subAdmin,normal,leichu"));
```
重启后再次重复上面3的步骤，发现此时可以进入到 `leichu.html`。

### 二. 基于 角色
大致流程和基于权限类似，需要修改的点如下：
1. 修改 `UserDetailServiceImpl.loadUserByUsername(String username)` 
```java
// 注意：此处加了一个已 ROLE_ 开头的字符串，代表拥有的角色为 abc
return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,subAdmin,normal,leichu,ROLE_abc"));
```
2. 修改 `SecurityConfig.configure(HttpSecurity http)`
```java
// 表明 只有 拥有 abc 角色的用户才能访问
http.authorizeRequests().antMatchers("/leichu.html").hasRole("abc") // 单个角色
// .antMatchers("/leichu.html").hasAnyRole("abc", "def") // 多个角色
```

### 三. 基于 IP 地址
```java
.antMatchers("/leichu.html").hasIpAddress("127.0.0.1")
```

### 四. 自定义 403 处理逻辑
1. 实现 `AccessDeniedHandler` 接口
```java
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json;charset=UTF-8");
		JSONObject res = new JSONObject();
		res.put("status", 403);
		res.put("msg", "权限不足");
		final PrintWriter writer = response.getWriter();
		writer.write(res.toJSONString());
		writer.flush();
		writer.close();
	}
}
```
2. 修改 `SecurityConfig.configure(HttpSecurity http)`
```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private MyAccessDeniedHandler myAccessDeniedHandler;  

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// myAccessDeniedHandler 需要注入进来  不能直接 new
		http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler); // 
	}
}
```
