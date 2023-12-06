package med.voll.api.domain.entity;


import med.voll.api.domain.DTO.medico.DadosAtualizarMedico;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

class MedicoTest {

    @Mock
    private DadosCadastroMedico dadosCadastroMedico;

    @Mock
    private DadosAtualizarMedico dadosAtualizarMedico;

    @InjectMocks
    private Medico medico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(dadosCadastroMedico.getNome()).thenReturn("Nome");
        when(dadosCadastroMedico.getEmail()).thenReturn("medico@medico.com");
        when(dadosCadastroMedico.getCrm()).thenReturn("CRM123");
        when(dadosCadastroMedico.getTelefone()).thenReturn("123456789");
        when(dadosCadastroMedico.getEspecialidade()).thenReturn(Especialidade.CARDIOLOGIA);
        Endereco dadosEndereco = new Endereco("Avenida Paulista", "Paulista", "02124033", "São Paulo", "SP", "Nenhum", "255");
        when(dadosCadastroMedico.getEndereco()).thenReturn(dadosEndereco);
        when(dadosAtualizarMedico.getNome()).thenReturn("Novo Nome");
        when(dadosAtualizarMedico.getTelefone()).thenReturn("987654321");
        Endereco dadosEndereco2 = new Endereco("Avenida Fagundes", "Monte Verde", "021505033", "São Paulo", "SP", "Próximo ao mercado Dia", "205");
        when(dadosAtualizarMedico.getEndereco()).thenReturn(dadosEndereco2);
    }

    @Test
    void constructor_DadosCadastroMedico_Correto() {
        Medico novoMedico = new Medico(dadosCadastroMedico);
        assertThat(novoMedico).isNotNull();
        assertThat(novoMedico.getNome()).isEqualTo("Nome");
        assertThat(novoMedico.getEmail()).isEqualTo("medico@medico.com");
        assertThat(novoMedico.getCrm()).isEqualTo("CRM123");
        assertThat(novoMedico.getTelefone()).isEqualTo("123456789");
        assertThat(novoMedico.getEspecialidade()).isEqualTo(Especialidade.CARDIOLOGIA);
        assertThat(novoMedico.getEndereco()).isNotNull();
    }

    @Test
    void atualizarInformacoes_DadosAtualizarMedico_Correto() {
        Medico medico = new Medico(dadosCadastroMedico);
        medico.atualizarInformacoes(dadosAtualizarMedico);
        assertThat(medico.getNome()).isEqualTo("Novo Nome");
        assertThat(medico.getTelefone()).isEqualTo("987654321");
        assertThat(medico.getEndereco()).isNotNull();
    }

    @Test
    void excluir_AtivoAlteradoParaFalse() {
        Medico medico = new Medico(dadosCadastroMedico);
        medico.excluir();
        assertThat(medico.isAtivo()).isFalse();
    }

    @Test
    void atualizarInformacoes_MantemValoresQuandoNadaMuda() {
        DadosAtualizarMedico dadosAtualizar = new DadosAtualizarMedico(null, "Dr. Teste", "987654321", null);
        medico.atualizarInformacoes(dadosAtualizar);
        assertEquals("Dr. Teste", medico.getNome());
        assertEquals("987654321", medico.getTelefone());
        assertEquals(null, medico.getEndereco());
    }

    @Test
    void atualizarInformacoes_TrataDadosNulosCorretamente() {
        DadosAtualizarMedico dadosAtualizar = new DadosAtualizarMedico(null, "Dr. Teste", "987654321", null );
        medico.atualizarInformacoes(dadosAtualizar);
        assertEquals(null, medico.getId());
        assertEquals("Dr. Teste", medico.getNome()); // Mantém o valor existente
        assertEquals("987654321", medico.getTelefone()); // Atualiza o telefone
        assertEquals(null, medico.getEndereco()); // Mantém o valor existente
    }

    @Test
    void excluir_MarcaMedicoComoInativo() {
        medico.excluir();
        assertFalse(medico.isAtivo());
    }

    @Test
    void excluir_MedicoJaInativo_MantemInatividade() {
        medico.excluir();
        medico.excluir();
        assertFalse(medico.isAtivo());
    }

    @Test
    void atualizarInformacoes_AtualizaNome() {
        DadosAtualizarMedico dadosAtualizar = new DadosAtualizarMedico(null,"Dr. Novo", "123456789", null);
        medico.atualizarInformacoes(dadosAtualizar);
        assertEquals("Dr. Novo", medico.getNome());
        assertEquals("123456789", medico.getTelefone());
        assertEquals(null, medico.getEndereco());
    }
}
