package med.voll.api.controller;

import med.voll.api.domain.DTO.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.domain.DTO.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.DTO.paciente.DadosListagemPaciente;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PacienteControllerUnitarioTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PacienteRepository repository;

    @Autowired
    private JacksonTester<DadosCadastroPaciente> dadadosCadastroPacienteJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoPaciente> dadosDetalhamentoPacienteJson;

    @Autowired
    private PacienteController pacienteController;

    @Test
    void cadastrar() {
        DadosCadastroPaciente dadosCadastroPaciente = new DadosCadastroPaciente("Manuel", "manuel@hotmail.com", "115545445", "4541551554", new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98"));
        Paciente paciente = new Paciente(dadosCadastroPaciente);
        when(repository.save(paciente)).thenReturn(paciente);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<DadosDetalhamentoPaciente> responseEntity = pacienteController.cadastrar(dadosCadastroPaciente, uriBuilder);
        Paciente paciente1 = new Paciente(responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(paciente, paciente1);
    }

    @Test
    void listar() {
        Pageable pageable = Pageable.unpaged();
        when(repository.findAllByAtivoTrue((pageable))).thenReturn(new PageImpl<>(Collections.emptyList()));
        ResponseEntity<Page<DadosListagemPaciente>> responseEntity = pacienteController.listar(pageable);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertThat(responseEntity.getBody().getContent()).isEmpty();
    }

    @Test
    void atualizar() {
        DadosCadastroPaciente dadosCadastroPaciente = new DadosCadastroPaciente("Manuel", "manuel@hotmail.com", "115545445", "4541551554", new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98"));
        DadosAtualizacaoPaciente dadosAtualizacaoPaciente = new DadosAtualizacaoPaciente(15L, "Lucas", "5585245", new Endereco());
        Paciente paciente = new Paciente(dadosCadastroPaciente);
        when(repository.getReferenceById(anyLong())).thenReturn(paciente);
        when(repository.save(any(Paciente.class))).thenReturn(paciente);
        ResponseEntity<DadosDetalhamentoPaciente> responseEntity = pacienteController.atualizar(dadosAtualizacaoPaciente);
        Paciente paciente1 = new Paciente(responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(paciente, paciente1);
    }

    @Test
    void remover() {
        Long pacienteId = 1L;
        when(repository.getReferenceById(anyLong())).thenReturn(new Paciente());
        ResponseEntity<Void> responseEntity = pacienteController.remover(pacienteId);
        assertEquals(204, responseEntity.getStatusCodeValue());
        verify(repository).getReferenceById(pacienteId);
    }

    @Test
    void detalhar() {
        Long pacienteId = 1L;
        when(repository.getReferenceById(anyLong())).thenReturn(new Paciente());
        ResponseEntity<DadosDetalhamentoPaciente> responseEntity = pacienteController.detalhar(pacienteId);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
    private DadosCadastroPaciente dadosPaciente(){
        return new DadosCadastroPaciente("Paulo", "paulinho@gmail.com", "55522552", "963852711", dadosEndereco());
    }
    private Endereco dadosEndereco(){
        return new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98");
    }
}