package com.book.library.security;

import static com.book.library.util.UserRole.*;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.book.library.jwt.JwtConfig;
import com.book.library.jwt.JwtTokenVerifier;
import com.book.library.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.book.library.service.BannedTokenService;
import com.book.library.service.impl.ApplicationUserDetailsServiceImpl;
/**
 * @author DSandoval
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private PasswordEncoder passwordEncoder;
    
	private ApplicationUserDetailsServiceImpl applicationUserService;
    
	private SecretKey secretKey;
    
	private JwtConfig jwtConfig;
	
	private BannedTokenService bannedTokenService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
    		ApplicationUserDetailsServiceImpl applicationUserService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig,
                                     BannedTokenService bannedTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.bannedTokenService = bannedTokenService;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig, bannedTokenService),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/",
                		"/h2/**",
                		"/favicon.ico",
                		"/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .antMatchers("/api/**").hasAnyRole(OWNER.name(), VIEWER.name())
                .anyRequest()
                .authenticated();
        
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

}
