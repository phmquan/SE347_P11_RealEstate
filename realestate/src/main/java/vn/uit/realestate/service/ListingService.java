package vn.uit.realestate.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.repository.ListingRepository;

@Service
public class ListingService {

  private final ListingRepository listingRepository;

  public ListingService(ListingRepository listingRepository) {
    this.listingRepository = listingRepository;
  }

  public List<Listing> getAllListings() {
    return listingRepository.findAll();
  }

  public Optional<Listing> getListingById(Long id) {
    return listingRepository.findById(id);
  }

  public Listing addListing(Listing listing) {
    return listingRepository.save(listing);
  }

  public Listing updateListing(Long id, Listing updatedListing) {
    return listingRepository
        .findById(id)
        .map(
            listing -> {
              listing.setListingType(updatedListing.getListingType());
              listing.setListingStatus(updatedListing.getListingStatus());
              listing.setListingTitle(updatedListing.getListingTitle());
              listing.setListingDescription(updatedListing.getListingDescription());
              listing.setPropertyType(updatedListing.getPropertyType());
              listing.setProperty(updatedListing.getProperty());
              return listingRepository.save(listing);
            })
        .orElseThrow(() -> new RuntimeException("Listing not found with id: " + id));
  }

  public void deleteListing(Long id) {
    listingRepository.deleteById(id);
  }
}
