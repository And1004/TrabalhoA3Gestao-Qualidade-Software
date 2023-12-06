package med.voll.api.validation.consulta.agendar;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exceptions.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{
    // Princípio de Controller: A classe ValidadorMedicoAtivo atua como um componente (bean) que fornece lógica de validação.
    // Princípio de Polymorphism: A classe implementa uma interface (ValidadorAgendamentoConsulta) que permite a aplicação de várias estratégias de validação.

    @Autowired
    private MedicoRepository repository;
    // Princípio de Creator: A classe é responsável por criar e manter a lógica de validação específica para o agendamento de consulta.
    public void validar(DadosAgendamentoConsulta dados){
        // Princípio de Information Expert: A classe ValidadorMedicoAtivo possui a informação sobre a validação do médico ativo.
        if(dados.getIdMedico() == null){
            return;
        }
        Boolean medicoEstaAtivo = repository.findAtivoById(dados.getIdMedico());
        // Princípio de Information Expert: A classe é responsável por decidir se a consulta pode ser agendada com base na ativação do médico.
        if(!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
