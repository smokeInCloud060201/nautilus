package vn.com.lol.nautilus.commons.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.io.IOException;
import java.time.Duration;

public class TokenSettingsDeserializer extends JsonDeserializer<TokenSettings> {
    @Override
    public TokenSettings deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        // Extract fields from JSON node
        boolean reuseRefreshTokens = node.get("settings.token.reuse-refresh-tokens").asBoolean();
        String idTokenSignatureAlgorithm = node.get("settings.token.id-token-signature-algorithm").asText();
        long accessTokenTimeToLive = node.get("settings.token.access-token-time-to-live").asLong();
        String accessTokenFormatValue = node.get("settings.token.access-token-format").get("value").asText();
        long refreshTokenTimeToLive = node.get("settings.token.refresh-token-time-to-live").asLong();
        long authorizationCodeTimeToLive = node.get("settings.token.authorization-code-time-to-live").asLong();
        long deviceCodeTimeToLive = node.get("settings.token.device-code-time-to-live").asLong();

        // Create an instance of TokenSettings using a builder or a factory method
        return TokenSettings.builder()
                .reuseRefreshTokens(reuseRefreshTokens)
                .idTokenSignatureAlgorithm(SignatureAlgorithm.valueOf(idTokenSignatureAlgorithm))
                .accessTokenTimeToLive(Duration.ofSeconds(accessTokenTimeToLive))
                .accessTokenFormat(new OAuth2TokenFormat(accessTokenFormatValue))
                .refreshTokenTimeToLive(Duration.ofSeconds(refreshTokenTimeToLive))
                .authorizationCodeTimeToLive(Duration.ofSeconds(authorizationCodeTimeToLive))
                .deviceCodeTimeToLive(Duration.ofDays(deviceCodeTimeToLive))
                .build();
    }
}