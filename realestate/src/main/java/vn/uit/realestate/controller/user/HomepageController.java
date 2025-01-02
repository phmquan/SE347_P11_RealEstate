package vn.uit.realestate.controller.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.ListingStatus;
import vn.uit.realestate.domain.User;
import vn.uit.realestate.service.AgencyService;
import vn.uit.realestate.service.BrokerCertificationService;
import vn.uit.realestate.service.ListingService;
import vn.uit.realestate.service.UploadService;
import vn.uit.realestate.service.UserService;

@Controller

public class HomepageController {

    private final ListingService listingService;
    private final UserService userService;
    private final UploadService uploadService;
    private final AgencyService agencyService;
    private final BrokerCertificationService brokerCertificationService;

    public HomepageController(ListingService listingService, UserService userService, UploadService uploadService,
            AgencyService agencyService, BrokerCertificationService brokerCertificationService) {
        this.listingService = listingService;
        this.userService = userService;
        this.uploadService = uploadService;
        this.agencyService = agencyService;
        this.brokerCertificationService = brokerCertificationService;
    }

    @GetMapping("/")
    public String getHomepage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            String role = session.getAttribute("role").toString();
            return switch (role) {
                case "USER" ->
                    "redirect:/homepage";
                case "AGENCY" ->
                    "redirect:/agency/listing?status=pending";
                case "ADMIN" ->
                    "redirect:/admin";
                default ->
                    "/";
            };

        }
    }

    @GetMapping("/homepage")
    public String getHomepage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Listing> displayingListings = listingService.getAllListingsByStatus(ListingStatus.DISPLAYING, pageable);

        model.addAttribute("listings", displayingListings.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", displayingListings.getTotalPages());
        model.addAttribute("totalItems", displayingListings.getTotalElements());

        return "user/homepage/show";
    }

    @GetMapping("/user/agency/register")
    public String getAgencyRegisterPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            String role = session.getAttribute("role").toString();
            if (role.equals("AGENCY")) {
                return "redirect:/agency/listing?status=null";
            }
        }

        model.addAttribute("newAgency", new Agency());
        return "user/agencyRegister/show";
    }

    @PostMapping("/user/agency/register")
    public String registerAgency(@ModelAttribute("newAgency") @Valid Agency agency, BindingResult bindingResult, Model model, @RequestParam("propertyImage") MultipartFile file, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "user/agencyRegister/show";
        }
        HttpSession session = request.getSession(false);
        User currentUser = userService.getUserByEmail(session.getAttribute("email").toString());
        agency.setUser(currentUser);
        brokerCertificationService.handleSaveBrokerCertification(agency.getBrokerCertification());
        String listAvatarFileName = this.uploadService.handleSaveUploadSingleFile(file, "avatar");
        agency.getUser().setAvatar(listAvatarFileName);
        agency.getUser().setRole(userService.getRoleByName("AGENCY"));
        agencyService.saveAgency(agency);
        return "redirect:/login";
    }
}
