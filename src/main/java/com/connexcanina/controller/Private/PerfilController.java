package com.connexcanina.controller.Private;

import com.connexcanina.domain.Mascota;
import com.connexcanina.domain.Usuario;
import com.connexcanina.service.MascotaService;
import com.connexcanina.service.UsuarioService;
import com.connexcanina.service.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PerfilController {
    private final UsuarioService usuarioService;

    @Autowired
    private MascotaService mascotaService;

    @Transactional
    @GetMapping("/perfil")
    public String perfil(HttpServletRequest request, Authentication auth, Model model) {
        model.addAttribute("currentURI", request.getRequestURI());

        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            Usuario usuario = usuarioService.findById(userDetails.getUsuario().getIdUsuario());
            usuario.getMascotas().size();
            usuario.getCitas().forEach(cita -> {
                if (cita.getIdServicio() != null) {
                    cita.getIdServicio().getNombreServicio();
                }
            });

            model.addAttribute("usuarioActual", usuario);
        }

        return "private/perfil/informacion/perfil";
    }

    @PostMapping("/perfil/actualizar")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> actualizarPerfil(@ModelAttribute Usuario datosActualizados, Authentication auth) {
        if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            Usuario usuario = usuarioService.findById(userDetails.getUsuario().getIdUsuario());

            // Actualizamos datos permitidos
            usuario.setNombre(datosActualizados.getNombre());
            usuario.setApellido(datosActualizados.getApellido());
            usuario.setCorreoElectronico(datosActualizados.getCorreoElectronico());
            usuario.setTelefono(datosActualizados.getTelefono());
            usuario.setDireccion(datosActualizados.getDireccion());

            usuarioService.save(usuario); // asegúrate de tener un save()

            return ResponseEntity.ok().body(Map.of("status", "ok"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/perfil/eliminarMascota/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        try {
            mascotaService.eliminarMascotaPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/perfil/registrarMascota")
    @Transactional
    public String registrarMascota(@ModelAttribute Mascota mascota, Authentication authentication, Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Usuario usuario = usuarioService.findById(userDetails.getUsuario().getIdUsuario());
            mascota.setIdUsuario(usuario);
            mascotaService.save(mascota);
        }
        return "redirect:/perfil?registro=exito";
    }


}
