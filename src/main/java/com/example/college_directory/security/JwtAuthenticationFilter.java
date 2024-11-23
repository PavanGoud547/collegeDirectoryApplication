package com.example.college_directory.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;  // Inject UserDetailsService instead of UserService

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // JWT token is in the form "Bearer token". Remove Bearer word and get only the token.
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7); // Remove "Bearer " from the token
            try {
                username = jwtUtil.extractUsername(jwtToken); // Extract username from token
            } catch (ExpiredJwtException e) {
                logger.warn("JWT Token has expired: " + e.getMessage());
            } catch (Exception e) {
                logger.warn("Unable to parse JWT Token: " + e.getMessage());
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token, validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details using UserDetailsService instead of UserService
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // If token is valid, configure Spring Security to manually set authentication.
            if (jwtUtil.validateToken(jwtToken, username)) {  // Use username as a String, not UserDetails

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the context.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Proceed with the filter chain
        chain.doFilter(request, response);
    }
}
