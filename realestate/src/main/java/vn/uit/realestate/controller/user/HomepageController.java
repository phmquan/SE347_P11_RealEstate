package vn.uit.realestate.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomepageController {

    @GetMapping("/")
    public String getHomepage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            String role = session.getAttribute("role").toString();
            return switch (role) {
                case "USER" ->
                    "user/homepage/show";
                case "AGENCY" ->
                    "redirect:/agency/listing";
                case "ADMIN" ->
                    "redirect:/admin";
                default ->
                    "/";
            };

        }

    }
}
