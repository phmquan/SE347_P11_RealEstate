package vn.uit.realestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.ListingStatus;
import vn.uit.realestate.repository.ListingRepository;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Page<Listing> getAllListings(Pageable pageable) {
        return listingRepository.findAll(pageable);
    }

    public Page<Listing> getAllListingsByStatus(ListingStatus status, Pageable pageable) {
        return listingRepository.findByStatus(status, pageable);
    }

    public Page<Listing> getAllListingsByStatusAndAgencyId(ListingStatus status, Agency agency, Pageable pageable) {
        return listingRepository.findByStatusAndAgencyId(status, agency.getId(), pageable);
    }

    public List<Listing> getAllListingsByStatus(ListingStatus status) {
        return listingRepository.findByStatus(status);
    }

    public Optional<Listing> getListingById(Long id) {
        return listingRepository.findById(id);
    }

    public Listing addListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public Listing updateListing(Listing updatedListing) {
        return listingRepository.save(updatedListing);
    }

    public long countByStatus(ListingStatus status) {
        return listingRepository.countByStatus(status);
    }

    public long countByStatusAndAgencyId(ListingStatus status, Agency agency) {
        return listingRepository.countByStatusAndAgencyId(status, agency.getId());
    }

    public Listing updateListing(Long id, Listing updatedListing) {
        return listingRepository
                .findById(id)
                .map(
                        listing -> {
                            listing.setListingType(updatedListing.getListingType());
                            listing.setStatus(updatedListing.getStatus());
                            listing.setListingTitle(updatedListing.getListingTitle());
                            listing.setListingDescription(updatedListing.getListingDescription());
                            listing.setPropertyType(updatedListing.getPropertyType());
                            listing.setProperty(updatedListing.getProperty());
                            return listingRepository.save(listing);
                        })
                .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
    }

    public void deleteListing(Long id) {
        Listing currentListing = listingRepository.findById(id).isPresent() ? listingRepository.findById(id).get() : null;
        if (currentListing != null) {
            currentListing.setStatus(ListingStatus.HIDDEN);
            listingRepository.save(currentListing);
        } else {
            throw new RuntimeException("Listing not found with id: " + id);
        }
    }

    public List<Listing> searchListing(String keyword) {
        return listingRepository.findByListingTitleContaining(keyword);

    }

    public Page<Listing> getFilteredListings(String district, String price, String type, String propertyType, Pageable pageable) {
        Long priceMin = null;
        Long priceMax = null;

        if (price != null) {
            switch (price) {
                case "1":
                    priceMax = 1000000000L; // Dưới 1 tỷ
                    break;
                case "2":
                    priceMin = 1000000000L;
                    priceMax = 3000000000L; // 1 tỷ - 3 tỷ
                    break;
                case "3":
                    priceMin = 3000000000L;
                    priceMax = 5000000000L; // 3 tỷ - 5 tỷ
                    break;
                case "4":
                    priceMin = 5000000000L;
                    priceMax = 7000000000L; // 5 tỷ - 7 tỷ
                    break;
                case "5":
                    priceMin = 7000000000L;
                    priceMax = 10000000000L; // 7 tỷ - 10 tỷ
                    break;
                case "6":
                    priceMin = 10000000000L; // Trên 10 tỷ
                    break;
            }
        }

        return listingRepository.findFilteredListings(district, priceMin, priceMax, type, propertyType, pageable);
    }
}
