package med.voll.api.domain.entity;


import med.voll.api.domain.DTO.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PacienteTest {

    @Mock
    private Endereco endereco;
    @InjectMocks
    private Paciente paciente;

    @Test
    public void testConstrutorComDadosCadastroPaciente() {
        Endereco endereco = new Endereco("Bebedouro", "Bom Retiro", "02124050", "São Paulo", "SP", "Nada", "255");
        DadosCadastroPaciente dadosCadastro = new DadosCadastroPaciente("João", "joao@gmail.com", "12345678901", "123456789", endereco);
        Paciente paciente = new Paciente(dadosCadastro);
        assertEquals("João", paciente.getNome());
        assertEquals("joao@gmail.com", paciente.getEmail());
        assertEquals("123456789", paciente.getCpf());
        assertEquals("12345678901", paciente.getTelefone());
        assertEquals(endereco, paciente.getEndereco());
        assertEquals(true, paciente.getAtivo());
    }

    @Test
    public void testAtualizarInformacoes() {
        DadosAtualizacaoPaciente dadosAtualizacao = new DadosAtualizacaoPaciente(1L,"Maria", null, null);
        paciente.atualizarInformacoes(dadosAtualizacao);
        assertEquals("Maria", paciente.getNome());
    }

    @Test
    public void testInativar() {
        paciente.inativar();
        assertEquals(false, paciente.getAtivo());
    }

    @Test
    public void testAtualizarInformacoesComDadosNulos() {
        DadosAtualizacaoPaciente dadosAtualizacao = new DadosAtualizacaoPaciente(null, null, null, null);
        paciente.atualizarInformacoes(dadosAtualizacao);

    }


}
