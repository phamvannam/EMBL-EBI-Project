package org.embl.ebi.demo.exception;

import java.text.MessageFormat;
import lombok.Getter;
import lombok.Setter;
import org.embl.ebi.demo.exception.code.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  @Getter @Setter protected String errorCode;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }

  public ResourceNotFoundException(String msg, Throwable e) {
    super(msg, e);
  }

  public ResourceNotFoundException(String msg, ExceptionCode exceptionCode, Throwable e) {
    super(msg, e);
    setErrorCode(exceptionCode.getCode());
  }

  public ResourceNotFoundException(ExceptionCode exceptionCode, Object... msgParams) {
    super(
        (msgParams == null || msgParams.length == 0)
            ? exceptionCode.getMessageTemplate()
            : MessageFormat.format(exceptionCode.getMessageTemplate(), msgParams));
    setErrorCode(exceptionCode.getCode());
  }
}
