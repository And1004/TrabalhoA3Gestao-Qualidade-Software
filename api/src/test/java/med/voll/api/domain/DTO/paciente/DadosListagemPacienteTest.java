package med.voll.api.domain.DTO.paciente;

import med.voll.api.domain.DTO.paciente.DadosListagemPaciente;
import med.voll.api.domain.entity.Paciente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DadosListagemPacienteTest {

    @Test
    void testConstructorWithPaciente() {
        // Criar instância de Endereco mock (objeto simulado que se comportará de acordo com suas configurações.)
        Paciente pacienteMock = mock(Paciente.class);
        // configuram o que o mock deve retornar quando métodos específicos são chamados
        when(pacienteMock.getId()).thenReturn(1L);
        when(pacienteMock.getNome()).thenReturn("João");
        when(pacienteMock.getEmail()).thenReturn("joão@gmail.com");
        when(pacienteMock.getCpf()).thenReturn("125221256");

        // Criar instância de DadosListagemPaciente
        DadosListagemPaciente dadosListagemPaciente = new DadosListagemPaciente(pacienteMock);

        // Verificar se os valores foram corretamente mapeados
        assertEquals(1L, dadosListagemPaciente.id());
        assertEquals("João", dadosListagemPaciente.nome());
        assertEquals("joão@gmail.com", dadosListagemPaciente.email());
        assertEquals("125221256", dadosListagemPaciente.cpf());
    }

    @Test
    void testConstructorWithValues() {
        // Criando instância de DadosListagemPaciente
        DadosListagemPaciente dadosListagemPaciente = new DadosListagemPaciente(2L, "Joana", "joana@hotmail.com", "987123456");

        // Verificar se os valores foram corretamente mapeados
        assertEquals(2L, dadosListagemPaciente.id());
        assertEquals("Joana", dadosListagemPaciente.nome());
        assertEquals("joana@hotmail.com", dadosListagemPaciente.email());
        assertEquals("987123456", dadosListagemPaciente.cpf());
    }
}
