package asapD.server.controller.exception;

import asapD.server.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsExceptionHandler(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message("로그인에 실패하였습니다")
                        .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message("타입이 맞지 않습니다").build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionResponse response = classifyError(e.getBindingResult());
        return ResponseEntity.status(response.getStatus()).body(
                ExceptionResponse.builder().status(response.getStatus()).message(response.getMessage())
                        .build());
    }

    private ExceptionResponse classifyError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ExceptionResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(bindingResult.getFieldError().getDefaultMessage())
                    .build();
        }
        return ExceptionResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("서버에러 입니다")
                .build();
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
