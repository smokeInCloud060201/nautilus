package vn.com.lol.nautilus.modules.auth.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private long expiredTime;
}
