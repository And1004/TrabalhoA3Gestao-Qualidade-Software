
package med.voll.api.domain.DTO.medico;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class PrescricaoMedica {
    private String nomeMedico;
    private String nomePaciente;
    private LocalDateTime dataConsulta;
    public String nomeRemedio;

    public PrescricaoMedica(String nomeMedico, String nomePaciente, LocalDateTime data, String nomeRemedio) {
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.dataConsulta = dataConsulta;
        this.nomeRemedio = nomeRemedio;
    }

   
}
