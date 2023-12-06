package med.voll.api.domain.DTO.consulta;

import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MotivoCancelamentoTest {

    @Test
    public void testEnumValues() {
        assertEquals("PACIENTE_DESISTIU", MotivoCancelamento.PACIENTE_DESISTIU.name());
        assertEquals("MEDICO_CANCELOU", MotivoCancelamento.MEDICO_CANCELOU.name());
        assertEquals("OUTROS", MotivoCancelamento.OUTROS.name());
    }

    @Test
    public void testEnumValuesEquality() {
        assertEquals(MotivoCancelamento.valueOf("PACIENTE_DESISTIU"), MotivoCancelamento.PACIENTE_DESISTIU);
        assertEquals(MotivoCancelamento.valueOf("MEDICO_CANCELOU"), MotivoCancelamento.MEDICO_CANCELOU);
        assertEquals(MotivoCancelamento.valueOf("OUTROS"), MotivoCancelamento.OUTROS);
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, MotivoCancelamento.PACIENTE_DESISTIU.ordinal());
        assertEquals(1, MotivoCancelamento.MEDICO_CANCELOU.ordinal());
        assertEquals(2, MotivoCancelamento.OUTROS.ordinal());
    }

    @Test
    public void testEnumValuesArray() {
        MotivoCancelamento[] expectedValues = { MotivoCancelamento.PACIENTE_DESISTIU, MotivoCancelamento.MEDICO_CANCELOU, MotivoCancelamento.OUTROS };
        assertArrayEquals(expectedValues, MotivoCancelamento.values());
    }
}
