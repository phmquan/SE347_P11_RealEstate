package vn.uit.realestate.controller.user;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.uit.realestate.domain.User;
import vn.uit.realestate.domain.dto.RegisterDTO;
import vn.uit.realestate.service.UserService;

@Controller
public class AuthenticationController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public AuthenticationController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("registerUser", new RegisterDTO());
    return "user/authentication/register";
  }

  @PostMapping("/register")
  public String handleRegisterUser(
      @ModelAttribute("registerUser") @Valid RegisterDTO register, BindingResult bindingResult) {

    User user = this.userService.RegisterDTOtoUser(register);

    if (bindingResult.hasErrors()) {
      return "user/authentication/register";
    }
    String hashPassword = this.passwordEncoder.encode(user.getPassword());

    user.setPassword(hashPassword);
    user.setRole(this.userService.getRoleByName("USER"));
    this.userService.handleSaveUser(user);
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String getLoginPage(Model model) {

    return "user/authentication/login";
  }

  // @PostMapping("/login")
  // public String handleLogin(@ModelAttribute(""))
  @GetMapping("/access-denied")
  public String getAccessDeniedPage() {
    return "user/authentication/access_denied";
  }

  @PostMapping("/logout")
  public String getLogoutPage(Model model) {
    return "redirect:/login";
  }
}
