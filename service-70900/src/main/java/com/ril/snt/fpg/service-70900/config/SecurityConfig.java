package com.ril.snt.fpg.service70900.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${api.p}")
	protected String p;
	
	@Value("${api.user}")
	protected String user;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
       .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()  
        .antMatchers("/**").authenticated()
           .and()
            .csrf().disable()
             .httpBasic();
    }
    
      @Override
	public void configure(WebSecurity web) throws Exception {
  
    	web.ignoring()
    	.antMatchers("/keepalive")
    	.antMatchers("/v2/**");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
         
    	auth.inMemoryAuthentication()
    	.passwordEncoder(passwordEncoder())
    	.withUser(user)
    	.password(passwordEncoder().encode(p))
    	.roles("USER");
 
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   @Bean(name="centralAuthenticationManager")
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception{
	   return super.authenticationManager();
   }
   
}
