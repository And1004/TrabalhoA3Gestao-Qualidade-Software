package med.voll.api.repository;

import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.DTO.paciente.DadosCadastroPaciente;
import med.voll.api.domain.entity.Endereco;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MedicoRepositoryIntegrationTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @BeforeAll     void beforeAll() {
        pacienteRepository.save(new Paciente(dadosPaciente()));
        medicoRepository.save(new Medico(dadosMedico()));
    }

    @Test
    public void testFindAllByAtivoTrue() {
        Iterable<Medico> medicos = medicoRepository.findAllByAtivoTrue(Pageable.unpaged());

        assertNotNull(medicos);
    }

    @Test
    public void testGetReferenceByIdWhereAtivoTrue() {
        Long id = 1L;

        Medico medico = medicoRepository.getReferenceByIdWhereAtivoTrue(id);

        assertNotNull(medico);
    }

    @Test
    public void testEscolherMedicoAleatorioLivreNaData() {

        Especialidade especialidade = Especialidade.CARDIOLOGIA;
        LocalDateTime data = LocalDateTime.now();

        Medico medico = medicoRepository.escolherMedicoAleatorioLivreNaData(especialidade, data);

        assertNotNull(medico);
    }

    @Test
    public void testFindAtivoById() {
        Long id = 1L;

        Boolean ativo = medicoRepository.findAtivoById(id);

        assertNotNull(ativo);
    }

private DadosCadastroPaciente dadosPaciente(){
    return new DadosCadastroPaciente("Paulo", "paulinho@gmail.com", "55522552", "963852711", dadosEndereco());
}

    private Endereco dadosEndereco(){
        return new Endereco("rua A", "bairro", "09876543", "Sao Paulo",
                "SP", "casa 8","98");
    }

    private DadosCadastroMedico dadosMedico(){
        return new DadosCadastroMedico("Lucas","lucas.lucas@voll.med","098765432",
                "123456", Especialidade.CARDIOLOGIA,dadosEndereco());
    }
}
