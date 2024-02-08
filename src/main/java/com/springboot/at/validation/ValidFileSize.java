package com.springboot.at.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD
    ,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFileSizeValidator.class)
@Documented
public @interface ValidFileSize {

  Class<? extends Payload> [] payload() default{};
  Class<?>[] groups() default {};
  long maxSizeInMB() default 512;

  String message() default "Max file size exceed.";
}
