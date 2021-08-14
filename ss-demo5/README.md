# spring-security-demo

## demo5 remember-me

1. 引入依赖
```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.0</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```
2. 配置数据源
```properties
spring.datasource.url=jdbc:mysql://10.8.0.246:3306/spring_security?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=eiduo
spring.datasource.password=edGdas*h)
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
3. 修改配置类
```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private DataSource dataSource;

	@Resource
	private PersistentTokenRepository persistentTokenRepository;

	@Resource
	private MyAccessDeniedHandler myAccessDeniedHandler;

	@Resource
	private UserDetailServiceImpl userDetailService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().usernameParameter("my-username").passwordParameter("my-password")
				// 自定义的登录界面
				.loginPage("/login.html")
				// 放行 /login 接口，这里的 /login 必须和 前端form表单的 action 配置的一致
				.loginProcessingUrl("/login")
				// 登陆成功之后跳转的界面 (必须是post请求，否则会出现 type=Method Not Allowed, status=405 )
				// .successForwardUrl("/main.html")
//				.successForwardUrl("/toMain")
//				.failureForwardUrl("/toError")
				.successHandler(new MyAuthenticationSuccessHandler("/main.html"))
				.failureHandler(new MyAuthenticationFailureHandler("/error.html"))

		;
		http.authorizeRequests()
				// 放行 ，不需要认证
				.antMatchers("/login.html").permitAll()
				.antMatchers("/error.html").permitAll()
				// 拦截所有请求，必须登录才能访问
				.anyRequest().authenticated();
		// myAccessDeniedHandler 需要注入进来  不能直接 new
		http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

		http.rememberMe() // TODO 核心配置
				// 这是数据源
				.tokenRepository(persistentTokenRepository)
				// 60 秒后过期
				.tokenValiditySeconds(60)
				.userDetailsService(userDetailService)
		;

		http.csrf().disable();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		final JdbcTokenRepositoryImpl jdbc = new JdbcTokenRepositoryImpl();
		jdbc.setDataSource(dataSource);
		jdbc.setCreateTableOnStartup(true);
		return jdbc;
	}
}
```
4. 修改前端界面，添加 remember-me
```html
<body>
	<form action="/login" method="post">
		用户名：<input name="my-username" type="text"><br>
		密码：<input name="my-password" type="password"><br>
		记住我：<input name="remember-me" type="checkbox" value="true"><br>
		<button type="submit" >登录</button>
	</form>
</body>
```
