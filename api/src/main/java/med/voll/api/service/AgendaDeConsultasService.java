package med.voll.api.service;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validation.consulta.agendar.ValidadorAgendamentoConsulta;
import med.voll.api.validation.consulta.cancelar.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;


    // Validação
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.getIdPaciente())) {
            throw new ValidacaoException("Id do paciente não existe");
        }
        if (dados.getIdMedico() != null && !medicoRepository.existsById(dados.getIdMedico())) {
            throw new ValidacaoException("Id do medico não existe");
        }


        validadores.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.getReferenceById(dados.getIdPaciente());
        Medico medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nesta data");
        }
        Consulta consulta = new Consulta(null, medico, paciente, dados.getData(), false);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);


    }


    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.getIdMedico() != null) {
            return medicoRepository.getReferenceById(dados.getIdMedico());
        }
        if (dados.getEspecialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatório quando médico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.getEspecialidade(), dados.getData());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.getIdConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.getIdConsulta());
        consulta.cancelar(dados.getMotivo());
        consulta.cancelar(dados.getMotivo());
        consultaRepository.save(consulta); // Este método deve ser chamado para salvar as alterações

    }

    @Autowired
    public AgendaDeConsultasService(
            ConsultaRepository consultaRepository,
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository,
            List<ValidadorAgendamentoConsulta> validadores,
            List<ValidadorCancelamentoDeConsulta> validadoresCancelamento
    ) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
        this.validadoresCancelamento = validadoresCancelamento;
    }

    // Setter para validadoresCancelamento
    public void setValidadoresCancelamento(List<ValidadorCancelamentoDeConsulta> validadoresCancelamento) {
        this.validadoresCancelamento = validadoresCancelamento;
    }
}
