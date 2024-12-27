package vn.uit.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.realestate.domain.BrokerCertification;

@Repository
public interface BrokerCertificationRepository extends JpaRepository<BrokerCertification, Long> {}
