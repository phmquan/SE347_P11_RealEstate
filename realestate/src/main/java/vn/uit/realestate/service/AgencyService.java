package vn.uit.realestate.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.Role;
import vn.uit.realestate.repository.AgencyRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgencyService {

  private final AgencyRepository agencyRepository;
  private final UserService userService;
  private final BrokerCertificationService brokerCertificationService;

  public List<Agency> getAllAgency() {
    return agencyRepository.findAll();
  }

  public Agency getAgencyById(Long id) {
    return agencyRepository.findById(id).orElse(null);
  }

  public Agency getAgencyByEmail(String username) {
    return agencyRepository.findByUserEmail(username).orElse(null);
  }

  public void deleteById(Long id) {
    agencyRepository.deleteById(id);
  }

  public void handleSaveAgency(Agency agency) {
    // Ensure role exists and assign it to the user
    Long roleId = agency.getUser().getRole().getId();
    Role role = userService.getRoleById(roleId);

    if (role == null) {
      log.error("Role with ID {} not found.", roleId);
      throw new IllegalArgumentException("Invalid Role ID.");
    }
    agency.getUser().setRole(role);

    // Save User
    userService.handleSaveUser(agency.getUser());

    // Save Broker Certification
    brokerCertificationService.handleSaveBrokerCertification(agency.getBrokerCertification());

    // Save Agency
    agencyRepository.save(agency);

    log.info("Agency saved successfully: {}", agency);
  }

  public void handleUpdateAgency(Agency agency) {
    Optional<Agency> currentAgencyOpt = this.agencyRepository.findById(agency.getId());
    if (currentAgencyOpt.isEmpty()) {
      log.warn("Agency with ID {} not found.", agency.getId());
      return;
    }

    Agency currentAgency = currentAgencyOpt.get();

    // Update User details
    if (agency.getUser() != null) {
      currentAgency.getUser().setFullName(agency.getUser().getFullName());
      currentAgency.getUser().setAddress(agency.getUser().getAddress());
      currentAgency.getUser().setPhone(agency.getUser().getPhone());
      currentAgency.getUser().setEmail(agency.getUser().getEmail());
      this.userService.handleSaveUser(currentAgency.getUser());
    }

    // Update Broker Certification
    if (agency.getBrokerCertification() != null) {
      this.brokerCertificationService.handleSaveBrokerCertification(
          agency.getBrokerCertification());
      currentAgency.setBrokerCertification(agency.getBrokerCertification());
    }

    // Save updated Agency
    agencyRepository.save(currentAgency);

    log.info("Agency updated successfully: {}", currentAgency);
  }
}
