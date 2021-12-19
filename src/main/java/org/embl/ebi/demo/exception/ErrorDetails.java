package org.embl.ebi.demo.exception;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;
  private String code;

  public ErrorDetails(Date timestamp, String message, String details, String code) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
    this.code = code;
  }

  public ErrorDetails(Date timestamp, String message, String details) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }
}
