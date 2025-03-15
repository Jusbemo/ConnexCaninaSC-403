package com.connexcanina.controller.Public;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SistemaController {

    @GetMapping("/sistema")
    public String sistema(HttpServletRequest request, Model model) {
        model.addAttribute("currentURI", request.getRequestURI());
        return "public/sistema/sistema";
    }
}
