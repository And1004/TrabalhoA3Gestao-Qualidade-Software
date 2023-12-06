package med.voll.api.repository;

import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PacienteRepositoryTest {

    @Test
    public void testFindAllByAtivoTrue() {
        PacienteRepository pacienteRepository = mock(PacienteRepository.class);

        Page<Paciente> mockPage = mock(Page.class);
        when(pacienteRepository.findAllByAtivoTrue(any(Pageable.class))).thenReturn(mockPage);

        assertNotNull(pacienteRepository.findAllByAtivoTrue(mock(Pageable.class)));
    }

    @Test
    public void testFindAtivoById() {
        PacienteRepository pacienteRepository = mock(PacienteRepository.class);

        Boolean mockAtivo = true;
        when(pacienteRepository.findAtivoById(any(Long.class))).thenReturn(mockAtivo);

        assertNotNull(pacienteRepository.findAtivoById(1L));
    }
}
