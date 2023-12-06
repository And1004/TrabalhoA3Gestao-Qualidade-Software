package med.voll.api.domain.DTO.paciente;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Paciente;

@Getter
@Setter
public class DadosDetalhamentoPaciente {
    String nome;
    String email;
    String telefone;
    String cpf;
    Endereco endereco;

    public DadosDetalhamentoPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }


    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),paciente.getCpf(), paciente.getEndereco());
    }
}
