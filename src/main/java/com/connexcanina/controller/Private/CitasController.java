package com.connexcanina.controller.Private;

import com.connexcanina.service.CitaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitasController {

    @Autowired
    private CitaService citasService;

    @GetMapping("/citas")
    public String citas(HttpServletRequest request, Model model) {
        var listaCitas = citasService.getCitas();
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("citas", listaCitas);
        return "private/citas/citas";
    }
}
