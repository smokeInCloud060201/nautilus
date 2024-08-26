package vn.com.lol.nautilus.modules.firstdb.token;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.lol.nautilus.modules.firstdb.token.entities.Token;
import vn.com.lol.common.repository.BaseRepository;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<Token, Long> {

    @Query("SELECT t FROM Token t " +
            " WHERE (t.accessToken = ?1 OR t.refreshToken = ?1) " +
            " AND (t.refreshToken = ?2 OR t.accessToken = ?2) " +
            " AND t.clientId = ?3" +
            "   AND t.tokenType = ?4 " +
            "   AND t.isRefreshed = FALSE")
    Optional<Token> findByToken(String token, String refreshToken, String clientId, TokenType tokenType);

    @Query("SELECT t FROM Token t " +
            " WHERE (t.refreshToken = ?1 OR t.accessToken = ?1) " +
            "   AND t.tokenType = ?2 " +
            "   AND t.isRefreshed = FALSE")
    Optional<Token> findByToken(String token, TokenType tokenType);

    @Query("SELECT t FROM Token t " +
            " WHERE t.accessToken = ?1 " +
            "   AND t.refreshToken = ?2 " +
            "   AND t.tokenType = ?3 " +
            "   AND t.isRefreshed = FALSE")
    Optional<Token> findByToken(String accessToken, String refreshToken, TokenType tokenType);

    @Query("SELECT t FROM Token t " +
            " WHERE t.tokenId = ?1" +
            "   AND t.tokenType = ?2 " +
            "   AND t.isRefreshed = FALSE")
    Optional<Token> findByTokenId(String id, TokenType tokenType);

    @Query("SELECT COUNT(t) > 0 FROM Token t " +
            " WHERE t.refreshToken = ?1 " +
            "   AND t.tokenType = ?2" +
            "   AND t.isRefreshed = FALSE")
    boolean isTokenNotRefreshed(String refreshToken, TokenType tokenType);
}
