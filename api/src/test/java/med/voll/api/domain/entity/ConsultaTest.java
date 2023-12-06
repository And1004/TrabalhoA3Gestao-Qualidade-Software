package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsultaTest {

    @Test
    public void testCancelarConsulta() {
        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        LocalDateTime dataConsulta = LocalDateTime.now();
        Consulta consulta = new Consulta(1L, medico, paciente, dataConsulta, true);

        MotivoCancelamento motivo = MotivoCancelamento.PACIENTE_DESISTIU;

        consulta.cancelar(motivo);

        assertEquals(motivo, consulta.getMotivoCancelamento());
    }

    @Test
    public void testIsCancelada() {
        Consulta consulta = new Consulta();
        assertFalse(consulta.isCancelada());
        consulta.setCancelada(true);
        assertTrue(consulta.isCancelada());
    }

    @Test
    public void testSetCancelada() {
        Consulta consulta = new Consulta();
        assertFalse(consulta.isCancelada());
        consulta.setCancelada(true);
        assertTrue(consulta.isCancelada());
    }

}
