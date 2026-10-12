package com.tcc.sistema_producao.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroFuncionarioDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A matrícula é obrigatória")
        String matricula,

        @NotBlank(message = "O cargo é obrigatório")
        String cargo,

        @NotBlank(message = "A senha é obrigatória")
        String senha

) {
}