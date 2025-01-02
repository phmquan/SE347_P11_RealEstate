package vn.uit.realestate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.uit.realestate.domain.Listing;

public interface ListingRepositoryCustom {

    Page<Listing> findFilteredListings(String district, Long priceMin, Long priceMax, String type, String propertyType, Pageable pageable);
}
