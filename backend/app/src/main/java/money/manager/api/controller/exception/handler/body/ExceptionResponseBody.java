package money.manager.api.controller.exception.handler.body;

import java.time.Instant;

public record ExceptionResponseBody(
        String error,
        String path,
        Integer status,
        Instant timestamp) {

}
