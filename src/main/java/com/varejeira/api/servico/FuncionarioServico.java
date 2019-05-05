package com.varejeira.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejeira.api.modelo.Funcionario;
import com.varejeira.api.repositorio.FuncionarioRepositorio;

@Service
public class FuncionarioServico {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServico.class);

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	public Optional<Funcionario> buscarPorId(Integer id) {
		log.info("Buscando Cliente por Id:", id);
		return funcionarioRepositorio.findById(id);
	}

	public List<Funcionario> listarFuncionarios() {
		log.info("Buscando Todos os Clientes:");
		return funcionarioRepositorio.findAll();
	}

	public Funcionario salvarFuncionario(Funcionario funcionario) {
		log.info("Salvando Funcionario:",funcionario);
		return funcionarioRepositorio.save(funcionario);
	}
	
	public void deletarFuncionario(Funcionario funcionario){
		log.info("Deletando Funcionario:",funcionario);
		this.funcionarioRepositorio.delete(funcionario);
	}
	
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando Funcionario pelo Cpf: {}",cpf);
		return Optional.ofNullable(this.funcionarioRepositorio.findByCpf(cpf));
	}

	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando Funcionario pelo Email: {}",email);
		return Optional.ofNullable(this.funcionarioRepositorio.findByEmail(email));
	}
}
