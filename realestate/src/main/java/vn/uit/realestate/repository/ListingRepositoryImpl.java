package vn.uit.realestate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import vn.uit.realestate.domain.Listing;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListingRepositoryImpl implements ListingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Listing> findFilteredListings(String district, Long priceMin, Long priceMax, String type, String propertyType, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Listing> cq = cb.createQuery(Listing.class);
        Root<Listing> listing = cq.from(Listing.class);
        List<Predicate> predicates = new ArrayList<>();

        if (district != null) {
            predicates.add(cb.equal(listing.get("property").get("district"), district));
        }
        if (priceMin != null) {
            predicates.add(cb.greaterThanOrEqualTo(listing.get("property").get("propertyPrice"), priceMin));
        }
        if (priceMax != null) {
            predicates.add(cb.lessThanOrEqualTo(listing.get("property").get("propertyPrice"), priceMax));
        }
        if (type != null) {
            predicates.add(cb.equal(listing.get("listingType"), type));
        }
        if (propertyType != null) {
            predicates.add(cb.equal(listing.get("propertyType"), propertyType));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        List<Listing> resultList = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Listing> countRoot = countQuery.from(Listing.class);
        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, count);
    }
}
