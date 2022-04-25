package com.sabitov.medsiteextended.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenRepository(tokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 365)
                .authenticationSuccessHandler((request, response, authentication) -> {
                    try {
                        request.getSession().setAttribute("email", request.getParameter("email"));
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                    response.sendRedirect("/sign_in");
                })
                .and()
                .authorizeRequests()
                .antMatchers("/sign_up").permitAll()
                .antMatchers("/sign_in").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/profile").authenticated()
                .and()
                .formLogin()
                .loginPage("/sign_in")
                .defaultSuccessUrl("/profile")
                .failureUrl("/sign_in?error")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    try {
                        request.getSession().setAttribute("email", request.getParameter("email"));
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e);
                    }
                    response.sendRedirect("/sign_in");
                })
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/sign_in?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
