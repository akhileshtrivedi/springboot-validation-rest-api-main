package com.springboot.at.dao.request;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.at.validation.AtLeastOneNotEmpty;
import com.springboot.at.validation.ValidFile;
import com.springboot.at.validation.ValidFileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneNotEmpty(fields = {"name", "website", "productUrl", "logo", "logoUrl", "size",
    "annualRevenue", "annualAdSpend"})
public class AccountRequest {

  @NotBlank(message = "The name is required.")
  private String name;
  @URL(message = "website is invalid", flags = {Flag.CASE_INSENSITIVE})
  private String website;
  @URL(message = "productUrl is invalid", flags = {Flag.CASE_INSENSITIVE})
  private String productUrl;
  @ValidFileSize(message = "Max image file size exceed.", maxSizeInMB = 1)
  @ValidFile(message = "Invalid image file type, system supports only jpeg/png/jpg", allowedExtensions = {
      "jpeg", "png", "jpg"})
  private MultipartFile logo;
  @JsonIgnore
  private String logoUrl;
  private String size;
  private String annualRevenue;
  private String annualAdSpend;
  private boolean active;
}
