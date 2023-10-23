package money.manager.api.controller.authentication.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
                @NotBlank(message = "Username field is mandatory.") String username,
                @NotBlank(message = "Password field is mandatory") @Size(min = 6, max = 24, message = "Password should have between 6 to 24 characters") String password) {
}
