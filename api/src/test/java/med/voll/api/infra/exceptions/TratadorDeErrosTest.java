package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import med.voll.api.controller.ConsultaController;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TratadorDeErrosTest {
    @Mock
    private MethodArgumentNotValidException exception;

    @InjectMocks
    private final TratadorDeErros tratadorDeErros = new TratadorDeErros();

    @InjectMocks
    private ConsultaController suaClasseDeController;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Test
    public void testTratarErro404() {
        ResponseEntity<String> responseEntity = tratadorDeErros.tratarErro404();
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testTratarErro400HttpMessageNotReadableException() {
        HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);
        String mensagem = "Mensagem de erro";
        when(ex.getMessage()).thenReturn(mensagem);
        ResponseEntity<String> responseEntity = tratadorDeErros.tratarErro400(ex);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mensagem, responseEntity.getBody());
    }

    @Test
    public void testTratarErro500() {

        Exception ex = mock(Exception.class);
        String mensagem = "Mensagem de erro";
        when(ex.getLocalizedMessage()).thenReturn(mensagem);
        ResponseEntity<String> responseEntity = tratadorDeErros.tratarErro500(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Erro: " + mensagem, responseEntity.getBody());
    }

    @Test
    public void testTratarErroRegraDeNegocio() {
        ValidacaoException ex = mock(ValidacaoException.class);
        String mensagem = "Mensagem de erro";
        when(ex.getMessage()).thenReturn(mensagem);
        ResponseEntity<String> responseEntity = tratadorDeErros.tratarErroRegraDeNegocio(ex);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mensagem, responseEntity.getBody());
    }

}
