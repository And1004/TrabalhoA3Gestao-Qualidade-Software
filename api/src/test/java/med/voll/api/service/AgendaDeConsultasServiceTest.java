package med.voll.api.service;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.AgendaDeConsultasService;
import med.voll.api.validation.consulta.agendar.ValidadorAgendamentoConsulta;
import med.voll.api.validation.consulta.cancelar.ValidadorCancelamentoDeConsulta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AgendaDeConsultasServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private ValidadorAgendamentoConsulta validadorAgendamento;

    @Mock
    private ValidadorCancelamentoDeConsulta validadorCancelamento;

    @InjectMocks
    private AgendaDeConsultasService agendaDeConsultasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        agendaDeConsultasService = new AgendaDeConsultasService(
                consultaRepository,
                medicoRepository,
                pacienteRepository,
                Collections.singletonList(validadorAgendamento),
                null
        );
        agendaDeConsultasService.setValidadoresCancelamento(Collections.singletonList(validadorCancelamento));
    }

    @Test
    void testAgendarConsulta() {
        DadosAgendamentoConsulta dados = new DadosAgendamentoConsulta(15L, 12L, LocalDateTime.of(2023, 12, 12, 12, 12), Especialidade.CARDIOLOGIA);
        when(pacienteRepository.existsById(any())).thenReturn(true);
        when(medicoRepository.existsById(any())).thenReturn(true);
        when(medicoRepository.getReferenceById(any())).thenReturn(new Medico());
        when(consultaRepository.save(any())).thenReturn(new Consulta());

        DadosDetalhamentoConsulta result = agendaDeConsultasService.agendar(dados);

        assertNotNull(result);
        verify(validadorAgendamento, times(1)).validar(dados);
        verify(pacienteRepository, times(1)).getReferenceById(any());
        verify(medicoRepository, times(1)).getReferenceById(any());
        verify(consultaRepository, times(1)).save(any());
    }

    @Test
    void testCancelarConsulta() {
        DadosCancelamentoConsulta dados = new DadosCancelamentoConsulta(10L, MotivoCancelamento.PACIENTE_DESISTIU);
        when(consultaRepository.existsById(any())).thenReturn(true);
        when(consultaRepository.getReferenceById(any())).thenReturn(new Consulta());
        agendaDeConsultasService.cancelar(dados);
        verify(validadorCancelamento, times(1)).validar(dados);
        verify(consultaRepository, times(1)).getReferenceById(any());
        verify(consultaRepository, times(1)).save(any());
    }
    }