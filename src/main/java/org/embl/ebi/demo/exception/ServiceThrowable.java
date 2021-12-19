package org.embl.ebi.demo.exception;

import org.embl.ebi.demo.exception.code.ExceptionCode;

public interface ServiceThrowable {
  ExceptionCode getCode();
}
