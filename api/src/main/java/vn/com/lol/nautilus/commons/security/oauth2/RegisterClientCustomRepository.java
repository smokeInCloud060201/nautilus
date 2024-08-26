package vn.com.lol.nautilus.commons.security.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;
import vn.com.lol.nautilus.modules.firstdb.clients.entities.Client;
import vn.com.lol.nautilus.modules.firstdb.clients.repositories.ClientRepository;

@Component("customRegistrationRepository")
@RequiredArgsConstructor
@Slf4j
public class RegisterClientCustomRepository implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        log.info("Saving registered client");
    }

    @Override
    public RegisteredClient findById(String id) {
        log.info("Finding registered client with id {}", id);
        return mapClientToRegisteredClient(clientRepository.findByClientId(id).orElse(null));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        log.info("Finding registered client with clientId {}", clientId);
        return mapClientToRegisteredClient(clientRepository.findByClientId(clientId).orElse(null));
    }

    private RegisteredClient mapClientToRegisteredClient(Client client) {
        if (client == null) {
            return null;
        }
        return RegisteredClient.withId(client.getRegisterClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientName(client.getClientName())
                .redirectUri(client.getRedirectUrl())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getClientAuthenticationMethod()))
                .authorizationGrantTypes(authorizationGrantTypes -> authorizationGrantTypes.addAll(client.getAuthorizationGrantTypeList()))
                .tokenSettings(client.getTokenSetting())
                .scopes(scopes -> scopes.addAll(client.getClientScopeList()))
                .build();
    }
}
