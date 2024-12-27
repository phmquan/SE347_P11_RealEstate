package vn.uit.realestate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.realestate.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findByid(Long id);

  List<User> findOneByEmail(String email);

  boolean existsByEmail(String email);
}
