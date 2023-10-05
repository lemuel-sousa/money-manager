package money.manager.api.controller.authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidateRquestDto(
    @NotBlank(message = "token is mandatory.") String token) {
}
