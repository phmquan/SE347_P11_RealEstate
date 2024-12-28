package vn.uit.realestate.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** PropertiesController */
@Controller
@RequestMapping("/user")
public class PropertiesController {

  @GetMapping("/properties")
  public String getProperties(Model model) {
    return "user/properties/show";
  }
}
