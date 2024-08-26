package vn.com.lol.nautilus.commons.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class Header {
        public static final String AUTHORIZATION = "Authorization";
        public static final String BEARER_TOKEN_TYPE = "Bearer ";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class GrantAuthority {
        public static final String SCOPE = "SCOPE_";
        public static final String ROLE = "ROLE_";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class JWT {
        public static final String IS_ENABLE = "is_enabled";
        public static final String IS_CREDENTIAL_NON_EXPIRED = "is_credential_non_expired";
        public static final String IS_ACCOUNT_NON_LOCKED = "is_credential_non_locked";
        public static final String IS_ACCOUNT_NON_EXPIRED = "is_account_non_expired";
        public static final String IS_EMAIL_VERIFIED = "is_email_verified";
        public static final String IS_MOBILE_NO_VERIFIED = "is_mobile_no_verified";
        public static final String IP_ADDRESS = "ip_address";
    }
}
