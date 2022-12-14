package asapD.server.controller.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ApiExceptionEnum {
  // General Exception
  BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 변수를 확인해주세요."),
  UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "인증이 실패하였습니다."),
  ACCESS_DENIED_EXCEPTION(HttpStatus.FORBIDDEN, "제한된 접근입니다."),
  NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "요청한 자원이 없습니다."),
  DUPLICATION_VALUE_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 값입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버가 응답하지 않습니다."),

  // Member
  MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "등록된 유저가 아닙니다."),

  // Orders
  ORDER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "요청한 자원이 없습니다."),
  SERIALNUM_INVALID_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 변수를 확인해주세요."),

  // redis
  TIMEOUT_EXCEPTION(HttpStatus.FORBIDDEN, "유효시간이 만료되었습니다."),

  // SMS
  SMS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "api 내부 통신에러입니다"),

  ;

  private final HttpStatus httpStatus;
  private final Integer status;
  private final String message;

  ApiExceptionEnum(HttpStatus httpStatus, String message) {
    this.httpStatus = httpStatus;
    this.status = httpStatus.value();
    this.message = message;
  }
}
