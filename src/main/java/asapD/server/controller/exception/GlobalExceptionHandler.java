package asapD.server.controller.exception;

import asapD.server.response.ExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleBadCredentialsExceptionHandler(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message("로그인에 실패하였습니다")
                        .build()
        );
    }


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(ApiException e) {
        ApiExceptionEnum exception = e.getError();
        log.error(exception.getHttpStatus().getReasonPhrase(), e);

        return ResponseEntity.status(exception.getStatus()).body(
            ExceptionResponse.builder().status(exception.getStatus()).message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> validationExceptionHandler(
        ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message(e.getMessage())
                .build());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(
        ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ExceptionResponse.builder().status(HttpStatus.NOT_FOUND.value()).message(e.getMessage())
                .build());
    }
}
