package com.varejeira.api.servico;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejeira.api.modelo.Venda;
import com.varejeira.api.repositorio.VendaRepositorio;

@Service
public class VendaServico {

	private static final Logger log = LoggerFactory.getLogger(VendaServico.class);

	@Autowired
	private VendaRepositorio vendaRepositorio;

	public Optional<Venda> buscarPorId(Integer id) {
		log.info("Buscando Venda por Id:", id);
		return vendaRepositorio.findById(id);
	}

	public List<Venda> listarVendas() {
		log.info("Buscando Todos as Vendas:");
		return vendaRepositorio.findAll();
	}

	public Venda salvarVenda(Venda venda) {
		log.info("Salvando Usuario:", venda);
		return vendaRepositorio.save(venda);
	}

	public void deletarVenda(Venda venda) {
		log.info("Deletando Venda:", venda);
		this.vendaRepositorio.delete(venda);
	}
}
