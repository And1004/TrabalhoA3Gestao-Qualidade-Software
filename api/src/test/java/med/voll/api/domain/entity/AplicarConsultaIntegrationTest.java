package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.medico.RealizarConsulta;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AplicarConsultaIntegrationTest {

    @Autowired
    private AplicarConsulta aplicarConsulta;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @BeforeAll
    void beforeAll() {
        pacienteRepository.save(new Paciente(dadosPaciente()));
        medicoRepository.save(new Medico(dadosMedico()));
    }

    @Test
    public void testRealizarConsulta() {
        DadosAgendamentoConsulta dadosAgendamento = new DadosAgendamentoConsulta(1L, 1L,
                LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        String especialidade = "CARDIOLOGIA";

        RealizarConsulta realizarConsulta = aplicarConsulta.realizarConsulta(dadosAgendamento, especialidade);

        Paciente paciente = pacienteRepository.findById(dadosAgendamento.getIdPaciente()).get();
        Medico medico = medicoRepository.findById(dadosAgendamento.getIdMedico()).get();

        assertEquals(paciente.getNome(), realizarConsulta.getNomePaciente());
        assertEquals(medico.getNome(), realizarConsulta.getNomeMedico());
        assertEquals(dadosAgendamento.getData(), realizarConsulta.getDataConsulta());
        assertEquals(especialidade, realizarConsulta.getEspecialidade().toString());
    }

    @Test
    public void testVerificarConsultaExistente() {
        DadosAgendamentoConsulta dadosAgendamento = new DadosAgendamentoConsulta(1L, 1L,
                LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        Long idMedico = 1L;
        LocalDateTime data = LocalDateTime.now();

        boolean consultaExistente = aplicarConsulta.verificarConsultaExistente(idMedico, data);

        assertTrue(consultaExistente);
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
