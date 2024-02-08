package com.springboot.at.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ValidFileSizeValidator
    implements ConstraintValidator<ValidFileSize, MultipartFile> {

  private static final Integer MB = 1024 * 512;

  private long maxSizeInMB;

  @Override
  public void initialize(ValidFileSize validFileSize) {
    this.maxSizeInMB = validFileSize.maxSizeInMB();
  }

  @Override
  public boolean isValid(MultipartFile multipartFile,
      ConstraintValidatorContext
          constraintValidatorContext) {

    if (multipartFile == null) {
      return true;
    }

    return multipartFile.getSize() < maxSizeInMB * MB;
  }
}