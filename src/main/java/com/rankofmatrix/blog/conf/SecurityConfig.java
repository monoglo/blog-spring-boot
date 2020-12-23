package com.rankofmatrix.blog.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin((login) -> login
                        .successForwardUrl("/login/success")
                        .failureForwardUrl("/login/failed")
                )
                .csrf().disable()
                .authorizeRequests((authorize) -> authorize
                        .antMatchers(HttpMethod.GET, "/articles/").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.POST, "/articles/").authenticated()
                        .antMatchers(HttpMethod.PUT, "/articles/").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/articles/aid/*").authenticated()
                        .antMatchers(HttpMethod.POST, "/articles/aid/*/add/clickAmount/value/*").authenticated()
                        .antMatchers(HttpMethod.PUT, "/articles/aid/*/add/tag/*").authenticated()
                        .antMatchers(HttpMethod.PUT, "/articles/aid/*/add/archive/*").authenticated()
                        .antMatchers(HttpMethod.GET, "/articles/deleted").authenticated()
                        .antMatchers(HttpMethod.POST, "/tags/").authenticated()
                        .antMatchers(HttpMethod.PUT, "/tags/").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/tags/id/*").authenticated()
                        .antMatchers(HttpMethod.POST, "/archives/").authenticated()
                        .antMatchers(HttpMethod.PUT, "/archives/").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/archives/id/*").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic();
    }

}
