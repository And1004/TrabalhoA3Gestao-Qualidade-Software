package med.voll.api.domain.DTO.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Endereco;

@Getter
@Setter
public class DadosCadastroPaciente {
        @NotBlank String nome;
        @NotBlank @Email String email;
        @NotBlank String telefone;
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf;
        @NotNull @Valid Endereco endereco;

    public DadosCadastroPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }
}
