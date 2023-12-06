
package med.voll.api.domain.entity;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.PrescricaoMedica;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import med.voll.api.service.PrescricaoService;

public class Prescricao implements PrescricaoService {

            
    @Autowired
    private PacienteRepository pacienteRepository;  
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public PrescricaoMedica prescricaoMedica (DadosAgendamentoConsulta dados, String nomeRemedio) {
        Paciente paciente = pacienteRepository.findById(dados.getIdPaciente()).get();
        Medico medico = medicoRepository.findById(dados.getIdMedico()).get();
        PrescricaoMedica prescricao1 = new PrescricaoMedica (medico.getNome(), paciente.getNome(), dados.getData(), nomeRemedio);
        return prescricao1;
    }
    
    
}
