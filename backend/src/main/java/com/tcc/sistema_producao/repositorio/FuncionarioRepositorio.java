package com.tcc.sistema_producao.repositorio;

import com.tcc.sistema_producao.entidade.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByMatricula(String matricula);

    Optional<Funcionario> findByMatriculaAndAtivoTrue(String matricula);
}