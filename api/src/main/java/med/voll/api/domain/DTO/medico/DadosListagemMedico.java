package med.voll.api.domain.DTO.medico;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Medico;

@Getter
@Setter
public class DadosListagemMedico {

    Long id;
    String nome;
    String email;
    String crm;
    Especialidade especialidade;

    public DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
