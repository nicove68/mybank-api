package com.mybank.api.exception;


public class RestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private ApiError apiError;

  public RestException(String message, Throwable cause) {
    super(message, cause);
  }

  public RestException(ApiError apiError) {
    super(apiError.getStatusCode() + ":" + apiError.getMessage());
    this.apiError = apiError;
  }

  public RestException(ApiError apiError, Throwable cause) {
    super(cause);
    this.apiError = apiError;
  }

  public ApiError getApiError() {
    return apiError;
  }
}
