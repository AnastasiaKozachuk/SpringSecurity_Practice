package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      /* UserBuilder userBuilder = User.withDefaultPasswordEncoder();

       auth.inMemoryAuthentication().withUser(userBuilder.username("john").password("123").roles("EMPLOYEE"))
               .withUser(userBuilder.username("mary").password("234").roles("EMPLOYEE", "ADMIN"))
               .withUser(userBuilder.username("susan").password("345").roles("EMPLOYEE", "MANAGER"));*/


      auth.jdbcAuthentication().dataSource(dataSource);



    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
               .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
        .and()
        .logout()
        .permitAll().and().exceptionHandling()
        .accessDeniedPage("/accessDenied");

    }
}
