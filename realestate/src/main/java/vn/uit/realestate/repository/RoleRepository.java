package vn.uit.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.realestate.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);

  boolean existsByName(String name);
}
