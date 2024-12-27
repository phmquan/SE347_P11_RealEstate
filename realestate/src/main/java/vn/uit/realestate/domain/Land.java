package vn.uit.realestate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Land extends Property {

    @NotBlank(message = "Loại đất không được để trống")
    private String landType; // Đất nền, đất thổ cư, đất nông nghiệp, đất công nghiệp
    private String landDirection; // Hướng đất

}
