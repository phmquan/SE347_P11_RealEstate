package vn.uit.realestate.init;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.uit.realestate.domain.Role;
import vn.uit.realestate.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    var roles = List.of("USER", "ADMIN", "AGENCY");

    roles.forEach(
        role -> {
          if (!roleRepository.existsByName(role)) {
            roleRepository.save(new Role(role));
          }
        });
  }
}
