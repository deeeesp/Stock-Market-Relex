package ru.stazaev.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/admin/setrate").hasRole("ADMIN")
                .requestMatchers("/admin/currency").hasRole("ADMIN")
                .requestMatchers("/admin/count").hasRole("ADMIN")
                .requestMatchers("/wallet/balance").hasAnyRole("ADMIN","USER")
                .requestMatchers("/operation/replenish").hasAnyRole("ADMIN","USER")
                .requestMatchers("/operation/withdrawal").hasAnyRole("ADMIN","USER")
                .requestMatchers("/operation/exchangeRate").hasAnyRole("ADMIN","USER")
                .requestMatchers("/operation/exchange").hasAnyRole("ADMIN","USER")
                .requestMatchers("/registration").permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
//        .anyRequest()
//            .hasRole()
//          .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
