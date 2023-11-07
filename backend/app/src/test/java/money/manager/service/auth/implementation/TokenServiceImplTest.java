package money.manager.service.auth.implementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;

import money.manager.domain.user.User;
import money.manager.service.auth.dto.input.TokenServiceInputDto;
import money.manager.utils.InstantUtils;

public class TokenServiceImplTest {

    @Test
    @DisplayName("Ginven UserDetails Object Wehn Create Tonken Then Return Token Dto Successfully.")
    void testCreateTokenSuccessfully() {
        final var aUser = User.newUser("Limão", "lemuel@gmail.com","123456");

        final var aToken = TokenServiceImpl.build().createToken(aUser);

        assertNotNull(aToken.token(), "Create token not should return null.");
    }

    @Test
    @DisplayName("Ginven UserDetails Object Wehn Create Tonken Then Return Token Dto Successfully.")
    void tesValidateTokenSuccesfully() {
        final var aUser = User.newUser("Limão", "lemuel@gmail.com","123456");
        final var aToken = TokenServiceImpl.build().createToken(aUser).token();

        final var aTokenValidateResult = TokenServiceImpl.build().validateToken(new TokenServiceInputDto(aToken));

        assertNotNull(aTokenValidateResult, "Create token not should return null.");
    }

    @Test
    @DisplayName("Given Anhother Algorithm When Validate Token Then Throw AlgorithmMismatchException.")
    void testValidateTokenFailureWithDiffereteAlgorithm() {
        final var aUser = User.newUser("Limão", "lemuel@gmail.com","123456");

        final var anotherAlgorithm = Algorithm.none();
        final var aToken = TokenServiceImpl.build().createToken(aUser).token();

        final var aVerifier = JWT.require(anotherAlgorithm)
                    .withIssuer("money.manager")
                    .build();

        assertThrows(AlgorithmMismatchException.class, () -> aVerifier.verify(aToken),
             "Should throw an AlgorithmMismatchException");
    }

    @Test
    @DisplayName("Given Invalid Token When Recover Validation Then Return False.")
    void recoverValidationFalse() {
        
        final var aToken = "invalid token";
        final var anInput = new TokenServiceInputDto(aToken);

        final var aValidation = TokenServiceImpl.build().recoverValidation(anInput);

        assertNotNull(aValidation.isValid(), "'isValid' should not be null.");
        assertFalse(aValidation.isValid(), "The 'isValid' property must return as false.");
    }

    @Test
    @DisplayName("Given Valid Token When Recover Validation Then Return True.")
    void recoverValidationTrue(){
        final String TOKEN_SECRET = "money-manager-security-token-secret";
        final String TOKEN_ISSUER = "money.manager";

        final var aSubject = "Limão";

        final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);
        final var aToken = JWT.create()
                .withIssuer(TOKEN_ISSUER)
                .withSubject(aSubject)
                .withExpiresAt(InstantUtils.now().plusSeconds(60 * 60 * 3))
                .sign(anAlgorithm);
        
        final var aServiceInput = new TokenServiceInputDto(aToken);

        final var aValidation = TokenServiceImpl.build().recoverValidation(aServiceInput);

        assertNotNull(aValidation.isValid(), "'isValid' should not be null.");
        assertTrue(aValidation.isValid(), "The 'isValid' property must be return as true.");
    }

}