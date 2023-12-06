package med.voll.api.domain.entity;

import med.voll.api.domain.entity.Pagar;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PagarTest {

    @Test
    void criarPagamento_ComDadosPagar_Sucesso() {
        String nomePaciente = "NomePaciente";
        String nomeMedico = "NomeMedico";
        LocalDateTime dataConsulta = LocalDateTime.now();
        double valorConsulta = 100.0;
        Pagar pagamento = new Pagar(nomePaciente, nomeMedico, dataConsulta, valorConsulta);
        assertEquals(nomePaciente, pagamento.getNomePaciente());
        assertEquals(nomeMedico, pagamento.getNomeMedico());
        assertEquals(dataConsulta, pagamento.getDataConsulta());
        assertEquals(valorConsulta, pagamento.getValorConsulta());
    }

}
