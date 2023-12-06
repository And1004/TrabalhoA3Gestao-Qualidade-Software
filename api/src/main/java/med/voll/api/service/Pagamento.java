package med.voll.api.service;

import java.util.List;
import java.util.Set;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import med.voll.api.domain.entity.Medico;
import med.voll.api.domain.entity.Paciente;
import med.voll.api.domain.entity.Pagar;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validation.consulta.cancelar.ValidadorCancelamentoDeConsulta;

@Service
// OCP - Princípio do aberto/fechado e SRP - Princípio da responsabilidade única
interface Pagamento {


    void pagar(DadosAgendamentoConsulta dados, double valorConsulta);

    void validar(DadosCancelamentoConsulta dados);
}