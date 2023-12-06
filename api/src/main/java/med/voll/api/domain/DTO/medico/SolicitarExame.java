
package med.voll.api.domain.DTO.medico;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitarExame {
    private String nomePaciente;
    private String nomeMedico;
    private String tipoExame;
    private String nomeExame;
    private LocalDateTime dataConsulta;

    public SolicitarExame(String nomePaciente, String nomeMedico, String tipoExame, LocalDateTime data, String nomeExame) {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.tipoExame = tipoExame;
        this.dataConsulta = data;
        this.nomeExame = nomeExame;
    }
}
