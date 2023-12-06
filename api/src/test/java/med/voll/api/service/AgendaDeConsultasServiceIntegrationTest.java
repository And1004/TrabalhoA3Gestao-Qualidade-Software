package med.voll.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.domain.DTO.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.AgendaDeConsultasService;
import med.voll.api.validation.consulta.agendar.ValidadorAgendamentoConsulta;
import med.voll.api.validation.consulta.cancelar.ValidadorCancelamentoDeConsulta;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureJsonTesters
public class AgendaDeConsultasServiceIntegrationTest {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendaDeConsultasService agendaDeConsultasService;
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    @BeforeAll
    void beforeAll() {
        pacienteRepository.save(new Paciente(dadosPaciente()));
        medicoRepository.save(new Medico(dadosMedico()));
        consultaRepository.save(new Consulta());
    }

    @Test
    public void testAgendarConsulta() throws Exception {
        DadosAgendamentoConsulta dadosAgendamento = new DadosAgendamentoConsulta(1L, 1L,
                LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        DadosDetalhamentoConsulta detalhamentoConsulta = agendaDeConsultasService.agendar(dadosAgendamento);
        detalhamentoConsulta.setId(2L);
        DadosDetalhamentoConsulta dadosDetalhamentoConsulta2 = new DadosDetalhamentoConsulta(2L, 1L,
                1L, LocalDateTime.of(2023, 12, 12, 12, 12));
        assertEquals(dadosDetalhamentoConsulta2, detalhamentoConsulta);

    }
    private DadosCadastroPaciente dadosPaciente(){
        return new DadosCadastroPaciente("Paulo", "paulinho@gmail.com", "55522552", "963852711", dadosEndereco());
    }
    private Endereco dadosEndereco(){
        return new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98");
    }
    private DadosCadastroMedico dadosMedico(){
        return new DadosCadastroMedico("Lucas","lucas.lucas@voll.med","098765432",
                "123456", Especialidade.CARDIOLOGIA,dadosEndereco());
    }
}

