
package med.voll.api.domain.DTO.medico;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealizarConsulta {
    private String nomePaciente;
    private String nomeMedico;
    private LocalDateTime dataConsulta;
    private String especialidade;

    public RealizarConsulta (String nomePaciente, String nomeMedico, LocalDateTime dataConsulta, String especialidade) {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.dataConsulta = dataConsulta;
        this.especialidade = especialidade;
    }
}
