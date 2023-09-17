package tgid.BackEnd.api.domain.empresa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tgid.BackEnd.api.domain.Infra.TaxaSistema;
import tgid.BackEnd.api.domain.empresa.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;

    private String nome;

    private String email;

    private String telefone;

    private BigDecimal saldo;

    @OneToMany(mappedBy = "empresa")
    private List<TaxaSistema> taxas;
    public Empresa(DadosCadastroEmpresa dadosCadastroEmpresa) {
        this.cnpj = dadosCadastroEmpresa.cnpj();
        this.nome = dadosCadastroEmpresa.nome();
        this.email = dadosCadastroEmpresa.email();
        this.telefone = dadosCadastroEmpresa.telefone();
        this.saldo = BigDecimal.ZERO; // Inicia com zero de saldo

    }

    public void atualizaDadosEmpresa(DadosEditaEmpresa dados) {
        this.email = dados.email();
        this.telefone = dados.telefone();
    }
}
