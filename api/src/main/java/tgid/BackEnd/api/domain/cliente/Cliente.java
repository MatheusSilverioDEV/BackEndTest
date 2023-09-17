package tgid.BackEnd.api.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private BigDecimal saldo;

    public Cliente(DadosCadastroCliente dadosCadastroCliente) {
        this.cpf = dadosCadastroCliente.cpf();
        this.nome = dadosCadastroCliente.nome();
        this.email = dadosCadastroCliente.email();
        this.telefone = dadosCadastroCliente.telefone();
        this.saldo = BigDecimal.ZERO; // Inicia com zero de saldo

    }
    public void atualizaDadosCliente(DadosEditaCliente dados) {
        this.email = dados.email();
        this.telefone = dados.telefone();
    }

}
