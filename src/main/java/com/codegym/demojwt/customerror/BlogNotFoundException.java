package com.codegym.demojwt.customerror;

public class BlogNotFoundException extends RuntimeException {

  public BlogNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
