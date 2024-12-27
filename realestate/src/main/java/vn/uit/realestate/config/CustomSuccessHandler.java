package vn.uit.realestate.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import vn.uit.realestate.domain.User;
import vn.uit.realestate.service.UserService;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
  @Autowired private UserService userService;

  protected String determineTargetUrl(final Authentication authentication) {

    Map<String, String> roleTargetUrlMap = new HashMap<>();
    roleTargetUrlMap.put("ROLE_AGENCY", "/agency");
    roleTargetUrlMap.put("ROLE_USER", "/");
    roleTargetUrlMap.put("ROLE_ADMIN", "/admin");

    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (final GrantedAuthority grantedAuthority : authorities) {
      String authorityName = grantedAuthority.getAuthority();
      if (roleTargetUrlMap.containsKey(authorityName)) {
        return roleTargetUrlMap.get(authorityName);
      }
    }

    throw new IllegalStateException();
  }

  protected void clearAuthenticationAttributes(
      HttpServletRequest request, final Authentication authentication) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    // get email
    String email = authentication.getName();
    // get information
    User user = this.userService.getUserByEmail(email);
    if (user != null) {
      session.setAttribute("fullName", user.getFullName());
      session.setAttribute("avatar", user.getAvatar());
      session.setAttribute("id", user.getId());
      session.setAttribute("email", user.getEmail());
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    String targetUrl = determineTargetUrl(authentication);

    if (response.isCommitted()) {
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
    clearAuthenticationAttributes(request, authentication);
  }
}
