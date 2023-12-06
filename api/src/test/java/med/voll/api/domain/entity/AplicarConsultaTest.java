package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.medico.RealizarConsulta;
import med.voll.api.domain.entity.AplicarConsulta;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AplicarConsultaTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private AplicarConsulta aplicarConsulta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void realizarConsulta_Sucesso() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.now(), null);
        String especialidade = "Cardiologia";
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        Medico medico = new Medico();
        medico.setNome("Dr. Silva");
        when(pacienteRepository.findById(2L)).thenReturn(Optional.of(paciente)); // thenReturn(Optional.of(paciente)): Especifica que, quando findById(2L) for chamado, ele deve retornar um Optional contendo o objeto paciente. Isso é útil em testes quando você quer simular a recuperação de um paciente específico do repositório.
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        RealizarConsulta realizarConsulta = aplicarConsulta.realizarConsulta(dados, especialidade);

        assertEquals("João", realizarConsulta.getNomePaciente());
        assertEquals("Dr. Silva", realizarConsulta.getNomeMedico());
        assertEquals(dados.getData(), realizarConsulta.getDataConsulta());
        assertEquals(especialidade, realizarConsulta.getEspecialidade());
    }

    @Test
    void realizarConsulta_PacienteNaoEncontrado() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.now(), null);
        String especialidade = "Cardiologia";
        when(pacienteRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> aplicarConsulta.realizarConsulta(dados, especialidade));
    }

    @Test
    void realizarConsulta_MedicoNaoEncontrado() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.now(), null);
        String especialidade = "Cardiologia";
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        when(pacienteRepository.findById(2L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> aplicarConsulta.realizarConsulta(dados, especialidade));
    }

    @Test
    void realizarConsulta_EspecialidadeNula_Sucesso() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.now(), null);
        String especialidade = null;
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        Medico medico = new Medico();
        medico.setNome("Dr. Silva");
        when(pacienteRepository.findById(2L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        RealizarConsulta realizarConsulta = aplicarConsulta.realizarConsulta(dados, especialidade);
    }

    @Test
    void realizarConsulta_DataFutura_Sucesso() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(1L, 2L, LocalDateTime.now().plusDays(1), Especialidade.CARDIOLOGIA);
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        Medico medico = new Medico();
        medico.setNome("Dr. Silva");
        when(pacienteRepository.findById(2L)).thenReturn(Optional.of(paciente));
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        RealizarConsulta realizarConsulta = aplicarConsulta.realizarConsulta(dados, "Cardiologia");
    }
}