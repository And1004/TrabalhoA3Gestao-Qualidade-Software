package med.voll.api.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.*;
import med.voll.api.validation.consulta.cancelar.ValidadorCancelamentoDeConsulta;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    @InjectMocks     private ConcreteCreditCardPayment concreteCreditCardPayment;

    @Mock
    private Validator validator;


    @Test
    void testCreditCardPaymentPagar() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdPaciente(1L);
        dados.setIdMedico(2L);
        dados.setData(LocalDateTime.now());
        Paciente pacienteMock = new Paciente();
        pacienteMock.setNome("Paciente Teste");
        when(pacienteRepository.findById(1L)).thenReturn(java.util.Optional.of(pacienteMock));
        Medico medicoMock = new Medico();
        medicoMock.setNome("Medico Teste");
        when(medicoRepository.findById(2L)).thenReturn(java.util.Optional.of(medicoMock));
        concreteCreditCardPayment.pagar(dados, 100.0);
        assertNotNull(pacienteMock);
        assertNotNull(medicoMock);
    }

    @Test
    void testDebitCardPaymentPagar() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        dados.setIdPaciente(1L);
        dados.setIdMedico(2L);
        dados.setData(LocalDateTime.now());
        Paciente pacienteMock = new Paciente();
        pacienteMock.setNome("Paciente Teste");
        when(pacienteRepository.findById(1L)).thenReturn(java.util.Optional.of(pacienteMock));
        Medico medicoMock = new Medico();
        medicoMock.setNome("Medico Teste");
        when(medicoRepository.findById(2L)).thenReturn(java.util.Optional.of(medicoMock));
        concreteCreditCardPayment.pagar(dados, 100.0);
        assertNotNull(pacienteMock);
        assertNotNull(medicoMock);
    }
}
