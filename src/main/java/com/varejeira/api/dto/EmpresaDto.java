package com.varejeira.api.dto;

import java.util.List;

import com.varejeira.api.modelo.EnumTipo;
import com.varejeira.api.modelo.Funcionario;

public class EmpresaDto {

	private Integer id;
	private String nomeEmpresa;
	private String proprietario;
	private String cnpj;
	private String descricao;
	private String horarioTrabalho;
	private String dataNascimento;
	private String nicho;
	private String nome;
	private String sexo;
	private String telefone;
	private String email;
	private String cpf;

	public EmpresaDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	@Override
	public String toString() {
		return "EmpresaDto [id=" + id + ", nomeEmpresa=" + nomeEmpresa + ", proprietario=" + proprietario + ", cnpj="
				+ cnpj + ", descricao=" + descricao + ", horarioTrabalho=" + horarioTrabalho + ", dataNascimento="
				+ dataNascimento + ", nicho=" + nicho + ", nome=" + nome + ", sexo=" + sexo + ", telefone=" + telefone
				+ ", email=" + email + ", cpf=" + cpf + "]";
	}

}
