package com.springboot.at.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Duplicate Record Exception.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateRecordException extends RuntimeException {

  /**
   * Default Constructor.
   */
  public DuplicateRecordException() {
    super("Record already exists");
  }

  /**
   * Constructor.
   *
   * @param message Exception message
   */
  public DuplicateRecordException(String message) {
    super(message);
  }
}