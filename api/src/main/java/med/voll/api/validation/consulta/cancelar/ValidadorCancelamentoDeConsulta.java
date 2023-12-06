package med.voll.api.validation.consulta.cancelar;

import med.voll.api.domain.DTO.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
