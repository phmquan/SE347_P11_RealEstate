package vn.uit.realestate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "broker_certification")
public class BrokerCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;
    @NotBlank(message = "Tên trên chứng chỉ không được để trống")
    private String nameOnCertification;
    @NotBlank(message = "Số chứng chỉ không được để trống")
    private String certificationNumber;
    @NotBlank(message = "Cơ quan cấp chứng chỉ không được để trống")
    private String certificationAuthority;
    @OneToOne(mappedBy = "brokerCertification")
    private Agency agency;
}
