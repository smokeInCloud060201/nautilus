package vn.com.lol.nautilus.modules.firstdb.clients.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ClientResponse {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("register_client_id")
    private String registerClientId;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("redirect_url")
    private String redirectUrl;

    @JsonProperty("client_authentication_method")
    private String clientAuthenticationMethod;

    @JsonProperty("token_setting")
    private String tokenSettings;

    @JsonProperty("authorization_grant_types")
    private List<String> authorizationGrantTypes;

    @JsonProperty("client_scope")
    private List<String> clientScope;
}
