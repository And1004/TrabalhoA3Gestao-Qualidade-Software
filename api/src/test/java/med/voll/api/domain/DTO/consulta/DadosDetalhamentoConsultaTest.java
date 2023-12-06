package med.voll.api.domain.DTO.consulta;

import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DadosDetalhamentoConsultaTest {

    @Test
    public void testConstrutorComParametros() {
        Long id = 1L;
        Long idMedico = 2L;
        Long idPaciente = 3L;
        LocalDateTime data = LocalDateTime.now();

        DadosDetalhamentoConsulta detalhamentoConsulta = new DadosDetalhamentoConsulta(id, idMedico, idPaciente, data);

        assertEquals(id, detalhamentoConsulta.getId());
        assertEquals(idMedico, detalhamentoConsulta.getIdMedico());
        assertEquals(idPaciente, detalhamentoConsulta.getIdPaciente());
        assertEquals(data, detalhamentoConsulta.getData());
    }
    @Test
    public void testConstrutorComConsulta() {
        Long idConsulta = 1L;
        Long idMedico = 2L;
        Long idPaciente = 3L;
        LocalDateTime data = LocalDateTime.now();

        Consulta consultaMock = mock(Consulta.class);
        when(consultaMock.getId()).thenReturn(idConsulta);
        when(consultaMock.getMedico()).thenReturn(new Medico(2L, "Matheus", "matheus@gmail.com", "15fg15g", "115266635", Especialidade.CARDIOLOGIA, true, new Endereco("Bairro Butatã", "Butatã", "0555552", "São Paulo", "SP", "Próximo a avenida", "555")));
        when(consultaMock.getPaciente()).thenReturn(new Paciente(3L, "Jorge", "teste@gmail.com", "200000", "1198255", new Endereco("Bairro da Luz", "Luz", "02125050", "São Paulo", "SP", "Próximo a Igreja", "255"),true));
        when(consultaMock.getData()).thenReturn(data);

        DadosDetalhamentoConsulta detalhamentoConsulta = new DadosDetalhamentoConsulta(consultaMock);

        assertEquals(idConsulta, detalhamentoConsulta.getId());
        assertEquals(idMedico, detalhamentoConsulta.getIdMedico());
        assertEquals(idPaciente, detalhamentoConsulta.getIdPaciente());
        assertEquals(data, detalhamentoConsulta.getData());
    }
}
