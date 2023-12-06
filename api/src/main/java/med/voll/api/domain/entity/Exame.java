
package med.voll.api.domain.entity;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.SolicitarExame;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import med.voll.api.service.ExameService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter
@Setter
public class Exame implements ExameService {
        
    @Autowired
    private PacienteRepository pacienteRepository;  
    @Autowired
    private MedicoRepository medicoRepository;

   @Override
    public SolicitarExame solicitarExame (DadosAgendamentoConsulta dados, String nomeExame, String tipoExame) {
        Paciente paciente = pacienteRepository.findById(dados.getIdPaciente()).get();
        Medico medico = medicoRepository.findById(dados.getIdMedico()).get();
        SolicitarExame exame1 = new SolicitarExame (paciente.getNome() , medico.getNome(), tipoExame, dados.getData().plusDays(5) ,nomeExame);
        return exame1;
    }


}
