package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.input.RegisterUserRequestDto;
import money.manager.service.auth.dto.input.RegisterUserServiceInputDto;

public class RegisterUserRequestToRegisterUserServiceInputMapper
    implements Function<RegisterUserRequestDto, RegisterUserServiceInputDto> {

  public static RegisterUserRequestToRegisterUserServiceInputMapper build() {
    return new RegisterUserRequestToRegisterUserServiceInputMapper();
  }

  @Override
  public RegisterUserServiceInputDto apply(final RegisterUserRequestDto input) {
    return new RegisterUserServiceInputDto(
        input.username(),
        input.email(),
        input.password());
  }

}
