package vn.com.lol.nautilus.modules.firstdb.clients.entities;

import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import vn.com.lol.nautilus.commons.constant.HibernateConstant;
import vn.com.lol.nautilus.commons.entity.BaseEntity;
import vn.com.lol.nautilus.commons.serializer.TokenSettingsDeserializer;
import vn.com.lol.nautilus.commons.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static vn.com.lol.nautilus.commons.constant.HibernateConstant.IS_NOT_DELETED;
import static vn.com.lol.nautilus.commons.constant.HibernateConstant.Table.SOFT_DELETE_CLIENT;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.CLIENTS)
@Table(name = HibernateConstant.Table.CLIENTS)
@SQLRestriction(IS_NOT_DELETED)
@SQLDelete(sql = SOFT_DELETE_CLIENT)
@Slf4j
public class Client extends BaseEntity {

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "register_client_id")
    private String registerClientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "redirect_url")
    private String redirectUrl;

    @Column(name = "client_authentication_method")
    private String clientAuthenticationMethod;

    @Getter(AccessLevel.NONE)
    @Column(name = "token_setting")
    private String tokenSettings;

    @Getter(AccessLevel.NONE)
    @Column(name = "authorization_grant_type")
    private String authrorizationGrantType;

    @Getter(AccessLevel.NONE)
    @Column(name = "client_scopes")
    private String clientScope;

    @Transient
    private TokenSettings tokenSetting;

    @Transient
    private List<AuthorizationGrantType> authorizationGrantTypeList = new ArrayList<>();

    @Transient
    private List<String> clientScopeList = new ArrayList<>();


    public TokenSettings getTokenSetting() {
        if (tokenSetting == null && !tokenSettings.isEmpty()) {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(TokenSettings.class, new TokenSettingsDeserializer());
            JsonUtil.setMapper(module);
            tokenSetting = JsonUtil.getObjectFromJsonString(TokenSettings.class, this.tokenSettings, TokenSettings.builder().build());
        }
        return tokenSetting;
    }

    public List<AuthorizationGrantType> getAuthorizationGrantTypeList() {
        if (!this.authrorizationGrantType.isEmpty()) {
            authorizationGrantTypeList = Arrays.stream(authrorizationGrantType.split(","))
                    .map(AuthorizationGrantType::new)
                    .toList();
        }
        return authorizationGrantTypeList;
    }

    public List<String> getClientScopeList() {
        if (!this.clientScope.isEmpty()) {
            clientScopeList = Arrays.stream(clientScope.split(",")).toList();
        }
        return clientScopeList;
    }
}
