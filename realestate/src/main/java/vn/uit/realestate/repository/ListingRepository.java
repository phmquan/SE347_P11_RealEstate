package vn.uit.realestate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.realestate.domain.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
  List<Listing> findByListingTitleContaining(String keyword);
}
