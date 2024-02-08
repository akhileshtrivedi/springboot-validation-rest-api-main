package com.springboot.at.controller;


import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.springboot.at.dao.request.AccountRequest;
import com.springboot.at.dao.response.RestApiError;
import com.springboot.at.dao.response.RestApiResponse;
import com.springboot.at.entity.Account;
import com.springboot.at.exception.RecordNotFoundException;
import com.springboot.at.service.AccountService;
import com.springboot.at.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
//@SecurityRequirement(name = "Bearer Authentication")
public class AccountController {

  private final AccountService accountService;
  private final FileStorageService fileStorageService;
  @Value("${file.storage.dir}")
  private String fileStorageLocation;

  @Operation(summary = "Create new account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Save account successful", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Account.class))}),
      @ApiResponse(responseCode = "400", description = "Required parameters not supplied in the request", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized. Invalid or missing token", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))})
  })
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public RestApiResponse<Account> create(@Valid @ModelAttribute AccountRequest accountRequest) {
    Account savedAccount = accountService.create(accountRequest);
    //return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);

    return new RestApiResponse<>(HttpStatus.CREATED.value(),
        savedAccount);
  }

  @Operation(summary = "Update account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Account.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized. Invalid or missing token", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))}),
      @ApiResponse(responseCode = "404", description = "Account not found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))}),
      @ApiResponse(responseCode = "409", description = "Duplicate account name found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))})
  })
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "{id}", produces = APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public RestApiResponse<Account> update(@PathVariable("id") Integer id,
      @Valid @ModelAttribute AccountRequest request) {
    Account account = accountService.findById(id);
    if (account == null) {
      throw new RecordNotFoundException("Account not found");
    }
    if (request.getLogo() != null) {
      String logoLocation = String.format("accounts/%s/logos", id);
      String logoUrl = fileStorageService.saveFile(logoLocation, request.getLogo());
      if (StringUtils.isNotEmpty(logoUrl)) {
        request.setLogoUrl(logoUrl);
      }
    }
    Account updatedAccount = accountService.update(id, request);

    return new RestApiResponse<>(HttpStatus.OK.value(), updatedAccount);
  }
/*
  @Operation(summary = "Create Account", description = "Create Account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "OK", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Account.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized. Invalid or missing token", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))}),
      @ApiResponse(responseCode = "404", description = "Account not found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))}),
      @ApiResponse(responseCode = "409", description = "Duplicate account name found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestApiError.class))})
  })
  @PostMapping(value = "{id}", produces = APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public RestApiResponse<Account> CreateAccount(@Valid @ModelAttribute AccountRequest request) {
    System.out.println("I am here");

    return new RestApiResponse<>(HttpStatus.OK.value(), new Account() );
  }
*/
}