package vn.com.lol.nautilus.commons.utils;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.HashMap;
import java.util.Set;

public class OAuth2AuthorizationWrapper extends OAuth2Authorization {

    public static class BuilderWrapper extends OAuth2Authorization.Builder {

        public BuilderWrapper(String registeredClientId, String id, OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken, String principalName, Set<String> scopes, AuthorizationGrantType grantType) {
            super(registeredClientId);
            super.id(id);
            super.accessToken(accessToken);
            super.refreshToken(refreshToken);
            super.principalName(principalName);
            super.authorizedScopes(scopes);
            super.tokens(new HashMap<>());
            super.authorizationGrantType(grantType);

        }
    }

}
