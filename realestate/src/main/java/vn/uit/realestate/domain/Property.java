package vn.uit.realestate.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "property")
    private Listing listing;
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(min = 5, max = 100, message = "Địa chỉ phải có ít nhất 5 kí tự")
    private String Address;
    private String propertyLength;
    private String propertyWidth;
    @NotBlank(message = "Diện tích không được để trống")
    private Double propertyArea;
    private String legalDocuments; // Đã có sổ đỏ, chưa có sổ đỏ
    @NotBlank(message = "Giá không được để trống")
    private String propertyPrice;

    private List<String> propertyImages;
}
