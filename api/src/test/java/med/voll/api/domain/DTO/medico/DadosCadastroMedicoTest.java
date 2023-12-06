package med.voll.api.domain.DTO.medico;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.DTO.medico.DadosCadastroMedico;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.domain.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class DadosCadastroMedicoTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = validatorFactory.getValidator();
    }

    @Test
    void testCadastroMedicoValido() {
        // Dados de teste válidos
        String nome = "Dr. Oscar";
        String email = "oscar@gmail.com";
        String telefone = "123456789";
        String crm = "12345";
        Especialidade especialidade = Especialidade.CARDIOLOGIA;
        Endereco endereco = mock(Endereco.class);

        // Criar instância de DadosCadastroMedico
        DadosCadastroMedico dadosCadastroMedico = new DadosCadastroMedico(nome, email, telefone, crm, especialidade, endereco);

        // Validar a instância usando o validador
        assertTrue(validator.validate(dadosCadastroMedico).isEmpty());
    }

    @Test
    void testCadastroMedicoInvalido() {
        // Dados de teste inválidos (email inválido)
        String nome = "Dr. Oscar";
        String email = "oscar@gmail.com";
        String telefone = "123456789";
        String crm = "12345";
        Especialidade especialidade = Especialidade.CARDIOLOGIA;
        Endereco endereco = mock(Endereco.class);

        // Criar instância de DadosCadastroMedico
        DadosCadastroMedico dadosCadastroMedico = new DadosCadastroMedico(nome, email, telefone, crm, especialidade, endereco);

        // Validar a instância usando o validador
        assertTrue(validator.validate(dadosCadastroMedico).isEmpty());
    }
}
