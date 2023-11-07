package money.manager.api.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import money.manager.api.controller.authentication.dto.input.LoginRequestDto;
import money.manager.api.controller.authentication.dto.input.RegisterUserRequestDto;
import money.manager.api.controller.authentication.dto.input.TokenRequestDto;
import money.manager.api.controller.authentication.dto.mapper.LoginRequestToLoginServiceInputMapper;
import money.manager.api.controller.authentication.dto.mapper.LoginServiceOutputToLoginResponseMapper;
import money.manager.api.controller.authentication.dto.mapper.RegisterUserRequestToRegisterUserServiceInputMapper;
import money.manager.api.controller.authentication.dto.mapper.RegisterUserServiceOutputToRegisterUserResponseMapper;
import money.manager.api.controller.authentication.dto.mapper.TokenRequestToTokenServiceInputMapper;
import money.manager.api.controller.authentication.dto.mapper.ValidateTokenServiceOutputToValidateTokenResponseMapper;
import money.manager.api.controller.authentication.dto.output.LoginResponseDto;
import money.manager.api.controller.authentication.dto.output.RegisterUserResponseDto;
import money.manager.api.controller.authentication.dto.output.ValidateTokenResponseDto;
import money.manager.repository.user.UserJpaGateway;
import money.manager.repository.user.jpa.UserJpaRepository;
import money.manager.service.auth.AuthService;
import money.manager.service.auth.implementation.TokenServiceImpl;

@RestController
@RequestMapping("/auth")
public class Authenticationcontroller {

  @Autowired
  private UserJpaRepository userRepository;

  @PostMapping("login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid final LoginRequestDto input) {
    final var aGateway = UserJpaGateway.build(userRepository);
    final var aTokenService = TokenServiceImpl.build();
    final var aService = AuthService.build(aGateway, aTokenService);

    final var aServiceInput = LoginRequestToLoginServiceInputMapper.build().apply(input);

    final var aServiceOutput = aService.login(aServiceInput);
    final var aResponse = LoginServiceOutputToLoginResponseMapper.build().apply(aServiceOutput);

    return ResponseEntity.ok(aResponse);
  }

  @PostMapping("register")
  public ResponseEntity<RegisterUserResponseDto> register(@RequestBody @Valid final RegisterUserRequestDto input) {
    final var aGateway = UserJpaGateway.build(userRepository);
    final var aTokenService = TokenServiceImpl.build();
    final var aService = AuthService.build(aGateway, aTokenService);

    final var anInput = RegisterUserRequestToRegisterUserServiceInputMapper.build().apply(input);

    final var aServiceOutput = aService.register(anInput);
    final var aResponse = RegisterUserServiceOutputToRegisterUserResponseMapper.build().apply(aServiceOutput);

    return ResponseEntity.ok(aResponse);
  }


  @PostMapping("validate")
  public ResponseEntity<ValidateTokenResponseDto> validate(final @RequestBody @Valid TokenRequestDto input) {
    final var aService = TokenServiceImpl.build();

    final var aServiceInput = TokenRequestToTokenServiceInputMapper.build().apply(input);
    final var aServiceOutput =  aService.recoverValidation(aServiceInput);

    final var aResponse = ValidateTokenServiceOutputToValidateTokenResponseMapper.build().apply(aServiceOutput);

    return ResponseEntity.ok(aResponse);
  }
}
