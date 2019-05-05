package com.varejeira.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varejeira.api.modelo.EnumTipo;
import com.varejeira.api.modelo.Funcionario;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer> {

	Funcionario findByCpf(String cpf);

	Funcionario findByCpfAndTipo(String cpf, EnumTipo tipo);

	Funcionario findByEmail(String email);

}
