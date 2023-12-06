package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

class ValidadorPacienteAtivoTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private ValidadorPacienteAtivo validadorPaciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidarComPacienteInativo() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdPaciente(1L);

        when(repository.findAtivoById(anyLong())).thenReturn(false);

        ValidacaoException exception = assertThrows(ValidacaoException.class,
                () -> validadorPaciente.validar(dados));

        assertEquals("Consulta não pode ser agendada com paciente excluído", exception.getMessage());

        verify(repository, times(1)).findAtivoById(dados.getIdPaciente());
    }

    @Test
    void testValidarComPacienteAtivo() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdPaciente(1L);

        when(repository.findAtivoById(anyLong())).thenReturn(true);

        assertDoesNotThrow(() -> validadorPaciente.validar(dados));

        verify(repository, times(1)).findAtivoById(dados.getIdPaciente());
    }
}
