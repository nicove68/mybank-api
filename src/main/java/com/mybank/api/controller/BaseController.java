package com.mybank.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mybank.api.exception.ApiError;
import com.mybank.api.exception.RestException;

public abstract class BaseController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(HttpServletRequest req, Exception ex) {
    logger.error("Error executing " + req.getRequestURI(), ex);

    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ApiError error = new ApiError(httpStatus.value(), ex.getMessage());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler(ServletRequestBindingException.class)
  public ResponseEntity<ApiError> handleBindingException(HttpServletRequest req, ServletRequestBindingException ex) {
    logger.error("Error executing " + req.getRequestURI(), ex);

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ApiError error = new ApiError(httpStatus.value(), ex.getMessage());

    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler(RestException.class)
  public ResponseEntity<ApiError> handleRestException(HttpServletRequest req, RestException ex) {
    logger.error("Error executing " + req.getRequestURI(), ex);

    ApiError response = ex.getApiError();
    HttpStatus httpStatus = HttpStatus.valueOf(response.getStatusCode());

    return new ResponseEntity<>(ex.getApiError(), httpStatus);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleInvalidFormatException(HttpServletRequest req, RestException ex) {
    logger.error("Error executing " + req.getRequestURI(), ex);

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    return new ResponseEntity<>(ex.getApiError(), httpStatus);
  }
}
