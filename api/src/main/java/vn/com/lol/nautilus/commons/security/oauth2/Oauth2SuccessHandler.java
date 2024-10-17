package vn.com.lol.nautilus.commons.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import vn.com.lol.nautilus.commons.dto.response.BaseResponse;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Component
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;
        BaseResponse<AuthenticationResponse> re = BaseResponse.<AuthenticationResponse>builder()
                .data(AuthenticationResponse.builder()
                        .accessToken(accessTokenAuthentication
                                .getAccessToken()
                                .getTokenValue())
                        .refreshToken(Optional.ofNullable(accessTokenAuthentication.getRefreshToken())
                                .map(AbstractOAuth2Token::getTokenValue)
                                .orElse(null))
                        .build())
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, re);
        responseStream.flush();
    }
}
