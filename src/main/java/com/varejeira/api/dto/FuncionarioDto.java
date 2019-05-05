package com.varejeira.api.dto;

import com.varejeira.api.modelo.EnumTipo;

public class FuncionarioDto {

	private Integer id;
	private String nome;
	private String sexo;
	private String telefone;
	private String email;
	private String cpf;
	private String horarioTrabalho;
	private String dataNascimento;
	private String nicho;
	private String cnpj;

	public FuncionarioDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getHorarioTrabalho() {
		return horarioTrabalho;
	}

	public void setHorarioTrabalho(String horarioTrabalho) {
		this.horarioTrabalho = horarioTrabalho;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNicho() {
		return nicho;
	}

	public void setNicho(String nicho) {
		this.nicho = nicho;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", telefone=" + telefone + ", email="
				+ email + ", cpf=" + cpf + ", horarioTrabalho=" + horarioTrabalho + ", dataNascimento=" + dataNascimento
				+ ", nicho=" + nicho + ", cnpj=" + cnpj + "]";
	}

}
