package com.surveyMonkey.controllers;

/**
@Configuration
@EnableWebSecurity
public class DefaultLogin extends WebSecurityConfigurerAdapter {
    //TODO
    //https://www.baeldung.com/spring-security-login
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // add your resources here. By default, spring security blocks all resources that is not under /resources/**
                .antMatchers(HttpMethod.GET, "/", "/js/**", "/css/**", "/images/**").permitAll()
                // prevent spring security from blocking some pages that doesn't require authentication to be access here.
                .antMatchers("/forgot-password", "/change-password").permitAll()
                .anyRequest().authenticated()
                .and()
                // login configuration
                .formLogin()
                .loginPage("/login") // can either be mapping or file
                .permitAll()
                .and()
                // logout configuration
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll();
    }
}
 **/
