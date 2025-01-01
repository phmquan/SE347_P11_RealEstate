package vn.uit.realestate.controller.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.ListingStatus;
import vn.uit.realestate.service.ListingService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/listings")
public class AdminListingController {

    private final ListingService listingService;

    // The endpoint should be something like
    // /admin/listings?page=0&size=30&sort=updatedAt,desc&sort=property.propertyPrice,asc&sort=property.district,asc
    // page is beginning from 0
    @GetMapping("")
    public String listListingsFollowStatus(
            @RequestParam(name = "status", defaultValue = "PENDING") ListingStatus status,
            @PageableDefault(
                    page = 0,
                    size = 30,
                    sort = {"property.propertyPrice,asc", "property.district,asc"}) Pageable pageable,
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
        model.addAttribute("listings", listingService.getAllListingsByStatus(status, pageable));
        return "listings/list";
    }

    @GetMapping("/no-page")
    public String listListingsByStatusNoPage(
            @RequestParam(name = "status", defaultValue = "PENDING") ListingStatus status, Model model) {
        model.addAttribute("listings", listingService.getAllListingsByStatus(status));
        return "listings/list";
    }

    @PostMapping("/accept/{id}")
    public Listing acceptListing(@RequestParam("status") boolean status, Long id) {
        var listing
                = listingService
                        .getListingById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Listing not found"));

        if (status) {
            listing.setStatus(ListingStatus.DISPLAYING);
        } else {
            listing.setStatus(ListingStatus.DECLINED);
        }
        return listingService.updateListing(listing);
    }
}
