package com.marcelocf87.apptransito.api.controller;

import com.marcelocf87.apptransito.api.assembler.AutuacaoAssembler;
import com.marcelocf87.apptransito.api.model.AutuacaoModel;
import com.marcelocf87.apptransito.api.model.input.AutuacaoInput;
import com.marcelocf87.apptransito.domain.model.Autuacao;
import com.marcelocf87.apptransito.domain.model.Veiculo;
import com.marcelocf87.apptransito.domain.service.RegistroAutuacaoService;
import com.marcelocf87.apptransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroAutuacaoService registroAutuacaoService;
    private final RegistroVeiculoService registroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId, @Valid @RequestBody AutuacaoInput autuacaoInput) {
        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, novaAutuacao);

        return autuacaoAssembler.toModel(autuacaoRegistrada);

    }

    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

        return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
    }

}
