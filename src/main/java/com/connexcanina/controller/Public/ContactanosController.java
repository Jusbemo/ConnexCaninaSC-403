package com.connexcanina.controller.Public;

import com.connexcanina.domain.Consulta;
import com.connexcanina.service.ConsultaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactanosController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/contactanos")
    public String contactanos(HttpServletRequest request, Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("currentURI", request.getRequestURI());
        return "public/contactanos/contactanos";
    }

    @PostMapping("/contactanos/enviar")
    public String enviar(@ModelAttribute Consulta consulta) {
        consultaService.save(consulta);
        return "redirect:/contactanos";
    }
}
