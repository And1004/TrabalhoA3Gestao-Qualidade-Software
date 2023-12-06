package med.voll.api.service;

import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConcreteCreditCardPaymentTest {

    @Test
    public void testValidar() {

        ConcreteCreditCardPayment concretePayment = mock(ConcreteCreditCardPayment.class);
        DadosCancelamentoConsulta mockDados = mock(DadosCancelamentoConsulta.class);
        concretePayment.validar(mockDados);
        verify(concretePayment, times(1)).validar(mockDados);
    }
}
