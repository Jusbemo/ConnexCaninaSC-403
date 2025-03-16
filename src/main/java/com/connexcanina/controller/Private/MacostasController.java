package com.connexcanina.controller.Private;

import com.connexcanina.domain.Mascota;
import com.connexcanina.domain.Usuario;
import com.connexcanina.service.MascotaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MacostasController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/mascotas")
    public String mascotas(HttpServletRequest request, Model model) {
        var listaMasccotas = mascotaService.getMascotas();
        model.addAttribute("mascotas", listaMasccotas);
        model.addAttribute("currentURI", request.getRequestURI());
        return "private/mascotas/mascotas";
    }


    @GetMapping("/mascotas/eliminar/{idMascota}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long idMascota) {
        try {
            Mascota mascota = new Mascota();
            mascota.setIdMascota(idMascota);
            mascotaService.delete(mascota);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
