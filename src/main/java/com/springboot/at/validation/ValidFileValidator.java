package com.springboot.at.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ValidFileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

  private List<String> allowedExtensions;

  @Override
  public void initialize(ValidFile constraintAnnotation) {
    this.allowedExtensions = List.of(constraintAnnotation.allowedExtensions());
  }

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    if (file == null || file.isEmpty()) {
      return true;
    }

    String contentType = file.getContentType();
    MimeTypeExtension mimeTypeExtension = MimeTypeExtension.fromValue(contentType);
    if (mimeTypeExtension == null) {
      return false;
    }
    return contentType != null && allowedExtensions.contains(
        mimeTypeExtension.name().toLowerCase());
  }

}

