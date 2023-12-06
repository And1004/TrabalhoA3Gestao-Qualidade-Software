package med.voll.api.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    String logradouro;
    String bairro;
    String cep;
    String cidade;
    String uf;
    String complemento;
    String numero;

    public Endereco(Endereco endereco) {
        logradouro = endereco.getLogradouro();
        bairro = endereco.getBairro();
        cep = endereco.getCep();
        cidade = endereco.getCidade();
        uf = endereco.getUf();
        complemento = endereco.getComplemento();
        numero = endereco.getNumero();
    }

    public void atualizarInformacoes(Endereco endereco) {
        if(endereco.getLogradouro() != null) {
            logradouro = endereco.getLogradouro();
        }
        if(endereco.getBairro() != null) {
            bairro = endereco.getBairro();
        }
        if(endereco.getCep() != null) {
            cep = endereco.getCep();
        }
        if(endereco.getCidade() != null) {
            cidade = endereco.getCidade();
        }
        if(endereco.getUf() != null) {
            uf = endereco.getUf();
        }
        if(endereco.getComplemento() != null) {
            complemento = endereco.getComplemento();
        }
        if(endereco.getNumero() != null) {
            numero = endereco.getNumero();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cep, endereco.cep) && Objects.equals(cidade, endereco.cidade) && Objects.equals(uf, endereco.uf) && Objects.equals(complemento, endereco.complemento) && Objects.equals(numero, endereco.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, bairro, cep, cidade, uf, complemento, numero);
    }
}





