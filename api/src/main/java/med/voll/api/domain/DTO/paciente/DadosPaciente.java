package med.voll.api.domain.DTO.paciente;


import med.voll.api.domain.entity.Endereco;

public record DadosPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) {
}
