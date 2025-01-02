package vn.uit.realestate.controller.admin;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.ListingStatus;
import vn.uit.realestate.service.AgencyService;
import vn.uit.realestate.service.BrokerCertificationService;
import vn.uit.realestate.service.UploadService;
import vn.uit.realestate.service.UserService;

@Controller
@RequiredArgsConstructor
public class AgencyController {

    private final UserService userService;
    private final AgencyService agencyService;
    private final PasswordEncoder passwordEncoder;
    private final UploadService uploadService;
    private final BrokerCertificationService brokerCertificationService;

    @GetMapping("/admin/agency")
    public String getAgencyPage(Model model) {
        List<Agency> agencies = this.agencyService.getAllAgency();
        model.addAttribute("agencies", agencies);
        return "admin/agency/show";
    }

    @GetMapping("/admin/agency/create")
    public String createAgencyPage(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "admin/agency/create";
    }

    @GetMapping("/admin/agency/{id}")
    public String getAgencyDetail(@PathVariable("id") Long id, Model model) {
        Agency agency = this.agencyService.getAgencyById(id);
        model.addAttribute("agency", agency);
        return "admin/agency/detail";
    }

    @PostMapping("/admin/agency/create")
    public String createAgency(
            @ModelAttribute("newAgency") @Valid Agency agency,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            return "admin/agency/create";
        }
        String hashedPassword = this.passwordEncoder.encode(agency.getUser().getPassword());
        agency.getUser().setPassword(hashedPassword);
        String avatarFileName = this.uploadService.handleSaveUploadSingleFile(file, "avatar");
        agency.getUser().setAvatar(avatarFileName);
        agency.setActivationStatus("DEACTIVATED");
        this.agencyService.handleSaveAgency(agency);
        return "redirect:/admin/agency";
    }

    @PostMapping("/admin/agency/accept/{id}")
    public String acceptAgency(@RequestParam("status") boolean status, @PathVariable("id") Long id) {
        var agency
                = agencyService
                        .getAgencyById(id);

        if (status) {
            agency.setActivationStatus("ACTIVATED");
        } else {
            agency.setActivationStatus("DEACTIVATED");
        }
        agencyService.saveAgencyWithoutReturn(agency);
        return "redirect:/admin/agency";

    }
}
