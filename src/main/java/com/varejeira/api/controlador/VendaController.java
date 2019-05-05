package com.varejeira.api.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varejeira.api.dto.VendaDto;
import com.varejeira.api.helper.Response;
import com.varejeira.api.modelo.Empresa;
import com.varejeira.api.modelo.Funcionario;
import com.varejeira.api.modelo.Venda;
import com.varejeira.api.servico.EmpresaServico;
import com.varejeira.api.servico.FuncionarioServico;
import com.varejeira.api.servico.VendaServico;

@RestController
@RequestMapping(value = "/venda")
@CrossOrigin(origins = "*")
public class VendaController {

	private static final Logger log = LoggerFactory.getLogger(VendaController.class);

	@Autowired
	private VendaServico vendaService;

	@Autowired
	private EmpresaServico empresaService;

	@Autowired
	private FuncionarioServico funcionarioService;

	@GetMapping
	public List<Venda> listarVendass() {
		List<Venda> vendas = this.vendaService.listarVendas();
		return vendas;
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Venda>> buscarVendaPorId(@PathVariable("id") Integer id) {

		log.info("Buscar Venda por Id");

		Response<Venda> response = new Response<Venda>();

		Optional<Venda> v = this.vendaService.buscarPorId(id);

		if (!v.isPresent()) {
			log.info("Venda não encontrada");
			response.getErros().add("Venda não encontrada");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(v.get());

		return ResponseEntity.ok(response);
	}

//	@PostMapping
//	public ResponseEntity<Response<VendaDto>> cadastrar(@Valid @RequestBody VendaDto vendaDto,
//			BindingResult result) throws NoSuchAlgorithmException {
//
//		log.info("Cadastrando PF: {}", vendaDto.toString());
//
//		Response<VendaDto> response = new Response<VendaDto>();
//
//		validarDadosExistentes(vendaDto, result);
//		Funcionario funcionario = this.converterDtoParaFuncionario(vendaDto, result);
//
//		if (result.hasErrors()) {
//			log.error("Erro validando dados do cadastro PF: {}", result.getAllErrors());
//
//			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
//			return ResponseEntity.badRequest().body(response);
//		}
//
//		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(vendaDto.getCnpj());
//		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
//		this.funcionarioService.persistir(funcionario);
//
//		response.setData(this.convertrCadastroPFDto(funcionario));
//		return ResponseEntity.ok(response);
//
//	}
//
//	private void validarDadosExistentes(VendaDto vendaDto, BindingResult result) {
//
//		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());
//
//		if (!empresa.isPresent()) {
//			result.addError(new ObjectError("empresa", "Empresa não cadastrada."));
//		}
//
//		this.funcionarioService.buscarPorCpf(cadastroPFDto.getCpf())
//				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));
//
//		this.funcionarioService.buscarPorEmail(cadastroPFDto.getEmail())
//				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
//
//	}

}
