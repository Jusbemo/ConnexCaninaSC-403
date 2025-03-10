package com.connexcanina.controller.Public;

import com.connexcanina.service.ServicioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        var listaTop3Servicios = servicioService.getTop3Servicios();
        model.addAttribute("servicios", listaTop3Servicios);
        model.addAttribute("currentURI", request.getRequestURI());
        return "home";
    }
}

