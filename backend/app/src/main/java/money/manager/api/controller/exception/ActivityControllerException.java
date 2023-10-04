package money.manager.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;
import money.manager.api.controller.exception.handler.body.ExceptionResponseBody;
import money.manager.domain.exception.DomainException;
import money.manager.service.exception.ServiceException;
import money.manager.utils.InstantUtils;

@ControllerAdvice("money.manager.api.controller.activity")
public class ActivityControllerException extends ResponseEntityExceptionHandler {

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

  @ExceptionHandler(PersistenceException.class)
  protected ResponseEntity<ExceptionResponseBody> hadlePersistenceExceptions(
      final PersistenceException anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        InstantUtils.now());

    return ResponseEntity.internalServerError().body(aBody);
  }

  @ExceptionHandler(ServiceException.class)
  protected ResponseEntity<ExceptionResponseBody> handleServiceExceptions(
      final ServiceException anException, final HttpServletRequest aRequest) {

    final var aBody = new ExceptionResponseBody(
        anException.getMessage(),
        aRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        InstantUtils.now());

    return ResponseEntity.badRequest().body(aBody);
  }
}
