package med.voll.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.DTO.medico.DadosAtualizarMedico;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;

@Setter
@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private boolean ativo;

    @Embedded // não considera endereço como uma outra tabela e sim seus elementos fazendo parte de Medico
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados){
        ativo = true;
        nome = dados.getNome();
        email = dados.getEmail();
        crm = dados.getCrm();
        telefone = dados.getTelefone();
        especialidade = dados.getEspecialidade();
        endereco = new Endereco(dados.getEndereco());
    }

    public void atualizarInformacoes(DadosAtualizarMedico dadosMedico) {
        if(dadosMedico.getNome() != null) {
            nome = dadosMedico.getNome();
        }
        if(dadosMedico.getTelefone() != null) {
            telefone = dadosMedico.getTelefone();
        }
        if(dadosMedico.getEndereco() != null) {
            endereco.atualizarInformacoes(dadosMedico.getEndereco());
        }
    }

    public void excluir() {
        ativo = false;
    }
}
