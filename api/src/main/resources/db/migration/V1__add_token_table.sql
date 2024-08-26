CREATE TABLE IF NOT EXISTS token
(
    id                   BIGINT                      NOT NULL,
    created_at           TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at           TIMESTAMP(6) WITH TIME ZONE NOT NULL DEFAULT now(),
    is_deleted           BOOLEAN                     NOT NULL,
    access_token         TEXT                                 DEFAULT '',
    refresh_token        TEXT                                 DEFAULT '',
    token_id             VARCHAR(80)                          DEFAULT '',
    client_id            VARCHAR(80)                          DEFAULT '',
    oauth2_authorization TEXT                                 DEFAULT FALSE,
    user_name            VARCHAR(80)                 NOT NULL,
    is_refreshed         BOOLEAN                              DEFAULT FALSE,
    token_type           VARCHAR(20)                 NOT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);
