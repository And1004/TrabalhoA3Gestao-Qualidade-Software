package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataConsulta = dados.getData();
        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAbertura = dataConsulta.getHour() < 7;
        boolean depoisDoFechamento = dataConsulta.getHour() >18;
        if(domingo || antesDaAbertura || depoisDoFechamento){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
