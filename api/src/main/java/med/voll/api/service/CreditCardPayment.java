package med.voll.api.service;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.domain.entity.Pagar;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validation.consulta.cancelar.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditCardPayment implements Pagamento{
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;


    @Autowired
    private ConsultaRepository consultaRepository;

    CreditCardPayment() {
    }
    public void validar(DadosCancelamentoConsulta dados) {

        if (!consultaRepository.existsById(dados.getIdConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.getIdConsulta());
        if (consulta == null) {
            throw new ValidacaoException("Consulta não encontrada para o ID fornecido");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));
        consulta.cancelar(dados.getMotivo());
    }

    public void pagar(DadosAgendamentoConsulta dados, double valorConsulta) {
        Paciente paciente = pacienteRepository.findById(dados.getIdPaciente()).get();
        Medico medico = medicoRepository.findById(dados.getIdMedico()).get();
        Pagar pagamento = new Pagar(paciente.getNome(), medico.getNome(), dados.getData(), valorConsulta);
    }

}
