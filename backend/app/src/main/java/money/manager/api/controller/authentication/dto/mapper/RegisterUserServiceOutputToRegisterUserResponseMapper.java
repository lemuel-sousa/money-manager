package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.output.RegisterUserResponseDto;
import money.manager.service.auth.dto.output.RegisterUserServiceOutputDto;

public class RegisterUserServiceOutputToRegisterUserResponseMapper
    implements Function<RegisterUserServiceOutputDto, RegisterUserResponseDto> {

  public static RegisterUserServiceOutputToRegisterUserResponseMapper build() {
    return new RegisterUserServiceOutputToRegisterUserResponseMapper();
  }

  @Override
  public RegisterUserResponseDto apply(final RegisterUserServiceOutputDto input) {
    return new RegisterUserResponseDto(
        input.id(),
        input.username(),
        input.email(),
        input.password());
  }

}
