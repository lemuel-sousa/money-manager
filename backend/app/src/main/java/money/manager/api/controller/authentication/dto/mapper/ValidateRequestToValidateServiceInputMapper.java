package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.ValidateRquestDto;
import money.manager.service.auth.dto.ValidateServiceInputDto;

public class ValidateRequestToValidateServiceInputMapper
    implements Function<ValidateRquestDto, ValidateServiceInputDto> {

  public static ValidateRequestToValidateServiceInputMapper build() {
    return new ValidateRequestToValidateServiceInputMapper();
  }

  @Override
  public ValidateServiceInputDto apply(final ValidateRquestDto input) {
    return new ValidateServiceInputDto(input.token());
  }

}
