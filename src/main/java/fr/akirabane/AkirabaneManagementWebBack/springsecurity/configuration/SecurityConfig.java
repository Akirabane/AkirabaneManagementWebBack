package fr.akirabane.AkirabaneManagementWebBack.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter JwtAuthFilter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AppUserService userDetailsService;

    public  SecurityConfig(JwtAuthFilter jwtAuthFilter, AppUserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        JwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity  http) throws Exception {

        return  http.csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests().antMatchers("/api/auth/**",  "/privatePage").authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().and()
                .build();

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(this.bCryptPasswordEncoder);
        return provider;
    }
}
