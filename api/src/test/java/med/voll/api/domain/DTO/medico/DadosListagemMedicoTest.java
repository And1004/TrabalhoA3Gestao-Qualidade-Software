package med.voll.api.domain.DTO.medico;


import med.voll.api.domain.entity.Medico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DadosListagemMedicoTest {

    @Test
    void testDadosListagemMedico() {
        // Dados de teste
        Long id = 1L;
        String nome = "João";
        String email = "joao@gmail.com";
        String crm = "123ABC";
        Especialidade especialidade = Especialidade.CARDIOLOGIA;

        // Criar instância de Medico mock
        Medico medicoMock = mock(Medico.class);
        // configuram o que o mock deve retornar quando métodos específicos são chamados
        when(medicoMock.getId()).thenReturn(id);
        when(medicoMock.getNome()).thenReturn(nome);
        when(medicoMock.getEmail()).thenReturn(email);
        when(medicoMock.getCrm()).thenReturn(crm);
        when(medicoMock.getEspecialidade()).thenReturn(especialidade);

        // Criar instância de DadosListagemMedico
        DadosListagemMedico dadosListagemMedico = new DadosListagemMedico(medicoMock);

        // Verificar se os valores foram corretamente mapeados
        assertEquals(id, dadosListagemMedico.getId());
        assertEquals(nome, dadosListagemMedico.getNome());
        assertEquals(email, dadosListagemMedico.getEmail());
        assertEquals(crm, dadosListagemMedico.getCrm());
        assertEquals(especialidade, dadosListagemMedico.getEspecialidade());
    }
}
