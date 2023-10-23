package money.manager.service.auth.dto.mapper;

import java.util.function.Function;

import money.manager.domain.user.User;
import money.manager.service.auth.dto.output.RegisterUserServiceOutputDto;

public class UserToRegisterUserServiceOutputMapper implements Function<User, RegisterUserServiceOutputDto> {

  public static UserToRegisterUserServiceOutputMapper build() {
    return new UserToRegisterUserServiceOutputMapper();
  }

  @Override
  public RegisterUserServiceOutputDto apply(final User input) {
    return new RegisterUserServiceOutputDto(
        input.getId(),
        input.getUsername(),
        input.getEmail(),
        input.getPassword());
  }

}
