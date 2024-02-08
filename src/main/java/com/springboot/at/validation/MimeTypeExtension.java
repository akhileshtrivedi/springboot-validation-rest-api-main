package com.springboot.at.validation;


import java.util.stream.Stream;

public enum MimeTypeExtension {
  JPEG("image/jpeg" ),
  PNG("image/png"),
  JPG("image/jpg");

  private final String value;

  MimeTypeExtension(String value) {
    this.value = value;
  }

  public static MimeTypeExtension fromValue(String stringValue) {
    return Stream.of(values())
        .filter(keyType -> keyType.value.equalsIgnoreCase(stringValue))
        .findAny().orElse(null);
  }

  public String getValue() {
    return value;
  }
}