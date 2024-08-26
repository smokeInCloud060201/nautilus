package vn.com.lol.nautilus.modules.firstdb.token.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.nautilus.commons.constant.HibernateConstant;
import vn.com.lol.nautilus.modules.firstdb.token.enums.TokenType;

import static vn.com.lol.common.constants.GlobalHibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.nautilus.commons.constant.HibernateConstant.Table.SOFT_DELETE_TOKEN;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.TOKEN)
@Table(name = HibernateConstant.Table.TOKEN)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_TOKEN)
public class Token extends BaseEntity {
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "oauth2_authorization")
    private String oauth2Authorization;

    @Column(name = "user_name")
    private String username;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "is_refreshed")
    private boolean isRefreshed;
}
