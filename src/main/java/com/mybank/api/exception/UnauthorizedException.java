package com.mybank.api.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RestException {
  private static final long serialVersionUID = 1L;

  public UnauthorizedException() {
    super(new ApiError(HttpStatus.UNAUTHORIZED.value(), "Authentication required."));
  }
}
