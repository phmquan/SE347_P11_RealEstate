package vn.uit.realestate.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.realestate.domain.Listing;
import vn.uit.realestate.domain.ListingStatus;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
  List<Listing> findByListingTitleContaining(String keyword);

  Page<Listing> findByStatus(ListingStatus status, Pageable pageable);

  List<Listing> findByStatus(ListingStatus status);
}
