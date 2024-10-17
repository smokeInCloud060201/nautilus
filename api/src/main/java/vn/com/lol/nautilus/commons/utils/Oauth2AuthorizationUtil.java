package vn.com.lol.nautilus.commons.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import vn.com.lol.nautilus.commons.serializer.OAuth2AccessTokenDeserializer;
import vn.com.lol.nautilus.commons.serializer.OAuth2RefreshTokenDeserializer;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Oauth2AuthorizationUtil {


    public static OAuth2Authorization mapTokensIntoOauthAuthorization(OAuth2Authorization oAuth2Authorization, String jsonString) throws IOException {
        JsonNode node = JsonUtil.convertStringToJsonNode(jsonString);
        JsonNode accessTokenNode = node.get("accessToken");
        JsonNode refreshTokenNode = node.get("refreshToken");
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OAuth2AccessToken.class, new OAuth2AccessTokenDeserializer());
        module.addDeserializer(OAuth2RefreshToken.class, new OAuth2RefreshTokenDeserializer());
        JsonUtil.setMapper(module);
        OAuth2AccessToken accessToken = JsonUtil.getObjectFromJsonString( OAuth2AccessToken.class, accessTokenNode.toString());
        OAuth2RefreshToken refreshToken = JsonUtil.getObjectFromJsonString( OAuth2RefreshToken.class, refreshTokenNode.toString());

        OAuth2AuthorizationWrapper.BuilderWrapper builderWrapper = new OAuth2AuthorizationWrapper.BuilderWrapper(
                oAuth2Authorization.getRegisteredClientId(), oAuth2Authorization.getId(),
                accessToken, refreshToken,
                oAuth2Authorization.getPrincipalName(),
                oAuth2Authorization.getAuthorizedScopes(),
        oAuth2Authorization.getAuthorizationGrantType());
        return builderWrapper.token(accessToken).token(refreshToken).build();

    }
}
