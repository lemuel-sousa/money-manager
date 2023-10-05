package money.manager.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import money.manager.api.controller.exception.handler.body.ExceptionResponseBody;
import money.manager.domain.exception.DomainException;
import money.manager.service.auth.exception.AuthenticationException;
import money.manager.service.auth.exception.LoginException;
import money.manager.service.auth.exception.ValidateException;
import money.manager.utils.InstantUtils;

@ControllerAdvice("money.manager.api.controller.authentication")
public class AuthControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(DomainException.class)
  protected ResponseEntity<ExceptionResponseBody> handleDomainExceptions(
      final DomainException anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        InstantUtils.now());

    return ResponseEntity.badRequest().body(aBody);
  }

  @ExceptionHandler(LoginException.class)
  protected ResponseEntity<ExceptionResponseBody> handleLoginExceptions(
      final LoginException anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        InstantUtils.now());

    return ResponseEntity.badRequest().body(aBody);
  }

  @ExceptionHandler(AuthenticationException.class)
  protected ResponseEntity<ExceptionResponseBody> handleAuthenticationExceptions(
      final AuthenticationException anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        InstantUtils.now());

    return ResponseEntity.internalServerError().body(aBody);
  }

  @ExceptionHandler(ValidateException.class)
  protected ResponseEntity<ExceptionResponseBody> handleValidateExceptions(
      final ValidateException anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        InstantUtils.now());

    return ResponseEntity.badRequest().body(aBody);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ExceptionResponseBody> handleUnespecificadeExceptions(
      final Exception anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        InstantUtils.now());

    return ResponseEntity.internalServerError().body(aBody);
  }

}
