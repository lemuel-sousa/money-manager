package money.manager.api.controller.authentication.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestDto(
    @NotBlank(message = "Username field is mandatory.") String username,
    @NotBlank(message = "E-mail field is mandatory.") @Email(message = "invalid e-mail.") String email,
    @NotBlank(message = "Password field is mandatory.") String password) {
}
