package vn.uit.realestate.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.uit.realestate.domain.ListingStatus;
import vn.uit.realestate.service.ListingService;

@Controller

public class AdminController {

    private final ListingService listingService;

    public AdminController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        long countPendingListing = listingService.countByStatus(ListingStatus.PENDING);
        model.addAttribute("countPendingListing", countPendingListing);
        return "admin/dashboard/show";
    }
}
