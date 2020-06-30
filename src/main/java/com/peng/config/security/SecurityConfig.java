package com.peng.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@SpringBootConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 /** 依赖注入自定义的登录成功处理器 */
    @Autowired
    private FuryAuthenticationSuccessHandler furyAuthenticationSuccessHandler;

    /** 依赖注入自定义的登录失败处理器 */
    @Autowired
    private FuryAuthenticationFailureHandler furyAuthenticationFailureHandler;
    
	protected void configure(HttpSecurity http) throws Exception {
		
		 // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
            .authorizeRequests()
            // 跨域预检请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 登录URL
            .antMatchers("/login").permitAll()
            // swagger
            .antMatchers("/swagger**/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/v2/**").permitAll()
            .antMatchers("/css/**","/fonts/**","/images/**","/js/**","/lib/**").permitAll()
            // 其他所有请求需要身份认证
            .anyRequest().authenticated();
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
