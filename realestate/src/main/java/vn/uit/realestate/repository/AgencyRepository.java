package vn.uit.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.realestate.domain.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {}
