package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.output.LoginResponseDto;
import money.manager.service.auth.dto.output.LoginServiceOutputDto;

public class LoginServiceOutputToLoginResponseMapper implements Function<LoginServiceOutputDto, LoginResponseDto> {

  public static LoginServiceOutputToLoginResponseMapper build() {
    return new LoginServiceOutputToLoginResponseMapper();
  }

  @Override
  public LoginResponseDto apply(final LoginServiceOutputDto input) {
    return new LoginResponseDto(input.token());
  }

}
