package med.voll.api.domain.entity;


import med.voll.api.domain.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EnderecoTest {
    private Endereco endereco;

    @BeforeEach     void setUp() {
        endereco = new Endereco();
    }
    @Test
    void criarEndereco_Sucesso() {
        Endereco dadosEndereco = mock(Endereco.class);
        when(dadosEndereco.getLogradouro()).thenReturn("Rua Andarai");
        when(dadosEndereco.getBairro()).thenReturn("Centro");
        when(dadosEndereco.getCep()).thenReturn("12345-678");
        when(dadosEndereco.getCidade()).thenReturn("São Paulo");
        when(dadosEndereco.getUf()).thenReturn("SP");
        when(dadosEndereco.getComplemento()).thenReturn("Apto 123");
        when(dadosEndereco.getNumero()).thenReturn("456");
        endereco = new Endereco(dadosEndereco);
        assertEquals("Rua Andarai", endereco.getLogradouro());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getUf());
        assertEquals("Apto 123", endereco.getComplemento());
        assertEquals("456", endereco.getNumero());
    }

    @Test
    void atualizarInformacoesEndereco_Sucesso() {
        Endereco dadosOriginais = mock(Endereco.class);
        when(dadosOriginais.getLogradouro()).thenReturn("Rua Formosa");
        when(dadosOriginais.getBairro()).thenReturn("Vila Formosa");
        when(dadosOriginais.getCep()).thenReturn("98765-432");
        when(dadosOriginais.getCidade()).thenReturn("Rio de Janeiro");
        when(dadosOriginais.getUf()).thenReturn("RJ");
        when(dadosOriginais.getComplemento()).thenReturn("Casa");
        when(dadosOriginais.getNumero()).thenReturn("789");
        Endereco dadosAtualizados = mock(Endereco.class);
        when(dadosAtualizados.getLogradouro()).thenReturn("Avenida Principal");
        when(dadosAtualizados.getBairro()).thenReturn("Centro");
        when(dadosAtualizados.getCep()).thenReturn("54321-876");
        when(dadosAtualizados.getCidade()).thenReturn("Belo Horizonte");
        when(dadosAtualizados.getUf()).thenReturn("MG");
        when(dadosAtualizados.getComplemento()).thenReturn("Apto 321");
        when(dadosAtualizados.getNumero()).thenReturn("123");
        endereco = new Endereco(dadosOriginais);
        endereco.atualizarInformacoes(dadosAtualizados);
        assertEquals("Avenida Principal", endereco.getLogradouro());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("54321-876", endereco.getCep());
        assertEquals("Belo Horizonte", endereco.getCidade());
        assertEquals("MG", endereco.getUf());
        assertEquals("Apto 321", endereco.getComplemento());
        assertEquals("123", endereco.getNumero());
    }

    @Test
    void atualizarInformacoesEndereco_ComDadosNulos() {
        Endereco dadosOriginais = mock(Endereco.class);
        when(dadosOriginais.getLogradouro()).thenReturn("Rua dos democratas");
        when(dadosOriginais.getBairro()).thenReturn("Vila Monte");
        Endereco dadosAtualizados = mock(Endereco.class);
        endereco = new Endereco(dadosOriginais);
        endereco.atualizarInformacoes(dadosAtualizados);
        assertEquals("Rua dos democratas", endereco.getLogradouro());
        assertEquals("Vila Monte", endereco.getBairro());
    }

    @Test
    void atualizarInformacoesEndereco_InformacoesParciais() {
        Endereco dadosOriginais = mock(Endereco.class);
        when(dadosOriginais.getLogradouro()).thenReturn("Rua maioral da silva");
        when(dadosOriginais.getBairro()).thenReturn("Vila Guilherme");
        when(dadosOriginais.getCidade()).thenReturn("Santos");
        Endereco dadosAtualizados = mock(Endereco.class);
        when(dadosAtualizados.getCidade()).thenReturn("São Paulo");
        endereco = new Endereco(dadosOriginais);
        endereco.atualizarInformacoes(dadosAtualizados);
        assertEquals("Rua maioral da silva", endereco.getLogradouro());
        assertEquals("Vila Guilherme", endereco.getBairro());
        assertEquals("São Paulo", endereco.getCidade());
    }

    @Test
    void criarEndereco_ComDadosNulos() {
        Endereco dadosEndereco = mock(Endereco.class);
        endereco = new Endereco(dadosEndereco);
        assertNull(endereco.getLogradouro());
        assertNull(endereco.getBairro());
    }

    @Test
    void criarEndereco_SemDados() {
        endereco = new Endereco();
        assertNull(endereco.getLogradouro());
        assertNull(endereco.getBairro());
    }

    @Test
    public void testHashCode() {
        Endereco endereco1 = new Endereco("Rua gustavo freire", "São Joaquim", "12345-678", "São Paulo", "SP", "Apto 121", "123");
        Endereco endereco2 = new Endereco("Rua gustavo freire", "São Joaquim", "12345-678", "São Paulo", "SP", "Apto 121", "123");
        Endereco endereco3 = new Endereco("Rua Tuiuti", "Santana", "98765-432", "Rio de Janeiro", "RJ", "Casa", "456");
        assertEquals(endereco1.hashCode(), endereco2.hashCode());
        assertNotEquals(endereco1.hashCode(), endereco3.hashCode());
    }
}
