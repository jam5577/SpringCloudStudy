package com.jam.config;

import com.jam.handler.*;
import com.jam.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;


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
                .loginPage("/toLoginForm")
                .loginProcessingUrl("/user/login")
            .successHandler(adminAuthenticationSuccessHandler)
            .failureHandler(new AdminAuthenticationFailureHandler())
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
            .logoutSuccessHandler(new AdminLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            .permitAll();
        http.csrf().disable();
        http.exceptionHandling()
//                .authenticationEntryPoint(new AdminAccessEntryPoint())
                .accessDeniedHandler(new AdminAccessDeniedHandler());
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(3600);
//                .tokenRepository(persistentTokenRepository());
        http.sessionManagement().sessionFixation().changeSessionId()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new AdminSessionStrategy());
        http.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/templates/**","/css/**","/img/**");
    }
}
