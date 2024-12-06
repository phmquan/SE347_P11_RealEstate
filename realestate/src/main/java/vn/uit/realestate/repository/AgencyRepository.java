package vn.uit.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.uit.realestate.domain.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    List<Agency> findAll();

    Agency save(Agency agency);

    void deleteById(Long id);
}
