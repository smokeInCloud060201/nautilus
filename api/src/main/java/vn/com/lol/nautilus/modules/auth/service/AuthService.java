package vn.com.lol.nautilus.modules.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.com.lol.nautilus.commons.exception.BadRequestException;
import vn.com.lol.nautilus.modules.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;

import java.util.Map;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest, HttpServletRequest request) throws BadRequestException;

    AuthenticationResponse refreshToken(Map<String, String> refreshTokenRequest, HttpServletRequest servletRequest) throws BadRequestException;
}
