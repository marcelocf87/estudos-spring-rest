package com.marcelocf87.apptransito.api.controller;

import com.marcelocf87.apptransito.domain.model.Proprietario;
import com.marcelocf87.apptransito.domain.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

   @Autowired
   private ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
//        return proprietarioRepository.findByNomeContaining("Maria");
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);

    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId, @RequestBody Proprietario proprietario) {

        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);

    }

}
