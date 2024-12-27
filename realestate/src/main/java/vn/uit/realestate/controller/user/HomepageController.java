package vn.uit.realestate.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        session.getAttribute("fullName");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        return "user/homepage/show";
    }
}
