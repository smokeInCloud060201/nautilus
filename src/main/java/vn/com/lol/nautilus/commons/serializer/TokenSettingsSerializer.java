package vn.com.lol.nautilus.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.io.IOException;

public class TokenSettingsSerializer extends JsonSerializer<TokenSettings> {

    @Override
    public void serialize(TokenSettings tokenSettings, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField("settings.token.reuse-refresh-tokens", tokenSettings.isReuseRefreshTokens());
        gen.writeStringField("settings.token.id-token-signature-algorithm", tokenSettings.getIdTokenSignatureAlgorithm().getName());
        gen.writeNumberField("settings.token.access-token-time-to-live", tokenSettings.getAccessTokenTimeToLive().getSeconds());
        gen.writeObjectFieldStart("settings.token.access-token-format");
        gen.writeStringField("value", tokenSettings.getAccessTokenFormat().getValue());
        gen.writeEndObject();
        gen.writeNumberField("settings.token.refresh-token-time-to-live", tokenSettings.getRefreshTokenTimeToLive().getSeconds());
        gen.writeNumberField("settings.token.authorization-code-time-to-live", tokenSettings.getAuthorizationCodeTimeToLive().getSeconds());
        gen.writeNumberField("settings.token.device-code-time-to-live", tokenSettings.getDeviceCodeTimeToLive().getSeconds());
        gen.writeEndObject();
    }
}