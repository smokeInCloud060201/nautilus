package vn.com.lol.nautilus.commons.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;

import java.io.IOException;
import java.time.Instant;

public class OAuth2RefreshTokenDeserializer extends JsonDeserializer<OAuth2RefreshToken> {
    @Override
    public OAuth2RefreshToken deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode tokenNode = node.get("token");
        String tokenValue = tokenNode.get("tokenValue").asText();
        Instant issuedAt = Instant.ofEpochSecond(tokenNode.get("issuedAt").longValue());
        Instant expiresAt = Instant.ofEpochSecond(tokenNode.get("expiresAt").longValue());
        return new OAuth2RefreshToken(tokenValue, issuedAt, expiresAt);
    }
}
