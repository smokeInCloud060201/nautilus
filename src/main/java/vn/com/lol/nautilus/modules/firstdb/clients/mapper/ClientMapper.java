package vn.com.lol.nautilus.modules.firstdb.clients.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.response.ClientResponse;
import vn.com.lol.nautilus.modules.firstdb.clients.entities.Client;

@Component
@RequiredArgsConstructor
public class ClientMapper {
    public ClientResponse mapEntityToDTO(Client client) {
        return ClientResponse.builder()
                .registerClientId(client.getRegisterClientId())
                .clientId(client.getClientId())
                .clientAuthenticationMethod(client.getClientAuthenticationMethod())
                .clientName(client.getClientName())
                .clientScope(client.getClientScopeList())
                .redirectUrl(client.getRedirectUrl())
                .authorizationGrantTypes(client.getAuthorizationGrantTypeList().stream().map(AuthorizationGrantType::getValue).toList())
                .tokenSettings(client.getTokenSetting().toString())
                .build();
    }
}
