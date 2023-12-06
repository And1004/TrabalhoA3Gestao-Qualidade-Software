package med.voll.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.DTO.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.DTO.consulta.MotivoCancelamento;
import med.voll.api.domain.DTO.medico.SolicitarExame;

import java.time.LocalDateTime;

@Entity(name = "Consulta")
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Princípio de Information Expert: A classe Consulta é responsável por manter informações sobre o médico associado à consulta.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    // Princípio de Information Expert: A classe Consulta é responsável por manter informações sobre o paciente associado à consulta.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    // Princípio de Information Expert: A classe Consulta é responsável por manter informações sobre o motivo de cancelamento.
    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;
    private boolean cancelada;

    // Princípio de Creator: O construtor permite criar instâncias válidas da classe Consulta.
    // Creator sugere que a classe que possui a responsabilidade de criar uma instância de outra classe deve ser responsável por ela.
    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data, boolean cancelada) {

    }

    // Princípio de Controller: O método cancelar atua como um controlador para a operação de cancelamento de consulta.
    // Controller sugere que a responsabilidade de controlar a interação entre objetos deve ser atribuída a uma classe apropriada.
    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;

    }
    // Princípio de Information Expert: A classe Consulta é responsável por manter informações sobre se a consulta está cancelada ou não.
    public boolean isCancelada() {
        return cancelada;
    }
    // Princípio de Information Expert: A classe Consulta é responsável por fornecer um método para definir o status de cancelamento.

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}

