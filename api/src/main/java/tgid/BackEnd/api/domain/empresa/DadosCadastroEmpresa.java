package tgid.BackEnd.api.domain.empresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroEmpresa(

            @NotBlank
            @ValidCNPJ
            String cnpj,

            @NotBlank(message = "O nome é obrigatorio")
            String nome,

            @NotBlank
            @Email(message = "O email é obrigatorio.")
            String email,

            @NotBlank(message = "O telefone é obrigatorio.")
            @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
            String telefone
    )
{}
