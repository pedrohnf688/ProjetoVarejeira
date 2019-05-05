package com.varejeira.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejeira.api.modelo.Cliente;
import com.varejeira.api.repositorio.ClienteRepositorio;

@Service
public class ClienteServico {

	private static final Logger log = LoggerFactory.getLogger(ClienteServico.class);

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	public Optional<Cliente> buscarPorId(Integer id) {
		log.info("Buscando Cliente por Id:", id);
		return clienteRepositorio.findById(id);
	}

	public List<Cliente> listarUsuarios() {
		log.info("Buscando Todos os Clientes:");
		return clienteRepositorio.findAll();
	}

	public Cliente salvarCliente(Cliente cliente) {
		log.info("Salvando Cliente:", cliente);
		return clienteRepositorio.save(cliente);
	}

	public void deletarUsuario(Cliente cliente) {
		log.info("Deletando cliente:", cliente);
		this.clienteRepositorio.delete(cliente);
	}
}
