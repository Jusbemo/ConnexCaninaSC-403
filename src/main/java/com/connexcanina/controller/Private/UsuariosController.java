package com.connexcanina.controller.Private;

import com.connexcanina.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String usuarios(HttpServletRequest request, Model model) {
        var listaUsuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", listaUsuarios);
        model.addAttribute("currentURI", request.getRequestURI());
        return "private/usuarios/usuarios";
    }
}
