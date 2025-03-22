package com.connexcanina.controller.Private;

import com.connexcanina.domain.Consulta;
import com.connexcanina.service.ConsultaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class ConsultasController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/consultas")
    public String consultas(HttpServletRequest request, Model model) {
        var listaConsultas = consultaService.getConsultas();
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("consultas", listaConsultas);
        return "private/consultas/consultas";
    }

    @PostMapping("/consultas/actualizarEstado")
    public ResponseEntity<?> actualizarEstado(@RequestBody Consulta consultaRequest) {
        Optional<Consulta> consultaOptional = consultaService.getConsultaById(consultaRequest.getIdConsulta());

        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            consulta.setEstado(consultaRequest.getEstado());
            consultaService.save(consulta);
            return ResponseEntity.ok().body("{\"message\": \"Estado actualizado correctamente\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Consulta no encontrada\"}");
        }
    }

    @GetMapping("/consultas/eliminar/{idConsulta}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Long idConsulta) {
        try {
            Consulta consulta = new Consulta();
            consulta.setIdConsulta(idConsulta);
            consultaService.delete(consulta);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
