package com.connexcanina.web;

import com.connexcanina.domain.Usuario;
import com.connexcanina.service.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("usuarioActual")
    public Usuario getUsuarioActual(Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails customUser) {
            return customUser.getUsuario();
        }
        return null;
    }
}
