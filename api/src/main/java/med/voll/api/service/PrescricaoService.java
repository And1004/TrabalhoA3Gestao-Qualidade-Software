package med.voll.api.service;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;
import med.voll.api.domain.DTO.medico.PrescricaoMedica;


@Service

// SRP - Princípio da Responsabilidade Unica (SOLID) e DIP - Princípio da Inversão de Dependência
public interface PrescricaoService {
    
    public PrescricaoMedica prescricaoMedica (DadosAgendamentoConsulta dados, String nomeRemedio);

}


