package vn.com.lol.nautilus.commons.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.lol.nautilus.commons.enums.ExceptionErrorCode;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    @JsonProperty("data")
    private T data;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("error_code")
    private ExceptionErrorCode errorCode;
    @JsonProperty("meta_data")
    private Map<String, Object> metaData;
}