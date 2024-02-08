package com.springboot.at.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

public class AtLeastOneNotEmptyValidator
    implements ConstraintValidator<AtLeastOneNotEmpty, Object> {

  private String[] fields;

  public void initialize(AtLeastOneNotEmpty constraintAnnotation) {
    this.fields = constraintAnnotation.fields();
  }

  public boolean isValid(Object value, ConstraintValidatorContext context) {

    List<String> fieldValues = new ArrayList<String>();

    for (String field : fields) {
      Object propertyValue = new BeanWrapperImpl(value).getPropertyValue(field);
      if (ObjectUtils.isEmpty(propertyValue)) {
        fieldValues.add(null);
      } else {
        fieldValues.add(propertyValue.toString());
      }
    }
    return fieldValues.stream().anyMatch(fieldValue -> fieldValue!= null);
  }
}
