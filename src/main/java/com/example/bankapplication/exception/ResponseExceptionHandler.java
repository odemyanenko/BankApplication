package com.example.bankapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ResponseExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorInfo> resourceNotFoundException(RuntimeException ex, WebRequest request) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    ErrorInfo message = getErrorMessage(httpStatus, ex, request);

    return new ResponseEntity<>(message, httpStatus);
  }

  @ExceptionHandler(ResourceListEmptyException.class)
  public ResponseEntity<ErrorInfo> resourceListEmptyException(RuntimeException ex, WebRequest request) {
    HttpStatus httpStatus = HttpStatus.NO_CONTENT;
    ErrorInfo message = getErrorMessage(httpStatus, ex, request);

    return new ResponseEntity<>(message, httpStatus);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorInfo> methodArgumentTypeMismatchException(RuntimeException ex, WebRequest request) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorInfo message = getErrorMessage(httpStatus, ex, request);

    return new ResponseEntity<>(message, httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorInfo> globalExceptionHandler(Exception ex, WebRequest request) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorInfo message = getErrorMessage(httpStatus, ex, request);

    return new ResponseEntity<>(message, httpStatus);
  }

  private ErrorInfo getErrorMessage(HttpStatus status, Exception ex, WebRequest request) {
    return new ErrorInfo(
            status.value(),
            ex.getMessage(),
            request.getDescription(false));
  }

}