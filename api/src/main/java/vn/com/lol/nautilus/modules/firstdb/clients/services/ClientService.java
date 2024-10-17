package vn.com.lol.nautilus.modules.firstdb.clients.services;


import vn.com.lol.nautilus.modules.firstdb.clients.dto.request.UpdateClientRequest;
import vn.com.lol.nautilus.modules.firstdb.clients.dto.response.ClientResponse;

public interface ClientService {


    ClientResponse updateClient(Long clientId, UpdateClientRequest request);
    void removeClient(Long clientId);
}
