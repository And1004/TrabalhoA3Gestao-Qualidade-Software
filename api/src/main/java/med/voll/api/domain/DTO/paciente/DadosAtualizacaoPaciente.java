package med.voll.api.domain.DTO.paciente;

import jakarta.validation.Valid;
import med.voll.api.domain.entity.Endereco;


public record DadosAtualizacaoPaciente(
        Long id,
        String nome,
        String telefone,
        @Valid Endereco endereco
) {
}
