package org.embl.ebi.demo.exception;

import lombok.Getter;
import org.embl.ebi.demo.exception.code.ExceptionCode;

public class ServiceRuntimeException extends RuntimeException implements ServiceThrowable {

  private static final long serialVersionUID = 1L;

  @Getter private ExceptionCode code;

  protected ServiceRuntimeException(ExceptionCode code, String message, Throwable exception) {
    super(message, exception);
    this.code = code;
  }
}
