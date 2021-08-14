# spring-security-demo

## demo3 
- anyRequest 
- antMatchers 
- regexMatchers 
- mvcMatchers

```java
http.authorizeRequests()
        // 放行 ，不需要认证
        .antMatchers("/login.html").permitAll()
        .antMatchers("/error.html").permitAll()
        // 放行某个目录下所有的文件
        .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
        // 放行指定的后缀名文件
        .antMatchers("/**/*.png", "/**/*.jpg").permitAll()
        // 正则匹配
        // .regexMatchers(HttpMethod.GET, "/demo").permitAll()
        // .regexMatchers(HttpMethod.POST, "/demo").permitAll()
        // mvc 匹配
        // .mvcMatchers("/demo").servletPath("/xxx").permitAll() // http://localhost:8080/xxx/demo
        // 拦截所有请求，必须登录才能访问 必须放在最后
        .anyRequest().authenticated();
```
