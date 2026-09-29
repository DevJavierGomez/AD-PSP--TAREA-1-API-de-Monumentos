package com.salesianos.dam.AD_PSP__TAREA_1_API_de_Monumentos.TAREA1.API.de.Monumentos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monument")
public class MonumentoController {

    private final MonumentoRepository monumentoRepository;

    @GetMapping
    public ResponseEntity<List<Monumentos>> getAllMonumento() {
        List<Monumentos> result = monumentoRepository.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monumentos> getById(@PathVariable Long id) {
        return ResponseEntity.of(monumentoRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Monumentos> addMonumentos(@RequestBody Monumentos monumentos) {
        if (StringUtils.hasText(monumentos.getNombreMonumento())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(monumentoRepository.save(monumentos));
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Monumentos> editMonumento(@PathVariable Long id, @RequestBody Monumentos monumentos) {

        if (monumentos.getNombreMonumento() == null || monumentos.getNombreMonumento().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return monumentoRepository.findById(id)
                .map(m -> {
                    m.setCodigoPais(monumentos.getCodigoPais());
                    m.setNombrePais(monumentos.getNombrePais());
                    m.setNombreCiudad(monumentos.getNombreCiudad());
                    m.setLatitud(monumentos.getLatitud());
                    m.setLongitud(monumentos.getLongitud());
                    m.setNombreMonumento(monumentos.getNombreMonumento());
                    m.setDescripcionMonumento(monumentos.getDescripcionMonumento());
                    m.setUrlfoto(monumentos.getUrlfoto());
                    return ResponseEntity.ok(monumentoRepository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonumentos(@PathVariable Long id) {
        monumentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
