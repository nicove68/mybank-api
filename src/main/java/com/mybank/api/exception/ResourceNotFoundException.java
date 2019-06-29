package com.mybank.api.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RestException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException() {
    super(new ApiError(HttpStatus.NOT_FOUND.value(), "The resource you are trying to access does not exist."));
  }
}
