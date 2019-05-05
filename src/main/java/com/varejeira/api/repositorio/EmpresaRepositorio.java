package com.varejeira.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varejeira.api.modelo.Empresa;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa,Integer>{
	
	Empresa findByCnpj(String cnpj);
}
