package vn.com.lol.nautilus.modules.auth.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
