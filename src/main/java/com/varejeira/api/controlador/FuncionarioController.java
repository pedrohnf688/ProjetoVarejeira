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

import com.varejeira.api.dto.EmpresaDto;
import com.varejeira.api.dto.FuncionarioDto;
import com.varejeira.api.helper.Response;
import com.varejeira.api.modelo.Empresa;
import com.varejeira.api.modelo.EnumTipo;
import com.varejeira.api.modelo.Funcionario;
import com.varejeira.api.servico.EmpresaServico;
import com.varejeira.api.servico.FuncionarioServico;

@RestController
@RequestMapping(value = "/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private FuncionarioServico funcionarioService;

	@Autowired
	private EmpresaServico empresaService;

	@GetMapping
	public List<Funcionario> listarFuncionarios() {
		List<Funcionario> funcionarios = this.funcionarioService.listarFuncionarios();
		return funcionarios;
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Funcionario>> buscarFucionarioPorId(@PathVariable("id") Integer id) {

		log.info("Buscar Funcionario por Id");

		Response<Funcionario> response = new Response<Funcionario>();

		Optional<Funcionario> f = this.funcionarioService.buscarPorId(id);

		if (!f.isPresent()) {
			log.info("Funcionario não encontrado");
			response.getErros().add("Funcionario não encontrado");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(f.get());

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<FuncionarioDto>> cadastrar(@Valid @RequestBody FuncionarioDto funcionarioDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando PF: {}", funcionarioDto.toString());

		Response<FuncionarioDto> response = new Response<FuncionarioDto>();

		validarDadosExistentes(funcionarioDto, result);
		Funcionario funcionario = this.converterDtoParaFuncionario(funcionarioDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados do cadastro do Funcionario: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(funcionarioDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.salvarFuncionario(funcionario);

		response.setData(this.converterFuncionarioDto(funcionario));
		return ResponseEntity.ok(response);

	}

	private void validarDadosExistentes(FuncionarioDto funcionarioDto, BindingResult result) {

		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(funcionarioDto.getCnpj());

		if (!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não cadastrada."));
		}

		this.funcionarioService.buscarPorCpf(funcionarioDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.buscarPorEmail(funcionarioDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));

	}

	private Funcionario converterDtoParaFuncionario(FuncionarioDto funcionarioDto, BindingResult result)
			throws NoSuchAlgorithmException {

		Funcionario funcionario = new Funcionario();

		funcionario.setNome(funcionarioDto.getNome());
		funcionario.setEmail(funcionarioDto.getEmail());
		funcionario.setCpf(funcionarioDto.getCpf());
		funcionario.setTipo(EnumTipo.VENDEDOR);
		funcionario.setDataNascimento(funcionarioDto.getDataNascimento());
		funcionario.setHorarioTrabalho(funcionario.getHorarioTrabalho());
		funcionario.setNicho(funcionarioDto.getNicho());
		funcionario.setSexo(funcionarioDto.getSexo());
		funcionario.setTelefone(funcionarioDto.getTelefone());

		return funcionario;
	}

	private FuncionarioDto converterFuncionarioDto(Funcionario funcionario) {

		FuncionarioDto funcionarioDto = new FuncionarioDto();

		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setNome(funcionario.getNome());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setCpf(funcionario.getCpf());
		funcionarioDto.setCnpj(funcionario.getEmpresa().getCnpj());
		funcionarioDto.setDataNascimento(funcionario.getDataNascimento());
		funcionarioDto.setHorarioTrabalho(funcionario.getHorarioTrabalho());
		funcionarioDto.setNicho(funcionario.getNicho());
		funcionarioDto.setSexo(funcionario.getSexo());
		funcionarioDto.setTelefone(funcionario.getTelefone());

		return funcionarioDto;
	}

}