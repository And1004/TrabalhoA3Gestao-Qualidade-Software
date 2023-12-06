
package med.voll.api.service;

import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.medico.RealizarConsulta;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

// SRP - Princípio da Responsabilidade Unica (SOLID) e DIP - Princípio da Inversão de Dependência
public interface ConsultaService {
        public RealizarConsulta realizarConsulta (DadosAgendamentoConsulta dados, String especialidade);

        boolean verificarConsultaExistente(Long idMedico, LocalDateTime data);
}
