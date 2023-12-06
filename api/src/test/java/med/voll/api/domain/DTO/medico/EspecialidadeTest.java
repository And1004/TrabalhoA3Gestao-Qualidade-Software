package med.voll.api.domain.DTO.medico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EspecialidadeTest {

    @Test
    void testEnumValues() {
        // Verificar se os valores do enum correspondem ao esperado
        assertEquals("ORTOPEDIA", Especialidade.ORTOPEDIA.name());
        assertEquals("CARDIOLOGIA", Especialidade.CARDIOLOGIA.name());
        assertEquals("GINECOLOGIA", Especialidade.GINECOLOGIA.name());
        assertEquals("DERMATOLOGIA", Especialidade.DERMATOLOGIA.name());
    }
}
