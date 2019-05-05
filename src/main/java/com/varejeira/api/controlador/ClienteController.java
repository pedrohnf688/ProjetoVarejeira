package com.varejeira.api.controlador;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varejeira.api.helper.Response;
import com.varejeira.api.modelo.Cliente;
import com.varejeira.api.servico.ClienteServico;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteServico clienteService;

	@GetMapping
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = this.clienteService.listarUsuarios();
		return clientes;
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> buscarClientePorId(@PathVariable("id") Integer id) {

		log.info("Buscar Cliente por Id");

		Response<Cliente> response = new Response<Cliente>();

		Optional<Cliente> c = this.clienteService.buscarPorId(id);

		if (!c.isPresent()) {
			log.info("Cliente não encontrado");
			response.getErros().add("Cliente não encontrado");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(c.get());

		return ResponseEntity.ok(response);
	}

}
