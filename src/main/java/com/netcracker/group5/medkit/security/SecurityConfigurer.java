package com.netcracker.group5.medkit.security;

import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthFilter authFilter;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests().antMatchers("/api/login", "/api/register", "/webjars/**", "/v2/api-docs/**", "/configuration/ui/**", "/swagger-resources/**",
                "/configuration/security/**", "/swagger-ui.html/**", "/swagger-ui.html#/**")
                    .permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/all-medicines/**")
                    .hasAuthority(Role.ADMINISTRATOR.getAuthority())
                .antMatchers(HttpMethod.POST, "/api/all-medicines/**")
                    .hasAuthority(Role.ADMINISTRATOR.getAuthority())
                .antMatchers(HttpMethod.PUT, "/api/all-medicines/**")
                    .hasAuthority(Role.ADMINISTRATOR.getAuthority())
                .antMatchers("/api/medicine-kit", "/api/notification", "/api/account", "/api/prescriptions/**", "/api/purchases")
                    .hasAuthority(Role.PATIENT.getAuthority())
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
