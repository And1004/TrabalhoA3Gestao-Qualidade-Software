package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataConsulta = dados.getData();
        LocalDateTime now = LocalDateTime.now();
        long diferenciaMinutos = Duration.between(now, dataConsulta).toMinutes();
        if (diferenciaMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com atencedencia de 30 minutos");
        }
    }
}
