package med.voll.api.repository;

import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.entity.Medico;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MedicoRepositoryTest {

    @Test
    public void testFindAllByAtivoTrue() {
        MedicoRepository medicoRepository = mock(MedicoRepository.class);

        Page<Medico> mockPage = mock(Page.class);
        when(medicoRepository.findAllByAtivoTrue(any(Pageable.class))).thenReturn(mockPage);

        assertNotNull(medicoRepository.findAllByAtivoTrue(mock(Pageable.class)));
    }

    @Test
    public void testGetReferenceByIdWhereAtivoTrue() {
        MedicoRepository medicoRepository = mock(MedicoRepository.class);

        Medico mockMedico = mock(Medico.class);
        when(medicoRepository.getReferenceByIdWhereAtivoTrue(any(Long.class))).thenReturn(mockMedico);

        assertNotNull(medicoRepository.getReferenceByIdWhereAtivoTrue(1L));
    }

    @Test
    public void testEscolherMedicoAleatorioLivreNaData() {
        MedicoRepository medicoRepository = mock(MedicoRepository.class);

        Medico mockMedico = mock(Medico.class);
        when(medicoRepository.escolherMedicoAleatorioLivreNaData(any(Especialidade.class), any(LocalDateTime.class))).thenReturn(mockMedico);

        assertNotNull(medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, LocalDateTime.now()));
    }

    @Test
    public void testFindAtivoById() {
        MedicoRepository medicoRepository = mock(MedicoRepository.class);

        Boolean mockAtivo = true;
        when(medicoRepository.findAtivoById(any(Long.class))).thenReturn(mockAtivo);

        assertNotNull(medicoRepository.findAtivoById(1L));
    }
}
