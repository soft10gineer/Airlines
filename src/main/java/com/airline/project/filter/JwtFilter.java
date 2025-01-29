package com.airline.project.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.airline.project.Utils.JwtUtil;
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
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Get the token from Authorization header
        String authorizationHeader = request.getHeader("Authorization");

        // Initialize token and username variables
        String username = null;
        String jwt = null;

        // Directly read the token (without "Bearer " prefix)
        if (authorizationHeader != null) {
            jwt = authorizationHeader.trim();

            // Ensure no whitespace in JWT
            if (jwt.contains(" ")) {
                throw new IOException("JWT contains whitespace, which is invalid.");
            }

            // Extract the username from the token
            username = jwtUtil.extractUsername(jwt);
        }

        // If username is not null and token is valid, authenticate the user
        if (username != null && jwtUtil.validateToken(jwt)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }
}
