package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.DTO.medico.DadosAtualizarMedico;
import med.voll.api.domain.DTO.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.DTO.medico.DadosListagemMedico;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.entity.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriBuilder){
        // Princípio de Information Expert: A classe MedicoController delega a responsabilidade de cadastrar médicos ao repositório correspondente (MedicoRepository).
        Medico medico = new Medico(dadosCadastroMedico);
        repository.save(medico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        Page<DadosListagemMedico> page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizarMedico dadosMedico){
        Medico medico = repository.getReferenceById(dadosMedico.getId());
        medico.atualizarInformacoes(dadosMedico);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity listarMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceByIdWhereAtivoTrue(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}