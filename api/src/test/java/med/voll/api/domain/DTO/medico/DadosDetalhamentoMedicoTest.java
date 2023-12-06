package med.voll.api.domain.DTO.medico;


import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DadosDetalhamentoMedicoTest {

    @Test
    void testDadosDetalhamentoMedico() {
        // Dados de teste
        Long id = 1L;
        String nome = "Marcos";
        String email = "marcoscrm@gmail.com";
        String telefone = "11985585";
        String crm = "187FD54";
        Especialidade especialidade = Especialidade.CARDIOLOGIA;

        // Criar instância de Endereco mock (objeto simulado que se comportará de acordo com suas configurações.)
        Endereco enderecoMock = mock(Endereco.class);
        // configuram o que o mock deve retornar quando métodos específicos são chamados
        when(enderecoMock.getLogradouro()).thenReturn("Casa do Paulo");
        when(enderecoMock.getBairro()).thenReturn("Vila Mariana");
        when(enderecoMock.getCep()).thenReturn("02525222");
        when(enderecoMock.getCidade()).thenReturn("São Paulo");
        when(enderecoMock.getUf()).thenReturn("SP");
        when(enderecoMock.getComplemento()).thenReturn("Próximo ao mercado");
        when(enderecoMock.getNumero()).thenReturn("2555");

        // Criar instância de Endereco mock (objeto simulado que se comportará de acordo com suas configurações.)
        Medico medicoMock = mock(Medico.class);
        // configuram o que o mock deve retornar quando métodos específicos são chamados
        when(medicoMock.getId()).thenReturn(1L);
        when(medicoMock.getNome()).thenReturn("Marcos");
        when(medicoMock.getEmail()).thenReturn("marcoscrm@gmail.com");
        when(medicoMock.getTelefone()).thenReturn("11985585");
        when(medicoMock.getCrm()).thenReturn("187FD54");
        when(medicoMock.getEspecialidade()).thenReturn(Especialidade.CARDIOLOGIA);
        when(medicoMock.getEndereco()).thenReturn(enderecoMock);

        // Criar instância de DadosDetalhamentoMedico
        DadosDetalhamentoMedico dadosDetalhamentoMedico = new DadosDetalhamentoMedico(medicoMock);

        // Verificar se os valores foram corretamente mapeados
        assertEquals(id, dadosDetalhamentoMedico.getId()); //  Verifica se o ID retornado por dadosDetalhamentoMedico.getId() é igual ao valor esperado id.
        assertEquals(nome, dadosDetalhamentoMedico.getNome()); // Verifica se o nome retornado por dadosDetalhamentoMedico.getNome() é igual ao valor esperado nome.
        assertEquals(email, dadosDetalhamentoMedico.getEmail()); // Verifica se o e-mail retornado por dadosDetalhamentoMedico.getEmail() é igual ao valor esperado email.
        assertEquals(telefone, dadosDetalhamentoMedico.getTelefone()); //  Verifica se o telefone retornado por dadosDetalhamentoMedico.getTelefone() é igual ao valor esperado telefone.
        assertEquals(crm, dadosDetalhamentoMedico.getCrm()); // Verifica se o CRM retornado por dadosDetalhamentoMedico.getCrm() é igual ao valor esperado crm.
        assertEquals(especialidade, dadosDetalhamentoMedico.getEspecialidade()); // Verifica se a especialidade retornada por dadosDetalhamentoMedico.getEspecialidade() é igual ao valor esperado especialidade.
        assertEquals(enderecoMock, dadosDetalhamentoMedico.getEndereco());  // Esta asserção compara se o objeto retornado por dadosDetalhamentoMedico.getEndereco() é igual ao objeto enderecoMock
    }
}
