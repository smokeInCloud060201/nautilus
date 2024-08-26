package vn.com.lol.nautilus.commons.security.oauth2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Oauth2GrantType {
    public static final AuthorizationGrantType GRANT_PASSWORD =
        new AuthorizationGrantType("grant_password");

    public static final AuthorizationGrantType REFRESH_TOKEN = AuthorizationGrantType.REFRESH_TOKEN;
    public static final AuthorizationGrantType AUTHORIZATION_CODE = AuthorizationGrantType.AUTHORIZATION_CODE;
    public static final AuthorizationGrantType CLIENT_CREDENTIALS = AuthorizationGrantType.CLIENT_CREDENTIALS;
    public static final AuthorizationGrantType DEVICE_CODE = AuthorizationGrantType.DEVICE_CODE;
    public static final AuthorizationGrantType JWT_BEARER = AuthorizationGrantType.JWT_BEARER;
}
