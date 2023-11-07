package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.output.ValidateTokenResponseDto;
import money.manager.service.auth.dto.output.ValidateTokenServiceOutputDto;

public class ValidateTokenServiceOutputToValidateTokenResponseMapper
            implements Function<ValidateTokenServiceOutputDto, ValidateTokenResponseDto> {

      public static ValidateTokenServiceOutputToValidateTokenResponseMapper build() {
            return new ValidateTokenServiceOutputToValidateTokenResponseMapper();
      }

      @Override
      public ValidateTokenResponseDto apply(final ValidateTokenServiceOutputDto input) {
            return new ValidateTokenResponseDto(input.isValid());
      }

}
