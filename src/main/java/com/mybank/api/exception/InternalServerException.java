package com.mybank.api.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends RestException {
  private static final long serialVersionUID = 1L;

  public InternalServerException(String body) {
    super(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), body));
  }

}
