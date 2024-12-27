package vn.uit.realestate.service;

import org.springframework.stereotype.Service;
import vn.uit.realestate.domain.BrokerCertification;
import vn.uit.realestate.repository.BrokerCertificationRepository;

@Service
public class BrokerCertificationService {

  private final BrokerCertificationRepository brokerCertificationRepository;

  public BrokerCertificationService(BrokerCertificationRepository brokerCertificationRepository) {
    this.brokerCertificationRepository = brokerCertificationRepository;
  }

  public void handleSaveBrokerCertification(BrokerCertification brokerCertification) {
    this.brokerCertificationRepository.save(brokerCertification);
  }
}
