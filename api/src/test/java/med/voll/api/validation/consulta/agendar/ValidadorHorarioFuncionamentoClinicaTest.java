package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ValidadorHorarioFuncionamentoClinicaTest {

    @InjectMocks
    private ValidadorHorarioFuncionamentoClinica validadorHorarioFuncionamentoClinica;

    @Mock
    private DadosAgendamentoConsulta dadosAgendamentoConsulta;

    @BeforeEach
    void setUp() {
        validadorHorarioFuncionamentoClinica = new ValidadorHorarioFuncionamentoClinica();
    }

    @Test
    void testValidarHorarioFuncionamentoDomingo() {
        MockitoAnnotations.openMocks(this);         LocalDateTime dataConsulta = LocalDateTime.now().withHour(12).withMinute(0).with(DayOfWeek.SUNDAY);
        when(dadosAgendamentoConsulta.getData()).thenReturn(dataConsulta);
        assertThrows(ValidacaoException.class, () ->
                        validadorHorarioFuncionamentoClinica.validar(dadosAgendamentoConsulta),
                "Consulta fora do horário de funcionamento da clínica");
    }

    @Test
    void testValidarHorarioFuncionamentoAntesAbertura() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime dataConsulta = LocalDateTime.now().withHour(6).withMinute(59);
        when(dadosAgendamentoConsulta.getData()).thenReturn(dataConsulta);
        assertThrows(ValidacaoException.class, () ->
                        validadorHorarioFuncionamentoClinica.validar(dadosAgendamentoConsulta),
                "Consulta fora do horário de funcionamento da clínica");
    }

    @Test
    void testValidarHorarioFuncionamentoAposFechamento() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime dataConsulta = LocalDateTime.now().withHour(19).withMinute(0);
        when(dadosAgendamentoConsulta.getData()).thenReturn(dataConsulta);
        assertThrows(ValidacaoException.class, () ->
                        validadorHorarioFuncionamentoClinica.validar(dadosAgendamentoConsulta),
                "Consulta fora do horário de funcionamento da clínica");
    }
}
