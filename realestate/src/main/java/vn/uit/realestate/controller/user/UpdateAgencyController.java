package vn.uit.realestate.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.uit.realestate.domain.Agency;
import vn.uit.realestate.domain.User;
import vn.uit.realestate.repository.RoleRepository;
import vn.uit.realestate.service.AgencyService;
import vn.uit.realestate.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/agency")
public class UpdateAgencyController {

  private final UserService userService;
  private final AgencyService agencyService;
  private final RoleRepository roleRepository;

  @GetMapping("/update")
  public String getUpdateAgencyForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    User user = userService.getUserByEmail(userDetails.getUsername());
    user.setPassword(null);
    model.addAttribute("currentUser", user);

    Agency agency = new Agency();
    model.addAttribute("agency", agency);

    if (userDetails.getUsername().equals("anonymousUser")) {
      return "redirect:/login";
    }
    return "agency/update";
  }

  @PostMapping("/update")
  public String updateToAgency(
      @ModelAttribute("agency") Agency agency,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {
    User user = userService.getUserByEmail(userDetails.getUsername());
    user.setRole(roleRepository.findByName("AGENCY"));
    User savedUser = userService.handleSaveUser(user);
    agency.setUser(savedUser);
    Agency agencySaved = agencyService.saveAgency(agency);
    agencySaved.getUser().setPassword(null);
    model.addAttribute("agency", agencySaved);
    return "redirect:/user/agency/update";
  }
}
