package com.itacademy.akulov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    // @formatter::off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable().headers().disable()
                    .authorizeRequests()
                    .antMatchers("/home", "/getcourses").authenticated()
                    .antMatchers("/createcourse").hasAnyAuthority("TEACH", "ADMIN")
                    .antMatchers("/beteacher",
                            "/deletecourse" ,
                            "/updatecourse",
                            "/upresult").hasAnyAuthority("TEACH", "ADMIN")
                    .antMatchers("/bestudent").hasAnyAuthority("STUDY", "ADMIN")
                    .antMatchers("/kbdelete", "/ublock", "/udelete", "/allusers").hasAnyAuthority("ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                    .loginProcessingUrl("/login-user")
                    .defaultSuccessUrl("/home")
                .and()
                    .httpBasic()
                .and()
                    .logout();
    }
    // @formatter::on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
}
