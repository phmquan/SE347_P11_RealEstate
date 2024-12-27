package vn.uit.realestate.service;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    vn.uit.realestate.domain.User user = this.userService.getUserByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    var userDetails =
        new User(
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())));

    log.info("User {} logged in.", user.getEmail());
    return userDetails;
  }
}
