package money.manager.api.controller.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
    @NotBlank(message = "E-mail field is mandatory.") @Email(message = "invalid e-mail.") String email,

    @NotBlank(message = "Password field is mandatory") @Size(min = 6, max = 24, message = "Password should have between 6 to 24 characters") String password) {
}
