package vn.uit.realestate.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.uit.realestate.service.ListingService;

@Controller
@RequestMapping("/user/listings")
@RequiredArgsConstructor
public class ListingController {

  private final ListingService listingService;

  @GetMapping("")
  public String listListings(Model model) {
    model.addAttribute("listings", listingService.getAllListings());
    return "listings/list";
  }

  @GetMapping("/{id}")
  public String viewListing(@PathVariable Long id, Model model) {
    listingService
        .getListingById(id)
        .ifPresentOrElse(
            listing -> model.addAttribute("listing", listing),
            () -> {
              throw new IllegalArgumentException("Listing not found");
            });
    return "listings/view";
  }
}
