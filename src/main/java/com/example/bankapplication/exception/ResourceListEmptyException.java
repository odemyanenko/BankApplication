package com.example.bankapplication.exception;

public class ResourceListEmptyException extends RuntimeException{
  public ResourceListEmptyException(String message) {
    super(message);
  }
}
