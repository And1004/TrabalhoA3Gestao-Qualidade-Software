package med.voll.api.domain.entity;

import med.voll.api.domain.entity.Usuario;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsuarioTest {

    @Test
    void criarUsuario_ComDadosPreenchidos_Sucesso() {
        Long id = 1L;
        String login = "usuario123";
        String senha = "senha123";
        Usuario usuario = new Usuario(id, login, senha);
        assertThat(usuario.getId()).isEqualTo(id);
        assertThat(usuario.getLogin()).isEqualTo(login);
        assertThat(usuario.getSenha()).isEqualTo(senha);
    }
}
