package med.voll.api.domain.DTO.consulta;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Consulta;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class DadosDetalhamentoConsulta {
    Long id;

    Long idMedico;
    Long idPaciente;

    LocalDateTime data;

    public DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
        this.id = id;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.data = data;
    }

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), getMedicoId(consulta), getPacienteId(consulta), consulta.getData());
    }

    private static Long getMedicoId(Consulta consulta) {
        return (consulta.getMedico() != null) ? consulta.getMedico().getId() : null;
    }

    private static Long getPacienteId(Consulta consulta) {
        return (consulta.getPaciente() != null) ? consulta.getPaciente().getId() : null;
    }

}
