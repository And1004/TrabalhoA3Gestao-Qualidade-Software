
package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.RealizarConsulta;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AplicarConsulta implements ConsultaService {

    // O princípio Information Expert sugere que a responsabilidade deve ser atribuída à classe
    // que possui as informações necessárias para cumprir a responsabilidade.
    // Neste caso, a classe AplicarConsulta tem informações sobre Paciente e Medico.

    // O baixo acoplamento é frequentemente alcançado por meio da injeção de dependência
    @Autowired
    private PacienteRepository pacienteRepository;  
    @Autowired
    private MedicoRepository medicoRepository;

    // A operação realizarConsulta tem alta coesão, pois está relacionada à aplicação de consultas.
    @Override
    public RealizarConsulta realizarConsulta (DadosAgendamentoConsulta dados, String especialidade) {
        // Aqui, a classe AplicarConsulta está agindo como um Information Expert ao acessar
        // diretamente os repositórios para obter informações sobre Paciente e Medico.
        Paciente paciente = pacienteRepository.findById(dados.getIdPaciente()).get();
        Medico medico = medicoRepository.findById(dados.getIdMedico()).get();
        // A operação está focada em realizar uma consulta, mantendo alta coesão.
        RealizarConsulta consulta1 = new RealizarConsulta (paciente.getNome(), medico.getNome(), dados.getData(), especialidade);
        return consulta1;
    }

    @Override
    public boolean verificarConsultaExistente(Long idMedico, LocalDateTime data) {
        return true;
    }

}
