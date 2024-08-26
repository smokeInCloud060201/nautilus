package vn.com.lol.nautilus.commons.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.io.IOException;

public class AuthorizationGrantTypeDeserializer extends JsonDeserializer<AuthorizationGrantType> {
    @Override
    public AuthorizationGrantType deserialize(JsonParser p, DeserializationContext text) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String nodeValue = node.get("value").asText();
        return new AuthorizationGrantType(nodeValue);
    }
}