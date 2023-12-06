package med.voll.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsultaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoJson;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    ConsultaController controller;

    @BeforeAll
    void beforeAll() {
        pacienteRepository.save(new Paciente(dadosPaciente()));
        medicoRepository.save(new Medico(dadosMedico()));
    }

    @Test
    void agendarConsulta() throws Exception {
        LocalDateTime data = LocalDateTime.of(2024,1,1,12,30);
        DadosAgendamentoConsulta dadosAgendamento = new DadosAgendamentoConsulta(1L, 1L,
                data, Especialidade.CARDIOLOGIA);
        ResponseEntity<DadosDetalhamentoConsulta> agendar = controller.agendar(dadosAgendamento);
        DadosDetalhamentoConsulta dadosDetalhamentoConsulta = new DadosDetalhamentoConsulta(1L,1L,
                1L,data);

        assertEquals(dadosDetalhamentoConsulta, agendar.getBody());
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
