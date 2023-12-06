package med.voll.api.domain.DTO.consulta;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DadosCancelamentoConsultaTest {

    @Test
    public void testCancelamentoConsultaValido() {
        DadosCancelamentoConsulta cancelamentoConsulta = new DadosCancelamentoConsulta(1L, MotivoCancelamento.PACIENTE_DESISTIU);

        Validator validator = mock(Validator.class);

        when(validator.validate(cancelamentoConsulta)).thenReturn(Set.of());

        cancelamentoConsulta.setValidator(validator);

        assertTrue(cancelamentoConsulta.validate());

        verify(validator).validate(cancelamentoConsulta);
    }

    @Test
    public void testCancelamentoConsultaInvalido() {
        DadosCancelamentoConsulta cancelamentoConsulta = new DadosCancelamentoConsulta(null, MotivoCancelamento.PACIENTE_DESISTIU);

        Validator validator = mock(Validator.class);

        ConstraintViolation<DadosCancelamentoConsulta> violation = mock(ConstraintViolation.class);

        when(validator.validate(cancelamentoConsulta)).thenReturn(Set.of(violation));

        cancelamentoConsulta.setValidator(validator);

        assertFalse(cancelamentoConsulta.validate());

        verify(validator).validate(cancelamentoConsulta);
    }
    @Test
    public void testMotivoCancelamentoInvalido() {
        DadosCancelamentoConsulta cancelamentoConsulta = new DadosCancelamentoConsulta(1L, null);

        Validator validator = mock(Validator.class);

        ConstraintViolation<DadosCancelamentoConsulta> violation = mock(ConstraintViolation.class);

        when(validator.validate(cancelamentoConsulta)).thenReturn(Set.of(violation));

        cancelamentoConsulta.setValidator(validator);

        assertFalse(cancelamentoConsulta.validate());

        verify(validator).validate(cancelamentoConsulta);
    }

    @Test
    public void testMotivoCancelamentoValido() {
        DadosCancelamentoConsulta cancelamentoConsulta = new DadosCancelamentoConsulta(1L, MotivoCancelamento.PACIENTE_DESISTIU);

        Validator validator = mock(Validator.class);

        when(validator.validate(cancelamentoConsulta)).thenReturn(Set.of());

        cancelamentoConsulta.setValidator(validator);

        assertTrue(cancelamentoConsulta.validate());

        verify(validator).validate(cancelamentoConsulta);
    }

}
