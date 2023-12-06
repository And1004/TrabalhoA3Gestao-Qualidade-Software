package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ValidadorMedicoAtivoTest {

    @Mock
    private MedicoRepository repository;

    @InjectMocks
    private ValidadorMedicoAtivo validadorMedicoAtivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidarMedicoAtivo() {
        DadosAgendamentoConsulta dadosAgendamentoConsulta = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dadosAgendamentoConsulta.setIdMedico(1L);

        when(repository.findAtivoById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> validadorMedicoAtivo.validar(dadosAgendamentoConsulta));

        verify(repository, times(1)).findAtivoById(1L);
    }

    @Test
    void testValidarMedicoInativo() {
        DadosAgendamentoConsulta dadosAgendamentoConsulta = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dadosAgendamentoConsulta.setIdMedico(1L);

        when(repository.findAtivoById(1L)).thenReturn(false);

        assertThrows(ValidacaoException.class, () -> validadorMedicoAtivo.validar(dadosAgendamentoConsulta));

        verify(repository, times(1)).findAtivoById(1L);
    }
}
