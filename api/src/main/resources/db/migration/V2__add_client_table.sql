CREATE TABLE IF NOT EXISTS clients
(
    id                           BIGINT                      NOT NULL,
    created_at                   TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at                   TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted                   BOOLEAN                     NOT NULL,
    client_id                    VARCHAR(80) UNIQUE          NOT NULL DEFAULT '',
    client_secret                VARCHAR(80)                 NOT NULL DEFAULT '',
    register_client_id           VARCHAR(80) UNIQUE          NOT NULL DEFAULT '',
    client_name                  VARCHAR(80)                 NOT NULL DEFAULT '',
    redirect_url                 TEXT                                 DEFAULT '',
    client_authentication_method VARCHAR(30)                          DEFAULT 'client_secret_post',
    token_setting                TEXT,
    authorization_grant_type     TEXT,
    client_scopes                TEXT,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);


INSERT INTO clients(id, created_at, updated_at, is_deleted, client_id, client_secret, register_client_id, client_name,
                    redirect_url, client_authentication_method, token_setting, authorization_grant_type, client_scopes)
VALUES (1111111111111111, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'demo-client',
        '$2a$10$NyUJXgkdoztj7Leq9MOM8.1NGdIUJcx.kWzEP.97WCA7Uek0VCflC', '75f78c2f-f0dc-4f75-b9d0-64f87604f9ea',
        'Demo client',
        'http://localhost:5173/auth', 'client_secret_post',
        '{"settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":"RS256","settings.token.access-token-time-to-live":3600.000000000,"settings.token.access-token-format":{"value":"self-contained"},"settings.token.refresh-token-time-to-live":86400.000000000,"settings.token.authorization-code-time-to-live":300.000000000,"settings.token.device-code-time-to-live":300.000000000},"refreshTokenTimeToLive":86400.000000000,"accessTokenTimeToLive":3600.000000000,"reuseRefreshTokens":true,"accessTokenFormat":{"value":"self-contained"},"idTokenSignatureAlgorithm":"RS256","deviceCodeTimeToLive":300.000000000,"authorizationCodeTimeToLive":300.000000000}',
        'authorization_code,refresh_token,grant_password,client_credentials', 'oid,profile,notification')