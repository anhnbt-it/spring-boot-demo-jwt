package com.codegym.demojwt.customerror;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BlogNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(BlogNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String employeeNotFoundHandler(BlogNotFoundException ex) {
    return ex.getMessage();
  }
}
