package med.voll.api.domain.DTO.medico;

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
public class DadosCadastroMedico {

        @NotBlank
        String nome;
        @NotBlank
        @Email
        String email;
        @NotBlank
        String telefone;
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm;
        @NotNull
        Especialidade especialidade;
        @NotNull
        @Valid
        Endereco endereco;

        public DadosCadastroMedico(String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
                this.nome = nome;
                this.email = email;
                this.telefone = telefone;
                this.crm = crm;
                this.especialidade = especialidade;
                this.endereco = endereco;
        }
        }

