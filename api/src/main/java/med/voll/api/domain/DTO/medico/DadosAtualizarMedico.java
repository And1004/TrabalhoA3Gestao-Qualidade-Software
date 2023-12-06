package med.voll.api.domain.DTO.medico;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Endereco;

@Getter
@Setter
public class DadosAtualizarMedico {

        @NotNull
        Long id;
        String nome;
        String telefone;
        Endereco endereco;

        public DadosAtualizarMedico(Long id, String nome, String telefone, Endereco endereco) {
                this.id = id;
                this.nome = nome;
                this.telefone = telefone;
                this.endereco = endereco;
        }
}
