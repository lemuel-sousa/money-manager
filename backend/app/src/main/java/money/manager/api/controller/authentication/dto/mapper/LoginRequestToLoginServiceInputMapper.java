package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.input.LoginRequestDto;
import money.manager.service.auth.dto.input.LoginServiceInputDto;

public class LoginRequestToLoginServiceInputMapper implements Function<LoginRequestDto, LoginServiceInputDto> {

  public static LoginRequestToLoginServiceInputMapper build() {
    return new LoginRequestToLoginServiceInputMapper();
  }

  @Override
  public LoginServiceInputDto apply(final LoginRequestDto input) {
    return new LoginServiceInputDto(
        input.username(),
        input.password());
  }

}
