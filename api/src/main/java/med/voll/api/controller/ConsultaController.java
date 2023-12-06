package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.service.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    // Princípio de Controller: A classe ConsultaController atua como um controlador para operações relacionadas a consultas.
    // Controller sugere que a responsabilidade de controlar a interação entre objetos deve ser atribuída a uma classe apropriada.
    @Autowired
    private AgendaDeConsultasService service;
    // Princípio de Controller: O método agendar atua como um controlador para a operação de agendamento de consultas.
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        // Princípio de Information Expert: A classe ConsultaController delega a responsabilidade de agendar consultas ao serviço correspondente (AgendaDeConsultasService).
        // Information Expert sugere que a responsabilidade deve ser atribuída à classe que possui as informações necessárias.
        DadosDetalhamentoConsulta dto = service.agendar(dados);
        return ResponseEntity.ok(dto);
    }
    // Princípio de Controller: O método cancelar atua como um controlador para a operação de cancelamento de consultas.
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        service.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}


