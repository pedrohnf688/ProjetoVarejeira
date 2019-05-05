package com.varejeira.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejeira.api.modelo.Empresa;
import com.varejeira.api.repositorio.EmpresaRepositorio;

@Service
public class EmpresaServico {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServico.class);

	@Autowired
	private EmpresaRepositorio empresaRepositorio;

	public Optional<Empresa> buscarPorId(Integer id) {
		log.info("Buscando Empresa por Id:", id);
		return empresaRepositorio.findById(id);
	}

	public List<Empresa> listarEmpresas() {
		log.info("Buscando Todos os Empresa:");
		return empresaRepositorio.findAll();
	}

	public Empresa salvarEmpresa(Empresa empresa) {
		log.info("Salvando Empresa:", empresa);
		return empresaRepositorio.save(empresa);
	}

	public void deletarEmpresa(Empresa empresa) {
		log.info("Deletando Empresa:", empresa);
		this.empresaRepositorio.delete(empresa);
	}

	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ: {}", cnpj);
		return Optional.ofNullable(empresaRepositorio.findByCnpj(cnpj));
	}
}
