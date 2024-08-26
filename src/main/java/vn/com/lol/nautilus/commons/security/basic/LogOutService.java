package vn.com.lol.nautilus.commons.security.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import vn.com.lol.nautilus.modules.firstdb.token.TokenRepository;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;

import static vn.com.lol.nautilus.commons.constant.SecurityConstant.Header.AUTHORIZATION;
import static vn.com.lol.nautilus.commons.constant.SecurityConstant.Header.BEARER_TOKEN_TYPE;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogOutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null && authorization.startsWith(BEARER_TOKEN_TYPE)) {
            String token = authorization.substring(7);
            tokenRepository.findByToken(token, TokenType.BASIC).ifPresent(tokenRepository::delete);
        }
    }
}