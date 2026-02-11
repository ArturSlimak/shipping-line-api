package com.shipping.freightops.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** A port of call (e.g. AEJEA - Jebel Ali, Dubai). */
@Entity
@Table(name = "ports")
public class Port extends BaseEntity {

  @NotBlank
  @Size(min = 5, max = 5)
  @Column(unique = true, nullable = false, length = 5)
  private String unlocode;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Column(nullable = false)
  private String country;

  public Port() {}

  public Port(String unlocode, String name, String country) {
    this.unlocode = unlocode;
    this.name = name;
    this.country = country;
  }

  public String getUnlocode() {
    return unlocode;
  }

  public void setUnlocode(String unlocode) {
    this.unlocode = unlocode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
