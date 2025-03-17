package com.connexcanina.controller.Private;

import com.connexcanina.domain.Cita;
import com.connexcanina.service.CitaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

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
    
    @PostMapping("/citas/actualizarEstado")
    public ResponseEntity<?> actualizarEstado(@RequestBody Cita citaRequest) {
        Optional<Cita> citaOptional = citasService.getCitaById(citaRequest.getIdCita());

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setEstado(citaRequest.getEstado());
            citasService.save(cita);
            return ResponseEntity.ok().body("{\"message\": \"Estado actualizado correctamente\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Cita no encontrada\"}");
        }
    }
}
