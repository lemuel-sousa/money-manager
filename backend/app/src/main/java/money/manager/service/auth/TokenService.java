package money.manager.service.auth;

import org.springframework.security.core.userdetails.UserDetails;

import money.manager.service.auth.dto.input.TokenServiceInputDto;
import money.manager.service.auth.dto.output.TokenServiceOutputDto;
import money.manager.service.auth.dto.output.TokenSubjectServiceOutputDto;
import money.manager.service.auth.dto.output.ValidateTokenServiceOutputDto;

public interface TokenService {
    TokenServiceOutputDto createToken(final UserDetails aUser);
    TokenSubjectServiceOutputDto validateToken(final TokenServiceInputDto anInput);
    ValidateTokenServiceOutputDto recoverValidation(final TokenServiceInputDto anInput);
    
}
