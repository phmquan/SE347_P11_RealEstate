package vn.uit.realestate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agency")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Khu vực môi giới không được để trống")
    private String brokerageArea;
    @NotBlank(message = "Loại hình môi giới không được để trống")
    private String brokerageType;
    @Valid
    @OneToOne
    @JoinColumn(name = "broker_certification_id")
    private BrokerCertification brokerCertification;
    @Valid
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String activationStatus;
}
