package vn.uit.realestate.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.uit.realestate.domain.Property;
import vn.uit.realestate.service.PropertyService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/properties")
public class PropertyController {

  private final PropertyService propertyService;

  @GetMapping
  public String listProperties(Model model) {
    model.addAttribute("properties", propertyService.getAllProperties());
    return "properties/list"; // Trả về file template `list.html` trong thư mục templates/properties
  }

  @GetMapping("/{id}")
  public String viewProperty(@PathVariable Long id, Model model) {
    propertyService
        .getPropertyById(id)
        .ifPresent(property -> model.addAttribute("property", property));
    // Trả về file template `view.html` trong thư mục templates/propertie
    return "properties/view";
  }

  @GetMapping("/add")
  public String addPropertyForm(Model model) {
    model.addAttribute("property", new Property());
    // Trả về file template `add.html`
    return "properties/add";
  }

  @PostMapping("/add")
  public String addProperty(@ModelAttribute Property property) {
    propertyService.addProperty(property);
    // Điều hướng về danh sách
    return "redirect:/properties";
  }

  @GetMapping("/edit/{id}")
  public String editPropertyForm(@PathVariable Long id, Model model) {
    propertyService
        .getPropertyById(id)
        .ifPresentOrElse(
            property -> model.addAttribute("property", property),
            () -> {
              throw new IllegalArgumentException("Property not found");
            });

    // Trả về file template `edit.html`
    return "properties/edit";
  }

  @PostMapping("/edit/{id}")
  public String updateProperty(@PathVariable Long id, @ModelAttribute Property property) {
    propertyService.updateProperty(id, property);

    // Điều hướng về danh sách
    return "redirect:/properties";
  }

  @PostMapping("/delete/{id}")
  public String deleteProperty(@PathVariable Long id) {
    propertyService.deleteProperty(id);
    return "redirect:/properties";
  }
}
