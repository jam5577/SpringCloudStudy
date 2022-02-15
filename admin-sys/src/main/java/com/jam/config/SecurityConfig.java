package com.jam.config;

import com.jam.handler.securityHandler.*;
import com.jam.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @program: SpringbootStudy
 * @description: 安全配置类
 * @author: Mr.Pu
 * @create: 2022-01-08 10:48
 **/
@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final UserServiceImpl userService;

    private final AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

    private final AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    private final AdminLogoutSuccessHandler adminLogoutSuccessHandler;

    private final AdminAccessDeniedHandler adminAccessDeniedHandler;

    private final AdminSessionStrategy adminSessionStrategy;

    @Autowired
    public SecurityConfig(DataSource dataSource, UserServiceImpl userService, AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler, AdminAuthenticationFailureHandler adminAuthenticationFailureHandler, AdminLogoutSuccessHandler adminLogoutSuccessHandler, AdminAccessDeniedHandler adminAccessDeniedHandler, AdminSessionStrategy adminSessionStrategy) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.adminAuthenticationSuccessHandler = adminAuthenticationSuccessHandler;
        this.adminAuthenticationFailureHandler = adminAuthenticationFailureHandler;
        this.adminLogoutSuccessHandler = adminLogoutSuccessHandler;
        this.adminAccessDeniedHandler = adminAccessDeniedHandler;
        this.adminSessionStrategy = adminSessionStrategy;
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler(adminAuthenticationSuccessHandler);
        loginFilter.setAuthenticationFailureHandler(adminAuthenticationFailureHandler);
        return loginFilter;
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        log.debug("调用了userService");
        provider.setUserDetailsService(userService);
        return provider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login","/","/user/updateUser").permitAll()
                .antMatchers("/css/**","/img/**","/js/**","/scss/**","/vendor/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
//                .loginPage("/toLoginForm")
//                .loginProcessingUrl("/user/login")
//            .successHandler(adminAuthenticationSuccessHandler)
//            .failureHandler(adminAuthenticationFailureHandler)
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
            .logoutSuccessHandler(adminLogoutSuccessHandler)
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            .permitAll();
        http.csrf().disable();
        http.exceptionHandling()
//                .authenticationEntryPoint(new AdminAccessEntryPoint())
                .accessDeniedHandler(adminAccessDeniedHandler);
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(3600);
//                .tokenRepository(persistentTokenRepository());
        http.sessionManagement().sessionFixation().changeSessionId()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(adminSessionStrategy);
        http.cors().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/templates/**","/css/**","/img/**");
    }
}
