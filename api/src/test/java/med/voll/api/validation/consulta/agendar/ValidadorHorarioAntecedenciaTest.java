package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ValidadorHorarioAntecedenciaTest {

    @InjectMocks
    private ValidadorHorarioAntecedencia validadorHorarioAntecedencia;

    @Mock
    private DadosAgendamentoConsulta dadosAgendamentoConsulta;

    @Test
    void testValidarComMenosDe30MinutosDeAntecedencia() {
        MockitoAnnotations.openMocks(this);         LocalDateTime dataConsulta = LocalDateTime.now().plusMinutes(29);
        when(dadosAgendamentoConsulta.getData()).thenReturn(dataConsulta);
        assertThrows(ValidacaoException.class, () -> validadorHorarioAntecedencia.validar(dadosAgendamentoConsulta),
                "Consulta deve ser agendada com antecedência de 30 minutos");
    }
}
