package med.voll.api.controller;

import med.voll.api.domain.DTO.medico.*;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import med.voll.api.repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
class MedicoControllerUnitarioTest {
    @Autowired
    private MockMvc mvc;


    @Autowired
    private JacksonTester<DadosCadastroMedico> dadadosCadastroMedicoJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;

    @InjectMocks     private MedicoController medicoController;

    @Mock     private MedicoRepository medicoRepository;

    @Test
    void cadastrar() {
        DadosCadastroMedico dadosCadastroMedico = new DadosCadastroMedico("Teste", "wjhhhuh@gmail.com", "11525255", "w48d54w45f", Especialidade.CARDIOLOGIA, new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98"));
        Medico medico = new Medico(dadosCadastroMedico);
        when(medicoRepository.save(medico)).thenReturn(medico);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<DadosDetalhamentoMedico> responseEntity = medicoController.cadastrar(dadosCadastroMedico, uriBuilder);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(medico.getId(), responseEntity.getBody().getId());
    }

    @Test
    void listar() {
        Pageable pageable = Pageable.unpaged();
        when(medicoRepository.findAllByAtivoTrue(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));
        ResponseEntity<Page<DadosListagemMedico>> responseEntity = medicoController.listar(pageable);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void atualizar() {
        DadosAtualizarMedico dadosAtualizarMedico = new DadosAtualizarMedico(15L, "Josué", "1152522", new Endereco("ruewwewea A", "baewweweirro", "wwewewe", "Sao Paulo",
                "SP", "casa 8","98"));
        DadosCadastroMedico dadosCadastroMedico = new DadosCadastroMedico("Teste", "wjhhhuh@gmail.com", "11525255", "w48d54w45f", Especialidade.CARDIOLOGIA, new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98"));
        Medico medico = new Medico(dadosCadastroMedico);
        medico.atualizarInformacoes(dadosAtualizarMedico);
        when(medicoRepository.getReferenceById(anyLong())).thenReturn(medico);
        ResponseEntity<DadosDetalhamentoMedico> responseEntity = medicoController.atualizar(dadosAtualizarMedico);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(medico.getId(), responseEntity.getBody().getId());
    }

    @Test
    void deletar() {
        Long medicoId = 1L;
        when(medicoRepository.getReferenceById(anyLong())).thenReturn(new Medico());
        ResponseEntity<Void> responseEntity = medicoController.deletar(medicoId);
        assertEquals(204, responseEntity.getStatusCodeValue());
        verify(medicoRepository).getReferenceById(medicoId);
    }

    private DadosCadastroMedico dadosMedico(){
        return new DadosCadastroMedico("Lucas","lucas.lucas@voll.med","098765432",
                "123456", Especialidade.CARDIOLOGIA,dadosEndereco());
    }
    private Endereco dadosEndereco(){
        return new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98");
    }
}