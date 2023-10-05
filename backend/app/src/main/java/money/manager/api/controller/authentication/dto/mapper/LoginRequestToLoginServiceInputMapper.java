package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.LoginRequestDto;
import money.manager.service.auth.dto.LoginInputDto;

public class LoginRequestToLoginServiceInputMapper implements Function<LoginRequestDto, LoginInputDto> {

  public static LoginRequestToLoginServiceInputMapper build() {
    return new LoginRequestToLoginServiceInputMapper();
  }

  @Override
  public LoginInputDto apply(final LoginRequestDto input) {
    return new LoginInputDto(input.email(), input.password());
  }

}
