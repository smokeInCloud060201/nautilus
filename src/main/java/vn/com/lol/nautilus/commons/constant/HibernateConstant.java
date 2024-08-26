package vn.com.lol.nautilus.commons.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static vn.com.lol.common.constants.GlobalHibernateConstant.Table.SOFT_DELETE_BY_ID_QUERY;
import static vn.com.lol.common.constants.GlobalHibernateConstant.Table.UPDATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Table {

        public static final String USER = "users";
        public static final String ROLE = "role";
        public static final String PERMISSION = "permission";
        public static final String TOKEN = "token";
        public static final String CLIENTS = "clients";
        public static final String TOKEN_SETTING = "token_setting";
        public static final String AUTHORIZATION_GRANT_TYPE = "authorization_grant_type";
        public static final String CLIENT_SCOPE = "client_scope";

        public static final String SOFT_DELETE_TOKEN = UPDATE + TOKEN + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_CLIENT = UPDATE + CLIENTS + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_TOKEN_SETTING = UPDATE + TOKEN_SETTING + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_AUTHORIZATION_GRANT_TYPE = UPDATE + AUTHORIZATION_GRANT_TYPE + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_CLIENT_SCOPE = UPDATE + CLIENT_SCOPE + SOFT_DELETE_BY_ID_QUERY;

    }

    /**
     * The HibernateConstant.Entity class modifier abstract constants of entity
     *
     * @author Ngoc Khanh
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Entity {
        public static final String USER = "User";
        public static final String ROLE = "Role";
        public static final String PERMISSION = "Permission";
        public static final String TOKEN = "Token";
        public static final String CLIENTS = "Clients";
        public static final String TOKEN_SETTING = "TokenSetting";
        public static final String AUTHORIZATION_GRANT_TYPE = "AuthorizationGrantType";
        public static final String CLIENT_SCOPE = "ClientScope";
    }
}
