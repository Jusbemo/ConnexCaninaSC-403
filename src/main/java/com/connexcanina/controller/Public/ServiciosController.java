package com.connexcanina.controller.Public;

import com.connexcanina.service.ServicioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiciosController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/servicios")
    public String servicios(HttpServletRequest request, Model model) {
        var listaServicios = servicioService.getServicios();
        model.addAttribute("servicios", listaServicios);
        model.addAttribute("currentURI", request.getRequestURI());
        return "servicios/servicios";
    }
}
