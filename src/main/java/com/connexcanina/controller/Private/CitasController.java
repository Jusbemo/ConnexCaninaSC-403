package com.connexcanina.controller.Private;

import com.connexcanina.domain.Cita;
import com.connexcanina.domain.EspacioCita;
import com.connexcanina.service.CitaService;
import com.connexcanina.service.EspacioCitaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CitasController {

    @Autowired
    private CitaService citasService;

    @Autowired
    private EspacioCitaService espacioCitaService;

    @GetMapping("/citas")
    public String citas(HttpServletRequest request, Model model) {
        var listaCitas = citasService.getCitas();
        var listaEspacios = espacioCitaService.getEspaciosCitas();
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("citas", listaCitas);
        model.addAttribute("espacios", listaEspacios);
        model.addAttribute("espacioCita", new EspacioCita());
        return "private/citas/citas";
    }

    @PostMapping("/citas/actualizarEstado")
    public ResponseEntity<?> actualizarEstado(@RequestBody Cita citaRequest) {
        Optional<Cita> citaOptional = citasService.getCitaById(citaRequest.getIdCita());

        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            cita.setEstado(citaRequest.getEstado());

            EspacioCita espacio = cita.getIdEspacio();
            if ("cancelada".equalsIgnoreCase(cita.getEstado()) && espacio != null) {
                espacio.setEstado("disponible");
                espacio.setCita(null);
                cita.setIdEspacio(null);

                espacioCitaService.save(espacio);
            }

            citasService.save(cita); // Ahora sí se guarda con ID correcto
            return ResponseEntity.ok().body("{\"message\": \"Estado actualizado correctamente\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Cita no encontrada\"}");
        }
    }


    @GetMapping("/citas/eliminar/{idCita}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long idCita) {
        try {
            Optional<Cita> citaOptional = citasService.getCitaById(idCita);
            if (citaOptional.isPresent()) {
                citasService.delete(citaOptional.get());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    @PostMapping("/citas/crearEspacioCita")
    public ResponseEntity<?> crearEspacioCita(@RequestBody EspacioCita espacioCita) {
        try {
            espacioCita.updateFechaHoraDisponible();
            espacioCita.setEstado("disponible");
            espacioCitaService.save(espacioCita);
            return ResponseEntity.ok().body("{\"message\": \"Espacio creado correctamente\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Error interno\"}");
        }
    }

    @GetMapping("/citas/eliminarEspacioCita/{idEspacioCita}")
    public ResponseEntity<Void> eliminarEspacioCita(@PathVariable Long idEspacioCita) {
        try {
            EspacioCita espacio = espacioCitaService.getEspacioCitaById(idEspacioCita);
            if (espacio != null) {
                espacioCitaService.delete(espacio);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/citas/fragmento/espacios")
    public String obtenerFragmentoEspacios(Model model) {
        var listaEspacios = espacioCitaService.getEspaciosCitas();
        model.addAttribute("espacios", listaEspacios);
        return "private/citas/secciones :: table-space-citas-section";
    }

    @PostMapping("/citas/actualizarEstadoEspacioCita")
    public ResponseEntity<?> actualizarEstadoEspacio(@RequestBody EspacioCita espacioRequest) {
        try {
            EspacioCita espacio = espacioCitaService.getEspacioCitaById(espacioRequest.getIdEspacio());

            if (espacio == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Espacio no encontrado\"}");
            }

            String nuevoEstado = espacioRequest.getEstado();
            espacio.setEstado(nuevoEstado);

            if ("cancelado".equalsIgnoreCase(nuevoEstado) && espacio.getCita() != null) {
                espacio.getCita().setEstado("cancelada");
                citasService.save(espacio.getCita());
            } else if ("disponible".equalsIgnoreCase(nuevoEstado) && espacio.getCita() != null) {
                espacio.getCita().setEstado("cancelada");
                espacio.getCita().setIdEspacio(null);
                citasService.save(espacio.getCita());

                espacio.setCita(null);
            }

            espacioCitaService.save(espacio);
            return ResponseEntity.ok().body("{\"message\": \"Estado actualizado correctamente\"}");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Error al actualizar estado\"}");
        }
    }
}
