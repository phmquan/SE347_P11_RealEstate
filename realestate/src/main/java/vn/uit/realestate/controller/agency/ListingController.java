package vn.uit.realestate.controller.agency;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import vn.uit.realestate.domain.ListingStatus;
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
    public String getAgencyHomepageListings(
            Model model,
            @RequestParam("status") String type,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request) {

        Pageable pageable = PageRequest.of(page, size);
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "redirect:/login";
        } else {

            model.addAttribute("fullName", session.getAttribute("fullName"));
            model.addAttribute("avatar", session.getAttribute("avatar"));

        }
        Agency currentAgency = agencyService.getAgencyByEmail(session.getAttribute("email").toString());
        long displayingCount = listingService.countByStatusAndAgencyId(ListingStatus.DISPLAYING, currentAgency);
        long rejectedCount = listingService.countByStatusAndAgencyId(ListingStatus.DECLINED, currentAgency);
        long pendingCount = listingService.countByStatusAndAgencyId(ListingStatus.PENDING, currentAgency);
        long hiddenCount = listingService.countByStatusAndAgencyId(ListingStatus.HIDDEN, currentAgency);
        model.addAttribute("displayingCount", displayingCount);
        model.addAttribute("rejectedCount", rejectedCount);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("hiddenCount", hiddenCount);
        if (type == null) {
            return "agency/listing";
        } else {
            Page<Listing> listings;
            switch (type) {
                case "pending" -> {
                    listings = listingService.getAllListingsByStatusAndAgencyId(ListingStatus.PENDING, currentAgency, pageable);

                    model.addAttribute("listings", listings);
                    break;
                }
                case "displaying" -> {
                    listings = listingService.getAllListingsByStatusAndAgencyId(ListingStatus.DISPLAYING, currentAgency, pageable);

                    model.addAttribute("listings", listings);
                    break;
                }
                case "rejected" -> {
                    listings = listingService.getAllListingsByStatusAndAgencyId(ListingStatus.DECLINED, currentAgency, pageable);

                    model.addAttribute("listings", listings);
                    break;
                }
                case "hidden" -> {
                    listings = listingService.getAllListingsByStatusAndAgencyId(ListingStatus.HIDDEN, currentAgency, pageable);

                    model.addAttribute("listings", listings);
                    break;
                }
                default -> {
                    return "agency/homepage/show";
                }
            }
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", listings.getTotalPages());
            model.addAttribute("totalItems", listings.getTotalElements());
            return "agency/homepage/show";
        }
    }

    @GetMapping("/create")
    public String getListingForm(@RequestParam("type") String type, Model model) {
        if (type == null) {
            return "agency/listing/createListing";
        } else {
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
        if (type == null) {
            return "agency/listing/createListing";
        } else {
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
        return "redirect:/agency/listing?status=pending";
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
        return "redirect:/agency/listing?status=pending";
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
        return "redirect:/agency/listing?status=pending";
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
        listing.setStatus(ListingStatus.PENDING);
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
        return "redirect:/admin/listing?status=pending";
    }

    @GetMapping("/delete/{id}")
    public String deleteListingForm(@PathVariable("id") Long id, Model model) {
        listingService
                .getListingById(id)
                .ifPresentOrElse(
                        listing -> model.addAttribute("listing", listing),
                        () -> {
                            throw new IllegalArgumentException("Listing not found");
                        });
        long displayingCount = listingService.countByStatus(ListingStatus.DISPLAYING);
        long rejectedCount = listingService.countByStatus(ListingStatus.DECLINED);
        long pendingCount = listingService.countByStatus(ListingStatus.PENDING);
        long hiddenCount = listingService.countByStatus(ListingStatus.HIDDEN);
        model.addAttribute("displayingCount", displayingCount);
        model.addAttribute("rejectedCount", rejectedCount);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("hiddenCount", hiddenCount);
        return "agency/listing/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteListing(@PathVariable("id") Long id) {
        listingService.deleteListing(id);
        return "redirect:/agency/listing?status=pending";
    }
}
