package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

class ValidadorPacienteSemOutraConsultaNoDiaTest {

    @Mock
    private ConsultaRepository repository;

    @InjectMocks
    private ValidadorPacienteSemOutraConsultaNoDia validadorPaciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidarComPacienteComOutraConsultaNoDia() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdPaciente(1L);
        dados.setData(LocalDateTime.of(2023, 1, 1, 12, 0));

        when(repository.existsByPacienteIdAndDataBetween(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(true);

        ValidacaoException exception = assertThrows(ValidacaoException.class,
                () -> validadorPaciente.validar(dados));

        assertEquals("Paciente já possui uma consulta agendada nesse dia", exception.getMessage());

        verify(repository, times(1)).existsByPacienteIdAndDataBetween(
                eq(dados.getIdPaciente()),
                eq(dados.getData().withHour(7)),
                eq(dados.getData().withHour(18))
        );
    }

    @Test
    void testValidarComPacienteSemOutraConsultaNoDia() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdPaciente(1L);
        dados.setData(LocalDateTime.of(2023, 1, 1, 12, 0));

        when(repository.existsByPacienteIdAndDataBetween(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(false);

        assertDoesNotThrow(() -> validadorPaciente.validar(dados));

        verify(repository, times(1)).existsByPacienteIdAndDataBetween(
                eq(dados.getIdPaciente()),
                eq(dados.getData().withHour(7)),
                eq(dados.getData().withHour(18))
        );
    }
}
