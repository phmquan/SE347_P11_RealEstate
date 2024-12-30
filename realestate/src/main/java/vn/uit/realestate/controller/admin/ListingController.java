package vn.uit.realestate.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.Apartment;
import vn.uit.realestate.domain.House;
import vn.uit.realestate.domain.Land;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.Property;
import vn.uit.realestate.service.AgencyService;
import vn.uit.realestate.service.ListingService;
import vn.uit.realestate.service.PropertyService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/listings")
public class ListingController {

  private final ListingService listingService;
  private final AgencyService agencyService;
  private final PropertyService propertyService;

  @GetMapping("/add")
  public String chooseListingType(Model model) {
    return "listings/chooseType";
  }

  /**
   * @param type house, land, apartment
   * @param model model
   * @return add listing form
   */
  @GetMapping("/add")
  public String addListingForm(@RequestParam String type, Model model) {
    switch (type.toLowerCase()) {
      case "house":
        model.addAttribute("listing", new House());
        break;
      case "land":
        model.addAttribute("listing", new Land());
        break;
      case "apartment":
        model.addAttribute("listing", new Apartment());
        break;
      default:
        throw new IllegalArgumentException("Invalid property type: " + type);
    }
    model.addAttribute("type", type);
    return "listings/add";
  }

  @PostMapping("/add")
  public String addListing(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam String type,
      @ModelAttribute Property property) {
    Listing listing = new Listing();
    Agency agency = agencyService.getAgencyByEmail(userDetails.getUsername());
    if (agency == null) {
      throw new UsernameNotFoundException("Agency not found");
    }
    listing.setAgency(agency);
    listing.setProperty(propertyService.addProperty(property));
    listingService.addListing(listing);
    return "redirect:/admin/listings";
  }

  @GetMapping("/edit/{id}")
  public String editListingForm(@PathVariable Long id, Model model) {
    listingService
        .getListingById(id)
        .ifPresentOrElse(
            listing -> model.addAttribute("listing", listing),
            () -> {
              throw new IllegalArgumentException("Listing not found");
            });
    return "listings/edit";
  }

  @PostMapping("/edit/{id}")
  public String updateListing(@PathVariable Long id, @ModelAttribute Listing property) {
    listingService.updateListing(id, property);
    return "redirect:/admin/listings";
  }

  @PostMapping("/delete/{id}")
  public String deleteListing(@PathVariable Long id) {
    listingService.deleteListing(id);
    return "redirect:/admin/listings";
  }
}
