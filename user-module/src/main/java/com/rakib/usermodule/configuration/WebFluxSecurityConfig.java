package com.rakib.usermodule.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Bean
    SecurityWebFilterChain authorization(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .exceptionHandling(this::handleExceptions)
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterAt(new WebSecurityInterceptor(), SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange()
                .pathMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .pathMatchers("/api/v1/user/**").permitAll()
                .pathMatchers("/api/v1/role/**").permitAll()
                .anyExchange().permitAll()
                .and().build();
    }

    private void handleExceptions(ServerHttpSecurity.ExceptionHandlingSpec exceptionHandlingSpec) {
        exceptionHandlingSpec.accessDeniedHandler((swe, e) ->
                Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage()))
        ).authenticationEntryPoint((swe, e) ->
                Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage()))
        );
    }

}
