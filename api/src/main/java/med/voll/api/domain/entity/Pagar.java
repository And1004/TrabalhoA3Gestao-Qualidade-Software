
package med.voll.api.domain.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Pagar {
    private String nomePaciente;
    private String nomeMedico;
    private LocalDateTime dataConsulta;
    private double valorConsulta;

    public Pagar(String nomePaciente, String nomeMedico, LocalDateTime dataConsulta, double valorConsulta) {
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
        this.dataConsulta = dataConsulta;
        this.valorConsulta = valorConsulta;

}
}

