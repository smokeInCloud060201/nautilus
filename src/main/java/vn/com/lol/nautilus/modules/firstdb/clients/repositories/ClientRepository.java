package vn.com.lol.nautilus.modules.firstdb.clients.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.lol.common.repository.BaseRepository;
import vn.com.lol.nautilus.modules.firstdb.clients.entities.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {

    @Query("SELECT c FROM Clients c WHERE UPPER(c.clientId) = UPPER(?1)")
    Optional<Client> findByClientId(String clientId);

    @Query("SELECT c FROM Clients c WHERE UPPER(c.registerClientId) = UPPER(?1)")
    Optional<Client> findByRegisterClientId(String registerClientId);
}
