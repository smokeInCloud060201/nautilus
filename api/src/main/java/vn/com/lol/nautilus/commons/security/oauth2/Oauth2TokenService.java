package vn.com.lol.nautilus.commons.security.oauth2;

import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import vn.com.lol.common.utils.JsonUtil;
import vn.com.lol.nautilus.commons.serializer.AuthorizationGrantTypeDeserializer;
import vn.com.lol.nautilus.commons.serializer.TokenSettingsSerializer;
import vn.com.lol.nautilus.modules.firstdb.token.TokenRepository;
import vn.com.lol.nautilus.modules.firstdb.token.entities.Token;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;
import vn.com.lol.nautilus.modules.seconddb.user.entities.User;
import vn.com.lol.nautilus.modules.seconddb.user.repository.UserRepository;

import java.util.Optional;

import static vn.com.lol.nautilus.commons.utils.Oauth2AuthorizationUtil.mapTokensIntoOauthAuthorization;

@Service
@Slf4j
@RequiredArgsConstructor
public class Oauth2TokenService implements OAuth2AuthorizationService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;


    @Override
    public void save(OAuth2Authorization authorization) {
        Token token = new Token();
        token.setAccessToken(authorization.getAccessToken().getToken().getTokenValue());
        token.setRefreshToken(Optional.ofNullable(authorization.getRefreshToken())
                .map(OAuth2Authorization.Token::getToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .orElse(""));
        SimpleModule module = new SimpleModule();
        module.addSerializer(TokenSettings.class, new TokenSettingsSerializer());
        JsonUtil.setMapper(module);
        String oauth2Authorization = JsonUtil.stringify(authorization);
        token.setOauth2Authorization(oauth2Authorization);
        token.setClientId(authorization.getRegisteredClientId());
        token.setTokenId(authorization.getId());
        token.setTokenType(TokenType.OAUTH2);
        OAuth2ClientAuthenticationToken authenticationToken = authorization.getAttribute("java.security.Principal");

        User currentUser = null;
        try {
            if (Optional.ofNullable(authenticationToken).map(OAuth2ClientAuthenticationToken::getDetails).isPresent()) {
                currentUser = (User) authenticationToken.getDetails();
                token.setUsername(currentUser.getEmail());
            }
        } catch (OAuth2AuthenticationException e) {
            log.error("Error occurred while getting user details from authentication token", e);
        }

        log.info("Saved token with id {} for client {}", token.getTokenId(), token.getClientId());
        tokenRepository.save(token);

    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        String accessToken = Optional.ofNullable(authorization.getAccessToken())
                .map(OAuth2Authorization.Token::getToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .orElse("");
        String refreshToken = Optional.ofNullable(authorization.getAccessToken())
                .map(OAuth2Authorization.Token::getToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .orElse("");
        String clientId = authorization.getRegisteredClientId();
        tokenRepository.findByToken(accessToken, refreshToken, clientId, TokenType.OAUTH2).ifPresent(tokenRepository::delete);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        Token token = tokenRepository.findByTokenId(id, TokenType.OAUTH2).orElse(null);
        return getOauth2AuthorizationFromToken(token);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Token token1 = tokenRepository.findByToken(token, TokenType.OAUTH2).orElse(null);
        return getOauth2AuthorizationFromToken(token1);
    }

    public User findUserDetailByToken(String tokenString) {
        Token token = tokenRepository.findByToken(tokenString, TokenType.OAUTH2).orElse(null);

        if (token == null) {
            return null;
        }

        return userRepository.findByUserName(token.getUsername()).orElse(null);
    }

    public void updateTokenRefreshStatus(String tokenString) {
        Token token = tokenRepository.findByToken(tokenString, TokenType.OAUTH2).orElse(null);
        if (token!= null) {
            log.info("Update token status into refreshed {}", tokenString);
            token.setRefreshed(true);
            tokenRepository.save(token);
        }
    }

    public boolean isTokenRefreshed(String tokenString) {
        return !tokenRepository.isTokenNotRefreshed(tokenString, TokenType.OAUTH2);
    }

    private OAuth2Authorization getOauth2AuthorizationFromToken(Token token) {
        OAuth2Authorization oAuth2Authorization = null;

        try {
            if (token != null) {
                SimpleModule module = new SimpleModule();
                module.addDeserializer(AuthorizationGrantType.class, new AuthorizationGrantTypeDeserializer());
                JsonUtil.setMapper(module);
                oAuth2Authorization = JsonUtil.getObjectFromJsonString(OAuth2Authorization.class, token.getOauth2Authorization());

                oAuth2Authorization = mapTokensIntoOauthAuthorization(oAuth2Authorization, token.getOauth2Authorization());
            }
        } catch (Exception e) {
            log.error("Can not parse oauth2 token json", e);
        }
        return oAuth2Authorization;
    }
}
