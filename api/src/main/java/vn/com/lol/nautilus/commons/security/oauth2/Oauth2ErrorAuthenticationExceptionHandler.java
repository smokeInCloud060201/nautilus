package vn.com.lol.nautilus.commons.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import vn.com.lol.nautilus.commons.dto.response.BaseResponse;
import vn.com.lol.nautilus.commons.enums.ExceptionErrorCode;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class Oauth2ErrorAuthenticationExceptionHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        BaseResponse<Object> re = BaseResponse.builder()
                .errorMessage("Error : " + exception.getMessage())
                .errorCode(ExceptionErrorCode.E_004)
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, re);
        responseStream.flush();
    }
}
