package med.voll.api.domain.DTO.consulta;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DadosCancelamentoConsulta {
        // Princípio de Controller: A classe DadosCancelamentoConsulta atua como um DTO para dados de cancelamento de consulta.
        // Princípio de Creator: A classe é responsável por criar e manter dados relacionados ao cancelamento de consulta.
        // Princípio de Information Expert: A classe DadosCancelamentoConsulta contém as informações necessárias para o cancelamento de consulta.
        private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        private final Validator validator = factory.getValidator();

        // Princípio de Information Expert: Os atributos idConsulta e motivo são necessários para o cancelamento de consulta.
        @NotNull
        Long idConsulta;

        @NotNull
        MotivoCancelamento motivo;
        // Princípio de Information Expert: O construtor é responsável por inicializar os dados necessários para o cancelamento de consulta.
        public DadosCancelamentoConsulta(Long idConsulta, MotivoCancelamento motivo) {
                this.idConsulta = idConsulta;
                this.motivo = motivo;
        }
        public boolean validate() {
                Set<?> violations = validator.validate(this);
                return violations.isEmpty();

        }
        // Princípio de Information Expert: O método setValidator é responsável por validar os dados usando um validador fornecido.
        public void setValidator(Validator validator) {
                Set<?> violations = validator.validate(this);

        }
}
