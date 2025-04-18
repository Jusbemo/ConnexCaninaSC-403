package com.connexcanina.controller.Public;

import com.connexcanina.service.ServicioService;
import com.connexcanina.service.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/debug")
    @ResponseBody
    public String debug(Authentication auth) {
        Object principal = auth.getPrincipal();

        if (principal instanceof CustomUserDetails customUser) {
            return "Usuario autenticado: " + customUser.getUsuario().getNombre() +
                    " | Rol: " + customUser.getAuthorities();
        }

        return "Principal NO es CustomUserDetails, es: " + principal.getClass().getName();
    }

}

