package vn.com.lol.nautilus.modules.firstdb.clients.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.request.UpdateClientRequest;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.response.ClientResponse;
import vn.com.lol.nautilus.modules.firstdb.clients.mapper.ClientMapper;
import vn.com.lol.nautilus.modules.firstdb.clients.repositories.ClientRepository;
import vn.com.lol.nautilus.modules.firstdb.clients.services.ClientService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse updateClient(Long clientId, UpdateClientRequest request) {
        return null;
    }

    @Override
    public void removeClient(Long clientId) {
        log.info("Start remove client {}", clientId);
        clientRepository.findById(clientId).ifPresent(clientRepository::delete);
    }
}
