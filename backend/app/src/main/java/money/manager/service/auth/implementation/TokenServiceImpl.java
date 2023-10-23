package money.manager.service.auth.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import money.manager.service.auth.TokenService;
import money.manager.service.auth.dto.input.TokenServiceInputDto;
import money.manager.service.auth.dto.output.TokenServiceOutputDto;
import money.manager.service.auth.dto.output.TokenSubjectServiceOutputDto;
import money.manager.service.auth.exception.AuthenticationException;
import money.manager.utils.InstantUtils;

@Service
public class TokenServiceImpl implements TokenService{

    private final String TOKEN_SECRET = "money-manager-security-token-secret";
    private final String TOKEN_ISSUER = "money.manager";

    public static TokenServiceImpl build(){
        return new TokenServiceImpl();
    }

    
    public TokenServiceOutputDto createToken(final UserDetails aUser) {
        try {
            final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var aToken = JWT.create()
                    .withIssuer(TOKEN_ISSUER)
                    .withSubject(aUser.getUsername())
                    .withExpiresAt(InstantUtils.now().plusSeconds(60 * 60 * 4)) // plus 4 hours
                    .sign(anAlgorithm);
            
            return new TokenServiceOutputDto(aToken);
        } catch (JWTCreationException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    public TokenSubjectServiceOutputDto validateToken(final TokenServiceInputDto input) {
        try {
            System.out.println(TOKEN_SECRET);
            final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var aVerifier = JWT.require(anAlgorithm)
                    .withIssuer(TOKEN_ISSUER)
                    .build();

            final var aDecodedToken = aVerifier.verify(input.token());
            final var aTokenSubject = aDecodedToken.getSubject();

            return new TokenSubjectServiceOutputDto(aTokenSubject);

        } catch (IllegalArgumentException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (AlgorithmMismatchException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (SignatureVerificationException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (TokenExpiredException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (MissingClaimException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (IncorrectClaimException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (JWTVerificationException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

}
