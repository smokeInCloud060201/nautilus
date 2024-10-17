package vn.com.lol.nautilus.modules.auth.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vn.com.lol.nautilus.commons.exception.BadRequestException;
import vn.com.lol.nautilus.commons.security.basic.JwtUtil;
import vn.com.lol.nautilus.modules.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;
import vn.com.lol.nautilus.modules.auth.service.AuthService;
import vn.com.lol.nautilus.modules.firstdb.token.TokenRepository;
import vn.com.lol.nautilus.modules.firstdb.token.entities.Token;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;
import vn.com.lol.nautilus.modules.seconddb.user.entities.User;
import vn.com.lol.nautilus.modules.seconddb.user.repository.UserRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;


    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest servletRequest) throws BadRequestException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));

        User user = userRepository.findByUserName(request.getEmail()).orElseThrow(() -> new
                BadRequestException("Not found user"));

        String accessToken = jwtUtil.generateToken(user, servletRequest);
        String refreshToken = jwtUtil.generateRefreshToken(user, servletRequest);

        saveToken(accessToken, refreshToken, user);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(Map<String, String> refreshTokenRequest, HttpServletRequest servletRequest) throws BadRequestException {
        String accessToken = refreshTokenRequest.get("access_token");
        String refreshToken = refreshTokenRequest.get("refresh_token");

        Token token = tokenRepository.findByToken(accessToken, refreshToken, TokenType.BASIC)
                .orElseThrow(() -> new BadRequestException("Token was not exists"));

        User user = userRepository.findByUserName(token.getUsername()).orElseThrow(() -> new BadRequestException("Not found user"));

        if (jwtUtil.isTokenValid(refreshToken, user)) {
            String newAccessToken = jwtUtil.generateToken(user, servletRequest);
            String newRefreshToken = jwtUtil.generateRefreshToken(user, servletRequest);

            token.setRefreshed(true);
            tokenRepository.save(token);
            saveToken(newAccessToken, newRefreshToken, user);

            return AuthenticationResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        } else {
            throw new BadRequestException("Refresh token is invalid");
        }
    }

    private void saveToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setUsername(user.getUsername());
        token.setTokenType(TokenType.BASIC);
        tokenRepository.save(token);
    }
}
