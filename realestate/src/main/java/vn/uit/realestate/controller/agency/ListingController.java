package vn.uit.realestate.controller.agency;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/agency/listing")
public class ListingController {

    private final ListingService listingService;
    private final AgencyService agencyService;
    private final PropertyService propertyService;
    private final UploadService uploadService;

    @GetMapping("")
    public String getAgencyHomepageListings(Model model) {
        model.addAttribute("listings", listingService.getAllListings());
        return "agency/homepage/show";
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

    @GetMapping("/create")
    public String getListingForm(@RequestParam("type") String type, Model model) {
        switch (type) {
            case "house" -> {
                model.addAttribute("newListing", new Listing());
                return "agency/listing/listingForm/houseForm";
            }

            case "apartment" -> {
                model.addAttribute("newListing", new Listing());
                return "agency/listing/listingForm/apartmentForm";
            }

            case "land" -> {
                model.addAttribute("newListing", new Listing());
                return "agency/listing/listingForm/landForm";
            }

            default -> {
                return "agency/listing/createListing";
            }

        }
    }

    @PostMapping("/create")
    public String postListingCreate(
            @RequestParam("type") String type,
            @ModelAttribute("newListing") @Valid Listing listing, // Binding happens here
            BindingResult bindingResult,
            Model model,
            @RequestParam("propertyImage") List<MultipartFile> images,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return switch (type) {
                case "house" ->
                    "agency/listing/listingForm/houseForm";
                case "apartment" ->
                    "agency/listing/listingForm/apartmentForm";
                case "land" ->
                    "agency/listing/listingForm/landForm";
                default ->
                    "agency/listing/createListing";
            };
        }
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "redirect:/login";
        }

        String email = session.getAttribute("email").toString();
        Agency agency = agencyService.getAgencyByEmail(email);

        return switch (type) {
            case "house" ->
                handleHouseCreation(listing, agency, images);
            case "apartment" ->
                handleApartmentCreation(listing, agency, images);
            case "land" ->
                handleLandCreation(listing, agency, images);
            default -> {
                yield "agency/listing/createListing";
            }
        };
    }

// 
// Helper methods for property creation
    private String handleHouseCreation(Listing form, Agency agency, List<MultipartFile> images) {
        // Create and save a House
        House house = new House();
        house.setHouseType("Nhà Phố");
        house.setHouseRoom(2);
        house.setHouseToilet(1);
        house.setHouseDirection("Đông Nam");
        populateCommonPropertyFields(house, form, images);
        // Persist house first

        // Link property to listing after persistence
        propertyService.addProperty(house);
        form.setProperty(house);
        form.setPropertyType("house");
        finalizeListing(form, agency);
        return "redirect:/agency/listing";
    }

    private String handleApartmentCreation(Listing form, Agency agency, List<MultipartFile> images) {
        // Create and save an Apartment
        Apartment apartment = new Apartment();
        apartment.setApartmentType("Chung Cư");
        apartment.setApartmentRoom(2);
        apartment.setApartmentToilet(1);
        apartment.setApartmentDirection("Đông Nam");
        populateCommonPropertyFields(apartment, form, images);
        // Persist apartment first

        propertyService.addProperty(apartment);
        // Link property to listing after persistence
        form.setProperty(apartment);
        form.setPropertyType("apartment");
        finalizeListing(form, agency);
        return "redirect:/agency/listing";
    }

    private String handleLandCreation(Listing form, Agency agency, List<MultipartFile> images) {
        Land land = new Land();
        land.setLandType("Đất Nền");
        land.setLandDirection("Đông Nam");
        populateCommonPropertyFields(land, form, images);
        propertyService.addProperty(land);
        form.setProperty(land);
        form.setPropertyType("land");
        finalizeListing(form, agency);
        return "redirect:/agency/listing";
    }

// Helper method to populate shared fields
    private void populateCommonPropertyFields(Property property, Listing listing, List<MultipartFile> images) {

        property.setAddress(listing.getProperty().getAddress());
        property.setDistrict(listing.getProperty().getDistrict());
        property.setPropertyLength(listing.getProperty().getPropertyLength());
        property.setPropertyWidth(listing.getProperty().getPropertyWidth());
        property.setPropertyArea(listing.getProperty().getPropertyArea());
        property.setLegalDocument(listing.getProperty().getLegalDocument());
        property.setPropertyPrice(listing.getProperty().getPropertyPrice());

        if (images != null && !images.isEmpty()) {
            List<String> imagePaths = images.stream()
                    .map(file -> uploadService.handleSaveUploadFile(file, "/images/listing"))
                    .toList();
            property.setPropertyImages(imagePaths);
        }
    }

// Helper method to finalize listing
    private void finalizeListing(Listing listing, Agency agency) {
        listing.setListingStatus("pending");
        listing.setAgency(agency);
        listingService.addListing(listing);  // Persist listing
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
                                    .map(file -> uploadService.handleSaveUploadFile(file, "/images/listing"))
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
