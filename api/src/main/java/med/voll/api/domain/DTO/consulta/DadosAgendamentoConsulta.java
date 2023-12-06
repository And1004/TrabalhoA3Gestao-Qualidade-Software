package med.voll.api.domain.DTO.consulta;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.DTO.medico.Especialidade;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class DadosAgendamentoConsulta {
    private Long idMedico;

    @NotNull
    private Long idPaciente;

    @NotNull
    @Future
    private LocalDateTime data;

    private Especialidade especialidade;

    public DadosAgendamentoConsulta(Long idMedico, Long idPaciente, LocalDateTime data, Especialidade especialidade) {
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.data = data;
        this.especialidade = especialidade;
    }

}
