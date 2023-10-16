package com.example.bankapplication.exception;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ErrorInfo {
  private Timestamp errorTime;
  private int statusCode;
  private String message;
  private String description;

  public ErrorInfo(int statusCode, String message, String description) {
    this.errorTime = new Timestamp(System.currentTimeMillis());

    this.statusCode = statusCode;
    this.message = message;
    this.description = description;
  }
}