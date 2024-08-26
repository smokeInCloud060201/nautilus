package vn.com.lol.nautilus.commons.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;

public class OAuth2AccessTokenDeserializer extends JsonDeserializer<OAuth2AccessToken> {

    @Override
    public OAuth2AccessToken deserialize(JsonParser p, DeserializationContext text) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode tokenNode = node.get("token");
        String tokenValue = tokenNode.get("tokenValue").asText();
        Instant issuedAt = Instant.ofEpochSecond(tokenNode.get("issuedAt").longValue());
        Instant expiresAt = Instant.ofEpochSecond(tokenNode.get("expiresAt").longValue());
        OAuth2AccessToken.TokenType tokenType = OAuth2AccessToken.TokenType.BEARER;

        Set<String> scopes = Collections.emptySet(); // Modify this to parse scopes if present in your JSON

        return new OAuth2AccessToken(tokenType, tokenValue, issuedAt, expiresAt, scopes);
    }
}