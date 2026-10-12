package com.tcc.sistema_producao.servico;

import com.tcc.sistema_producao.dto.CadastroFuncionarioDTO;
import com.tcc.sistema_producao.entidade.Funcionario;
import com.tcc.sistema_producao.repositorio.FuncionarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroFuncionarioServico {

    private final FuncionarioRepositorio funcionarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final AuditoriaServico auditoriaServico;

    public CadastroFuncionarioServico(
            FuncionarioRepositorio funcionarioRepositorio,
            PasswordEncoder passwordEncoder,
            AuditoriaServico auditoriaServico) {

        this.funcionarioRepositorio = funcionarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.auditoriaServico = auditoriaServico;
    }

    public Funcionario cadastrar(CadastroFuncionarioDTO dto) {

        if (funcionarioRepositorio
                .findByMatricula(dto.matricula())
                .isPresent()) {

            throw new IllegalArgumentException(
                    "A matrícula já está cadastrada"
            );
        }

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(dto.nome());
        funcionario.setMatricula(dto.matricula());
        funcionario.setCargo(dto.cargo());
        funcionario.setAtivo(true);
        funcionario.setAcessoSistema(true);
        funcionario.setSenha(
                passwordEncoder.encode(dto.senha())
        );
        funcionario.setPerfil("USUARIO");

        Funcionario funcionarioSalvo =
                funcionarioRepositorio.save(funcionario);

        auditoriaServico.registrarUsuarioAtual(
                "CADASTRO_FUNCIONARIO",
                "Funcionário "
                        + funcionarioSalvo.getMatricula()
                        + " cadastrado"
        );

        return funcionarioSalvo;
    }
}