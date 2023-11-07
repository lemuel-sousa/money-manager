package money.manager.api.controller.authentication.dto.input;

import jakarta.validation.constraints.NotBlank;

public record TokenRequestDto(
      @NotBlank(message = "Token field is mandatory.") String token
) {
      
}
