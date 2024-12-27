package vn.uit.realestate.controller.agency;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgencyUserController {

    @GetMapping("/agency")
    public String getMethodName(Model model) {
        return "agency/show";
    }

}
