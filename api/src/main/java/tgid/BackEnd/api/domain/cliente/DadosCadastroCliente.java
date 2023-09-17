package tgid.BackEnd.api.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroCliente(
        @ValidCPF
        @NotBlank
        String cpf,

        @NotBlank(message = "O nome é obrigatorio")
        String nome,

        @NotBlank
        @Email(message = "O email é obrigatorio.")
        String email,

        @NotBlank(message = "O telefone é obrigatorio.")
        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
        String telefone
) {
}
