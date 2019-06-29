package com.mybank.api.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestException {
  private static final long serialVersionUID = 1L;

  public BadRequestException(String body) {
    super(new ApiError(HttpStatus.BAD_REQUEST.value(), body));
  }

}