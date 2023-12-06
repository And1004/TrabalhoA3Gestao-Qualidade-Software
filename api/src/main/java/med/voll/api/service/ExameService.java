package med.voll.api.service;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Service;
import med.voll.api.domain.DTO.medico.SolicitarExame;


@Service

// SRP - Princípio da Responsabilidade Unica (SOLID) e DIP - Princípio da Inversão de Dependência
public interface ExameService {
    
    public SolicitarExame solicitarExame (DadosAgendamentoConsulta dados, String nomeExame, String tipoExame);

}


// Realizar consulta
// Gera prescrição médica
// Solicita exames
// Os dois testes
