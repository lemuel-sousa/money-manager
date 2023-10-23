package money.manager.service.auth.dto.mapper;

import java.util.function.Function;

import at.favre.lib.crypto.bcrypt.BCrypt;
import money.manager.domain.user.User;
import money.manager.service.auth.dto.input.RegisterUserServiceInputDto;

public class RegisterUserServiceInputToUserMapper implements Function<RegisterUserServiceInputDto, User> {

  public static RegisterUserServiceInputToUserMapper build() {
    return new RegisterUserServiceInputToUserMapper();
  }

  @Override
  public User apply(final RegisterUserServiceInputDto input) {
    return User.newUser(
        input.getUsername(),
        input.getEmail(),
        BCrypt.withDefaults()
          .hashToString(12, input.getPassword().toCharArray()));
  }

}
