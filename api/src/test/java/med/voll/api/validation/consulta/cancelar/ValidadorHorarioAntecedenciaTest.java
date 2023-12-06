package med.voll.api.validation.consulta.cancelar;

import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ValidadorHorarioAntecedenciaTest {

    @Mock
    private ConsultaRepository repository;

    @Spy
    @InjectMocks
    private ValidadorHorarioAntecedencia validador;

    @Test
    void testValidarHorarioAntecedencia() {
        DadosCancelamentoConsulta dados = new DadosCancelamentoConsulta(1L, MotivoCancelamento.PACIENTE_DESISTIU);
        dados.setIdConsulta(1L);
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horarioConsulta = agora.plusHours(25);
        Consulta consulta = new Consulta();
        consulta.setData(horarioConsulta);
        when(repository.getReferenceById(1L)).thenReturn(consulta);
        Mockito.doThrow(new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!"))
                .when(validador).validar(dados);
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void testValidarHorarioAntecedenciaComSucesso() {
        DadosCancelamentoConsulta dados = new DadosCancelamentoConsulta(1L, MotivoCancelamento.PACIENTE_DESISTIU);
        dados.setIdConsulta(1L);
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horarioConsulta = agora.plusHours(48);
        Consulta consulta = new Consulta();
        consulta.setData(horarioConsulta);
        when(repository.getReferenceById(1L)).thenReturn(consulta);
        validador.validar(dados);
    }

}



