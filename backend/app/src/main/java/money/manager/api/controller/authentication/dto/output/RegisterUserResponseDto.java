package money.manager.api.controller.authentication.dto.output;

public record RegisterUserResponseDto(
        String id,
        String username,
        String email,
        String password) {
}
