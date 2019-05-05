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
import com.varejeira.api.helper.Response;
import com.varejeira.api.modelo.Empresa;
import com.varejeira.api.modelo.EnumTipo;
import com.varejeira.api.modelo.Funcionario;
import com.varejeira.api.modelo.Usuario;
import com.varejeira.api.servico.EmpresaServico;
import com.varejeira.api.servico.FuncionarioServico;

@RestController
@RequestMapping(value = "/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {

	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);

	@Autowired
	private EmpresaServico empresaService;

	@Autowired
	private FuncionarioServico funcionarioService;

	@GetMapping
	public List<Empresa> listarEmpresas() {
		List<Empresa> empresas = this.empresaService.listarEmpresas();
		return empresas;
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Empresa>> buscarEmpresaPorId(@PathVariable("id") Integer id) {

		log.info("Buscar Empresa por Id");

		Response<Empresa> response = new Response<Empresa>();

		Optional<Empresa> e = this.empresaService.buscarPorId(id);

		if (!e.isPresent()) {
			log.info("Empresa não encontrada");
			response.getErros().add("Empresa não encontrada");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(e.get());

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<EmpresaDto>> cadastrarEmpresa(@Valid @RequestBody EmpresaDto empresaDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando PJ: {}", empresaDto.toString());

		Response<EmpresaDto> response = new Response<EmpresaDto>();

		validarDadosExistentes(empresaDto, result);

		Empresa empresa = this.converterDtoParaEmpresa(empresaDto);

		Funcionario funcionario = this.converterDtoParaFuncionario(empresaDto, result);

		if (result.hasErrors()) {
			log.info("Erro validando dados da Empresa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.empresaService.salvarEmpresa(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.salvarFuncionario(funcionario);

		response.setData(this.converterCadastroPJDto(funcionario));

		return ResponseEntity.ok(response);

	}

	private void validarDadosExistentes(EmpresaDto empresaDto, BindingResult result) {
		this.empresaService.buscarPorCnpj(empresaDto.getCnpj())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.funcionarioService.buscarPorCpf(empresaDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

		this.funcionarioService.buscarPorEmail(empresaDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
		;
	}

	private Empresa converterDtoParaEmpresa(EmpresaDto empresaDto) {
		Empresa empresa = new Empresa();

		empresa.setCnpj(empresaDto.getCnpj());
		empresa.setNomeEmpresa(empresaDto.getNomeEmpresa());
		empresa.setProprietario(empresaDto.getProprietario());
		empresa.setDescricao(empresaDto.getDescricao());

		return empresa;

	}

	private Funcionario converterDtoParaFuncionario(EmpresaDto empresaDto, BindingResult result)
			throws NoSuchAlgorithmException {

		Funcionario funcionario = new Funcionario();

		funcionario.setNome(empresaDto.getNome());
		funcionario.setEmail(empresaDto.getEmail());
		funcionario.setCpf(empresaDto.getCpf());
		funcionario.setTipo(EnumTipo.GERENTE);
		funcionario.setSexo(empresaDto.getSexo());
		funcionario.setDataNascimento(empresaDto.getDataNascimento());
		funcionario.setHorarioTrabalho(empresaDto.getHorarioTrabalho());
		funcionario.setNicho(empresaDto.getNicho());
		funcionario.setTelefone(empresaDto.getTelefone());

		return funcionario;
	}

	private EmpresaDto converterCadastroPJDto(Funcionario funcionario) {
		EmpresaDto empresaDto = new EmpresaDto();

		empresaDto.setId(funcionario.getId());
		empresaDto.setNome(funcionario.getNome());
		empresaDto.setEmail(funcionario.getEmail());
		empresaDto.setCpf(funcionario.getCpf());
		empresaDto.setDataNascimento(funcionario.getHorarioTrabalho());

		empresaDto.setDescricao(funcionario.getEmpresa().getDescricao());
		empresaDto.setCnpj(funcionario.getEmpresa().getCnpj());
		empresaDto.setNomeEmpresa(funcionario.getEmpresa().getNomeEmpresa());
		empresaDto.setProprietario(funcionario.getEmpresa().getProprietario());

		empresaDto.setNicho(funcionario.getNicho());
		empresaDto.setHorarioTrabalho(funcionario.getHorarioTrabalho());
		empresaDto.setSexo(funcionario.getSexo());
		empresaDto.setTelefone(funcionario.getTelefone());

		return empresaDto;
	}
}
