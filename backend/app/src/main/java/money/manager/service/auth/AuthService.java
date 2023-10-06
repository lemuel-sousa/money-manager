package money.manager.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import money.manager.domain.user.User;
import money.manager.service.auth.dto.LoginInputDto;
import money.manager.service.auth.dto.LoginOutputDto;
import money.manager.service.auth.dto.ValidateServiceInputDto;
import money.manager.service.auth.exception.AuthenticationException;
import money.manager.service.auth.exception.LoginException;
import money.manager.service.auth.exception.ValidateException;
import money.manager.utils.InstantUtils;

@Service
public class AuthService implements UserDetailsService {

  final User uniqueUser = User.with("user@example.com", "123456");

  private final String TOKEN_SECRET = "12345689";
  private final String TOKEN_ISSUER = "money.manager";

  public LoginOutputDto login(final LoginInputDto input) {

    final var anUser = User.with(input.email(), input.password());

    if (!uniqueUser.getEmail().equals(anUser.getEmail())
        || !uniqueUser.getPassword().equals(anUser.getPassword())) {
      throw new LoginException("User or password not found");
    }

    final var aToken = this.createToken(anUser);

    return new LoginOutputDto(aToken);
  }

  private String createToken(final User anUser) {
    try {
      final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);
      final var aToken = JWT.create()
          .withIssuer(TOKEN_ISSUER)
          .withSubject(anUser.getEmail())
          .withExpiresAt(InstantUtils.now().plusSeconds(60 * 60 * 4))
          .sign(anAlgorithm);

      return aToken;
    } catch (IllegalArgumentException e) {
      throw new AuthenticationException(e.getMessage());
    } catch (JWTCreationException e) {
      throw new AuthenticationException(e.getMessage());
    }
  }

  public String validateToken(final ValidateServiceInputDto input) {
    try {
      // validates if the token siganrure corresponds with the siganrure algorithm and
      // password
      final var anAlgorithm = Algorithm.HMAC256(TOKEN_SECRET);
      final var aVerifier = JWT.require(anAlgorithm)
          .withIssuer(TOKEN_ISSUER)
          .build();

      // decodes the token
      final var aDecodedToken = aVerifier.verify(input.token());

      return aDecodedToken.getSubject();
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

  public boolean isValid(final String aSubjectToken) {
    if (!aSubjectToken.isBlank()) {
      return true;
    } else {
      throw new ValidateException("invalid token.");
    }
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    if (username.equals(this.uniqueUser.getUsername())) {
      return this.uniqueUser;
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
  }
}
