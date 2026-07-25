package com.shahid.shopsphere.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shahid.shopsphere.service.CustomUserDetailsService;
import com.shahid.shopsphere.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
      
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected  void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, java.io.IOException
    {
       //This line reads the header.
        final String authHeader = request.getHeader("Authorization");
        //check if it has bearer
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
             filterChain.doFilter(request, response);
             return;
        }
        //token
        String jwt = authHeader.substring(7);

         //extract username
         String username = jwtService.extractUserName(jwt);

         //no authentication exist
         if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
         {
            //load user
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isTokenValid(jwt, userDetails))
            {
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                 userDetails,
                 null,
                 userDetails.getAuthorities()
                );
                //Attach request details.
                authToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(request));

                //Store authentication.
                SecurityContextHolder.getContext().setAuthentication(authToken);
                //continue to filter
            }
         }
         filterChain.doFilter(request,response);

    }
}
