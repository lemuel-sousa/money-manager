package money.manager.api.controller.authentication.dto.mapper;

import java.util.function.Function;

import money.manager.api.controller.authentication.dto.input.TokenRequestDto;
import money.manager.service.auth.dto.input.TokenServiceInputDto;

public class TokenRequestToTokenServiceInputMapper implements Function<TokenRequestDto, TokenServiceInputDto>{

      public static TokenRequestToTokenServiceInputMapper build() {
            return new TokenRequestToTokenServiceInputMapper();
      }

      @Override
      public TokenServiceInputDto apply(final TokenRequestDto input) {
            return new TokenServiceInputDto(input.token());
      }



}
