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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

  @NotNull private ListingStatus status = ListingStatus.PENDING;

  @OneToOne
  @JoinColumn(name = "property_id", nullable = false)
  private Property property;

  @CreatedDate @Null
  // This field is automatically set by Spring Data when a new entity is created
  // When user need to retrieve the created date of an entity, they can use this field and change to
  // local date time
  private Instant createdDate;

  @LastModifiedDate @Null
  // This field is automatically set by Spring Data when an entity is updated
  // When user need to retrieve the updated date of an entity, they can use this field and change
  // to
  private Instant updatedDate;
}
