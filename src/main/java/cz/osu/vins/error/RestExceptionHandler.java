package cz.osu.vins.error;

import cz.osu.vins.error.exception.InvalidTokenException;
import cz.osu.vins.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidTokenException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public MessageResponse badRequestException(Exception exception, WebRequest request) {
        return new MessageResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), Instant.now().toString());
    }
}
