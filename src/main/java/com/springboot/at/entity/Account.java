package com.springboot.at.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "account")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false)
  private String name;
  private String website;
  @Column(name = "product_url")
  private String productUrl;
  @Column(name = "logo_url")
  private String logoUrl;
  private String size;
  @Column(name = "annual_revenue")
  private String annualRevenue;
  @Column(name = "annual_ad_spend")
  private String annualAdSpend;
  @Column(nullable = false)
  private boolean active;
}
