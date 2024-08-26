package vn.com.lol.nautilus.modules.firstdb.clients.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.lol.common.dto.request.SearchDTO;
import vn.com.lol.common.exceptions.ResourceNotFoundException;
import vn.com.lol.common.search.SearchRequestMapper;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.request.UpdateClientRequest;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.response.ClientResponse;
import vn.com.lol.nautilus.modules.firstdb.clients.entities.Client;
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
    public Page<ClientResponse> getAllClient(SearchDTO searchDTO) {
        Pageable pageable = SearchRequestMapper.getPageable(searchDTO);
        Specification<Client> clientSpecification = SearchRequestMapper.getSpecifications(searchDTO.getFilterList());
        clientSpecification = clientSpecification.and(SearchRequestMapper.getSpecForSearchAll(searchDTO.getSearchAllKey(), Client.class));
        return clientRepository.findAll(clientSpecification, pageable).map(clientMapper::mapEntityToDTO);
    }

    @Override
    public ClientResponse getClientById(Long clientId) throws ResourceNotFoundException {
        return clientMapper.mapEntityToDTO(clientRepository.findByClientId(String.valueOf(clientId))
                .orElseThrow(() -> new ResourceNotFoundException("Does not exists client")));
    }

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
