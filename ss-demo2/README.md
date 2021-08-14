# spring-security-demo

## demo2 自定义登录

### 一. 自定义 登录逻辑
1. 配置 `PasswordEncoder`，参考：com.leichu.ss.demo2.config.SecurityConfig.java
2. 实现 `org.springframework.security.core.userdetails.UserDetailsService` 参考：com.leichu.ss.demo2.service.UserDetailServiceImpl.java
### 二. 自定义 登录界面、登录成功跳转、登录失败 跳转
1. `SecurityConfig` 继承 `WebSecurityConfigurerAdapter`，重写 `configure(HttpSecurity http)` 方法.
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
            // 自定义的登录界面
            .loginPage("/login.html")
            // 放行 /login 接口，这里的 /login 必须和 前端form表单的 action 配置的一致
            .loginProcessingUrl("/login")
            // 登陆成功之后跳转的界面 (必须是post请求，否则会出现 type=Method Not Allowed, status=405 )
            // .successForwardUrl("/main.html")
            .successForwardUrl("/toMain")
            .failureForwardUrl("/toError")
    ;
    http.authorizeRequests()
            // 放行 ，不需要认证
            .antMatchers("/login.html").permitAll()
            .antMatchers("/error.html").permitAll()
            // 拦截所有请求，必须登录才能访问
            .anyRequest().authenticated();

    http.csrf().disable();
}
```
2. 编写 `POST` 接口 `/toMain`、`toError`
```java
@RequestMapping(value = "/toMain")
public String toMain(){
    return "redirect:main.html";
}

@RequestMapping(value = "/toError")
public String toError(){
    return "redirect:error.html";
}
```
### 三. 设置请求 用户名 和 密码 的 参数名
默认的表单登录用户名和密码参数必须是 `username` 和 `password`，这是因为 spring-security 在执行登录的时候会默认执行一个过滤器 `UsernamePasswordAuthenticationFilter`，
查看源码，它有如下成员变量：
```java
public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";

public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");

private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

private boolean postOnly = true;
```
从上面的变量可以看出，表单登录用户名和密码参数必须是 `username` 和 `password`，而且，只支持 `POST` 请求。
修改方式：
1. 修改 `SecurityConfig.configure(HttpSecurity http)`
```java
http.formLogin().usernameParameter("my-username").passwordParameter("my-password")
```
2. 修改登录的 form 表单
```html
用户名：<input name="my-username" type="text"><br>
密码：<input name="my-password" type="password"><br>
```
### 四. 自定义 登录成功 和 登录失败 处理器

查看 `SecurityConfig.configure(HttpSecurity http)` 中 `successForwardUrl("/toMain")` 和 `failureForwardUrl("/toError")` 的源码会发现，
其本质是各自实现了一个 `AuthenticationSuccessHandler` 和 `AuthenticationFailureHandler` 接口，
所以在自定义登录成功和失败处理器时也是要实现这两个接口。
具体参考：
- `com.leichu.ss.demo2.hadler.MyAuthenticationSuccessHandler.java`
- `com.leichu.ss.demo2.hadler.MyAuthenticationFailureHandler.java` 

最后，修改 `SecurityConfig.configure(HttpSecurity http)` 中 `successForwardUrl("/toMain")` 和 `failureForwardUrl("/toError")`，如下：
```java
http.formLogin().usernameParameter("my-username").passwordParameter("my-password")
        // 自定义的登录界面
        .loginPage("/login.html")
        // 放行 /login 接口，这里的 /login 必须和 前端form表单的 action 配置的一致
        .loginProcessingUrl("/login")
        // 登陆成功之后跳转的界面 (必须是post请求，否则会出现 type=Method Not Allowed, status=405 )
        // .successForwardUrl("/main.html")
        // .successForwardUrl("/toMain")
        // .failureForwardUrl("/toError")
        .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
        .failureHandler(new MyAuthenticationFailureHandler("/error.html"));
```


