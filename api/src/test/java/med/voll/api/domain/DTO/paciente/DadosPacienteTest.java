package med.voll.api.domain.DTO.paciente;

import med.voll.api.domain.DTO.paciente.DadosPaciente;
import med.voll.api.domain.entity.Endereco;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DadosPacienteTest {

    @Test
    void testGetters() {
        // a variável enderecoMock será um objeto simulado do tipo Endereco.
        Endereco enderecoMock = mock(Endereco.class);
        // Criando instância de DadosPaciente
        DadosPaciente dadosPaciente = new DadosPaciente("Josué", "josue@gmail.com", "123456789", "123.456.789-00", enderecoMock);
        // Verificar se os valores foram corretamente mapeados
        assertEquals("Josué", dadosPaciente.nome());
        assertEquals("josue@gmail.com", dadosPaciente.email());
        assertEquals("123456789", dadosPaciente.telefone());
        assertEquals("123.456.789-00", dadosPaciente.cpf());
        assertEquals(enderecoMock, dadosPaciente.endereco());
    }

    @Test
    void testRecordEquals() {
        // Criando instâncias de Endereco
        Endereco endereco1 = new Endereco("Rua endaiatuba", "Vila monte alegre", "12345", "São Paulo", "SP", "Próximo a ubs", "123");
        Endereco endereco2 = new Endereco("Rua general montes", "Vila guarani", "54321", "Rio de Janeiro", "RJ", "Próximo ao mercado", "321");
        // Criando instâncias de DadosPaciente
        DadosPaciente paciente1 = new DadosPaciente("Mauro Cezar", "MauroCezar@gmail.com", "123456789", "123.456.789-00", endereco1);
        DadosPaciente paciente2 = new DadosPaciente("Luciana Moraes", "LucianaMoraes@gmail.com", "987654321", "987.654.321-00", endereco2);
        // verificar se os dois objetos, paciente1 e paciente2, não são iguais
        assertNotEquals(paciente1, paciente2);
        // Instanciando novo DadosPaciente
        DadosPaciente paciente3 = new DadosPaciente("Mauro Cezar", "MauroCezar@gmail.com", "123456789", "123.456.789-00", endereco1);
        // Verificando se as variáveis paciente1 e paciente3 são iguais
        assertEquals(paciente1, paciente3);
    }
}
