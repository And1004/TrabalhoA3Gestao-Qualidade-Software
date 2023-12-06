package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.validation.consulta.agendar.ValidadorMedicoComOutraConsultaNoMesmoHorario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

class ValidadorMedicoComOutraConsultaNoMesmoHorarioTest {

    @Mock
    private ConsultaRepository repository;

    @InjectMocks
    private ValidadorMedicoComOutraConsultaNoMesmoHorario validadorMedico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidarComOutraConsultaNoMesmoHorario() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdMedico(1L);
        dados.setData(LocalDateTime.now());
        when(repository.existsByMedicoIdAndData(anyLong(), any(LocalDateTime.class))).thenReturn(true);
        ValidacaoException exception = assertThrows(ValidacaoException.class,
                () -> validadorMedico.validar(dados));
        assertEquals("Médico já possui outra consulta agendada nesse mesmo horário", exception.getMessage());
        verify(repository, times(1)).existsByMedicoIdAndData(dados.getIdMedico(), dados.getData());
    }

    @Test
    void testValidarSemOutraConsultaNoMesmoHorario() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdMedico(1L);
        dados.setData(LocalDateTime.now());
        when(repository.existsByMedicoIdAndData(anyLong(), any(LocalDateTime.class))).thenReturn(false);
        assertDoesNotThrow(() -> validadorMedico.validar(dados));
        verify(repository, times(1)).existsByMedicoIdAndData(dados.getIdMedico(), dados.getData());
    }
}
