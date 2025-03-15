package com.connexcanina.controller.Public;

import com.connexcanina.domain.Servicio;
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
        return "public/servicios/servicios";
    }


    @GetMapping("/servicios/{nombreServicio}")
    public String servicioInformacion(HttpServletRequest request, Servicio servicio, Model model){
        servicio = servicioService.getServicio(servicio);
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("servicio", servicio);
        return "public/servicios/informacion";
    }
}

