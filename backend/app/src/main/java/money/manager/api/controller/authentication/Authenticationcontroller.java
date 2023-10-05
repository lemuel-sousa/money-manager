package money.manager.api.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import money.manager.api.controller.authentication.dto.LoginRequestDto;
import money.manager.api.controller.authentication.dto.LoginResponseDto;
import money.manager.api.controller.authentication.dto.ValidateResponseDto;
import money.manager.api.controller.authentication.dto.ValidateRquestDto;
import money.manager.api.controller.authentication.dto.mapper.LoginRequestToLoginServiceInputMapper;
import money.manager.api.controller.authentication.dto.mapper.ValidateRequestToValidateServiceInputMapper;
import money.manager.api.controller.authentication.dto.mapper.ValidateServiceOutputToValidateResponseMapper;
import money.manager.service.auth.AuthService;

@RestController
@RequestMapping("/auth")
public class Authenticationcontroller {

  @Autowired
  private AuthService authService;

  @PostMapping("login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid final LoginRequestDto input) {
    final var aToken = authService.login(LoginRequestToLoginServiceInputMapper
        .build().apply(input)).token();

    final var aTokenResponse = new LoginResponseDto(aToken);

    return ResponseEntity.ok().body(aTokenResponse);
  }

  @PostMapping("validate")
  public ResponseEntity<ValidateResponseDto> validate(@RequestBody @Valid final ValidateRquestDto input) {
    final var aServiceOutput = this.authService.validateToken(
        ValidateRequestToValidateServiceInputMapper.build().apply(input));

    final var aResponse = ValidateServiceOutputToValidateResponseMapper.build().apply(aServiceOutput);

    return ResponseEntity.ok().body(aResponse);
  }

}
