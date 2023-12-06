package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime primeiroHorario = dados.getData().withHour(7);
        LocalDateTime ultimoHorario = dados.getData().withHour(18);
        boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.getIdPaciente(),
                primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
