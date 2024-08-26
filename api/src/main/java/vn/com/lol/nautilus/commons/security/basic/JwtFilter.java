package vn.com.lol.nautilus.commons.security.basic;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.com.lol.nautilus.modules.firstdb.token.TokenRepository;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;

import java.io.IOException;

import static vn.com.lol.nautilus.commons.constant.SecurityConstant.Header.AUTHORIZATION;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.Header.BEARER_TOKEN_TYPE;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String token;

        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_TOKEN_TYPE)) {
           filterChain.doFilter(request, response);
           return;
        } else {
            token = authorizationHeader.substring(7);
        }
        final String userName = jwtUtil.extractUsername(token);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            boolean isTokenValid = tokenRepository.findByToken(token, TokenType.BASIC)
                    .isPresent() && jwtUtil.isTokenValid(token, userDetails);

            if (jwtUtil.isTokenValid(token, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}