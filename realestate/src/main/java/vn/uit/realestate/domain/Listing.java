package vn.uit.realestate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "listing")
public class Listing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "agency_id", nullable = false)
  private Agency agency;

  private String listingType; // sale, rent

  private String listingStatus; // active, inactive, pending

  @NotBlank(message = "Tiêu đề không được để trống")
  @Size(min = 5, max = 50, message = "Tiêu đề phải có ít nhất 5 kí tự")
  private String listingTitle;

  @NotBlank(message = "Mô tả không được để trống")
  @Size(min = 20, max = 1500, message = "Mô tả phải có ít nhất 20 kí tự")
  @Column(columnDefinition = "MEDIUMTEXT")
  private String listingDescription;

  private String propertyType; // House, Apartment, Land

  @OneToOne
  @JoinColumn(name = "property_id", nullable = false)
  private Property property;
}
