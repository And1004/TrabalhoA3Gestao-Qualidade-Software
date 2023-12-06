package med.voll.api.domain.DTO.medico;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;


@Getter
@Setter
public class DadosDetalhamentoMedico {

    Long id;
    String nome;
    String email;
    String telefone;
    String crm;
    Especialidade especialidade;
    Endereco endereco;

    public DadosDetalhamentoMedico(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
        
    }
}
