package vn.uit.realestate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.Role;
import vn.uit.realestate.repository.AgencyRepository;

@Service
public class AgencyService {

    private final AgencyRepository agencyRepository;
    private final UserService userService;
    private final BrokerCertificationService brokerCertificationService;

    public AgencyService(BrokerCertificationService brokerCertificationService, UserService userService, AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
        this.userService = userService;
        this.brokerCertificationService = brokerCertificationService;

    }

    public List<Agency> getAllAgency() {
        return this.agencyRepository.findAll();
    }

    public Agency getAgencyById(Long id) {
        return this.agencyRepository.findById(id).orElse(null);
    }

    public void handleSaveAgency(Agency agency) {
        Long roleId = agency.getUser().getRole().getId();
        Role role = this.userService.getRoleById(roleId);
        agency.getUser().setRole(role);
        this.userService.handleSaveUser(agency.getUser());

        agency.setUser(agency.getUser());

        this.brokerCertificationService.handleSaveBrokerCertification(agency.getBrokerCertification());

        agency.setBrokerCertification(agency.getBrokerCertification());

        this.agencyRepository.save(agency);

    }

    public void handleUpdateAgency(Agency agency) {
        Agency currentAgency = this.agencyRepository.findById(agency.getId()).orElse(null);
        if (currentAgency == null) {
            return;
        }
        currentAgency.getUser().setFullName(agency.getUser().getFullName());
        currentAgency.getUser().setAddress(agency.getUser().getAddress());
        currentAgency.getUser().setPhone(agency.getUser().getPhone());
        currentAgency.getUser().setEmail(agency.getUser().getEmail());

        currentAgency.setBrokerCertification(agency.getBrokerCertification());
        this.agencyRepository.save(currentAgency);
    }
}
