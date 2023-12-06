package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.medico.Especialidade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.PrescricaoMedica;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.PrescricaoService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PrescricaoTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private PrescricaoService prescricaoService = new Prescricao();

    @Test
    public void testPrescricaoMedica() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(2L, 1L, LocalDateTime.of(2023, 11, 11, 11, 11), Especialidade.CARDIOLOGIA);
        String nomeRemedio = "Paracetamol";
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        Medico medico = new Medico();
        medico.setNome("Dr. Silva");
        when(medicoRepository.findById(2L)).thenReturn(Optional.of(medico));
        PrescricaoMedica resultadoPrescricao = prescricaoService.prescricaoMedica(dados, nomeRemedio);
        resultadoPrescricao.setDataConsulta(LocalDateTime.of(2023, 11, 11, 11, 11));
        assertEquals("João", resultadoPrescricao.getNomePaciente());
        assertEquals("Dr. Silva", resultadoPrescricao.getNomeMedico());
        assertEquals(LocalDateTime.of(2023, 11, 11, 11, 11), resultadoPrescricao.getDataConsulta());
        assertEquals("Paracetamol", resultadoPrescricao.getNomeRemedio());
        verify(pacienteRepository, times(1)).findById(1L);
        verify(medicoRepository, times(1)).findById(2L);
    }

    @Test
    public void testPrescricaoMedicaComDadosNulos() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(null, null,null, null);
        dados.setIdPaciente(null);
        dados.setIdMedico(null);
        dados.setData(null);
        assertEquals(null, dados.getIdMedico());
        assertEquals(null, dados.getIdPaciente());
        assertEquals(null, dados.getData());
        assertEquals(null, dados.getEspecialidade());
    }

    @Test
    public void testPrescricaoMedicaComPacienteNaoEncontrado() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(2L, 1L, LocalDateTime.now(), Especialidade.ORTOPEDIA);
        dados.setIdPaciente(1L);
        dados.setIdMedico(2L);
        String nomeRemedio = "Paracetamol";
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            prescricaoService.prescricaoMedica(dados, nomeRemedio);
        });
    }

    @Test
    public void testPrescricaoMedicaComMedicoNaoEncontrado() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(2L, 1L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.ORTOPEDIA);
        dados.setIdPaciente(1L);
        dados.setIdMedico(2L);
        String nomeRemedio = "Paracetamol";
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(new Paciente()));
        when(medicoRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            prescricaoService.prescricaoMedica(dados, nomeRemedio);
        });
    }
}
