package com.manpower.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.manpower.common.Contants;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTVerifier jwtVerifier;

    @Override
    protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain
    ) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.toLowerCase().startsWith("bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        try {
            if (StringUtils.isNotBlank(token)) {
                // Verify the signature and decode the token
                DecodedJWT jwt = jwtVerifier.verify(token);

                // Check token expiry
                Date expiresAt = jwt.getExpiresAt();
                if (expiresAt != null && expiresAt.before(new Date())) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token has expired.");
                    return;
                }

                // Obtain claims from the token
                String userID = jwt.getClaim(Contants.RateType.Claims.USER_ID.name()).asString();
                String companyID = jwt.getClaim(Contants.RateType.Claims.COMPANY_ID.name()).asString();
                List<String> roles = jwt.getClaim("roles").asList(String.class);

                // Store claims in a Map
                Map<String, Object> claims = new HashMap<>();
                claims.put(Contants.RateType.Claims.USER_ID.name(), userID);
                claims.put(Contants.RateType.Claims.COMPANY_ID.name(), companyID);
                claims.put("roles", roles);

                // Create a list of granted authorities (Spring Security roles)
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }

                // Create a UsernamePasswordAuthenticationToken with claims as the principal and roles as authorities
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims, token, authorities);

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.debug("Token validated and claims stored in SecurityContext");
            }
        } catch (JWTVerificationException jwtException) {
            log.error("Invalid token.", jwtException);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT token.");
            return;
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }
}
