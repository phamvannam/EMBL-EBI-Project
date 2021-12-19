package org.embl.ebi.demo.exception;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.embl.ebi.demo.exception.code.ExceptionCode;

public class ServiceRuntimeExceptionBuilder {
  private ExceptionCode code;
  private Throwable origin;

  private void setCode(ExceptionCode code) {
    this.code = code;
  }

  private ServiceRuntimeExceptionBuilder() {}

  private List<Object> properties = new ArrayList<>();

  public final ServiceRuntimeExceptionBuilder add(Object value) {
    properties.add(value);
    return this;
  }

  public static ServiceRuntimeExceptionBuilder newBuilder(ExceptionCode code) {
    ServiceRuntimeExceptionBuilder serviceExceptionBuilder = new ServiceRuntimeExceptionBuilder();
    serviceExceptionBuilder.setCode(code);
    return serviceExceptionBuilder;
  }

  public final ServiceRuntimeException build() {
    return new ServiceRuntimeException(
        code, MessageFormat.format(code.getMessageTemplate(), properties.toArray()), origin);
  }
}
