package med.voll.api.domain.DTO.paciente;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entity.Paciente;

public record DadosListagemPaciente (Long id,
        String nome,
        String email,
        String cpf){

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }


}
