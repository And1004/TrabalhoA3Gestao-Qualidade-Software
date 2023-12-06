package med.voll.api.repository;

import med.voll.api.ApiApplication;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = ApiApplication.class)
public class ConsultaRepositoryTest {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Test
    public void testCancelarConsulta() {
        Consulta consulta = new Consulta();
        MotivoCancelamento motivo = MotivoCancelamento.PACIENTE_DESISTIU;

        consulta.cancelar(motivo);

        assertEquals(motivo, consulta.getMotivoCancelamento());
    }
}
