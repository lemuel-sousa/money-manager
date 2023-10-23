package money.manager.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import money.manager.domain.gateway.UserGateway;
import money.manager.service.auth.dto.input.LoginServiceInputDto;
import money.manager.service.auth.dto.input.RegisterUserServiceInputDto;
import money.manager.service.auth.dto.mapper.RegisterUserServiceInputToUserMapper;
import money.manager.service.auth.dto.mapper.UserToRegisterUserServiceOutputMapper;
import money.manager.service.auth.dto.output.LoginServiceOutputDto;
import money.manager.service.auth.dto.output.RegisterUserServiceOutputDto;
import money.manager.service.auth.exception.LoginException;
import money.manager.service.exception.ServiceException;

@Service
public class AuthService implements UserDetailsService {

  private UserGateway userGateway;
  private TokenService tokenService;

  private AuthService(final UserGateway aGateway, final TokenService aTokenService) {
    this.userGateway = aGateway;
    this.tokenService = aTokenService;
  }

  public static AuthService build(final UserGateway aGateway, final TokenService aTokenService) {
    return new AuthService(aGateway, aTokenService);
  }

  public LoginServiceOutputDto login(final LoginServiceInputDto anInput) {
    final var aUser = this.loadUserByUsername(anInput.username());

    final var aPasswordVerify = BCrypt.verifyer().verify(anInput.password().toCharArray(), aUser.getPassword());

    if (!aPasswordVerify.verified)
      throw new LoginException("Invalid user.");

    final var aNewToken = this.tokenService.createToken(aUser);
    
    return new LoginServiceOutputDto(aNewToken.toString());
  }

  public RegisterUserServiceOutputDto register(final RegisterUserServiceInputDto anInput) {
    if (this.userGateway.findByUsername(anInput.getUsername()) != null)
      throw new ServiceException(
          String.format("A user with the username '%s' already exists. ", anInput.getUsername()));

    final var aUser = RegisterUserServiceInputToUserMapper.build().apply(anInput);

    this.userGateway.save(aUser);

    return UserToRegisterUserServiceOutputMapper.build().apply(aUser);
  }

  @Override
  public UserDetails loadUserByUsername(final String aUsername) throws UsernameNotFoundException {
    final var aUser = this.userGateway.findByUsername(aUsername);

    if (aUser == null)
      throw new UsernameNotFoundException("User not found");

    return aUser;
  }
}
