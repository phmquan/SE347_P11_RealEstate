package vn.uit.realestate.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.uit.realestate.service.ListingService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/listings")
public class UserListingController {

  private final ListingService listingService;

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
    //
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

  @GetMapping("/search")
  public String searchListings(@RequestParam("keyword") String keyword, Model model) {
    model.addAttribute("listings", listingService.searchListing(keyword));
    return "listings/list";
  }
}
