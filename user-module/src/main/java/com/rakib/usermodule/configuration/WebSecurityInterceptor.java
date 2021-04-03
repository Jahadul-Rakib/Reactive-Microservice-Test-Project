package com.rakib.usermodule.configuration;

import com.rakib.usermodule.user_utilities.exception.classes.NotAllowException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
public class WebSecurityInterceptor implements WebFilter {
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authToken = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(authToken) && authToken.startsWith("Bearer ")) {
            String token = StringUtils.removeStart(authToken, "Bearer ");
            Mono<Claims> claims = validateToken(token);
            setUpSpringAuthentication((Claims) claims, token);
        }else {
            Mono.error(new NotAllowException("Credential is incorrect."));
        }
        return chain.filter(exchange);
    }

    private Mono<Claims> validateToken(String token) {
        return Mono.just(Jwts.parserBuilder()
                .setSigningKey(AppConstant.SECURITY_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody());
    }
    private void setUpSpringAuthentication(Claims claims, String token) {
        String authorities = String.valueOf(claims.get(AppConstant.AUTHORITIES));
        UsernamePasswordAuthenticationToken localAuthentication =
                new UsernamePasswordAuthenticationToken(claims.getIssuer(), token,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        SecurityContextHolder.getContext().setAuthentication(localAuthentication);
    }

}
