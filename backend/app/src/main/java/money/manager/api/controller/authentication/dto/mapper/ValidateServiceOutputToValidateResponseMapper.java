package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.ValidateResponseDto;
import money.manager.service.auth.dto.ValidateServiceOutputDto;

public class ValidateServiceOutputToValidateResponseMapper
    implements Function<ValidateServiceOutputDto, ValidateResponseDto> {

  public static ValidateServiceOutputToValidateResponseMapper build() {
    return new ValidateServiceOutputToValidateResponseMapper();
  }

  @Override
  public ValidateResponseDto apply(ValidateServiceOutputDto input) {
    return new ValidateResponseDto(input.isValid());
  }

}
