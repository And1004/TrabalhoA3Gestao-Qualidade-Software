package med.voll.api.controller;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.DTO.medico.Especialidade;
import med.voll.api.service.AgendaDeConsultasService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaDeConsultasService agendaDeConsultasService;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    void agendarCenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    void agendarCenario2() throws Exception {
        LocalDateTime data = LocalDateTime.now().plusHours(1);
        Especialidade especialidade = Especialidade.CARDIOLOGIA;
        DadosAgendamentoConsulta dadosAgendamentoConsulta = new DadosAgendamentoConsulta(2L, 5L, data, especialidade);

        DadosDetalhamentoConsulta dadosDetalhamentoConsultaEsperado = new DadosDetalhamentoConsulta(null, 2L, 5L, data);

        when(agendaDeConsultasService.agendar(dadosAgendamentoConsulta))
                .thenReturn(dadosDetalhamentoConsultaEsperado);

        MockHttpServletResponse response = mvc
                .perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJson.write(dadosAgendamentoConsulta).getJson()))
                .andReturn().getResponse();

        String jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamentoConsultaEsperado).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}