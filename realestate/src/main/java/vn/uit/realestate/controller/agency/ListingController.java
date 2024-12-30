package vn.uit.realestate.controller.agency;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.Apartment;
import vn.uit.realestate.domain.House;
import vn.uit.realestate.domain.Land;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.Property;
import vn.uit.realestate.service.AgencyService;
import vn.uit.realestate.service.ListingService;
import vn.uit.realestate.service.PropertyService;
import vn.uit.realestate.service.UploadService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agency/listings")
public class ListingController {

  private final ListingService listingService;
  private final AgencyService agencyService;
  private final PropertyService propertyService;
  private final UploadService uploadService;

  @GetMapping("")
  // The endpoint should be something like
  // /admin/listings?page=0&size=30&sort=updatedAt,desc&sort=property.propertyPrice,asc&sort=property.district,asc
  // page is beginning from 0
  public String listListings(
      @PageableDefault(
              page = 0,
              size = 30,
              sort = {"updatedAt,desc", "property.propertyPrice,asc", "property.district,asc"})
          Pageable pageable,
      Model model) {
    // the page had format like this
    // data class PageResponse<T>(
    //     val content: List<T>,
    //     val totalElements: Int,
    //     val totalPages: Int,
    //     val last: Boolean,
    //     val size: Int,
    //     val number: Int,
    //     val sort: {
    //        val sorted: Boolean = false,
    //        val unsorted: Boolean = true,
    //        val empty: Boolean = true
    //     },
    //
    //     val first: Boolean,
    //     val numberOfElements: Int,
    //     val empty: Boolean,
    //     val pageable: {
    //        val pageNumber: Int = 0,
    //        val pageSize: Int,
    //        val offset: Int,
    //        val paged: Boolean,
    //        val unpaged: Boolean,
    //     }
    // )
    model.addAttribute("listings", listingService.getAllListings(pageable));
    return "listings/list";
  }

  @GetMapping("/no-page")
  public String listListingsNoPage(Model model) {
    model.addAttribute("listings", listingService.getAllListings());
    return "listings/list";
  }

  @GetMapping("/{id}")
  public String viewListing(@PathVariable("id") Long id, Model model) {
    listingService
        .getListingById(id)
        .ifPresentOrElse(
            listing -> model.addAttribute("listing", listing),
            () -> {
              throw new IllegalArgumentException("Listing not found");
            });
    return "listings/view";
  }

  /**
   * @param type house, land, apartment
   * @param model model
   * @return add listing form
   */
  @GetMapping("/add")
  public String addListingForm(@RequestParam("type") String type, Model model) {
    Property property;
    switch (type.toLowerCase()) {
      case "house":
        property = new House();
        break;
      case "land":
        property = new Land();
        break;
      case "apartment":
        property = new Apartment();
        break;
      default:
        throw new IllegalArgumentException("Invalid property type: " + type);
    }
    property.setListing(new Listing());
    model.addAttribute("listing", property);
    model.addAttribute("type", type);
    return "listings/add";
  }

  @PostMapping("/add")
  @Transactional
  public String addListing(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("type") String type,
      @ModelAttribute("listing") Property property,
      @RequestParam("images") List<MultipartFile> images) {
    Agency agency = agencyService.getAgencyByEmail(userDetails.getUsername());
    if (agency == null) {
      throw new UsernameNotFoundException("Agency not found");
    }
    property.getListing().setAgency(agency);

    if (images != null && !images.isEmpty()) {
      property.setPropertyImages(
          images.stream()
              .map(file -> uploadService.handleSaveUploadFile(file, "property_images"))
              .toList());
    }

    propertyService.addProperty(property);
    return "redirect:/admin/listings";
  }

  @GetMapping("/edit/{id}")
  public String editListingForm(@PathVariable("id") Long id, Model model) {
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
  @Transactional
  public String updateListing(
      @RequestParam("new_images") List<MultipartFile> newImages,
      @PathVariable("id") Long id,
      @ModelAttribute("listing") Listing property) {
    if (newImages != null && !newImages.isEmpty()) {
      property
          .getProperty()
          .getPropertyImages()
          .addAll(
              newImages.stream()
                  .map(file -> uploadService.handleSaveUploadFile(file, "property_images"))
                  .toList());
    }
    listingService.updateListing(id, property);
    return "redirect:/admin/listings";
  }

  @PostMapping("/delete/{id}")
  public String deleteListing(@PathVariable("id") Long id) {
    listingService.deleteListing(id);
    return "redirect:/admin/listings";
  }
}
