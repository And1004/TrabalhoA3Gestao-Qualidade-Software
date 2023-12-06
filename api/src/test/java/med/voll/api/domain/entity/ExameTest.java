package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.SolicitarExame;
import med.voll.api.domain.entity.Exame;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExameTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private Exame exame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void solicitarExame_Sucesso() {
        DadosAgendamentoConsulta dados = mock(DadosAgendamentoConsulta.class);
        when(dados.getIdPaciente()).thenReturn(1L);  // Configura o mock para retornar 1L quando o método getIdPaciente() é chamado.
        when(dados.getIdMedico()).thenReturn(2L); // Configura o mock para retornar 2L quando o método getIdMedico() é chamado.
        when(dados.getData()).thenReturn(LocalDateTime.now()); // Configura o mock para retornar o valor atual de LocalDateTime quando o método getData() é chamado.
        Paciente paciente = new Paciente();
        paciente.setNome("NomePaciente");
        when(pacienteRepository.findById(1L)).thenReturn(java.util.Optional.of(paciente));
        Medico medico = new Medico();
        medico.setNome("NomeMedico");
        when(medicoRepository.findById(2L)).thenReturn(java.util.Optional.of(medico));
        SolicitarExame resultadoExame = exame.solicitarExame(dados, "Exame1", "Tipo1");
        assertEquals("NomePaciente", resultadoExame.getNomePaciente());
        assertEquals("NomeMedico", resultadoExame.getNomeMedico());
        assertEquals("Tipo1", resultadoExame.getTipoExame());
    }

    @Test
    void solicitarExame_PacienteOuMedicoInexistente() {
        DadosAgendamentoConsulta dados = mock(DadosAgendamentoConsulta.class);
        when(dados.getIdPaciente()).thenReturn(1L); // Configura o mock para retornar 1L quando o método getIdPaciente() é chamado.
        when(dados.getIdMedico()).thenReturn(2L); // Configura o mock para retornar 2L quando o método getIdMedico() é chamado.
        when(dados.getData()).thenReturn(LocalDateTime.now()); // Configura o mock para retornar o valor atual de LocalDateTime quando o método getData() é chamado.
        when(pacienteRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(NoSuchElementException.class, () -> exame.solicitarExame(dados, "Exame1", "Tipo1"));
    }
}
