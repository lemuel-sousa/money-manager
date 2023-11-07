package money.manager.api.controller.authentication.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidateTokenResponseDto(
      @JsonProperty("is_valid") boolean isValid
) {
      
}
