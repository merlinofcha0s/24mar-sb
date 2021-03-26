package com.plb.vinylmgt.configuration.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.plb.vinylmgt.configuration.security.RestSecurityConfiguration.*;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String headerValue = httpServletRequest.getHeader(HEADER_AUTHORIZATION);

        //Bearer tokenJWT => tokenJWT
        if (headerValue != null && headerValue.startsWith(AUTHORIZATION_TOKEN_PREFIX)) {
            try {
                UsernamePasswordAuthenticationToken authentication = verifyAndGetAuthentication(headerValue);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException | IllegalArgumentException jve) {

            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken verifyAndGetAuthentication(String authorizationValue)
            throws JWTVerificationException, IllegalArgumentException {
        //
        String tokenWithoutPrefix = authorizationValue.replace(AUTHORIZATION_TOKEN_PREFIX, "");
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(tokenWithoutPrefix);

        String user = decodedJWT.getSubject();

        List<SimpleGrantedAuthority> authorities = Arrays.stream(decodedJWT.getClaim("auth")
                .asString().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        if (user != null) {
            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        }
        return usernamePasswordAuthenticationToken;
    }
}
