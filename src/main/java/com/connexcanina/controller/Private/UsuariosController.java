package com.connexcanina.controller.Private;

import com.connexcanina.domain.Usuario;
import com.connexcanina.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/usuarios/eliminar/{idUsuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long idUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuarioService.delete(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
