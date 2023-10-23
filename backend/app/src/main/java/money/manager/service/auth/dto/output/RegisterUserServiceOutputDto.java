package money.manager.service.auth.dto.output;

public record RegisterUserServiceOutputDto(
    String id,
    String username,
    String email,
    String password) {
}
