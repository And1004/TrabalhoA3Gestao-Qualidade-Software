package med.voll.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.domain.DTO.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.DTO.paciente.DadosDetalhamentoPaciente;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.getNome();
        this.email = dados.getEmail();
        this.telefone = dados.getTelefone();
        this.cpf = dados.getCpf();
        this.endereco = new Endereco(dados.getEndereco());
    }

    public Paciente (DadosDetalhamentoPaciente dadosDetalhamentoPaciente) {
        this.ativo = true;
        this.nome = dadosDetalhamentoPaciente.getNome();
        this.email = dadosDetalhamentoPaciente.getEmail();
        this.telefone = dadosDetalhamentoPaciente.getTelefone();
        this.cpf = dadosDetalhamentoPaciente.getCpf();
        this.endereco = new Endereco(dadosDetalhamentoPaciente.getEndereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null)
            this.nome = dados.nome();

        if (dados.telefone() != null)
            this.telefone = dados.telefone();

        if (dados.endereco() != null)
            endereco.atualizarInformacoes(dados.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }

}
