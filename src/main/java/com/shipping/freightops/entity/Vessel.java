package com.shipping.freightops.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/** A cargo vessel that carries containers between ports. */
@Entity
@Table(name = "vessels")
public class Vessel extends BaseEntity {

  @NotBlank
  @Column(nullable = false)
  private String name;

  /** IMO number – unique 7-digit vessel identifier. */
  @NotBlank
  @Column(unique = true, nullable = false, length = 7)
  private String imoNumber;

  /** Maximum TEU (Twenty-foot Equivalent Unit) capacity. */
  @Positive
  @Column(nullable = false)
  private int capacityTeu;

  public Vessel() {}

  public Vessel(String name, String imoNumber, int capacityTeu) {
    this.name = name;
    this.imoNumber = imoNumber;
    this.capacityTeu = capacityTeu;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImoNumber() {
    return imoNumber;
  }

  public void setImoNumber(String imoNumber) {
    this.imoNumber = imoNumber;
  }

  public int getCapacityTeu() {
    return capacityTeu;
  }

  public void setCapacityTeu(int capacityTeu) {
    this.capacityTeu = capacityTeu;
  }
}
