package vn.uit.realestate.controller.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.Property;
import vn.uit.realestate.service.ListingService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserListing {

    private ListingService listingService;

    public UserListing(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/user/listing/detail/{id}")
    public String getListingDetail(Model model, @PathVariable("id") Long id) {
        Listing listing = listingService.getListingById(id).isPresent() ? listingService.getListingById(id).get() : null;
        model.addAttribute("type", listing.getPropertyType());
        switch (listing.getPropertyType()) {
            case "APARTMENT" -> {
                Property currentProperty = listing.getProperty();
                model.addAttribute("currentProperty", currentProperty);
            }

            case "HOUSE" -> {
                Property currentProperty = listing.getProperty();
                model.addAttribute("currentProperty", currentProperty);
            }

            case "LAND" -> {
                Property currentProperty = listing.getProperty();
                model.addAttribute("currentProperty", currentProperty);
            }

            default -> {
            }

        }
        model.addAttribute("listing", listing);
        return "user/listing/detail";
    }

    @GetMapping("/user/listing")
    public String getFilteredListings(
            @RequestParam(value = "district", required = false) String district,
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "propertyType", required = false) String propertyType,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size,
            Model model) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Listing> listings = listingService.getFilteredListings(district, price, type, propertyType, pageable);

        model.addAttribute("listings", listings.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listings.getTotalPages());
        model.addAttribute("totalItems", listings.getTotalElements());

        return "user/homepage/show";
    }

}
