package vn.com.lol.nautilus.modules.seconddb.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.lol.nautilus.commons.repository.BaseRepository;
import vn.com.lol.nautilus.modules.seconddb.user.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE UPPER(u.email) = UPPER(?1)")
    Optional<User> findByUserName(String userName);
}
