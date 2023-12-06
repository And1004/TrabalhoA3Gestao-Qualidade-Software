package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.medico.SolicitarExame;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ExameIntegrationTest {

    @Autowired
    private Exame exame;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @BeforeAll
    void beforeAll() {
        pacienteRepository.save(new Paciente(dadosPaciente()));
        medicoRepository.save(new Medico(dadosMedico()));
    }

    @Test
    public void testSolicitarExame() {
        DadosAgendamentoConsulta dadosAgendamento = new DadosAgendamentoConsulta(1L, 1L,
                LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        String nomeExame = "Exame de Sangue";
        String tipoExame = "Laboratorial";
        SolicitarExame solicitarExame = exame.solicitarExame(dadosAgendamento, nomeExame, tipoExame);
        Paciente paciente = pacienteRepository.findById(dadosAgendamento.getIdPaciente()).get();
        Medico medico = medicoRepository.findById(dadosAgendamento.getIdMedico()).get();
        assertNotNull(solicitarExame);
        assertEquals(paciente.getNome(), solicitarExame.getNomePaciente());
        assertEquals(medico.getNome(), solicitarExame.getNomeMedico());
        assertEquals(tipoExame, solicitarExame.getTipoExame());
        assertEquals(dadosAgendamento.getData().plusDays(5), solicitarExame.getDataConsulta());
        assertEquals(nomeExame, solicitarExame.getNomeExame());
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
